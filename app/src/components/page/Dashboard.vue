<template>
    <div>
        <el-row :gutter="20">

            <el-col :span="24">
                <el-row :gutter="20" class="mgb20">
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-1">
                                <i class="el-icon-lx-people grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div>服务</div>
                                    <div class="grid-num">{{servicecount}}</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-2">
                                <i class="el-icon-lx-notice grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div>节点</div>
                                    <div class="grid-num">{{nodecount}}</div>

                                </div>
                            </div>
                        </el-card>
                    </el-col>
                    <el-col :span="8">
                        <el-card shadow="hover" :body-style="{padding: '0px'}">
                            <div class="grid-content grid-con-3">
                                <i class="el-icon-lx-goods grid-con-icon"></i>
                                <div class="grid-cont-right">
                                    <div>上线时间</div>
                                    <div class="grid-num">{{uptime}}</div>
                                </div>
                            </div>
                        </el-card>
                    </el-col>
                </el-row>
            </el-col>
        </el-row>
        <el-row :gutter="20">
            <el-col :span="12">
                <el-card shadow="hover">
                    <schart ref="bar" class="schart" canvasId="bar" :data="bardata" type="bar"
                            :options="options"></schart>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card shadow="hover">
                    <schart ref="pie" class="schart" canvasId="pie" :data="piedata" type="pie"
                            :options="options2"></schart>
                </el-card>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    import Schart from 'vue-schart';
    import bus from '../common/bus';

    export default {
        name: 'dashboard',
        data() {
            return {
                servicecount: '0',
                nodecount: '0',
                uptime: '',
                name: localStorage.getItem('ms_username'),


                bardata: [{
                    name: '服务1',
                    value: 10
                },
                    {
                        name: '服务2',
                        value: 20
                    },
                    {
                        name: '服务3',
                        value: 30
                    },
                    {
                        name: '服务4',
                        value: 40
                    }, {
                        name: '服务5',
                        value: 50
                    }

                ],
                piedata: [{
                    name: '已用节点',
                    value: 40
                },
                    {
                        name: '未用节点',
                        value: 60
                    }

                ],
                options: {
                    title: '服务统计',
                    showValue: false,
                    fillColor: 'rgb(45, 140, 240)',
                    bottomPadding: 30,
                    topPadding: 30
                },
                options2: {
                    title: '节点统计',
                    // fillColor: '#FC6FA1',
                    // axisColor: '#008ACD',
                    // contentColor: '#EEEEEE',
                    // bgColor: '#F5F8FD',
                    bottomPadding: 30,
                    topPadding: 30
                }
            }
        },
        components: {
            Schart
        },
        computed: {
            role() {
                return this.name === 'admin' ? '超级管理员' : '普通用户';
            }
        },
        created() {
            this.handleListener();
            // this.changeDate();
        },
        activated() {
            this.handleListener();
        },
        deactivated() {
            window.removeEventListener('resize', this.renderChart);
            bus.$off('collapse', this.handleBus);
        },
        methods: {
            changeDate() {
                const now = new Date().getTime();
                this.data.forEach((item, index) => {
                    const date = new Date(now - (6 - index) * 86400000);
                    item.name = `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`
                })
            },
            handleListener() {
                bus.$on('collapse', this.handleBus);
                // 调用renderChart方法对图表进行重新渲染
                window.addEventListener('resize', this.renderChart)
            },
            handleBus(msg) {
                setTimeout(() => {
                    this.renderChart()
                }, 300);
            },
            renderChart() {
                this.$refs.bar.renderChart();
                this.$refs.pie.renderChart();
            },
            service() {
                var vm = this;
                vm.$axios
                    .post("/v1/service")
                    .then(data => {
                        vm.servicecount = data;
                    })
                    .catch(e => {

                    });

            },
            nodes() {
                var vm = this;
                vm.$axios
                    .post("/v1/nodes")
                    .then(data => {
                        vm.nodecount = data.length;
                    })
                    .catch(
                        e => {

                        });
            },
            launchtime() {
                var vm = this;

                //日期格式化
                Date.prototype.Format = function (fmt) {
                    var o = {
                        "M+": this.getMonth() + 1,
                        "d+": this.getDate(),
                        "h+": this.getHours(),
                        "m+": this.getMinutes(),
                        "s+": this.getSeconds(),
                        "q+": Math.floor((this.getMonth() + 3) / 3),
                        "S": this.getMilliseconds()
                    };
                    if (/(y+)/.test(fmt))
                        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
                    for (var k in o)
                        if (new RegExp("(" + k + ")").test(fmt))
                            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                    return fmt;
                };
                vm.uptime = new Date(localStorage.getItem("launchtime")).Format("yyyy-MM-dd hh:mm:ss");
            }
        },
        mounted() {
            this.service();
            this.nodes();
            this.launchtime();
        }
    }

</script>


<style scoped>
    .el-row {
        margin-bottom: 20px;
    }

    .grid-content {
        display: flex;
        align-items: center;
        height: 100px;
    }

    .grid-cont-right {
        flex: 1;
        text-align: center;
        font-size: 14px;
        color: #999;
    }

    .grid-num {
        font-size: 15px;
        font-weight: bold;
    }

    .grid-con-icon {
        font-size: 50px;
        width: 100px;
        height: 100px;
        text-align: center;
        line-height: 100px;
        color: #fff;
    }

    .grid-con-1 .grid-con-icon {
        background: rgb(45, 140, 240);
    }

    .grid-con-1 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-2 .grid-con-icon {
        background: rgb(100, 213, 114);
    }

    .grid-con-2 .grid-num {
        color: rgb(45, 140, 240);
    }

    .grid-con-3 .grid-con-icon {
        background: rgb(242, 94, 67);
    }

    .grid-con-3 .grid-num {
        color: rgb(242, 94, 67);
    }

    .user-info {
        display: flex;
        align-items: center;
        padding-bottom: 20px;
        border-bottom: 2px solid #ccc;
        margin-bottom: 20px;
    }

    .user-avator {
        width: 120px;
        height: 120px;
        border-radius: 50%;
    }

    .user-info-cont {
        padding-left: 50px;
        flex: 1;
        font-size: 14px;
        color: #999;
    }

    .user-info-cont div:first-child {
        font-size: 30px;
        color: #222;
    }

    .user-info-list {
        font-size: 14px;
        color: #999;
        line-height: 25px;
    }

    .user-info-list span {
        margin-left: 70px;
    }

    .mgb20 {
        margin-bottom: 20px;
    }

    .todo-item {
        font-size: 14px;
    }

    .todo-item-del {
        text-decoration: line-through;
        color: #999;
    }

    .schart {
        width: 100%;
        height: 300px;
    }

</style>
