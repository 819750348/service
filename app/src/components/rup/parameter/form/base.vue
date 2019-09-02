<template>
    <el-form :model="info">
        <el-form-item label="模型名称">
            <el-input v-model="info.name"></el-input>
        </el-form-item>
        <el-form-item label="模型组件名称">
            <el-input v-model="info.modelName"></el-input>
        </el-form-item>
        <el-form-item label="模型标签">
            <el-tag v-for="tag in info.tags" type="success" :key="tag">{{tag}}</el-tag>
            <el-tag type="success" class="el-icon-plus"></el-tag>
        </el-form-item>
        <el-form-item label="模型类型">
            <el-radio-group v-model="info.modelType">
                <el-radio :label="1">装备</el-radio>
                <el-radio :label="2">作战</el-radio>
                <el-radio :label="3">环境</el-radio>
                <el-radio :label="4">评估</el-radio>
                <el-radio :label="5">支撑类</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="模型开发人员">
            <el-input v-model="info.developer"></el-input>
        </el-form-item>
        <el-form-item label="模型上传单位">
            <el-input v-model="info.uploadOrganization"></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
            <el-input v-model="info.contract">
                <el-checkbox slot="append" v-model="info.contractDownloaderDisplay">下载者可见</el-checkbox>
            </el-input>
        </el-form-item>
        <el-form-item label="模型开放权限">
            <el-select v-model="info.authority" placeholder="请选择">
                <el-option :value="1" label="A级(所有用户均可下载)"></el-option>
                <el-option :value="2" label="B级(注册用户均可下载)"></el-option>
                <el-option :value="3" label="C级(注册用户可预览，需申请下载权限)"></el-option>
                <el-option :value="4" label="D级(仅管理员可下载)"></el-option>
            </el-select>
        </el-form-item>
        <el-form-item>
            <el-button>预览</el-button>
            <el-button>保存</el-button>
            <el-button type="primary" @click="saveAndNext">保存并下一步</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
export default {
  data() {
    return {
      info: {
        id: 1,
        name: "",
        modelName: "",
        tags: ["tag1", "tag2"],
        modelType: 1,
        developer: "",
        uploadOrganization: "",
        contract: "",
        contractDownloaderDisplay: false,
        authority: 2
      }
    };
  },
  created() {},
  methods: {
    saveAndNext() {
      //   this.$router.push({ path: "/model-parameter-upload-second" });
      this.$emit("nextStep");
    }
  }
};
</script>
<style>
</style>
