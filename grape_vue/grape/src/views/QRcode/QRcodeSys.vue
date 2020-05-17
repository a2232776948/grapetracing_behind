<template>
    <div>
        <!--        <el-form inline="true" style="margin-top: 20px" :model="QRform">-->
        <!--            <el-form-item prop="id" label="二维码编号：">-->
        <!--                <el-input v-model="QRform.id"></el-input>-->
        <!--            </el-form-item>-->
        <!--            <el-form-item prop="area" label="种植区块：">-->
        <!--                <el-select v-model="QRform.area" placeholder="请选择">-->
        <!--                    <el-option-->
        <!--                            v-for="item in areaOPs"-->
        <!--                            :key="item.value"-->
        <!--                            :label="item.label"-->
        <!--                            :value="item.value">-->
        <!--                    </el-option>-->
        <!--                </el-select>-->
        <!--            </el-form-item>-->
        <!--            <el-form-item prop="treeId" label="树编号：">-->
        <!--                <el-select v-model="QRform.area" placeholder="请选择">-->
        <!--                    <el-option-->
        <!--                            v-for="item in treeIds"-->
        <!--                            :key="item.value"-->
        <!--                            :label="item.label"-->
        <!--                            :value="item.value">-->
        <!--                    </el-option>-->
        <!--                </el-select>-->
        <!--            </el-form-item>-->
        <!--            <br>-->
        <!--            <el-form-item prop="date" label="采摘时间：">-->
        <!--                <el-date-picker-->
        <!--                        size="small"-->
        <!--                        v-model="QRform.date"-->
        <!--                        type="date"-->
        <!--                        placeholder="选择日期">-->
        <!--                </el-date-picker>-->
        <!--            </el-form-item>-->
        <!--            <el-button type="primary" style="margin-left: 15px" size="medium">查询</el-button>-->
        <!--            <el-button style="background-color: #eaeaea; margin-left: 30px" size="medium">清除</el-button>-->
        <!--        </el-form>-->
        <div style="margin-top: 20px">
            <span><i class="el-icon-s-grid"/>二维码表</span>
            <div style="width: 775px; margin-top: 20px">
                <el-table
                        :data="tableData"
                        border="true"
                        fit="true"
                        element-loading-text="正在加载..."
                        element-loading-spinner="el-icon-loading"
                        element-loading-background="rgba(0, 0, 0, 0.8)"
                        size="mini">
                    <el-table-column
                            type="selection"
                            width="55px"/>
                    <el-table-column
                            prop="qrcodeId"
                            label="二维码编号"
                            width="150"
                            :resizable="false">
                    </el-table-column>
                    <el-table-column
                            prop="areaId"
                            label="关联地块"
                            width="150"
                            :resizable="false">
                    </el-table-column>
                    <el-table-column
                            prop="treeId"
                            label="关联树号"
                            width="150"
                            :resizable="false">
                    </el-table-column>
                    <el-table-column
                            prop="date"
                            label="生成时间"
                            width="150"
                            :resizable="false">
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            width="120"
                            :resizable="false">
                        <template slot-scope="scope">
                            <el-button @click="dialogVisableChange(scope.row)" type="text" size="small">删除</el-button>
                            <el-button type="text" size="small">导出</el-button>
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
        <el-dialog
                title="提示"
                :visible.sync="dialogVisible"
                width="30%">
            <span>是否删除该二维码{{this.row}}</span>
            <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="deleteQrcode">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>


    export default {
        name: "QRcodeSys",
        data() {
            return {
                tableData: [],
                multipleSelection: [],
                total: 0,
                pagesize: 10,
                currentPage: 1,
                QRform: {
                    id: '',
                    area: '',
                    areaOPs: [],
                    treeId: '',
                    treeIds: [],
                    date: ''
                },
                areaOPs: [],
                dialogVisible: false,
                row: ''
            }
        },
        mounted() {
            this.initTable();
        },
        methods: {
            initTable() {
                // this.getRequest("/qrcode/getQrcodePage", {pageNum: 1, pageSize: 10}).then(resp => {
                this.getRequest("/qrcode/getQrcodePage?pageNum=" + this.currentPage + "&pageSize=" + this.pagesize).then(resp => {
                    if (resp) {
                        this.tableData = resp.result.result
                    }
                })
            },
            currentChange(currentPage) {
                this.currentPage = currentPage;
                this.initTable();
            },
            sizeChange(currentSize) {
                this.pagesize = currentSize;
                this.initTable();
            },
            dialogVisableChange(row) {
                this.dialogVisible = true;
                this.row = row;
            },
            deleteQrcode() {
                let arr = []
                arr.push(this.row)
                this.postRequest("/qrcode/deleteQrcode", arr).then(resp => {
                    if (resp) {
                        this.$message("删除成功");
                        this.dialogVisible = false;
                        this.initTable();
                    }
                })
            }
        }
    }
</script>

<style scoped>

</style>