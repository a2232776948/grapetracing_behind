<template>
    <div>
        <span><i class="el-icon-s-grid"/>质检表</span>
        <el-table
                :data="tableData"
                border
                max-height="450"
                style="width: 66%; margin-top: 20px">
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column
                    prop="area"
                    label="关联区号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="treeId"
                    label="关联树号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="today"
                    label="今日农事"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="history"
                    label="历史农事"
                    width="150">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="120">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">添加</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
    export default {
        name: "Table",
        data(){
            return{
                tableData:[]
            }
        },
        mounted(){
            this.initTable();
        },
        methods:{
            initTable(){
                this.getRequest()
            }
        }
    }
</script>

<style scoped>

</style>