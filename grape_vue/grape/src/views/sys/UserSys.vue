<template>
    <div>
        <div style="margin: 20px 0px;">
            <span style="margin-right: 20px"><i class="el-icon-s-grid"/>用户列表</span>
            <el-input v-model="keywords" placeholder="通过用户名搜索用户..." prefix-icon="el-icon-search"
                      style="width: 400px;margin-right: 10px" @keydown.enter.native="search"></el-input>
            <el-button icon="el-icon-search" type="primary" @click="search">搜索</el-button>
            <el-button type="primary" @click="commandHandler('addUser')">添加用户</el-button>
        </div>
        <el-table
                :data="tableData"
                style="width: 100%">
            <el-table-column type="expand">
                <template slot-scope="scope">
                    <el-form label-position="left" inline class="table-expand">
                        <el-form-item label="姓名" label-width="80px">
                            <span>{{ scope.row.name }}</span>
                        </el-form-item>
                        <el-form-item label="性别" label-width="80px">
                            <span>{{ scope.row.gender}}</span>
                        </el-form-item>
                        <el-form-item label="ID" label-width="80px">
                            <span>{{ scope.row.id }}</span>
                        </el-form-item>
                        <el-form-item label="手机" label-width="80px">
                            <span>{{ scope.row.phone }}</span>
                        </el-form-item>
                        <el-form-item label="年龄" label-width="80px">
                            <span>{{ scope.row.age}}</span>
                        </el-form-item>
                        <el-form-item label="创建日期" label-width="80px">
                            <span>{{dataFormat(scope.row.date)}}</span>
                        </el-form-item>
                        <el-form-item label="信息" label-width="80px">
                            <span>{{ scope.row.detail}}</span>
                        </el-form-item>
                        <el-form-item label="hash" label-width="80px">
                            <span>{{ scope.row.hash}}</span>
                        </el-form-item>
                    </el-form>
                </template>
            </el-table-column>
            <el-table-column
                    prop="id"
                    label="编号"
                    width="75">
            </el-table-column>
            <el-table-column
                    prop="name"
                    label="姓名"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="用户名"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="hash"
                    label="hash"
                    width="320">
            </el-table-column>
            <el-table-column
                    label="用户角色"
                    width="300">
                <template slot-scope="scope">
                    <template v-for="(role, indexj) in scope.row.roles">
                        <el-tag type="success" style="margin-right: 4px"
                                :key="indexj">{{role.nameZh}}
                        </el-tag>
                    </template>
                    <el-popover
                            placement="right"
                            title="角色列表"
                            @show="showPop(scope.row)"
                            @hide="hidePop(scope.row)"
                            width="200"
                            trigger="click">
                        <el-select v-model="selectedRoles" multiple placeholder="请选择">
                            <el-option
                                    v-for="(r,indexk) in allroles"
                                    :key="indexk"
                                    :label="r.nameZh"
                                    :value="r.id">
                            </el-option>
                        </el-select>
                        <el-button slot="reference" icon="el-icon-more" type="text"></el-button>
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column
                    label="用户状态"
                    width="140">
                <template slot-scope="scope">
                    <el-switch
                            v-model="scope.row.enabled"
                            active-text="启用"
                            @change="enabledChange(scope.row)"
                            active-color="#13ce66"
                            inactive-color="#ff4949"
                            inactive-text="禁用">
                    </el-switch>
                </template>
            </el-table-column>
            <el-table-column
                    label="操作">
                <template slot-scope="scope">
                    <el-button
                            size="mini"
                            @click="commandHandler('edit', scope.row)">编辑
                    </el-button>
                    <el-button
                            size="mini"
                            type="danger"
                            @click="commandHandler('delete', scope.row)">删除
                    </el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-dialog :title="dialogtitle" :visible.sync="dialogVisible" width="30%" @close="closeHandler">
            <el-form :model="dialogForm" label-position="left">
                <el-form-item label="姓名" label-width="80px">
                    <el-input v-model="dialogForm.name" autocomplete="off" :placeholder="dialogForm.name"></el-input>
                </el-form-item>
                <el-form-item label="用户名" label-width="80px">
                    <el-input v-model="dialogForm.username" autocomplete="off"
                              :placeholder="dialogForm.username"></el-input>
                </el-form-item>
                <el-form-item label="性别" label-width="80px">
                    <div style="width: 300px">
                        <el-radio v-model=dialogForm.gender label='男' style="width: 30%">男</el-radio>
                        <el-radio v-model=dialogForm.gender label='女'>女</el-radio>
                    </div>
                </el-form-item>
                <el-form-item label="手机" label-width="80px">
                    <el-input v-model="dialogForm.phone" autocomplete="off" :placeholder="dialogForm.phone"></el-input>
                </el-form-item>
                <el-form-item label="年龄" label-width="80px">
                    <el-input v-model="dialogForm.age" autocomplete="off" :placeholder="dialogForm.age"></el-input>
                </el-form-item>
                <el-form-item label="信息" label-width="80px">
                    <el-input
                            type="textarea"
                            :rows="2"
                            :placeholder="dialogForm.detail"
                            v-model="dialogForm.detail">
                    </el-input>
                </el-form-item>
                <el-form-item label="hash" label-width="80px">
                    <el-input v-model="dialogForm.hash" autocomplete="off" :placeholder="dialogForm.hash"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitHandler">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "UserSys",
        data() {
            return {
                tableData: [],
                selectedRoles: [],
                keywords: '',
                allroles: [],
                dialogtitle: '',
                dialogVisible: false,
                dialogForm: {
                    id: '',
                    name: '',
                    username: '',
                    gender: '',
                    phone: '',
                    age: '',
                    detail: '',
                    hash: ''
                },
                command: ''
            }
        },
        mounted() {
            this.initUsers();
        },
        methods: {
            initUsers() {
                this.getRequest("/user/getAllUsers").then(resp => {
                    if (resp) {
                        this.tableData = resp.result;
                    }
                })
            },
            commandHandler(command, row) {
                this.command = command;
                if (command === 'addUser') {
                    this.dialogtitle = "添加用户";
                    this.dialogVisible = true;
                } else if (command === 'edit') {
                    this.dialogtitle = "修改用户";
                    this.dialogVisible = true;
                    Object.assign(this.dialogForm, row);

                } else if (command === 'delete') {
                    this.postRequest("/user/delete?userId=" + row.id).then(resp => {
                        if (resp) {
                            this.$message.success(resp.msg);
                            this.initUsers();
                        }
                    })
                }
            },
            submitHandler() {
                if (this.command === 'addUser') {
                    this.postJson("/user/addUser", this.dialogForm).then(resp => {
                        if (resp) {
                            this.$message.success(resp.msg);
                            this.initUsers();
                            this.dialogVisible = false;
                        }
                    });
                } else if (this.command === 'edit') {
                    this.postJson("/user/changeUser", this.dialogForm).then(resp => {
                        if (resp) {
                            this.$message.success(resp.msg);
                            this.initUsers();
                            this.dialogVisible = false;
                        }
                    });
                }
            },
            initAllRoles() {
                this.getRequest("/user/getAllRoles").then(resp => {
                    if (resp) {
                        this.allroles = resp.result;
                    }
                })
            },
            enabledChange(row) {
                // console.log(JSON.stringify(row))
                this.postJson("/user/changeUser/", row).then(resp => {
                    if (resp) {
                        this.initHrs();
                    }
                })
            },
            showPop(row) {
                this.initAllRoles();
                let roles = row.roles;
                this.selectedRoles = [];
                roles.forEach(r => {
                    this.selectedRoles.push(r.id);
                })
            },
            hidePop(row) {
                let roles = [];
                Object.assign(roles, row.roles);
                let flag = false;
                if (roles.length !== this.selectedRoles.length) {
                    flag = true;
                } else {
                    for (let i = 0; i < roles.length; i++) {
                        let role = roles[i];
                        for (let j = 0; j < this.selectedRoles.length; j++) {
                            let sr = this.selectedRoles[j];
                            if (role.id === sr) {
                                roles.splice(i, 1);
                                i--;
                                break;
                            }
                        }
                    }
                    if (roles.length !== 0) {
                        flag = true;
                    }
                }
                if (flag) {
                    let url = '/user/changeRole?userId=' + row.id;
                    this.selectedRoles.forEach(sr => {
                        url += '&rids=' + sr;
                    });
                    this.putRequest(url).then(resp => {
                        if (resp) {
                            this.initUsers();
                        }
                    });
                }
            },
            search() {
                this.getRequest("/user/getUserByKeywords?keywords=" + this.keywords).then(resp => {
                    if (resp) {
                        this.tableData = resp.result;
                    }
                })
            },
            dataFormat(value) {
                return this.$moment(value).format("YYYY-MM-DD");
            },
            closeHandler() {
                this.dialogForm = {
                    id: '',
                    name: '',
                    username: '',
                    gender: '',
                    phone: '',
                    age: '',
                    detail: '',
                    hash: ''
                };
            }
        }
    }
</script>

<style scoped>
    .table-expand {
        font-size: 0;
    }

    .table-expand label {
        width: 90px;
        color: #99a9bf;
    }

    .table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }
</style>