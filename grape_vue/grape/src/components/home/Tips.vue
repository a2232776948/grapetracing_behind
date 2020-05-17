<template>
    <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>通知</span>
        </div>
        <div class="text item">
            一号农田 浇水
        </div>
        <div class="text item">
            二号农田 除草
        </div>
        <div class="text item">
            三号农田 施肥
        </div>

    </el-card>
</template>

<script>
    export default {
        name: "Tips"
    }
</script>

<style scoped>
    .text {
        font-size: 14px;
    }

    .item {
        margin-bottom: 18px;
    }

    .clearfix:before,
    .clearfix:after {
        display: table;
        content: "";
    }

    .clearfix:after {
        clear: both
    }

    .box-card {
        width: 480px;
        height: 450px;
    }
</style>