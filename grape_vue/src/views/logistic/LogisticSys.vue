<template>
    <div style="margin-top: 20px">
        <div>
            <span><i class="el-icon-s-grid"/>物流列表</span>
            <el-button size="mini"  style="margin-left: 20px">录入单号</el-button>
            <el-button size="mini"  style="margin-left: 20px">搜索单号</el-button>
            <el-button size="mini"  style="margin-left: 20px"
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
                            label="物流编号"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="goods_id"
                            label="产品编号"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="express_number"
                            label="快递单号"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="status"
                            label="状态"
                            width="300">
                    </el-table-column>
                    <el-table-column
                            prop="date"
                            label="录入日期"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            width="120">
                        <template slot-scope="scope">
                            <el-button type="text" size="small">删除
                            </el-button>
                            <el-button  type="text" size="small">编辑
                            </el-button>
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
        name: "LogisticSys",
        data(){
            return{
                tableData: [],
                currentPage: 1,
                pageSize: 10,
                total: 0,
                multipleSelection: []
            }
        },
        methods:{
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