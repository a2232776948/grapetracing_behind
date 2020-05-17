<template>
    <div>
        <div style="margin-top: 20px; margin-bottom: 20px">
            <div>
                <el-radio-group v-model="tabPosition" @change="changeHandler">
                    <el-radio-button label="left">植株清单</el-radio-button>
                    <el-radio-button label="right">种类清单</el-radio-button>
                </el-radio-group>
                <el-button size="mini" style="margin-left: 20px" @click="commanderHandler('searchPlant')"
                           v-if="tabPosition === 'left'">搜索植株
                </el-button>
                <el-button size="mini" style="margin-left: 20px" @click="commanderHandler('addPlant')"
                           v-if="tabPosition === 'left'">添加植株
                </el-button>
                <el-button size="mini" style="margin-left: 20px" @click="commanderHandler('deletePlant')"
                           v-if="this.multipleSelection.length !== 0">删除植株
                </el-button>
                <el-button size="mini" style="margin-left: 20px" @click="commanderHandler('exportMany')"
                           v-if="this.multipleSelection.length !== 0">导出
                </el-button>
                <!--                <el-button size="mini" style="margin-left: 20px" @click="commanderHandler('addCategory')"
                                           v-if="tabPosition === 'right'">添加种类
                                </el-button>-->
            </div>
            <div style="width: 926px; margin-top: 20px" v-if="tabPosition === 'left'">
                <el-table
                        :data="tableData.slice((currentPage - 1) * pageSize, currentPage * pageSize)"
                        border
                        row-key="id"
                        size="mini"
                        @selection-change="handleSelectionChange">
                    <el-table-column
                            type="selection"
                            width="55"
                            :reserve-selection="true">
                    </el-table-column>
                    <el-table-column
                            prop="id"
                            label="植株编号"
                            width="150"
                            sort-by="id"
                            :resizable="false">
                    </el-table-column>
                    <el-table-column
                            prop="plant_date"
                            label="种植时间"
                            width="150"
                            :formatter="dataFormat"
                            :resizable="false">
                    </el-table-column>
                    <el-table-column
                            prop="category"
                            label="植株种类"
                            width="150"
                            :resizable="false">
                    </el-table-column>
                    <el-table-column
                            prop="area"
                            label="所在地块"
                            width="150"
                            :resizable="false">
                    </el-table-column>
                    <el-table-column
                            prop="status"
                            label="植株状态"
                            width="150"
                            :resizable="false">
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            width="120"
                            :resizable="false">
                        <template slot-scope="scope">
                            <el-button @click="commanderHandler('deletePlant', scope.row)" type="text" size="small">删除
                            </el-button>
                            <el-button @click="commanderHandler('editPlant', scope.row)" type="text" size="small">编辑
                            </el-button>
                            <el-tooltip class="item" effect="dark" content="导出二维码" placement="bottom" hide-after="1000">
                                <el-button @click="commanderHandler('export',scope.row)" type="text" size="small">导出
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

            <div style="width: 655px; margin-top: 20px" v-if="tabPosition === 'right'">
                <el-table
                        :data="categoryTableData"
                        border
                        size="mini">
                    <el-table-column type="expand" width="55px"/>
                    <el-table-column
                            prop="name"
                            label="植株种类"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="count"
                            label="植株总数"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="alive"
                            label="存活总数"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="dead"
                            label="死亡总数"
                            width="150">
                    </el-table-column>
                </el-table>
            </div>
        </div>

        <el-dialog :title="plantDialogtitle" :visible.sync="plantDialog" width="30%" @close="handleClosePlantDialog">
            <el-form :model="plantForm" label-position="left">
                <el-form-item label="所在地块" label-width="80px">
                    <el-select v-model="plantForm.area_id" filterable placeholder="请选择">
                        <el-option
                                v-for="item in areas"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="植株种类" label-width="80px">
                    <el-select v-model="plantForm.category" filterable placeholder="请选择" allow-create>
                        <el-option
                                v-for="item in categoryTableData"
                                :key="item.index"
                                :label="item.name"
                                :value="item.name">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="种植时间" label-width="80px">
                    <el-date-picker
                            v-model="plantForm.plant_date"
                            type="date"
                            placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="植株状态" label-width="80px">
                    <el-select v-model="plantForm.status" filterable placeholder="请选择">
                        <el-option value="存活" label="存活"/>
                        <el-option value="死亡" label="死亡"/>
                    </el-select>
                </el-form-item>
                <el-form-item label="数量" label-width="80px">
                    <el-input v-model="plantForm.count" placeholder="1"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="plantDialog = false">取 消</el-button>
                <el-button type="primary" @click="submitHandler">确 定</el-button>
            </div>
        </el-dialog>

        <el-dialog :title="searchDialogTitle" :visible.sync="searchDialog" width="30%" @close="handleCloseSearchDialog">
            <el-form :model="searchForm" label-position="left">
                <el-form-item label="方式">
                    <el-checkbox-group v-model="checkList" :min="1" @change="selectedChange">
                        <el-checkbox label="按时间"></el-checkbox>
                        <el-checkbox label="按地块"></el-checkbox>
                        <el-checkbox label="按植株"></el-checkbox>
                        <el-checkbox label="按状态"></el-checkbox>
                    </el-checkbox-group>
                </el-form-item>
                <el-form-item label="种植时间" label-width="80px" v-if="checkList.indexOf('按时间') > -1">
                    <el-date-picker
                            v-model="searchForm.period"
                            type="daterange"
                            range-separator="至"
                            start-placeholder="开始日期"
                            end-placeholder="结束日期"
                            style="width: 250px">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="地块号" label-width="80px" v-if="checkList.indexOf('按地块') > -1">
                    <el-select v-model="searchForm.area_id" filterable placeholder="请选择">
                        <el-option
                                v-for="item in areas"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="树号" label-width="80px" v-if="checkList.indexOf('按植株') > -1">
                    <el-input v-model="searchForm.id" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="状态" label-width="80px" v-if="checkList.indexOf('按状态') > -1">
                    <el-select v-model="searchForm.status" filterable placeholder="请选择">
                        <el-option value="存活" label="存活"/>
                        <el-option value="死亡" label="死亡"/>
                    </el-select>
                </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="searchDialog = false">取 消</el-button>
                <el-button type="primary" @click="submitHandler">搜 索</el-button>
            </div>
        </el-dialog>

        <!--        <el-dialog :title="categoryDialogtitle" :visible.sync="categoryDialog" width="30%">
                    <el-form :model="categoryForm" label-position="left">
                        <el-form-item label="植株种类" label-width="80px">
                            <el-input v-model="categoryForm.categoryName" autocomplete="off"></el-input>
                        </el-form-item>
                    </el-form>
                    <div slot="footer" class="dialog-footer">
                        <el-button @click="categoryDialog = false">取 消</el-button>
                        <el-button type="primary" @click="submitHandler">确 定</el-button>
                    </div>
                </el-dialog>-->
    </div>
