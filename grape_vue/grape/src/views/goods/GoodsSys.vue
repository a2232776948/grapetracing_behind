<template>
    <div style="margin-top: 20px">
        <div>
            <span><i class="el-icon-s-grid"/>产品列表</span>
            <el-button size="mini" @click="commandHandler('add')">添加产品</el-button>
            <el-button size="mini" @click="commandHandler('search')" style="margin-left: 20px">搜索产品</el-button>
            <el-button size="mini" style="margin-left: 20px"
                       v-if="this.multipleSelection.length">删除
            </el-button>
            <el-button size="mini" @click="commandHandler('exportMany')" style="margin-left: 20px"
                       v-if="this.multipleSelection.length">导出
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
                            label="产品编号"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            prop="treeId"
                            label="植株编号"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            prop="areaId"
                            label="地块编号"
                            width="100">
                    </el-table-column>
                    <el-table-column
                            prop="category"
                            label="产品类型"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="desc"
                            label="产品信息"
                            width="150">
                    </el-table-column>
                    <el-table-column
                            prop="trackingNumber"
                            label="溯源码"
                            width="170">
                    </el-table-column>
                    <el-table-column
                            prop="date"
                            label="生产日期"
                            width="130"
                            :formatter="dataFormat">
                    </el-table-column>
                    <el-table-column
                            label="操作"
                            width="120">
                        <template slot-scope="scope">
                            <el-button @click="commandHandler('delete', scope.row)" type="text" size="small">删除
                            </el-button>
                            <el-tooltip class="item" effect="dark" content="导出二维码" placement="bottom" hide-after="1000">
                                <el-button @click="commandHandler('export', scope.row)" type="text" size="small">导出
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

                <el-form-item label="产品分类" label-width="80px">
                    <el-select v-model="editform.category" placeholder="请选择" filterable allow-create>
                        <el-option
                                v-for="(item, index) in categoryOps"
                                :key="index"
                                :label="item"
                                :value="item">
                        </el-option>
                    </el-select>
                </el-form-item>
<!--                <el-form-item label="产品分类" label-width="80px">-->
<!--                    <el-input v-model="editform.category" autocomplete="off"-->
<!--                              :placeholder="editform.category"></el-input>-->
<!--                </el-form-item>-->
                <el-form-item label="描述" label-width="80px">
                    <el-input v-model="editform.desc" autocomplete="off"
                              :placeholder="editform.desc"></el-input>
                </el-form-item>
                <el-form-item label="地块号" label-width="80px" v-if="radio === '0'">
                    <el-select v-model="editform.areaId" filterable placeholder="请选择">
                        <el-option
                                v-for="item in areas"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="树号" label-width="80px" v-if="radio > 0">
                    <el-input v-model="editform.treeId" autocomplete="off" :placeholder="editform.treeId"></el-input>
                </el-form-item>
                <el-form-item label="数量" label-width="80px" >
                    <el-input v-model="editform.count" autocomplete="off" :placeholder="editform.count"></el-input>
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
        name: "GoodsSys",
        data() {
            return {
                command:'',
                editDialog: false,
                searchDialog: false,
                editDialogTitle: '',
                categoryOps: [
                     '新鲜葡萄'
                ],
                radio: '0',
                categoryTableData: [],
                checkList:['按时间'],
                areas: [],
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
                        value: '按分类'
                    }
                ],
                editform: {
                    categoryOps: [],
                    date: '',
                    category: ['新鲜葡萄'],
                    desc: '',
                    areaId:['1','2','3'],
                    treeId: '',
                    count: 1
                },
                searchForm:{
                    period: '',
                    area_id: '',
                    tree_id: '',
                    category: '',
                    mode:''
                 },
                tableData: [{
                    id: 1,
                    treeId : 12,
                    areaId: 2,
                    category: '果汁',
                    desc: '鲜榨果汁',
                    trackingNumber: '100000000000001',
                    date: '2019-09-05'
                }],
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
                this.getRequest('/goods/getGoodsForm').then(resp => {
                    if (resp) {
                        this.tableData = resp.result;
                        this.total = this.tableData.length;
                    }
                });
                this.getRequest('/area/getAllArea').then(resp => {
                    if (resp) {
                        this.areas = resp.result;
                    }
                });
                this.getRequest("/goods/getCategory").then(resp => {
                    if (resp) {
                        this.categoryOps = resp.result;
                    }
                });
                console.log("this.categoryOps");
                console.log(this.categoryOps);
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
            downloadGoodsQrById(id){
                const elink = document.createElement('a');
                elink.download = "goodsId="+id+".jpg";
                elink.style.display = 'none';
                elink.href = '/goods/getGoodsQRcode?id='+id;
                document.body.appendChild(elink);
                elink.click();
                URL.revokeObjectURL(elink.href);// 释放URL 对象
                document.body.removeChild(elink)
            },
            downloadGoodsQrByIds(ids){
                let url;
                this.postJson('/goods/addGoodsQRcodes',ids).then(resp =>{
                    url = resp.result;
                    const elink = document.createElement('a');
                    elink.download = "goods"+".zip";
                    elink.style.display = 'none';
                    elink.href = '/goods/getGoodsQRcodes?url='+url;
                    document.body.appendChild(elink);
                    elink.click();
                    URL.revokeObjectURL(elink.href);// 释放URL 对象
                    document.body.removeChild(elink)
                });
            },
            commandHandler(cmd, row) {
                this.command = cmd;
                if (cmd === 'add') {
                    this.title = '添加葡萄商品';
                    this.editDialog = true;
                } else  if(cmd === 'search'){
                    this.title='搜所葡萄商品'
                    this.searchDialog=true;
                }else if (cmd === 'edit') {
                    this.title = '修改葡萄商品信息';
                    this.form.address = row.address;
                    this.form.name = row.name;
                    this.form.id = row.id;
                    this.form.status = row.status;
                    this.editDialog = true;
                } else if (cmd === 'delete') {
                    var ids = [];
                    if (row) {
                        ids.push(row.id);
                    } else {
                        this.multipleSelection.forEach(row => {
                            ids.push(row.id);
                        })
                    }
                    this.postJson("goods/deleteGoods", ids).then(resp => {
                        if (resp) {
                            this.$message.success(resp.msg);
                            this.init();
                        }
                    })
                } else if (cmd === 'export') {
                    this.downloadGoodsQrById(row.id);
                    //this.$message("未接入接口");
                }else if(cmd === 'exportMany'){
                    var ids = [];
                    if (row) {
                        ids.push(row.id);
                    } else {
                        this.multipleSelection.forEach(row => {
                            ids.push(row.id);
                        })
                    }
                    this.downloadGoodsQrByIds(ids);
                }
            },
            submitform() {
                if (this.command === 'add') {
                    if (this.radio === '0') {
                        this.postJson("/goods/addGoodsForArea", this.editform).then(resp => {
                            if (resp) {
                                this.$message(resp.msg);
                                this.init();
                            }
                        })
                    } else if (this.radio === '1') {
                        this.postJson("/goods/addGoodsForTree", this.editform).then(resp => {
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
                    console.log("this.searchForm=");
                    console.log(this.searchForm);
                    this.postJson("/goods/findGoods", this.searchForm).then(resp => {
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
            dataFormat(row, column, cellValue) {
                return this.$moment(cellValue).format("YYYY-MM-DD");
            }
        }
    }
</script>

<style scoped>

</style>