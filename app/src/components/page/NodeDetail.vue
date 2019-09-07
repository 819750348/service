<template>
    <el-container>
        <el-main>
            <el-row>
                <el-col :offset="index=0" :span="8">
                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                            <span>静态信息</span>
                        </div>
                        <div
                            class="text item"
                        >主机名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{"TK-123kGbs"
                            }}
                        </div>
                        <div
                            class="text item"
                        >IP&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{"192.168.10.12"}}
                        </div>
                        <div
                            class="text item"
                        >CPU数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{'12 '}}
                        </div>
                        <div
                            class="text item"
                        >线程数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{'1283'}}
                        </div>
                        <div
                            class="text item"
                        >CPU主频&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{'数据 '}}
                        </div>
                        <div
                            class="text item"
                        >内存&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{'200G'}}
                        </div>
                        <div
                            class="text item"
                        >硬盘&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{'4MB'}}
                        </div>
                    </el-card>
                </el-col>
                <el-col :offset="index=1" :span="8">
                    <el-card class="box-card dynamic">
                        <div slot="header" class="clearfix">
                            <span>动态信息</span>
                        </div>
                        <el-row>
                            <el-col :offset="index=0" :span="8">
                                <div class="text item">CPU占用率</div>
                            </el-col>
                            <el-col :offset="index=3" :span="12">
                                <el-progress :percentage="70"></el-progress>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :offset="index=0" :span="8">
                                <div class="text item">已用内存</div>
                            </el-col>
                            <el-col :offset="index=3" :span="12">
                                <el-progress :percentage="50"></el-progress>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :offset="index=0" :span="8">
                                <div class="text item">磁盘使用</div>
                            </el-col>
                            <el-col :offset="index=3" :span="12">
                                <el-progress :percentage="70"></el-progress>
                            </el-col>
                        </el-row>
                        <el-row>
                            <el-col :offset="index=0" :span="8">
                                <div class="text item">调用次数</div>
                            </el-col>
                            <el-col :offset="index=3" :span="12">{{callCount}}</el-col>
                        </el-row>
                        <el-row>
                            <el-col :offset="index=0" :span="8">
                                <div class="text item">平均耗时</div>
                            </el-col>
                            <el-col :offset="index=3" :span="12">{{timePer}}</el-col>
                        </el-row>
                    </el-card>
                </el-col>
            </el-row>
            <el-row style="margin-top: 20px;">
                <el-table :data="tableData" style="width: 100%;">
                    <el-table-column prop="serviceid" label="服务id"></el-table-column>
                    <el-table-column prop="time" label="上线时间"></el-table-column>
                    <el-table-column label="CPU占用率" v-html="tableData.cpu">
                        <!-- {{tableData.cpu}} -->
                        <template slot-scope="scope">
                            <el-progress :percentage="70"></el-progress>
                        </template>
                    </el-table-column>
                    <el-table-column label="内存使用率">
                        <template slot-scope="scope">
                            <el-progress :percentage="70"></el-progress>
                        </template>
                    </el-table-column>
                    <el-table-column prop="disk" label="磁盘使用率">
                        <template slot-scope="scope">
                            <el-progress :percentage="70"></el-progress>
                        </template>
                    </el-table-column>
                    <el-table-column prop="callnum" label="调用次数"></el-table-column>
                    <el-table-column prop="avetime" label="平均耗时(ms)"></el-table-column>
                </el-table>
            </el-row>
        </el-main>
    </el-container>
</template>
<style scoped>
    .text {
        font-size: 14px;
    }

    .item {
        padding: 8px 0;
    }

    .box-card {
        width: 260px;
    }

    .dynamic {
        width: 360px;
    }
</style>
<script>
    export default {
        data() {
            return {
                callCount: 20,
                timePer: "10ms",
                tableData: [
                    {
                        serviceid: "02660990566",
                        time: "2018-1-24",
                        cpu: '<el-progress :percentage="0"></el-progress>',
                        storage: '<el-progress :percentage="0"></el-progress>',
                        disk: '<el-progress :percentage="0"></el-progress>',
                        callnum: "130",
                        avetime: "10"
                    },
                    {
                        serviceid: "02660990566",
                        time: "2018-1-24",
                        cpu:
                            '<template slot-scope="scope"><el-progress :percentage="70"></el-progress></template>',
                        storage: '<el-progress :percentage="0"></el-progress>',
                        disk: '<el-progress :percentage="0"></el-progress>',
                        callnum: "130",
                        avetime: "10"
                    }
                ]
            };
        }
    };
</script>
