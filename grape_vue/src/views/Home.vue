<template>
    <el-container>
        <el-header>
            <span class="title">基于区块链的葡萄溯源平台</span>
            <div>
                <el-dropdown class="userInfo" @command="commandHandler">
                    <span class="el-dropdown-link">
<!--                        bois-->
<!--                        {{user.name}}<i><img :src="user.userface" alt=""/></i>-->
                        {{user.name}}<el-avatar style="margin-left: 5px;" :size="40" :src="user.userface"/>
                </span>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item command="userinfo">个人中心</el-dropdown-item>
                        <el-dropdown-item command="logout" divided>注销</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </el-header>
        <el-container>
            <el-aside width="200px">
                <el-menu router unique-opened
                         default-active="this.$router.currentRoute.path"
                         class="el-menu-vertical-demo">
                    <el-menu-item index="/home">
                        <i class="el-icon-s-home"></i>
                        <span slot="title">首页</span>
                    </el-menu-item>
                    <template v-for="(item, index) in routes[0].children">
                        <el-menu-item :index="item.path" :key="index">
                            <i :class="item.iconCls"/>
                            <span slot="title" >{{item.name}}</span>
                        </el-menu-item>
                    </template>
                </el-menu>
            </el-aside>
            <el-main>
                <el-breadcrumb separator-class="el-icon-arrow-right" v-if="this.$router.currentRoute.path !== '/home'" style="margin-bottom: 20px">
                    <el-breadcrumb-item :to="{path: '/home'}">首页</el-breadcrumb-item>
                    <el-breadcrumb-item>
                        {{this.$router.currentRoute.name}}
                    </el-breadcrumb-item>
                </el-breadcrumb>
<!--                <el-divider></el-divider>-->
                <div v-if="this.$router.currentRoute.path === '/home'">
                    <!--卡片组-->
                    <div style="margin-top: 20px">
                        <el-row :gutter="20" type="flex">
                            <el-col :span="8">
                                <Card name="今日种植数量" num="5"></Card>
                            </el-col>
                            <el-col :span="8">
                                <card name="今日采摘数量" num="10"/>
                            </el-col>
                            <el-col :span="8">
                                <card name="今日出货数量" num="10"/>
                            </el-col>
                        </el-row>
                    </div>
                    <!--今日农事-->
                    <Tips style="margin-top: 20px"></Tips>
                    <!--天气-->
                    <Weather class="weather" location="北京" rain="15.6" temperature="30" wind="7"/>
                </div>
                <router-view/>
            </el-main>
        </el-container>
    </el-container>
</template>

<script>
    import Card from "@/components/home/Card";
    import Weather from "@/components/home/Weather";
    import Tips from "@/components/home/Tips";

    export default {
        name: "Home",
        components: {Tips, Weather, Card},
        computed: {
            routes() {
                return this.$store.state.routes;
            },
            user() {
                return this.$store.state.currentUser;
            }
        },
        methods: {
            commandHandler(cmd) {
                if (cmd === 'logout') {
                    this.$confirm('此操作将注销登录, 是否继续?', '提示', {
                        confirmButtonText: '确定',
                        cancelButtonText: '取消',
                        type: 'warning'
                    }).then(() => {
                        this.getRequest("/logout");
                        window.localStorage.removeItem("user")
                        this.$store.commit('initRoutes', []);
                        this.$router.replace("/login");
                    }).catch(() => {
                        this.$message({
                            type: 'info',
                            message: '已取消操作'
                        });
                    });
                } else if (cmd === 'userinfo') {
                    this.$router.push('/userinfo');
                }
            }
        }
    }
</script>

<style scoped>
    .el-header {
        background: #409EFF;
        display: flex;
        align-items: center;
        justify-content: space-between;
        box-sizing: border-box;
        position: relative;
    }

    .el-aside {
        position: absolute;
        top: 60px;
        left: 0px;
        bottom: 0px;
    }

    .el-main {
        position: absolute;
        overflow-y: scroll;
        left: 200px;
        top: 60px;
        right: 0px;
        bottom: 0px;
    }

    .title {
        font-size: 30px;
        font-family: "PingFang SC", serif;
        color: #ffffff
    }

    .homeHeader .userInfo {
        cursor: pointer;
    }


    .el-dropdown-link img {
        width: 48px;
        height: 48px;
        border-radius: 24px;
        margin-left: 8px;
    }

    .el-dropdown-link {
        display: flex;
        align-items: center;
        color: white;
    }

    .tips {
        width: 87%;
        margin-top: 20px;
        border-radius: 10px;
        background-color: #cac6c6;
        height: 190px;
    }

    .weather {
        position: absolute;
        top: 50%;
        left: 55%;
    }

    .el-divider {
        margin: 10px 0;
    }


</style>