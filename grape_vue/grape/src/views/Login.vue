<template>
    <el-container >
        <el-form
                :rules="rules"
                ref="loginForm"
                v-loading="loading"
                :model="loginForm"
                element-loading-text="正在登录..."
                element-loading-spinner="el-icon-loading"
                element-loading-background="rgba(0, 0, 0, 0.8)"
                class="loginContainer"
                label-position="left">
            <h3 class="loginTitle">系统登陆</h3>
            <el-form-item prop="username" label="用户名：" label-width="80px">
                <el-input size="normal"
                          type="text"
                          v-model="loginForm.username"
                          auto-complete="false"
                          placeholder="请输入用户名"/>
            </el-form-item>
            <el-form-item prop="password" label="密  码：" label-width="80px">
                <el-input size="normal"
                          type="password"
                          v-model="loginForm.password"
                          placeholder="请输入密码"/>
            </el-form-item>
            <el-form-item prop="code" label="验证码：" label-width="80px">
                <el-input size="normal" type="text" v-model="loginForm.code" auto-complete="off"
                          placeholder="点击图片更换验证码" @keydown.enter.native="submitLogin" style="width: 160px"></el-input>
                <img :src="vcUrl" @click="updateVerifyCode" alt="">
            </el-form-item>
            <el-checkbox size="normal" class="loginRemember" v-model="checked">记住密码</el-checkbox>
            <el-button size="normal" type="primary" style="width: 100%;" @click="submitLogin">登录</el-button>
        </el-form>
    </el-container>


</template>


<script>
    export default {
        name: "Login",
        data() {
            return {
                loading: false,
                vcUrl: '/verifyCode?time=' + new Date(),
                loginForm: {
                    username: 'admin',
                    password: 'admin',
                    code: ''
                },
                checked: true,
                rules: {
                    username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
                    password: [{required: true, message: '请输入密码', trigger: 'blur'}],
                    code: [{required: true, message: '请输入验证码', trigger: 'blur'}]
                }
            }
        },
        methods: {
            updateVerifyCode() {
                this.vcUrl = '/verifyCode?time=' + new Date();
            },
            submitLogin() {
                this.$refs.loginForm.validate((valid) => {
                    if (valid) {
                        this.loading = true;
                        this.postKeyValueRequest('/doLogin', this.loginForm).then(resp => {
                            this.loading = false;
                            if (resp) {
                                this.$store.commit('INIT_CURRENTUSER', resp.result);
                                window.localStorage.setItem("user", JSON.stringify(resp.result));
                                let path = this.$route.query.redirect;
                                this.$router.replace((path === '/login' || path === undefined) ? '/home' : path);
                            } else {
                                this.vcUrl = '/verifyCode?time=' + new Date();
                            }
                        })
                    } else {
                        return false;
                    }
                });
            }
        }
    }

</script>

<style scoped>
    .loginContainer {
        border-radius: 15px;
        background-clip: padding-box;
        width: 350px;
        height: 350px;
        padding: 15px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
        position: absolute;
        left: 65%;
        top: 20%;
    }

    .loginTitle {
        margin: 15px auto 20px auto;
        text-align: center;
        color: #4eb4ff;
    }

    .loginRemember {
        text-align: left;
        margin: 0px 0px 15px 0px;
    }

    .el-form-item__content {
        display: flex;
        align-items: center;
    }

    .el-container {
        background: url("../assets/image/background.jpg");
        background-size: cover;
        height: 100%;
    }

</style>