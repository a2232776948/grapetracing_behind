<template>
    <div class="grid-content">
        <div style="text-align: center;color: #ffffff">{{name}}</div>
        <el-divider></el-divider>
        <div style="font-size: 50px;color: white;text-align: center">{{num + ' 株'}}</div>
    </div>
</template>

<script>
    export default {
        name: "card",
        props:{
            num: Number,
            name: String
        }
    }
</script>

<style scoped>

    .grid-content {
        background: #DBEFFF;
        border-radius: 10px;
        height: 110px;
        width: 210px;
        padding: 20px;
    }

    .el-divider {
        margin-top: 8px;
        margin-bottom: 8px;
        background-color: #ffffff;
    }

</style>