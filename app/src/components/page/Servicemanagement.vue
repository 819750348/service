<template>
    <div>
        <el-container>
            <el-aside width="200px">
                <div class="sidebar">
                    <el-menu>
                        <el-menu-item>
                            <el-button style="float: right; padding: 25px " type="text" @click="createLabel">+新建标签
                            </el-button>
                        </el-menu-item>
                    </el-menu>
                    <el-tree
                        :data="labels"
                        node-key="id"
                        default-expand-all
                        :expand-on-click-node="false"
                        @node-click="selectLabel">
                       <span class="custom-tree-node" slot-scope="{ labelw, data }">
                          <span>{{data.name}}</span>
                        </span>
                    </el-tree>
                </div>
            </el-aside>
            <el-main>
                <el-form>
                    <el-form-item>
                        <el-row type="flex">
                            <el-col :span="6" offset="6">
                                <el-radio-group>
                                    <el-input v-model="searchKeys" placeholder="搜索你想要的服务" style="width: 350px"
                                              size="medium">
                                        <el-button slot="append" @click="search">搜索</el-button>
                                    </el-input>
                                </el-radio-group>
                            </el-col>
                        </el-row>
                    </el-form-item>
                    <el-form-item>
                        <el-table :data="models" :show-header="false" :fit="true">
                            <el-table-column min-width="20px">
                                <template slot-scope="scope">
                                    <a href="#">
                                        <img src="static\img\img.jpg" width="80px" height="80px">
                                    </a>
                                </template>
                            </el-table-column>
                            <el-table-column min-width="140px">
                                <template slot-scope="scope">
                                    <div>
                                        <div class="Biaoti">{{scope.row.name}}</div>
                                        <div class="Status">{{scope.row.designerStatus}}</div>
                                    </div>
                                    <div>
                                        <el-row class="devp">

                                            <el-button size="mini" @click="devService(scope.row)">开发</el-button>

                                            <el-button size="mini" type="primary">提交</el-button>
                                            <el-button size="mini" @click="deleteService(scope.row)" type="danger">删除
                                            </el-button>
                                        </el-row>
                                    </div>
                                    <p class="text">
                                        <a href="#">{{scope.row.description}}</a>
                                    </p>
                                    <div class="can_div">
                                        <div class="can_left">
                                            标签：
                                            <span v-if="scope.row.serviceLabel">
                        <h4
                            v-for="tag in scope.row.serviceLabel.split(',')"
                            class="biaoshi"
                            :key="tag"
                        >{{tag}}</h4>
                      </span>
                                        </div>
                                    </div>

                                    <div
                                        class="subit"
                                    >上传者：{{scope.row.designer}} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        发布时间：{{scope.row.createTime.substring(0,10)}}
                                    </div>
                                </template>
                            </el-table-column>
                        </el-table>
                        <div>
                            <el-pagination
                                @size-change="handleSizeChange"
                                @current-change="handleCurrentChange"
                                :current-page="currentPage4"
                                :page-sizes="[10, 20, 50, 100]"
                                :page-size="10"
                                layout="total, sizes, prev, pager, next, jumper"
                                :total="100"
                            ></el-pagination>
                        </div>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>
<script>
    export default {
        name: "servicedevelopment",
        data() {
            const data = [{
                id: 1,
                label: '一级 1',
                children: [{
                    id: 4,
                    label: '二级 1-1',
                    children: [{
                        id: 9,
                        label: '三级 1-1-1'
                    }, {
                        id: 10,
                        label: '三级 1-1-2'
                    }]
                }]
            }, {
                id: 2,
                label: '一级 2',
                children: [{
                    id: 5,
                    label: '二级 2-1'
                }, {
                    id: 6,
                    label: '二级 2-2'
                }]
            }, {
                id: 3,
                label: '一级 3',
                children: [{
                    id: 7,
                    label: '二级 3-1'
                }, {
                    id: 8,
                    label: '二级 3-2'
                }]
            }];

            return {
                data5: JSON.parse(JSON.stringify(data)),
                labelid: "",
                keys: "",
                pageSize: 20,
                models: [],
                collapse: false,
                categoryId: "",
                labels: "",
                size: "",
                searchKeys: "",
                searchKey: "",
                searchCategory: "0",
                total: 123,
                pageSize: 15
            };
        },
        props: {
            categorys: {
                type: Array,
                default: []
            }
        },
        created() {
        },
        methods: {
            getModelList(pageNum) {
                var vm = this;
                var categoryId = vm.categoryId;
                vm.$axios
                    .post("/all/getServiceModelListAll?pageNum=" +
                        pageNum +
                        "&pageSize=" +
                        this.pageSize,
                        {keys: this.keys}
                    )
                    .then(data => {

                        vm.models = [].concat(data.list);

                    })
                    .catch(e => {
                        // vm.$alert("login failure !!!", "tips");
                    });
            },
            createLabel() {
                var vm = this;
                this.$prompt("新建标签").then(({value}) => {
                    var label = {name: value};
                    vm.$axios.post("/all/addServiceLabel", label).then(data => {
                        vm.labels.push(data);
                    });
                });

            },
            fetchLabels() {
                var vm = this;
                this.$axios.get("/all/getServiceLabels").then(data => {
                    vm.labels = data;
                });
            },
            selectLabel(nodeobj, node, nodecomp) {
                this.labelid = nodeobj.id;
                this.fetchData(1, nodeobj, node, nodecomp);
            },
            fetchData(pageNum, nodeobj, node, nodecomp) {
                var vm = this;
                this.$axios
                    .post(
                        "/all/getLabelServiceUser?pageNum=" +
                        pageNum +
                        "&pageSize=" +
                        this.size,
                        {labelid: nodeobj.id, keys: this.searchKey}
                    )
                    .then(data => {
                        vm.models = [].concat(data.list);
                    });
            },
            addService() {
                this.$router.push({path: '/detail', query: {labelid: this.labelid, method: "add"}});
            },
            search() {
                var vm = this;
                var searchKeys = vm.searchKeys;
                this.$axios.post("all/searchServicekeys", {searchKeys: searchKeys})
                    .then(data => {
                        vm.models = [].concat(data.list);
                    })
            },
            update(rowService) {
                var vm = this;

                this.$router.push({path: '/detail', query: {rowService: rowService, method: "update"}});
            },
            deleteService(rowService) {
                var vm = this;
                vm.$axios
                    .post("/all/deleteService", rowService
                    )
                    .then(data => {
                        vm.models = [].concat(data.list);
                    })
                    .catch(e => {
                        // vm.$alert("login failure !!!", "tips");
                    });
            },
            devService(rowService) {

                this.$router.push({path: '/devdetail', query: {rowService: rowService, method: "devmethod"}});
            },
            getParams() {
                //获取明细传过来数据
                var vm = this;
                if (this.$route.query.method == "onsubmit") {

                    vm.onSubmit(vm.$route.query.allServiceModel);
                }
            },
            onSubmit(data) {
                this.models = [].concat(data.list);
            }
        },
        mounted() {
            this.getModelList(1);
            this.fetchLabels();
        },
        watch: {
            '$route': 'getParams'
        },
        created: function () {
            this.getParams();
        }
    };
