<template>
    <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
        <el-form-item>
            <div style="margin-bottom: 15px;">
                <el-input v-model="filename" class="input-with-select">
                    <el-button slot="append" type="primary">搜索</el-button>
                </el-input>
            </div>
        </el-form-item>
        <el-form-item>
            <el-row>
                部署状态:{{step1}}>{{step2}}>{{step3}}
                <el-button type="primary">部署</el-button>
            </el-row>
        </el-form-item>

        <el-form-item>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="date" label="节点名称" width="180"></el-table-column>
                <el-table-column prop="name" label="IP" width="180"></el-table-column>
                <el-table-column prop="address" label="实例数"></el-table-column>
            </el-table>
        </el-form-item>
    </el-form>
</template>

<script>
    export default {
        data() {
            return {
                step1: "未部署",
                step2: "部署中",
                step3: "已部署",
                filename: [],
                labelPosition: "right",
                formLabelAlign: {
                    name: "",
                    region: "",
                    type: ""
                },
                tableData: [
                    {
                        date: "2016-05-02",
                        name: "192.23.232.32",
                        address: "129"
                    },
                    {
                        date: "2016-05-02",
                        name: "192.23.232.32",
                        address: "129"
                    },
                    {
                        date: "2016-05-02",
                        name: "192.23.232.32",
                        address: "129"
                    },
                    {
                        date: "2016-05-02",
                        name: "192.23.232.32",
                        address: "129"
                    }
                ]
            };
        }
    };
</script>
<style scoped>
    .el-button {
        float: right;
    }
</style>
