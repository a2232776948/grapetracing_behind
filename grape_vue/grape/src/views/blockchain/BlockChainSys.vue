<template>
    <div style="margin-top: 20px">
        <el-tag>区块数量:</el-tag>
        <span>{{blockNumber}}</span>
        <el-tag style="margin-left: 20px">挖矿状态</el-tag>
        <span>{{minerStatus}}</span>
        <el-button size="mini" style="margin-left: 20px" @click="controlMiner">{{minerController}}</el-button>

        <div style="margin-top: 20px">
            <span><i class="el-icon-s-grid"/>溯源信息</span>
            <el-input v-model="tracking_number" placeholder="请输入溯源码进行搜索.." size="mini"
                      style="width: 200px; margin-left: 20px" @keydown.enter.native="search"></el-input>
            <el-button size="mini" @click="search">搜索</el-button>
            <div style="width: 850px; margin-top: 10px">
                <!--                        :data="tableData"-->
                <el-table
                        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                        border
                        size="mini"
                        row-key="id"
                        element-loading-text="正在加载..."
                        element-loading-spinner="el-icon-loading"
                        element-loading-background="rgba(0, 0, 0, 0.8)"
                        @selection-change="handleSelectionChange">
                    <el-table-column
                            prop="txHash"
                            label="交易号"
                            width="200">
                    </el-table-column>
                    <el-table-column
                            prop="data"
                            label="信息"
                            width="300">
                    </el-table-column>
                    <el-table-column
                            prop="from"
                            label="交易发起人"
                            width="200">
                    </el-table-column>
                    <el-table-column
                            prop="date"
                            label="交易日期"
                            :formatter="dataFormat"
                            width="150">
                    </el-table-column>
                </el-table>
                <div style="display: flex;justify-content: center; margin-top: 5px;">
                    <el-pagination
                            background
                            @current-change="currentChange"
                            @size-change="sizeChange"
                            layout="sizes, prev, pager, next, jumper, ->, total, slot"
                            :total="total">
                    </el-pagination>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "BlockChainSys",
        data() {
            return {
                coinbase: '',
                blockNumber: 458,
                address: '',
                tracking_number: '',
                minerStatus: '停止',
                minerController: '开启挖矿',
                tableData: [],
                currentPage: 1,
                pageSize: 10,
                total: 0,
                multipleSelection: []
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            init() {
                this.getRequest("/blockchain/blockNumber").then(resp => {
                    if (resp) {
                        this.blockNumber = resp;
                    }
                }),
                this.getRequest("/blockchain/mining").then(resp => {
                    if (resp) {
                        this.minerStatus = "开启";
                        this.minerController = "停止挖矿";
                    } else {
                        this.minerStatus = "停止";
                        this.minerController = "开启挖矿";
                    }
                })
            },
            currentChange(currentPage) {
                this.currentPage = currentPage;
            },
            sizeChange(pageSize) {
                this.pageSize = pageSize;
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
                console.log("val" + val);
                console.log("multipleSelection" + this.multipleSelection);
            },
            search() {
                this.getRequest("/blockchain/find?id=" + this.tracking_number).then(resp => {
                    if (resp) {
                        this.tableData = resp.result;
                        this.total = this.tableData.length;
                        console.log(resp);
                        this.$message.success("查找成功")
                    }
                })
            },
            dataFormat(row, column, cellValue) {
                return this.$moment(cellValue).format("YYYY-MM-DD");
            },
            controlMiner() {
                if ( this.minerController === "开启挖矿") {
                    this.postRequest("/blockchain/startMiner").then(resp => {
                        if (resp) {
                            this.minerController = "停止挖矿";
                            this.minerStatus = "开启";
                        }
                    })
                } else if (this.minerController === '停止挖矿') {
                    this.postRequest("/blockchain/stopMiner").then(resp => {
                        if (resp) {
                            this.minerController = "开启挖矿";
                            this.minerStatus = "停止";
                        }
                    })
                }
            }
        }
    }
</script>

<style scoped>

</style>