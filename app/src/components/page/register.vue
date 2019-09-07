<template>
    <div class="body">
        <el-form
            ref="userForm"
            :model="user"
            :rules="userRules"
            label-position="left"
            label-width="100px"
            style="width:360px;padding:0 auto;margin:0 auto;padding-top:100px;color:white;"
        >
            <el-form-item label="姓名" prop="name">
                <el-input v-model="user.name" placeholder="姓名"/>
            </el-form-item>
            <el-form-item label="单位" prop="department">
                <el-input v-model="user.department" placeholder="单位"/>
            </el-form-item>
            <el-form-item label="联系方式" prop="contactInformation">
                <el-input v-model="user.contactInformation" placeholder="联系方式"/>
            </el-form-item>
            <el-form-item label="注册原因" prop="reason" required>
                <el-select v-model="user.reason" value-key="value" placeholder="注册原因">
                    <el-option
                        v-for="reason in registerReasons"
                        :key="reason.id"
                        :label="reason.name"
                        :value="reason.id"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="登录名" prop="username">
                <el-input v-model="user.username" type="text" placeholder="登录名"/>
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="user.password" type="password" placeholder="密码"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="confirmPassword">
                <el-input v-model="user.confirmPassword" type="password" placeholder="确认密码"></el-input>
            </el-form-item>
            <el-form-item label="验证码" prop="verificationCode" required>
                <el-input v-model="user.verificationCode" placeholder="验证码">
                    <div slot="append" ref="verifyCode" style="width:80px;height:30px;"></div>
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="doRegister('userForm')" style="width:260px;">注册</el-button>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="$router.go(-1)" style="width:260px;">已有账户，去登陆</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>
<script>
    import Gverify from "../../common/gverify";

    export default {
        data() {
            var validateConfirmPassword = (rule, value, callback) => {
                if (!value || value != this.user.password) {
                    callback("请输入相同的密码");
                } else {
                    callback();
                }
            };
            var validateVerificationCode = (rule, value, callback) => {
                if (!value) {
                    callback("请输入验证码");
                } else if (this.gverify.validate(value)) {
                    callback();
                } else {
                    callback(new Error('验证码错误'));
                }
            };
            return {
                user: {
                    username: "",
                    password: "",
                    confirmPassword: "",
                    reason: "",
                    department: "",
                    contactInformation: "",
                    verificationCode: ""
                },
                userRules: {
                    name: [
                        {
                            required: true,
                            message: "请输入用户姓名",
                            trigger: "blur"
                        },
                        {
                            min: 2,
                            max: 6,
                            message: "长度在2-6个字符之间",
                            trigger: "blur"
                        }
                    ],
                    username: [
                        {
                            required: true,
                            message: "请输入登录名",
                            trigger: "blur"
                        },
                        {
                            min: 6,
                            max: 24,
                            message: "长度在6-24个字符之间",
                            trigger: "blur"
                        }
                    ],
                    password: [
                        {
                            required: true,
                            message: "请输入密码",
                            trigger: "blur"
                        },
                        {
                            min: 6,
                            max: 24,
                            message: "长度在6-24个字符之间",
                            trigger: "blur"
                        }
                    ],
                    confirmPassword: [
                        {
                            required: true,
                            message: "请输入相同的密码",
                            trigger: "blur"
                        },
                        {
                            validator: validateConfirmPassword,
                            trigger: "blur"
                        }
                    ],
                    department: [
                        {
                            required: true,
                            message: "请输入部门",
                            trigger: "blur"
                        }
                    ],
                    contactInformation: [
                        {
                            required: true,
                            message: "请输入联系方式",
                            trigger: "blur"
                        }
                    ],
                    reason: [
                        {
                            required: true,
                            message: "请输入注册原因",
                            trigger: "blur"
                        }
                    ],
                    verificationCode: [
                        {
                            validator: validateVerificationCode,
                            trigger: "blur"
                        }
                    ]
                },
                gverify: {},
                registerReasons: [
                    {
                        id: 1,
                        name: "提供模型"
                    },
                    {
                        id: 2,
                        name: "使用模型"
                    },
                    {
                        id: 3,
                        name: "随便看看"
                    }
                ]
            };
        },
        mounted() {
            this.gverify = new Gverify(this.$refs.verifyCode);
        },
        methods: {
            doRegister(formName) {
                var vm = this;
                this.$refs[formName].validate(valid => {
                    if (valid) {
                        vm.user.passwordAndEncode = vm.user.password
                        vm.$axios.post("/businessuser/register", vm.user)
                            .then(data => {
                                vm.$alert("注册成功，请登录", "tips");
                            })
                            .catch(error => {
                                vm.$alert(error.message, "tips");
                            })
                    }
                });
            }
        }
    };
</script>
<style scoped>
    .body {
        width: 100%;
        height: 100%;
        background: url(../../assets/img/login-bg-1.2.jpg) no-repeat;
        background-size: 100%;
    }

    label {
        color: aliceblue !important;
    }
</style>
