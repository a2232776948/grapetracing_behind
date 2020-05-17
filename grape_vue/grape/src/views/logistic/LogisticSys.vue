<template>
    <div style="margin-top: 20px">
        <div>
            <span><i class="el-icon-s-grid"/>物流列表</span>
            <el-button size="mini" @click="commandHandler('add')" style="margin-left: 20px">录入单号</el-button>
            <el-button size="mini"  style="margin-left: 20px" @click="commandHandler('search')">搜索单号</el-button>
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
                            prop="goodsId"
                            label="产品编号"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="expressNumber"
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
                            width="150"
                            :formatter="dataFormat">
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            width="120">
                        <template slot-scope="scope">
                            <el-button type="text" size="small" @click="commandHandler('delete', scope.row)" >删除
                            </el-button>
<!--                            <el-button  type="text" size="small" @click="commandHandler('edit', scope.row)">编辑-->
<!--                            </el-button>-->
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

        <el-dialog :title="editDialogTitle" :visible.sync="editDialog" width="30%" @close="closeHandler">
            <el-form :model="editform" label-position="left">
                <el-form-item label="填写时间" label-width="80px">
                    <el-date-picker
                            v-model="editform.date"
                            type="date"
                            placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>

                <el-form-item label="葡萄商品ID" label-width="90px">
                    <el-input v-model="editform.goodsIds[0]" autocomplete="off"
                              :placeholder="editform.goodsIds[0]"></el-input>
                </el-form-item>
                <el-form-item label="快递单号" label-width="80px">
                    <el-input v-model="editform.expressNumber" autocomplete="off"
                              :placeholder="editform.expressNumber"></el-input>
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
                    <el-input v-model="searchForm.goodsId" filterable placeholder="请选择" :disabled="checkList.indexOf('按商品') < 0">
                    </el-input>
                </el-form-item>
                <el-form-item label="快递编号" label-width="80px" >
                    <el-input v-model="searchForm.expressNumber" filterable placeholder="请选择" :disabled="checkList.indexOf('按快递') < 0">
                    </el-input>
                </el-form-item>
                <el-form-item label="状态" label-width="80px">
                    <el-select v-model="searchForm.status" autocomplete="off"  :disabled="checkList.indexOf('按状态') < 0">
                        <el-option
                                v-for="item in expressStatus"
                                :key="item"
                                :label="item"
                                :value="item">
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
        name: "LogisticSys",
        data() {
            return {
                command: '',
                editDialog: false,
                editDialogTitle: '',
                searchDialog: false,
                tableData: [],
                expressStatus:[],
                checkList: ['按时间'],
                currentPage: 1,
                pageSize: 10,
                total: 0,
                multipleSelection: [],
                editform: {
                    date: '',
                    goodsIds: [],
                    expressNumber: ''
                },
                searchForm:{
                    period:'',
                    startDate: '',
                    endDate:'',
                    goodsId: '',
                    status:'',
                    expressNumber: '',
                    mode:''
                },
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
                        value: '按快递'
                    },
                    {
                        label: 4,
                        value: '按状态'
                    }
                ],
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            init() {
                this.getRequest("/logistic/getAllLogistic").then(resp => {
                    if (resp) {
                        this.tableData = resp.result;
                        this.total = this.tableData.length;
                    }
                });
                this.getRequest("/logistic/getAllStatus").then(resp => {
                    if (resp) {
                        this.expressStatus = resp.result;
                    }
                });
            },
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
            dataFormat(row, column, cellValue) {
                return this.$moment(cellValue).format("YYYY-MM-DD");
            },
            getMode() {
                let mode = 0;
                if (this.checkList.indexOf('按时间') > -1)
                    mode += 1;
                if (this.checkList.indexOf('按商品') > -1)
                    mode += 2;
                if (this.checkList.indexOf('按快递') > -1)
                    mode += 4;
                if (this.checkList.indexOf('按状态') > -1)
                    mode += 8;
                return mode;
            },
            commandHandler(cmd, row) {
                this.command = cmd;
                //this.radio = "0";
                //this.category = this.categoryOps[0];
                if (cmd === 'add') {
                    this.editDialog = true;
                    this.editDialogTitle = "录入单号";
                } else if (cmd === 'edit') {
                    this.editDialogTitle = "修改单号";
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
                    this.postJson("/logistic/delete", ids).then(resp => {
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
                    this.postJson("/logistic/addLogistic", this.editform).then(resp => {
                        console.log("editform date");
                        console.log(this.editform);
                        if (resp) {
                            this.$message(resp.msg);
                            this.init();
                        }
                    });
                }else if (this.command === 'search') {
                    this.searchForm.startDate = this.searchForm.period[0];
                    this.searchForm.endDate = this.searchForm.period[1];
                    this.searchForm.mode = this.getMode();
                    this.postJson("/logistic/search", this.searchForm).then(resp => {
                        console.log("search date");
                        console.log(this.searchForm);
                        if (resp) {
                            this.$message(resp.msg);
                            this.tableData = resp.result;
                            this.total = this.tableData.length;
                            this.searchDialog = false;
                        }
                    });
                }
            },
        }
    }
</script>

<style scoped>

</style>