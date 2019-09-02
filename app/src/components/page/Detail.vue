<template>
    <div>
        <div style="margin: 20px;"></div>
        <el-form :label-position="labelPosition" label-width="80px" :model="formcontent">
            <el-form-item label="名称" label-width="120px">
                <el-input v-model="formcontent.name"></el-input>
            </el-form-item>
            <el-form-item label="标签" label-width="120px">

                <el-tag v-for="(value, key) in labels" :key="key">{{value}}</el-tag>

                <el-button @click="tjtag">+</el-button>
            </el-form-item>
            <el-form-item label="设计人员" label-width="120px">
                <el-input v-model="formcontent.designer"></el-input>
            </el-form-item>
            <el-form-item label="单位" label-width="120px">
                <el-input v-model="formcontent.designerDepartment"></el-input>
            </el-form-item>
            <el-form-item label="联系方式" label-width="120px">
                <el-input v-model="formcontent.contactInformation"></el-input>
            </el-form-item>

            <el-form-item label="描述" label-width="120px">
                <el-input v-model="formcontent.description" type="textarea"></el-input>
            </el-form-item>
            <el-form-item label="接口描述文件" label-width="120px">
                <div style="margin-bottom: 15px;">
                    <el-input v-model="formcontent.interfaceDescriptionFileId" class="input-with-select">
                        <el-button slot="append" type="primary">浏览</el-button>
                    </el-input>
                </div>
            </el-form-item>
            <el-form-item label="开发人员" label-width="120px">

                <el-select v-model="formcontent.developer" placeholder="请选择" style="width:100%;">
                    <el-option
                        v-for="item in options"
                        :key="item.value"
                        :label="item.label"
                        :value="item.value"
                    ></el-option>
                </el-select>
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
    </div>
</template>
<script>
    export default {
        data() {
            return {
                labels: [],//标签数组
                options: [
                    {
                        value: "选项1",
                        label: "于文雅"
                    },
                    {
                        value: "选项2",
                        label: "周静"
                    },
                    {
                        value: "选项3",
                        label: "陈皮皮"
                    }

                ],
                filename: [],
                labelPosition: "right",
                formcontent: {
                    id: "",
                    name: "",
                    categoryId: "",
                    serviceLabel: "",
                    designer: "", //设计人员
                    designerDepartment: "",//设计单位
                    contactInformation: "",//联系方式
                    description: "", //描述
                    interfaceDescriptionFileId: "",//接口描述文件id
                    developer: "" //开发人员
                }
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
                    .post("/all/addorupdateservice", form
                    )
                    .then(data => {
                        this.$router.push({
                            path: '/serviceregistration',
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
                            path: '/serviceregistration',
                            query: {allServiceModel: data, method: "onsubmit"}
                        });
                    })
                    .catch(e => {
                        // vm.$alert("login failure !!!", "tips");
                    });
            },
            getParams: function () {
//添加方法
                if (this.$route.query.method == "add") {
                    // 取到路由带过来的参数
                    var routerParams = this.$route.query.labelid;
                    // 将数据放在当前组件的数据内
                    this.formcontent.categoryId = routerParams;
                }

//   修改方法
                if (this.$route.query.method == "update") {
                    var rowService = this.$route.query.rowService;
                    this.updateService(rowService);
                }

            },
            updateService(rowService) {
                this.formcontent.id = rowService.id;
                this.formcontent.name = rowService.name;
                this.formcontent.categoryId = rowService.categoryId;

//       标签转换
                var arrayl = rowService.serviceLabel.split(",");
                for (var arrayindex  in arrayl) {
                    this.labels.push(arrayl[arrayindex]);
                }

                this.formcontent.serviceLabel = this.labels.join(",");

                this.formcontent.designer = rowService.designer;
                this.formcontent.designerDepartment = rowService.designerDepartment;
                this.formcontent.contactInformation = rowService.contactInformation;
                this.formcontent.description = rowService.description;
                this.formcontent.interfaceDescriptionFileId = rowService.interfaceDescriptionFileId;
                this.formcontent.developer = rowService.developer;
            },
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
