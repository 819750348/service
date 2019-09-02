<template>
    <el-form :label-position="labelPosition" label-width="80px" :model="formcontent">
        <el-form-item label="服务名称" label-width="120px">
            <el-input v-model="formcontent.name"></el-input>
        </el-form-item>
        <el-form-item label="标签" label-width="120px">

            <el-tag v-for="(value, key) in labels" :key="key">{{value}}</el-tag>

            <el-button @click="tjtag">+</el-button>
        </el-form-item>
        <el-form-item label="服务开发人员" label-width="120px">
            <el-input v-model="formcontent.developer"></el-input>
        </el-form-item>
        <el-form-item>
            <el-button type="primary">服务调用代码框架</el-button>
            <el-button type="primary">服务实现代码框架</el-button>
        </el-form-item>
        <el-form-item label="服务实现" label-width="120px">
            <div style="margin-bottom: 15px;">
                <el-input v-model="formcontent.serviceImplementationFileId" class="input-with-select">
                    <el-button slot="append" type="primary">浏览</el-button>
                </el-input>
            </div>
        </el-form-item>
        <el-form-item label="用户文档" label-width="120px">
            <div style="margin-bottom: 15px;">
                <el-input v-model="formcontent.userManualFileId" class="input-with-select">
                    <el-button slot="append" type="primary">浏览</el-button>
                </el-input>
            </div>
        </el-form-item>
        <el-form-item label="使用案列" label-width="120px">
            <div style="margin-bottom: 15px;">
                <el-input v-model="formcontent.demoFileId" class="input-with-select">
                    <el-button slot="append" type="primary">浏览</el-button>
                </el-input>
            </div>
        </el-form-item>
        <el-form-item label="最小实例数" label-width="120px">
            <el-input v-model="formcontent.minInstance"></el-input>
        </el-form-item>
        <el-form-item label="最大实例数" label-width="120px">
            <el-input v-model="formcontent.maxInstance"></el-input>
        </el-form-item>
        <el-row type="flex" justify="center">
            <el-col :span="6">
                <el-form-item>
                    <el-button type="primary" @click="onSubmit">提交</el-button>
                    <el-button @click="onSave">保存</el-button>
                </el-form-item>
            </el-col>
        </el-row>
    </el-form>
</template>
<script>
    export default {
        data() {
            return {
                labels: [],
                labelPosition: "right",
                formcontent: {
                    id: "",
                    name: "",
                    categoryId: "",
                    serviceLabel: "",
                    developer: "", //开发人员
                    serviceImplementationFileId: "",//服务实现ID
                    userManualFileId: "",//用户文档id
                    demoFileId: "", //使用案例id
                    minInstance: "",//最小实例数
                    maxInstance: "" //最大实例数
                },
            };
        },
        methods: {
            tjtag() {
                this.$prompt('请输入标签名称', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消'
                }).then(({value}) => {
                    this.$message({
                        type: 'success',
                        message: '添加成功'
                    });
                    this.labels.push(value),
                        this.formcontent.serviceLabel = this.labels.join(",")
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消'
                    });
                });

            },
            onSubmit() {
                var vm = this;
                var form = vm.formcontent;
                vm.$axios
                    .post("/all/devservice", form
                    )
                    .then(data => {
                        this.$router.push({
                            path: '/servicedevelopment',
                            query: {allServiceModel: data, method: "onsubmit"}
                        });
                    })
                    .catch(e => {
                        // vm.$alert("login failure !!!", "tips");
                    });
            },
            onSave() {
                var vm = this;
                var form = vm.formcontent;
                vm.$axios
                    .post("/all/saveaddorupdateservice", form
                    )
                    .then(data => {
                        this.$router.push({
                            path: '/servicedevelopment',
                            query: {allServiceModel: data, method: "onsubmit"}
                        });
                    })
                    .catch(e => {
                        // vm.$alert("login failure !!!", "tips");
                    });
            },
            getParams: function () {

//   开发按钮路由传参
                if (this.$route.query.method == "devmethod") {
                    var rowService = this.$route.query.rowService;
                    this.devmethod(rowService);
                }

            },
            devmethod(data) {
                this.formcontent.name = data.name;
                this.formcontent.serviceLabel = data.serviceLabel;
                this.labels = [].concat(data.serviceLabel.split(","));
                this.formcontent.id = data.id;

            }
        },
        watch: {
            // 监测路由变化,只要变化了就调用获取路由参数方法将数据存储本组件即可
            '$route': 'getParams'
        },
        created: function () {
            this.getParams();
        }
    };
</script>
