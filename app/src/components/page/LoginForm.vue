<template>
    <div class="body">
        <div class="bg1"></div>
        <div class="gyl">服务综合管理系统
            <div class="gy2">打造资源、模型一键管理平台</div>
        </div>
        <div class="bg">
            <form>
                <div class="wel">用户登录</div>
                <div class="user">
                    <div class="login-label">用户名</div>
                    <input type="text" v-model="ruleForm.name">
                </div>
                <div class="password">
                    <div class="login-label">密&nbsp;&nbsp;&nbsp;码</div>
                    <input type="password" v-model="ruleForm.password" @keydown.enter="submitForm">
                </div>
                <div class="rem">
                    <el-checkbox v-model="remeberMe">记住我</el-checkbox>
                </div>
                <div class="fg">
                    <el-button type="text" size="mini">忘记密码？</el-button>
                </div>
                <div class="login-btn">
                    <input class="btn" type="button" name="登录" value="登录" @click="submitForm">
                </div>
                <div class="register-btn">
                    <input class="btn" type="button" name="注册" value="注册" @click="goRegister">
                </div>
            </form>
        </div>
    </div>
</template>
<script>
    export default {
        data: function () {
            return {
                remeberMe: false,
                ruleForm: {
                    name: "admin",
                    password: "admin"
                },
                rules: {
                    name: [{required: true, message: "请输入用户名", trigger: "blur"}],
                    password: [{required: true, message: "请输入密码", trigger: "blur"}]
                }
            };
        },
        methods: {

            submitForm() {
                var vm = this;
                if (!vm.ruleForm.name || vm.ruleForm.name.length < 1) {
                    vm.$alert("name can not blank !!!", "tips");
                } else if (!vm.ruleForm.password || vm.ruleForm.password.length < 1) {
                    vm.$alert("password can not blank !!!", "tips");
                } else {
                    vm.$axios
                        .post("/all/login", vm.ruleForm)
                        .then(data => {
                            var indexPath = data.index;


                            var launchtime = new Date();

                            localStorage.setItem("JWT_TOKEN", data.token);
                            localStorage.setItem("role_layout", data.layout);
                            localStorage.setItem("ms_username", vm.ruleForm.name);
                            localStorage.setItem("ms_user_id", data.userId);
                            localStorage.setItem("launchtime", launchtime);
                            if (indexPath && indexPath.length > 0) {
                                vm.$router.push(indexPath);
                            } else {
                                vm.$router.push("/home");
                            }
                        })
                        .catch(e => {
                            vm.$alert("login failure !!!", "tips");
                        });
                }
            },
            goRegister() {
                this.$router.push({path: "/register"});
            }
        }
    };
</script>
<style scoped>
    * {
        font-family: "微软雅黑";
        font-size: 16px;
        border: 0;
        padding: 0;
        margin: 0;
        color: #666;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        -webkit-box-sizing: border-box;
    }

    .body {
        width: 100%;
        height: 100%;
        background: url(../../assets/img/login-bg-1.2.jpg) no-repeat;
        background-size: 100%;
    }

    .bg1 {
        width: 100%;
        height: 237px;
        background: #347ebc;
        opacity: 0.6;
        position: absolute;
        left: 0;
        top: 0px;
        right: 0px;
        bottom: 0px;
        margin: auto;
    }

    .gyl {
        width: 700px;
        height: 237px;
        color: #ffffff;
        font-size: 60px;
        position: absolute;
        left: 15%;
        top: 11%;

        bottom: 0px;
        margin: auto;
    }

    .gy2 {
        color: #fff;
        margin-left: 6px;
        font-size: 18px;
        text-align: center;
        margin-top: 10px;
    }

    .bg {
        width: 375px;
        height: 387px;
        background: #fff;
        position: relative;
        position: absolute;
        left: 56%;
        top: 0px;
        right: 0px;
        bottom: 0px;
        margin: auto;
        z-index: 1;
        box-shadow: 0px 0px 50px rgba(0, 0, 0, 0.4);
        border-radius: 5px;
    }

    .password {
        position: absolute;
        top: 9.5rem;
        right: 2.5rem;
        display: flex;
    }

    .btn {
        border: none;
        color: #fff;
        width: 18.825rem;
        text-align: center;
        background-color: #347ebc;
        text-indent: 0rem;
    }

    .img {
        width: 100%;
        position: absolute;
        left: 0;
        right: 0;
        margin: 0 auto;
        z-index: -1;
    }

    .wel {
        width: 375px;
        height: 35px;
        color: #347ebc;
        font-size: 1.5rem;
        position: absolute;
        top: 2rem;
        text-align: center;
    }

    input {
        border-bottom: 0.0625rem solid #347ebc;
        height: 2.5625rem;
        width: 15.8125rem;
        text-indent: 3.125rem;
        outline: none;
    }

    .user {
        position: absolute;
        top: 6rem;
        right: 2.5rem;
        display: flex;
    }

    .rem {
        position: absolute;
        top: 12.5rem;
        right: 17.2rem;
        border: none;
        color: #323333;
    }

    .rem input {
        width: 0.8125rem;
    }

    .fg {
        position: absolute;
        top: 12.5rem;
        right: 2.5rem;
        border: none;
        color: #323333;
    }

    .login-label {
        border-bottom: 1px solid #347ebc;
        height: 41px;
        line-height: 41px;
    }

    .remember {
        font-size: 11px;
    }

    .login-btn {
        position: absolute;
        top: 15.2rem;
        right: 2.5rem;
    }

    .register-btn {
        position: absolute;
        top: 19rem;
        right: 2.5rem;
    }

    @media (min-width: 1201px) and (max-width: 1500px) {
        .gyl {
            width: 700px;
            font-size: 60px;
            top: 16%;
        }
    }

    @media (min-width: 800px) and (max-width: 1200px) {
        .bg {
            width: 355px;
            height: 300px;
        }

        .gyl {
            font-size: 50px;
        }

        .wel {
            width: 355px;
        }

        .user {
            top: 5rem;
            right: 3.5rem;
        }

        .password {
            top: 8rem;
            right: 3.5rem;
        }

        input {
            width: 12rem;
        }

        .rem {
            top: 11rem;
            right: 14.3rem;
        }

        .fg {
            top: 11rem;
            right: 3.5rem;
        }

        .btn {
            top: 14rem;
            width: 15rem;
        }
    }
</style>
