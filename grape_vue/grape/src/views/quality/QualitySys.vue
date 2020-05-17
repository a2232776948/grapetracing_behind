<template>
    <div>
        <div style="margin-top: 20px">
            <div>
                <span><i class="el-icon-s-grid"/>质检表</span>
                <el-input v-model="keywords" placeholder="通过编号搜索质检信息..." prefix-icon="el-icon-search"
                          style="width: 200px;margin-right: 10px; margin-left: 20px" @keydown.enter.native="search"
                          size="mini"></el-input>
                <el-button icon="el-icon-search" @click="commanderHandler('search')" size="mini">搜索</el-button>
                <el-button style="margin-left: 20px" size="mini" @click="commanderHandler('add')">添加信息</el-button>
            </div>
            <div style="width: 1070px; margin-top: 10px">
                <el-table
                        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                        border
                        row-key="id"
                        size="mini"
                        @selection-change="handleSelectionChange">
                    <el-table-column
                            type="selection"
                            width="50">
                    </el-table-column>
                    <el-table-column
                            prop="id"
                            label="质检编号"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="goods_id"
                            label="商品编号"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="category"
                            label="质检分类"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="description"
                            label="质检详情"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="date"
                            label="日期"
                            width="150"
                            :formatter="dataFormat">
                    </el-table-column>
                    <el-table-column
                            prop="opperson"
                            label="质检人"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            width="120">
                        <template slot-scope="scope">
                            <el-button @click="commanderHandler('delete',scope.row)" type="text" size="small">删除
                            </el-button>
                            <el-button @click="commanderHandler('edit', scope.row)" type="text" size="small">编辑
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

        <el-dialog :title="editDialogTitle" :visible.sync="editDialog" width="30%" @close="handleClosePlantDialog">
            <el-form :model="editform" label-position="left">
                <el-form-item label="商品编号" label-width="80px">
                    <el-input v-model="editform.goods_id" auto-complete="false"/>
                </el-form-item>
                <el-form-item label="质检分类" label-width="80px">
                    <el-input v-model="editform.category" auto-complete="false"/>
                </el-form-item>
                <el-form-item label="质检详情" label-width="80px">
                    <el-input type="textarea"
                              :rows="2"
                              v-model="editform.description"
                              auto-complete="false"/>
                </el-form-item>
                <el-form-item label="日期" label-width="80px">
                    <el-date-picker
                            v-model="editform.date"
                            type="date"
                            placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="质检人" label-width="80px">
                    <el-select v-model="editform.user_id" filterable placeholder="请选择">
                        <el-option
                                v-for="item in userOPs"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="plantDialog = false">取 消</el-button>
                <el-button type="primary" @click="submitHandler">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="搜索" :visible.sync="searchDialog" width="30%" @close="closeHandler">
            <el-form :model="searchForm" label-position="left">
                <el-form-item label="方式">
                    <el-form :model="searchForm">
                        <el-checkbox-group v-model="checkList" :min="1"  >
                            <el-checkbox v-for="item in waitCheck" :label="item.value" :key="item.label"></el-checkbox>
                        </el-checkbox-group>
                    </el-form>
                </el-form-item>
                <el-form-item label="时间段" label-width="80px" >
                    <el-date-picker
                            v-model="searchForm.period"
                            type="daterange"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            style="width: 250px"
                            :disabled="checkList.indexOf('按时间') < 0">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="商品编号" label-width="80px">
                    <el-input v-model="searchForm.goods_id" filterable placeholder="请选择" :disabled="checkList.indexOf('按商品') < 0">
                    </el-input>
                </el-form-item>
                <el-form-item label="分类" label-width="80px" >
                    <el-select v-model="searchForm.category" filterable placeholder="请选择" :disabled="checkList.indexOf('按分类') < 0">
                        <el-option
                                v-for="item in categoryOps"
                                :key="item"
                                :label="item"
                                :value="item">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="质检人" label-width="80px">
                    <el-select v-model="searchForm.user_id" autocomplete="off"  :disabled="checkList.indexOf('按质检人') < 0">
                        <el-option
                                v-for="item in userOPs"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>

                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="searchDialog = false">取 消</el-button>
                <el-button type="primary" @click="submitHandler">搜 索</el-button>
            </div>
        </el-dialog>

    </div>
</template>

