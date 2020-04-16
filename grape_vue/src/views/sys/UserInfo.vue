<template>
    <div style="margin-top: 20px">
        <el-form :model="user" label-position="right" label-width="100px">
            <el-form-item label="头    像:">
                <el-upload
                        class="avatar-uploader"
                        action="/user/userface"
                        :show-file-list="false"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload">
                    <el-avatar v-if="user.userface" :src="user.userface"/>
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </el-form-item>
            <el-form-item label="姓    名:">
                <el-input v-model="user.name" style="width: 200px"/>
            </el-form-item>
            <el-form-item label="用户名:">
                <span>{{user.username}}</span>
            </el-form-item>
            <el-form-item label="手    机:">
                <el-input v-model="user.phone" style="width: 200px"/>
            </el-form-item>
            <el-form-item label="性    别:">
                <el-radio v-model="user.gender" label="男">男</el-radio>
                <el-radio v-model="user.gender" label="女">女</el-radio>
            </el-form-item>
            <el-form-item label="注册日期:">
                <span>{{dataFormat(user.date)}}</span>
            </el-form-item>
            <el-form-item label="年龄">
                <el-input v-model="user.age" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="详细信息:">
                <el-input type="textarea" rows="2" v-model="user.detail" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item label="hash:">
                <el-input rows="2" v-model="user.hash" style="width: 200px"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" size="mini" @click="commandHandler('save')">保存</el-button>
                <el-button type="primary" size="mini" @click="commandHandler('changepwd')">修改密码</el-button>
            </el-form-item>
        </el-form>
        <el-dialog
                title="修改密码"
                :visible.sync="pwdDialogVisible"
                width="30%">
            <el-form :model="pwdForm" status-icon :rules="rules" ref="pwdForm" label-width="100px"
                     class="demo-ruleForm">
                <el-form-item label="请输入旧密码" prop="oldpwd">
                    <el-input type="password" v-model="pwdForm.oldpwd" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="请输入新密码" prop="pwd">
                    <el-input type="password" v-model="pwdForm.pwd" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="新确认密码" prop="checkpwd">
                    <el-input type="password" v-model="pwdForm.checkpwd" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="submitForm('pwdForm')">提交</el-button>
                    <el-button @click="resetForm('pwdForm')">取消</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        name: "UserInfo",
        data() {
            var validatepwd = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else {
                    if (this.pwdForm.checkpwd !== '') {
                        this.$refs.pwdForm.validateField('checkpwd');
                    }
                    callback();
                }
            };
            var validatepwd2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value !== this.pwdForm.pwd) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };
            return {
                user: {
                    id: '',
                    name: '',
                    username: '',
                    phone: '',
                    gender: '',
                    date: '',
                    age: '',
                    userface: '',
                    detail: '',
                    hash: ''
                },
                pwdDialogVisible: false,
                pwdForm: {
                    oldpwd: '',
                    pwd: '',
                    checkpwd: ''
                },
                rules: {
                    oldpwd: [
                        {validator: validatepwd, trigger: 'blur'}
                    ],
                    pwd: [
                        {validator: validatepwd, trigger: 'blur'}
                    ],
                    checkpwd: [
                        {validator: validatepwd2, trigger: 'blur'}
                    ]
                },
            }
        },
        mounted() {
            this.init();
        },
        methods: {
            init() {
                this.getRequest('/user/info').then(resp => {
                    if (resp) {
                        this.user = resp.result;
                    }
                })
            },
            commandHandler(command) {
                if (command === 'save') {
                    console.log(this.user);
                    this.postJson("/user/update", this.user).then(resp => {
                        if (resp) {
                            this.init();
                        }
                    })
                } else if (command === 'changepwd') {
                    this.pwdDialogVisible = true;
                }
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        this.pwdForm.user_id = this.user.id;
                        this.postRequest("/user/changePwd", this.pwdForm).then(resp => {
                            if (resp) {
                                this.getRequest("/logout");
                                window.sessionStorage.removeItem("user");
                                this.$store.commit('initRoutes', []);
                                this.$router.replace("/");
                            }
                        });
                    } else {
                        return false;
                    }
                });
            },
            resetForm(formName){
                this.$refs[formName].resetFields();
            },
            onSuccess() {
                this.init();
            },
            handleAvatarSuccess(res, file) {
                this.imageUrl = URL.createObjectURL(file.raw);
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            dataFormat(value) {
                return this.$moment(value).format("YYYY-MM-DD");
            },
        }
    }
</script>

<style scoped>

</style>