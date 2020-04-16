<template>
    <div>
        <div style="margin-top: 20px">
            <div>
                <span><i class="el-icon-s-grid"/>农事操作表</span>
                <el-button size="mini" style="margin-left: 20px" @click="commandHandler('add')">添加记录</el-button>
                <el-button size="mini" style="margin-left: 20px" @click="commandHandler('search')">搜索记录</el-button>
                <el-button size="mini" style="margin-left: 20px" @click="commandHandler('delete')"
                           v-if="this.multipleSelection.length !== 0">删除
                </el-button>
            </div>
            <!--                        :data="tableData"-->
            <div style="width: 981px; margin-top: 10px">
                <el-table
                        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                        border
                        size="mini"
                        row-key="id"
                        element-loading-text="正在加载..."
                        element-loading-spinner="el-icon-loading"
                        element-loading-background="rgba(0, 0, 0, 0.8)"
                        @selection-change="handleSelectionChange">
                    <el-table-column
                            type="selection"
                            width="40"
                            :reserve-selection="true">
                    </el-table-column>
                    <el-table-column
                            sortable
                            prop="id"
                            label="编号"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            sortable
                            prop="tree_id"
                            label="树号"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="area_id"
                            label="地块号"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="category"
                            label="农事种类"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="desc"
                            label="具体操作"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            prop="date"
                            label="操作时间"
                            width="120"
                            :formatter="dataFormat">
                    </el-table-column>
                    <el-table-column
                            prop="opperson"
                            label="操作人"
                            width="120">
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            width="100">
                        <template slot-scope="scope">
                            <el-button @click="commandHandler('delete', scope.row)" type="text" size="small">删除
                            </el-button>
                            <el-button @click="commandHandler('edit', scope.row)" size="small" type="text">编辑
                            </el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div style="display: flex;justify-content: center; margin-top: 5px">
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

        <el-dialog :title="editDialogTitle" :visible.sync="editDialog" width="30%" @close="closeHandler">
            <el-form :model="editform" label-position="left">
                <el-form-item label="操作时间" label-width="80px">
                    <el-date-picker
                            v-model="editform.date"
                            type="date"
                            placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item v-if="this.command === 'add'" style="">
                    <el-radio v-model="radio" label="0">按地块</el-radio>
                    <el-radio v-model="radio" label="1">按植株</el-radio>
                </el-form-item>
                <el-form-item label="农事分类" label-width="80px">
                    <el-select v-model="editform.category" placeholder="请选择" filterable allow-create>
                        <el-option
                                v-for="(item, index) in categoryOps"
                                :key="index"
                                :label="item.category"
                                :value="item.category">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="具体操作" label-width="80px">
                    <el-input v-model="editform.desc" autocomplete="off"
                              :placeholder="editform.desc"></el-input>
                </el-form-item>
                <el-form-item label="操作人" label-width="80px">
                    <el-select v-model="editform.user_id" filterable placeholder="请选择">
                        <el-option
                                v-for="item in userOPs"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="地块号" label-width="80px" v-if="radio === '0'">
                    <el-select v-model="editform.area_id" filterable placeholder="请选择">
                        <el-option
                                v-for="item in areas"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="树号" label-width="80px" v-if="radio > 0">
                    <el-input v-model="editform.tree_id" autocomplete="off" :placeholder="editform.treeId"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="editDialog = false">取 消</el-button>
                <el-button type="primary" @click="submitform">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog title="搜索" :visible.sync="searchDialog" width="30%" @close="closeHandler">
            <el-form :model="searchForm" label-position="left">
                <el-form-item label="方式">
                    <el-form :model="searchForm">
                        <el-checkbox-group v-model="checkList" :min="1"  @change="selectedChange">
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
                <el-form-item label="地块号" label-width="80px">
                    <el-select v-model="searchForm.area_id" filterable placeholder="请选择" :disabled="checkList.indexOf('按地块') < 0">
                        <el-option
                                v-for="item in areas"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="树号" label-width="80px">
                    <el-input v-model="searchForm.tree_id" autocomplete="off"  :disabled="checkList.indexOf('按植株') < 0"></el-input>
                </el-form-item>
                <el-form-item label="操作人" label-width="80px" >
                    <el-select v-model="searchForm.user_id" filterable placeholder="请选择" :disabled="checkList.indexOf('按操作人') < 0">
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
                <el-button type="primary" @click="submitform">搜 索</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "PlantSys",
        data() {
            return {
                categoryOps: [],
                category: '',
                treeIds: [],
                treeId: '',
                areas: [],
                currentPage: 1,
                pageSize: 10,
                total: 0,
                person: '',
                tableData: [],
                multipleSelection: [],
                command: '',
                userOPs: [],
                editDialog: false,
                editDialogTitle: '',
                radio: '0',
                editform: {
                    date: '',
                    category: '',
                    desc: '',
                    opperson: '',
                    area_id: '',
                    tree_id: '',
                    user_id: ''
                },

                searchDialog: false,
                searchForm: {
                    period: '',
                    user_id: '',
                    area_id: '',
                    tree_id: '',
                    mode:''
                },
                checkList: ['按时间'],
                waitCheck: [
                    {
                        label: 1,
                        value: '按时间'
                    },
                    {
                        label: 2,
                        value: '按地块'
                    },
                    {
                        label: 3,
                        value: '按植株'
                    },
                    {
                        label: 4,
                        value: '按操作人'
                    }
                ]
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            init() {
                //initTable
                this.getRequest("/farmop/getAllOps").then(resp => {
                    if (resp) {
                        this.tableData = resp.result;
                        this.total = this.tableData.length;

                    }
                });

                this.getRequest("/farmop/getAllFarmCate").then(resp => {
                    if (resp) {
                        this.categoryOps = resp.result;
                    }
                });
                this.getRequest('/area/getAllArea').then(resp => {
                    if (resp) {
                        this.areas = resp.result;
                    }
                });

                this.getRequest('/user/getAllUsers').then(resp => {
                    if (resp) {
                        this.userOPs = resp.result;
                    }
                })
            },
            currentChange(currentPage) {
                this.currentPage = currentPage;

            },
            sizeChange(currentSize) {
                this.pageSize = currentSize;

            },
            commandHandler(cmd, row) {
                this.command = cmd;
                this.radio = "0";
                this.category = this.categoryOps[0];
                if (cmd === 'add') {
                    this.editDialog = true;
                    this.editDialogTitle = "添加农事";
                } else if (cmd === 'edit') {
                    this.editDialogTitle = "修改农事";
                    this.editDialog = true;
                    this.editform = JSON.parse(JSON.stringify(row));

                } else if (cmd === 'delete') {
                    var ids = [];
                    if (row) {
                        ids.push(row.id)
                    } else {
                        this.multipleSelection.forEach(row => {
                            ids.push(row.id);
                        });
                    }
                    this.postJson("/farmop/delete", ids).then(resp => {
                        if (resp) {
                            this.$message(resp.msg);
                            this.init();
                        }
                    })
                } else if (cmd === 'search') {
                    this.searchDialog = true;
                }
            },
            submitform() {
                if (this.command === 'add') {
                    if (this.radio === '0') {
                        this.postJson("/farmop/insertOneFarmopForArea", this.editform).then(resp => {
                            if (resp) {
                                this.$message(resp.msg);
                                this.init();
                            }
                        })
                    } else if (this.radio === '1') {
                        this.postJson("/farmop/insertOneFarmopForTree", this.editform).then(resp => {
                            if (resp) {
                                this.$message(resp.msg);
                                this.init();
                            }
                        })
                    }
                    this.editDialog = false;
                } else if (this.command === 'edit') {
                    this.postRequest("/farmop/updateOneFarmop", this.editform).then(resp => {
                        if (resp) {
                            this.$message(resp.msg);
                            this.init();
                        }
                    })
                } else if (this.command === 'search') {
                    this.searchForm.start_date = this.searchForm.period[0];
                    this.searchForm.end_date = this.searchForm.period[1];
                    this.searchForm.mode = this.getMode();
                    this.postJson("/farmop/findFarmOPs", this.searchForm).then(resp => {
                        if (resp) {
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
                if (this.checkList.indexOf('按地块') > -1)
                    mode += 2;
                if (this.checkList.indexOf('按植株') > -1)
                    mode += 4;
                if (this.checkList.indexOf('按操作人') > -1)
                    mode += 8;
                return mode;
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            closeHandler() {
                this.editform = {
                    farmopTime: '',
                    farmcateName: '',
                    farmopDes: '',
                    opperson: '',
                    areaId: '',
                    treeId: '',
                    user_id: ''
                };
                this.searchForm = {
                    period: '',
                    user_id: '',
                    area_id: '',
                    tree_id: '',
                    mode:''
                };
            },
            dataFormat(row, column, cellValue) {
                return this.$moment(cellValue).format("YYYY-MM-DD");
            },
            selectedChange(val){
                console.log(val);
                if (val.indexOf('按植株')  > -1 && val.indexOf('按地块') > -1){
                    var index1 = val.indexOf('按植株');
                    var index2 = val.indexOf('按地块');
                    if (index1 > index2){
                        this.checkList.splice(index2, 1);
                    }else {
                        this.checkList.splice(index1, 1);
                    }
                }
            }
        }
    }
</script>

<style scoped>
    .el-dialog__body {
        padding: 10px 20px;
    }
</style>