<script>
    export default {
        name: "QualitySys",
        data() {
            return {
                tableData: [],
                currentPage: 1,
                pageSize: 10,
                total: 0,
                multipleSelection: [],
                command: "",
                userOPs: [],
                categoryOps:[],
                editDialog: false,
                editDialogTitle: '',
                searchDialog: false,
                checkList: ['按时间'],
                waitCheck: [
                    {
                        label: 1,
                        value: '按时间'
                    },
                    {
                        label: 2,
                        value: '按商品'
                    },
                    {
                        label: 3,
                        value: '按分类'
                    },
                    {
                        label: 4,
                        value: '按质检人'
                    }
                ],
                editform: {
                    id: '',
                    goods_id: '',
                    category: '',
                    description: '',
                    date: '',
                    user_id: '',
                    opperson: ''
                },
                searchForm:{
                    period: '',
                    goods_id: '',
                    user_id: '',
                    category: '',
                    mode:''
                },
                keywords: ''
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            init() {
                this.getRequest("/quality/getAllQuality").then(resp => {
                    if (resp) {
                        this.tableData = resp.result;
                        this.total = this.tableData.length;
                        console.log("resp.result");
                        console.log(resp.result);
                        this.$message(resp.msg);
                    }
                });
                this.getRequest('/user/getAllUsers').then(resp => {
                    if (resp) {
                        this.userOPs = resp.result;
                    }
                })
                this.getRequest('/quality/getQualityCate').then(resp => {
                    if (resp) {
                        this.categoryOps = resp.result;
                    }
                })
            },
            commanderHandler(command, row) {
                this.command = command;
                if (this.command === 'add') {
                    this.editDialog = true;
                    this.editDialogTitle = "添加质检信息";
                } else if (this.command === 'delete') {
                    var ids = [];
                    if (row) {
                        ids.push(row.id)
                    } else {
                        this.multipleSelection.forEach(row => {
                            ids.push(row.id);
                        })
                    }
                    this.postJson("/quality/remove", ids).then(resp => {
                        if (resp) {
                            this.$message(resp.msg);
                            this.init();
                        }
                    })
                } else if (this.command === 'edit') {
                    this.editDialogTitle = '修改质检信息';
                    this.editform = JSON.parse(JSON.stringify(row));
                    this.editDialog = true;
                }
                // else if (this.command === 'search') {
                //     this.getRequest("/quality/getQualityById?id=" + this.keywords).then(resp => {
                //         if (resp) {
                //             this.tableData = resp.result;
                //             this.total = this.tableData.length;
                //         }
                //     });
                // }
                else if (this.command === 'search') {
                    this.searchDialog = true;
                }
            },
            submitHandler() {
                if (this.command === 'add') {
                    this.postJson("/quality/addOneInfo", this.editform).then(resp => {
                        if (resp) {
                            this.$message(resp.msg);
                            this.init();
                        }
                    });
                    this.editDialog = false;
                } else if (this.command === 'edit') {
                    this.postJson("/quality/updateOne", this.editform).then(resp => {
                        if (resp) {
                            this.$message(resp.msg);
                            this.init();
                        }
                    });
                    this.editDialog = false;
                }else if (this.command === 'search') {
                    this.searchForm.start_date = this.searchForm.period[0];
                    this.searchForm.end_date = this.searchForm.period[1];
                    this.searchForm.mode = this.getMode();
                    console.log("searchForm");
                    console.log(this.searchForm);
                    this.postJson("/quality/findQualities", this.searchForm).then(resp => {
                        if (resp) {
                            console.log("/quality/findQualities resp.msp");
                            console.log(resp.result);
                            this.$message(resp.msg);
                            this.tableData = resp.result;
                            this.total = this.tableData.length;
                            this.searchDialog = false;
                        }
                    });
                }
                this.command = '';
            },
            getMode() {
                let mode = 0;
                if (this.checkList.indexOf('按时间') > -1)
                    mode += 1;
                if (this.checkList.indexOf('按商品') > -1)
                    mode += 2;
                if (this.checkList.indexOf('按分类') > -1)
                    mode += 4;
                if (this.checkList.indexOf('按质检人') > -1)
                    mode += 8;
                return mode;
            },
            currentChange(currentPage) {
                this.currentPage = currentPage;
            },
            sizeChange(pageSize) {
                this.pageSize = pageSize;
            },
            handleClosePlantDialog() {
                this.editform = {
                    id: '',
                    goods_id: '',
                    category: '',
                    description: '',
                    date: '',
                    user_id: '',
                    opperson: ''
                }
            },
            dataFormat(row, column, cellValue) {
                return this.$moment(cellValue).format("YYYY-MM-DD");
            },
        }
    }
</script>

<style scoped>

</style>