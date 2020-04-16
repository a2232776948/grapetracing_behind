<template>
    <div>
        <span><i class="el-icon-s-grid"/>农事操作表</span>
        <el-table
                :data="tableData"
                border
                max-height="420"
                style="width: 75%; margin-top: 20px">
            <el-table-column
                    type="selection"
                    width="55">
            </el-table-column>
            <el-table-column
                    fixed
                    prop="farmopTime"
                    label="农事时间"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="farmcateName"
                    label="农事分类"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="farmopDes"
                    label="农事操作"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="opperson"
                    label="操作人"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="areaId"
                    label="地块号"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="treeId"
                    label="树号"
                    width="120">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="100">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">删除</el-button>
                    <el-button type="text" size="small">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>
    </div>

</template>

<script>
    export default {
        name: "Table",
        data() {
            return {
                tableData: []
            }
        },
        mounted() {
            this.initTable();
        },
        methods: {
            initTable() {
                this.getRequest("/farmop/getAllFarmop").then(resp => {
                    if(resp){
                        this.tableData = resp.result;
                        console.log(resp)
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>