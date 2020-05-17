<template>
    <div>
        <span><i class="el-icon-s-grid"/>二维码表</span>
        <el-table
                :data="tableData"
                border
                max-height="450"
                style="width: 66%; margin-top: 20px">
            <el-table-column
                    type="selection"
                    width="55px"/>
            <el-table-column
                    fixed
                    prop="qrcodeId"
                    label="二维码编号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="areaId"
                    label="关联地块"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="treeId"
                    label="关联树号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="date"
                    label="生成时间"
                    width="150">
            </el-table-column>
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="120">
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
                // this.getRequest("/qrcode/getQrcodePage", {pageNum: 1, pageSize: 10}).then(resp => {
                this.getRequest("/qrcode/getQrcodePage?pageNum="+1+"&pageSize="+10).then(resp => {
                    if (resp) {
                        this.tableData = resp.result.result
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>