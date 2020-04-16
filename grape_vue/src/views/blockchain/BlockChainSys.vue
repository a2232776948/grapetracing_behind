<template>
    <div style="margin-top: 20px">
        <el-tag>区块数量:</el-tag>
        <span>{{blockNumber}}</span>
        <el-tag style="margin-left: 20px">挖矿状态</el-tag>
        <span>{{minerStatus}}</span>
        <el-button size="mini" style="margin-left: 20px">开启挖矿</el-button>

        <div style="margin-top: 20px">
            <span><i class="el-icon-s-grid"/>溯源信息</span>
            <el-input v-model="tracking_number" placeholder="请输入溯源码进行搜索.." size="mini"
                      style="width: 200px; margin-left: 20px"></el-input>
            <el-button size="mini">搜索</el-button>
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
                            prop="info"
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
                minerStatus:'停止',
                tableData: [
                    {
                        txHash: '0xe904289bec625d9fd67e9e1e96109edbde326393ae501cb94754d7e771a8e016',
                        info: '施磷肥 2kg',
                        from: "0x04a163c0bc974a731347010d3cd1b900707053ea",
                        date: '2019-09-03'
                    },
                    {
                        txHash: '0xe9042867e9e1e969bec625d9fd109edbde326393ae501cb94754d7e771a8e016',
                        info: '施氮肥 2kg',
                        from: "0x04a163c0bc974a731347010d3cd1b900707053ea",
                        date: '2019-09-01'
                    },
                    {
                        txHash: '0xe9042bde326393ae50e9e1e96986709edbec625d9fd11cb94754d7e771a8e016',
                        info: '除草',
                        from: "0x04a163c0bc974a731347010d3cd1b900707053ea",
                        date: '2019-08-15'
                    }
                ],
                currentPage: 1,
                pageSize: 10,
                total: 0,
                multipleSelection: []
            }
        },
        mounted() {

        },
        methods: {
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
            }

        }
    }
</script>

<style scoped>

</style>