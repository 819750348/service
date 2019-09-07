<template>
    <div>
        <el-container>
            <el-main>
                <el-form>
                    <el-form-item>
                        <div style="margin-bottom: 15px;">
                            <el-input placeholder="可根据主机编号、主机名、IP搜索" v-model="keyword"  style="width: 350px" size="medium">
                                <el-button slot="append" type="primary" @click="selectKeyword">搜索</el-button>
                            </el-input>
                        </div>
                        <el-table :data="nodes" style="width: 100%">
                            <el-table-column prop="id" label="节点编号" width="120"></el-table-column>
                            <el-table-column prop="host_name" label="主机名" width="180"></el-table-column>
                            <el-table-column prop="ip" label="IP"></el-table-column>
                            <el-table-column prop="deployed_services" label="部署服务数"></el-table-column>
                            <el-table-column prop="running_services" label="运行服务数"></el-table-column>
                            <el-table-column prop="memory_size" label="内存占用率">
                                <template slot-scope="scope">
                                    <el-progress :percentage="scope.row.memory_size"></el-progress>
                                </template>
                            </el-table-column>

                            <el-table-column prop="address" label="CPU占用率">
                                <template slot-scope="scope">
                                    <el-progress :percentage="scope.row.cpu_frequency"></el-progress>
                                </template>
                            </el-table-column>
                        </el-table>
                        <div class="block">
                            <el-pagination
                                :page-sizes="[10, 20, 50, 100]"
                                :page-size="pageSize"
                                layout="total, sizes, prev, pager, next, jumper"
                                :total="total"
                                background
                                :pager-count="11"
                                @size-change="changePage"
                                @current-change="currentChange"
                            ></el-pagination>
                        </div>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>
<style scoped>
    .el-input {
        width: 300px;
        left: 40%;
    }
    .el-pagination {
        text-align: center;
    }
</style>
<script>
    export default {
        data() {
            return {
                keyword: "",
                pageSize: 10,
                start: 1,
                nodes: [],
                total: "",
            };
        },
        methods: {
            selectNodes() {
                var vm = this;
                vm.$axios
                    .post("/v1/nodes?start=" + vm.start + "&pageSize=" + vm.pageSize)
                    .then(data => {
                        vm.total = data.length;
                        vm.nodes = [].concat(data.slice(0, 10));
                    })
                    .catch(e => {
                    });
            },
            selectKeyword() {
                var vm = this;
                var keyword = vm.keyword;
                vm.$axios
                    .post("/v1/selectKeyword?keyword=" + keyword)
                    .then(data => {

                        vm.nodes = [].concat(data);
                        vm.total = vm.nodes.length;
                    })
                    .catch(e => {

                    });
            },
            changePage(param) {
                var vm = this;

                vm.pageSize = param;
                vm.$axios
                    .post("/v1/changePage?start=" + vm.start + "&pageSize=" + vm.pageSize + "&keys=" + vm.keyword)
                    .then(data => {
                            vm.nodes = [].concat(data);
                        }
                    )
                    .catch(e => {

                    })

            },
            currentChange(start) {
                var vm = this;
                vm.$axios
                    .post("/v1/changePage?start=" + start + "&pageSize=" + vm.pageSize + "&keys=" + vm.keyword)
                    .then(data => {
                            vm.nodes = [].concat(data);
                        }
                    )
                    .catch(e => {

                    })

            }

        },
        mounted() {
            this.selectNodes();
        },
        computed: {
            cpuFrequency() {
                var vm = this;

            }
        }
    };
</script>
