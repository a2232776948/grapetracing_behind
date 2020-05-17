<template>

    <div style="margin-top: 20px">
        <el-radio-group v-model="tabPosition" style="margin-bottom: 30px;" @change="tabChange">
            <el-radio-button label="1" @click="opCommand('1')">植株统计</el-radio-button>
            <el-radio-button label="2" @click="oPCmmand('2')">农事统计</el-radio-button>
            <el-radio-button label="3" @click="oPCmmand('3')">葡萄统计</el-radio-button>
        </el-radio-group>
        <div id="myChart" style="width: 80%;height: 500px"></div>
    </div>

</template>

<script>
    export default {
        name: "StatisticsSys",
        data() {
            return {
                tabPosition: '1',

                option1: {
                    title: {
                        text: '植株统计'
                    }
                    ,
                    tooltip: {}
                    ,
                    xAxis: {
                        name: '地块号',
                        data: []
                    }
                    ,
                    yAxis: {
                        name: '植株数量'
                    }
                    ,
                    series: [{
                        name: '数量',
                        type: 'bar',
                        data: []
                    }]
                },
                option2: {
                    title: {
                        text: '农事统计'
                    }
                    ,
                    tooltip: {}
                    ,
                    xAxis: {
                        data: []
                    }
                    ,
                    yAxis: {
                        name: '农事数量'
                    }
                    ,
                    series: [{
                        name: '数量',
                        type: 'bar',
                        data: []
                    }]
                },
                option3: {
                    title: {
                        text: '葡萄商品统计'
                    }
                    ,
                    tooltip: {}
                    ,
                    xAxis: {
                        name: '地块号',
                        data: []
                    }
                    ,
                    yAxis: {}
                    ,
                    series: [{
                        name: '数量',
                        type: 'bar',
                        data: []
                    }]
                }
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            // opCommand(num){
            //   if(num === '1'){
            //       this.tabPosition = 1;
            //       this.drawArea();
            //   }else if(num === '2'){
            //       this.tabPosition = 2;
            //       this.drawFormOp();
            //   }else if(num === '3'){
            //       this.tabPosition = 3;
            //   }
            // },
            drawArea(){
                let treeCount = [];
                let areaIds = [];
                this.getRequest('/area/getAllArea').then(resp=>{
                    console.log("this.arearesp");
                    console.log(resp);
                    if(resp){
                        resp.result.forEach(area => {
                            treeCount.push(area.treeCount);
                            areaIds.push(area.id);
                        });
                        this.option1.xAxis.data = areaIds;
                        this.option1.series[0].data = treeCount;
                        let myChart = this.$echarts.init(document.getElementById('myChart'));
                        myChart.setOption(this.option1);
                    }
                })
            },
            drawFormOp(){
                let farmOpCount = [];
                let areaIds = [];
                this.getRequest('/farmop/getAreaFarmopCount').then(resp=>{
                    console.log("this.farmopresp");
                    console.log(resp);
                    if(resp){
                        resp.result.forEach(area => {
                            farmOpCount.push(area.count);
                            areaIds.push(area.areaId);
                        });
                        this.option2.xAxis.data = areaIds;
                        this.option2.series[0].data = farmOpCount;
                        let myChart = this.$echarts.init(document.getElementById('myChart'));
                        myChart.setOption(this.option2);
                    }
                })
            },
            drawgoods(){
                let goodsCount = [];
                let areaIds = [];
                this.getRequest('/goods/getAreaGoodsCount').then(resp=>{
                    console.log("this.goodsresp");
                    console.log(resp);
                    if(resp){
                        resp.result.forEach(area => {
                            goodsCount.push(area.count);
                            areaIds.push(area.id);
                        });
                        this.option3.xAxis.data = areaIds;
                        this.option3.series[0].data = goodsCount;
                        let myChart = this.$echarts.init(document.getElementById('myChart'));
                        myChart.setOption(this.option3);
                    }
                })
            },

            init(){
                this.drawLine();

            },
            tabChange(val) {
                if (val === '1') {
                    // let myChart = this.$echarts.init(document.getElementById('myChart'));
                    this.drawArea();
                } else if (val === '2') {
                    // let myChart = this.$echarts.init(document.getElementById('myChart'));
                    this.drawFormOp();
                } else if (val === '3'){
                    // let myChart = this.$echarts.init(document.getElementById('myChart'));
                    this.drawgoods();
                }
            },
            drawLine() {
                // 基于准备好的dom，初始化echarts实例
                // let myChart = this.$echarts.init(document.getElementById('myChart'));
                // 绘制图表
                this.drawArea();
                // myChart.setOption(this.option1);
            }

        }
    }
</script>

<style scoped>

</style>