</template>

<script>
    export default {
        name: "PlantSys",
        data() {
            return {
                tableData: [],
                orders: ['ascending', 'descending', null],
                categoryTableData: [],
                areas: [],
                tabPosition: 'left',
                plantDialogtitle: '添加植株',
                plantDialog: false,
                plantForm: {
                    area_id: '',
                    category: '',
                    plant_date: '',
                    status: '',
                    count: ''
                },
                searchDialog: false,
                searchDialogTitle: '搜索',
                checkList: ['按时间'],
                searchForm: {
                    area_id: '',
                    id: '',
                    period: '',
                    start_date: '',
                    end_date: '',
                    status: '',
                    mode:''
                },
                // categoryDialogtitle: '',
                // categoryDialog: false,
                // categoryForm: {
                //     categoryName: ''
                // },
                command: '',
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
                this.getRequest("/tree/getAllTrees").then(resp => {
                    if (resp) {
                        this.tableData = resp.result;
                        this.total = this.tableData.length;
                        console.log(this.tableData);
                    }
                });
                this.getRequest("/tree/getAllCategory").then(resp => {
                    if (resp) {
                        this.categoryTableData = resp.result;
                        console.log(this.categoryTableData);
                    }
                });
                this.getRequest('/area/getAllArea').then(resp => {
                    if (resp) {
                        this.areas = resp.result;
                    }
                })
            },
            initPlantsCategoryTableData() {
                this.getRequest("/tree/getAllCategory").then(resp => {
                    if (resp) {
                        this.categoryTableData = resp.result;
                        console.log(this.categoryTableData);
                    }
                });
            },
            currentChange(currentPage) {
                this.currentPage = currentPage;
            },
            sizeChange(pageSize) {
                this.pageSize = pageSize;
            },
            downloadTreeQrById(id){
                const elink = document.createElement('a');
                elink.download = "treeId="+id+".jpg";
                elink.style.display = 'none';
                elink.href = '/tree/getTreeQRcode?id='+id;
                document.body.appendChild(elink);
                elink.click();
                URL.revokeObjectURL(elink.href);// 释放URL 对象
                document.body.removeChild(elink)
            },
            downloadTreeQrByIds(ids){
                var url = '';
                this.postJson("/tree/addTreeQRcodes",ids).then(resp => {
                    if (resp) {
                        url = resp.result;
                        this.$message.success(resp.msg);
                        console.log('url='+url);
                        const elink = document.createElement('a');
                        elink.download = "tree"+".zip";
                        elink.style.display = 'none';
                        elink.href = '/tree/getTreeQRcodes?url='+url;
                        document.body.appendChild(elink);
                        elink.click();
                        URL.revokeObjectURL(elink.href);// 释放URL 对象
                        document.body.removeChild(elink)
                    }
                });
            },
            commanderHandler(cmd, row) {
                this.command = cmd;
                if (cmd === 'searchPlant') {
                    this.searchDialog = true;
                } else if (cmd === 'addPlant') {
                    this.plantDialogtitle = '添加植株';
                    this.plantDialog = true;
                } else if (cmd === 'deletePlant') {
                    var ids = [];
                    if (row) {
                        ids.push(row.id);
                    } else {
                        this.multipleSelection.forEach(row => {
                            ids.push(row.id);
                        });
                    }
                    this.postJson("/tree/removeTrees", ids).then(resp => {
                        if (resp) {
                            this.$message.success(resp.msg);
                            this.init();
                        }
                    })
                } else if (cmd === 'editPlant') {
                    this.plantDialogtitle = "修改植株";
                    this.plantForm.id = row.id;
                    this.plantForm.area_id = row.area_id;
                    this.plantForm.category = row.category;
                    this.plantForm.plant_date = row.plant_date;
                    this.plantForm.status = row.status;
                    this.plantDialog = true;
                } else if (cmd === 'addCategory') {
                    this.categoryDialogtitle = "添加种类";
                    this.categoryDialog = true;
                } else if (cmd === 'deleteCategory') {
                    console.log(row);
                    this.$message("未接入接口");
                } else if (cmd === 'editCategory') {
                    this.$message("未接入接口");
                }else if(cmd === 'export'){
                    this.downloadTreeQrById(row.id);
                }else if(cmd === 'exportMany'){
                    ids = [];
                    if (row) {
                        ids.push(row.id);
                    } else {
                        this.multipleSelection.forEach(row => {
                            ids.push(row.id);
                        })
                    }
                    this.downloadTreeQrByIds(ids);
                }

            },
            submitHandler() {
                if (this.command === 'searchPlant') {
                    this.searchForm.start_date = this.searchForm.period[0];
                    this.searchForm.end_date = this.searchForm.period[1];
                    this.searchForm.mode = this.getMode();
                    console.log('this.searchForm=');
                    console.log(this.searchForm);
                    this.postJson("/tree/findTrees", this.searchForm).then(resp => {
                        if (resp) {
                            this.tableData = resp.result;
                            this.total = this.tableData.length;
                            this.$message(resp.msg);
                            this.searchDialog = false;
                        }
                    })
                } else if (this.command === 'addPlant') {
                    this.postJson("/tree/addTrees", this.plantForm).then(resp => {
                        if (resp) {
                            this.$message(resp.msg);
                            this.plantDialog = false;
                            this.init();
                        }
                    })
                } else if (this.command === 'editPlant') {
                    this.postJson("/tree/editTree", this.plantForm).then(resp => {
                        if (resp) {
                            this.$message.success(resp.msg);
                            this.plantDialog = false;
                            this.init();
                        }
                    })
                } /*else if (this.command === 'addCategory') {
                    this.postJson("/addTreeCate", this.categoryForm.categoryName).then(resp => {
                        if (resp) {
                            this.$message.success("添加成功");
                            this.categoryDialog = false;
                            this.categoryForm.categoryName = '';
                            this.initPlantsCategoryTableData();
                        } else {
                            this.$message.error("添加失败");
                        }
                    })
                }*/
            },
            getMode(){
                var mode = 0;
                if (this.checkList.indexOf("按时间") > -1){
                    mode += 1;
                }
                if (this.checkList.indexOf("按地块") > -1){
                    mode += 2;
                }
                if (this.checkList.indexOf("按植株") > -1){
                    mode += 4;
                }
                if (this.checkList.indexOf("按状态") > -1){
                    mode += 8;
                }
                return mode;
            },
            changeHandler() {
                if (this.tabPosition === 'right') {
                    this.initPlantsCategoryTableData();
                }
            },
            handleClosePlantDialog() {
                this.plantForm.area_id = '';
                this.plantForm.category = '';
                this.plantForm.status = '';
                this.plantForm.plant_date = '';
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
            },
            dataFormat(row, column, cellValue) {
                return this.$moment(cellValue).format("YYYY-MM-DD");
            },
            selectedChange(val) {
                var index = val.indexOf('按植株');
                if (index == val.length - 1) {
                    this.checkList = ['按植株'];
                } else if (val.indexOf('按植株') > -1) {
                    this.checkList.splice(index, 1);
                }
            },
            handleCloseSearchDialog() {
                this.searchForm.area_id = '';
                this.searchForm.period = [];
                this.searchForm.status = '';
                this.searchForm.id = '';
                this.searchForm.start_date = '';
                this.searchForm.end_date = '';
            }
        }
    }
</script>

<style scoped>
    .el-dialog__body {
        padding: 10px 20px;
    }
</style>