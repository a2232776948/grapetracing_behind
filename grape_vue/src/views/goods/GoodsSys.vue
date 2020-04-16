<template>
    <div style="margin-top: 20px">
        <div>
            <span><i class="el-icon-s-grid"/>产品列表</span>
            <el-button size="mini" style="margin-left: 20px">添加产品</el-button>
            <el-button size="mini" style="margin-left: 20px">搜索产品</el-button>
            <el-button size="mini" style="margin-left: 20px"
                       v-if="this.multipleSelection.length">删除
            </el-button>
            <div style="width: 1070px; margin-top: 10px">
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
                    <el-table-column type="selection" width="50" :reserve-selection="true"/>
                    <el-table-column
                            prop="id"
                            label="产品编号"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="category"
                            label="产品类型"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="desc"
                            label="产品信息"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="tracking_number"
                            label="溯源码"
                            width="300">
                    </el-table-column>
                    <el-table-column
                            prop="date"
                            label="生产日期"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            width="120">
                        <template slot-scope="scope">
                            <el-button type="text" size="small">删除
                            </el-button>
                            <el-button type="text" size="small">编辑
                            </el-button>
                            <el-tooltip class="item" effect="dark" content="导出二维码" placement="bottom" hide-after="1000">
                                <el-button type="text" size="small">导出
                                </el-button>
                            </el-tooltip>
                        </template>
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
        name: "GoodsSys",
        data() {
            return {
                tableData: [{
                    id: 1,
                    category: '果汁',
                    desc: '鲜榨果汁',
                    tracking_number: '100000000000001',
                    date: '2019-09-05'
                }, {
                    id: 6,
                    category: '果脯',
                    desc: '葡萄干',
                    tracking_number: '100000000000006',
                    date: '2019-09-23'
                }],
                currentPage: 1,
                pageSize: 10,
                total: 0,
                multipleSelection: []
            }
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
            },
        }
    }
</script>

<style scoped>

</style>