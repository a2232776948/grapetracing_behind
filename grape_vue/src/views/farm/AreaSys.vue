<template>
    <div style="margin-top: 20px">
        <div>
            <span><i class="el-icon-s-grid"/>农田列表</span>
            <el-button size="mini" @click="commandHandler('add')" style="margin-left: 20px">添加农田</el-button>
            <el-button size="mini" @click="commandHandler('delete')" style="margin-left: 20px"
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
                            label="农田编号"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="name"
                            label="农田名称"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="treeCount"
                            label="植株数量"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="address"
                            label="地址"
                            width="300">
                    </el-table-column>
                    <el-table-column
                            prop="status"
                            label="使用状态"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            width="120">
                        <template slot-scope="scope">
                            <el-button @click="commandHandler('delete', scope.row)" type="text" size="small">删除
                            </el-button>
                            <el-button @click="commandHandler('edit',scope.row)" type="text" size="small">编辑
                            </el-button>
                            <el-tooltip class="item" effect="dark" content="导出二维码" placement="bottom" hide-after="1000">
                                <el-button @click="commandHandler('export',scope.row)" type="text" size="small">导出
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

        <el-dialog :title="title" :visible.sync="dialogVisible" width="30%" @close="handleBeforeClose">
            <el-form :model="form" label-position="left">
                <el-form-item label="农田名称" label-width="80px">
                    <el-input v-model="form.name" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="农田地址" label-width="80px">
                    <el-input v-model="form.address" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="使用状态" label-width="80px">
                    <el-input v-model="form.status" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "AreaSys",
        data() {
            return {
                tableData: [],
                title: "",
                dialogVisible: false,
                command: "",
                form: {
                    name: "",
                    address: "",
                    id: "",
                    status
                },
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
                this.getRequest('/area/getAllArea').then(resp => {
                    if (resp) {
                        this.tableData = resp.result;
                        this.total = this.tableData.length;
                    }
                })
            },
            currentChange(currentPage) {
                this.currentPage = currentPage;
            },
            sizeChange(pageSize) {
                this.pageSize = pageSize;
            },
            commandHandler(cmd, row) {
                this.command = cmd;
                if (cmd === 'add') {
                    this.title = '添加农田';
                    this.dialogVisible = true;
                } else if (cmd === 'edit') {
                    this.title = '修改农田信息';
                    this.form.address = row.address;
                    this.form.name = row.name;
                    this.form.id = row.id;
                    this.form.status = row.status;
                    this.dialogVisible = true;
                } else if (cmd === 'delete') {
                    var ids = [];
                    if (row) {
                        ids.push(row.id);
                    } else {
                        this.multipleSelection.forEach(row => {
                            ids.push(row.id);
                        })
                    }
                    this.postJson("area/deleteAreas", ids).then(resp => {
                        if (resp) {
                            this.$message.success(resp.msg);
                            this.init();
                        }
                    })
                } else if (cmd === 'export') {
                    this.$message("未接入接口");
                }
            },
            submit() {
                if (this.command === 'add') {
                    this.postJson("/area/addArea", {
                        "name": this.form.name,
                        "address": this.form.address,
                        "status": this.form.status
                    }).then(resp => {
                        if (resp) {
                            this.$message('添加成功');
                            this.init();
                        } else {
                            this.$message('添加失败');
                            console.log(resp)
                        }
                    })
                } else if (this.command === 'edit') {
                    this.postJson('/area/changeAreaInfo', this.form).then(resp => {
                        if (resp) {
                            this.$message('修改成功');
                            this.init();
                        } else {
                            this.$message('修改失败');
                        }
                    });
                }
                this.dialogVisible = false;
                this.command = "";
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
                console.log("val" + val);
                console.log("multipleSelection" + this.multipleSelection);
            },
            handleBeforeClose() {
                this.form.name = '';
                this.form.address = '';
                this.form.id = '';
                this.form.status = '';
            }

        }
    }
</script>

<style scoped>

</style>