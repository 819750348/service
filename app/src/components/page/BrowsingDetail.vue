<template>
    <el-card>
        <el-row type="flex">
            <el-col :span="1">
                <span class="dot-inner"></span>
                <span>基本信息</span>
            </el-col>
            <el-col :span="23">
                <span class="dot-inner"></span>
                <span>基本信息</span>
            </el-col>
        </el-row>
    </el-card>
</template>
<style>
    .dot-inner {
        background-color: #00a5e2;
        height: 6px;
        width: 6px;
        margin: 15px 5px 5px 0;
        display: inline-block;
        border-radius: 50%;
        position: relative;
        top: 1px;
    }
</style>