</script>

<style scoped>
    .m_left {
        width: 140px;
        height: 140px;
        float: left;
        margin-right: 15px;
    }

    .m_right {
        float: left;
        width: 520px;
    }

    .Biaoti {
        float: left;
        font-size: 18px;
        font-weight: bold;
    }

    .Status {
        float: left;
        font-size: 10px;
        color: #fe8a2d;
        margin-left: 5px;
    }

    .canshu {
        float: right;
        color: #999;
        font-size: 12px;
    }

    .canshu a:hover {
        color: #fe8a2d;
    }

    .canshu a {
        color: #999;
        text-decoration: none;
    }

    .text {
        width: 100%;
        display: block;
        float: left;
        height: 38px;
        line-height: 38px;
    }

    .text a {
        text-decoration: none;
    }

    .text a:hover {
        text-decoration: underline;
    }

    .can_div {
        width: 100%;
        display: block;
        float: left;
        height: 28px;
        line-height: 28px;
    }

    .can_left {
        float: left;
        color: #999999;
        font-size: 14px;
    }

    .can_right {
        float: right;
        color: #999999;
        font-size: 14px;
    }

    .biaoshi {
        background: #f0f0f0;
        display: inline;
        padding: 3px 10px;
        font-size: 12px;
        font-weight: normal;
        color: #999999;
    }

    .subit {
        width: 100%;
        color: #999;
        display: block;
        font-size: 14px;
        height: 28px;
        line-height: 28px;
        float: left;
    }

    .line {
        background: #f0f0f0;
        height: 1px;
        width: 100%;
        float: left;
        margin-top: 15px;
        margin-bottom: 25px;
    }

    .el-pagination {
        text-align: center;
    }

    .sidebar {
        margin-top: 71px;
    }

    .add {
        float: right;
    }

    .m_left {
        width: 140px;
        height: 140px;
        float: left;
        margin-right: 15px;
    }

    .m_right {
        float: left;
        width: 520px;
    }

    .Biaoti {
        float: left;
        font-size: 18px;
        font-weight: bold;
    }

    .Status {
        float: left;
        font-size: 10px;
        color: #fe8a2d;
        margin-left: 5px;
    }

    .devp {
        float: right;
        color: #999;
        font-size: 12px;
    }

    .canshu {
        float: right;
        margin-right: 8px;
        color: #999;
        font-size: 12px;
    }

    .canshu a:hover {
        color: #fe8a2d;
    }

    .canshu a {
        color: #999;
        text-decoration: none;
    }

    .text {
        width: 100%;
        display: block;
        float: left;
        height: 38px;
        line-height: 38px;
    }

    .text a {
        text-decoration: none;
    }

    .text a:hover {
        text-decoration: underline;
    }

    .can_div {
        width: 100%;
        display: block;
        float: left;
        height: 28px;
        line-height: 28px;
    }

    .can_left {
        float: left;
        color: #999999;
        font-size: 14px;
    }

    .can_right {
        float: right;
        color: #999999;
        font-size: 14px;
    }

    .biaoshi {
        background: #f0f0f0;
        display: inline;
        padding: 3px 10px;
        font-size: 12px;
        font-weight: normal;
        color: #999999;
    }

    .subit {
        width: 100%;
        color: #999;
        display: block;
        font-size: 14px;
        height: 28px;
        line-height: 28px;
        float: left;
    }

    .line {
        background: #f0f0f0;
        height: 1px;
        width: 100%;
        float: left;
        margin-top: 15px;
        margin-bottom: 25px;
    }

    .el-pagination {
        text-align: center;
    }

    .sidebar {
        margin-top: 71px;
    }
</style>
