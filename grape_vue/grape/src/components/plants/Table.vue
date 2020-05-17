<template>
    <div>
        <span><i class="el-icon-s-grid"/>植株清单</span>
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
                    prop="plantsid"
                    label="植株编号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="date"
                    label="种植时间"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="category"
                    label="植株种类"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="status"
                    label="植株状态"
                    width="150">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="120">
                <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">修改状态</el-button>
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
                tableData: [
                    {
                        plantsid: "0000001",
                        date: "2016-01-01",
                        category: "黑加仑",
                        status: "存活"
                    }
                ]
            }
        }
    }
</script>

<style scoped>

</style>