<template>
  <el-row>
    <el-col :span="4">
      <el-steps direction="vertical" :active="activeStep" :space="100">
        <el-step title="描述信息"></el-step>
        <el-step title="基本信息"></el-step>
        <el-step title="文件上传"></el-step>
        <el-step title="测试"></el-step>
        <el-step title="可信度评估"></el-step>
      </el-steps>
    </el-col>
    <el-col :span="20">
      <props-form
        :editable="editable"
        v-if="activeStep == 0 || !editable"
        @preview="editable=false"
        @updateFileId="updateModelFileId($event)"
        @createModelInfo="createModelInfo($event)"
        @nextStep="nextStep"
        :model="model"
        :info="model.modelInfo"
        :views="model.modelViews"
        :config="uploadConfig"
        :categorys="categorys"
      ></props-form>
      <base-form
        :editable="editable"
        v-if="activeStep == 1 || !editable"
        @prevStep="activeStep --"
        @nextStep="nextStep"
        @preview="editable=false"
        :model="model"
        :info="model.modelInfo"
        :config="uploadConfig"
        :categorys="categorys"
      ></base-form>
      <file-form
        :editable="editable"
        v-if="activeStep == 2 || !editable"
        @prevStep="activeStep --"
        @preview="editable=false"
        @nextStep="nextStep"
        :model="model"
        :info="model.modelInfo"
        :views="model.modelViews"
        :config="uploadConfig"
        :categorys="categorys"
      ></file-form>
      <test-form
        :editable="editable"
        v-if="activeStep == 3 || !editable"
        @prevStep="activeStep --"
        @preview="editable=false"
        @nextStep="nextStep"
        :model="model"
        :info="model.modelInfo"
        :views="model.modelViews"
        :config="uploadConfig"
        :categorys="categorys"
      ></test-form>
      <assess-form
        :editable="editable"
        v-if="activeStep == 4 || !editable"
        @prevStep="activeStep --"
        @preview="editable=false"
        :model="model"
        :info="model.modelInfo"
        :views="model.modelViews"
        :config="uploadConfig"
        :categorys="categorys"
      ></assess-form>
      <el-button v-if="!editable" @click="editable = true">返回</el-button>
      <!-- <router-view @nextStep="activeStep = $event"></router-view> -->
    </el-col>
  </el-row>
</template>
<script>
import BaseForm from "./form/base";
import PropsForm from "./form/props";
import FileForm from "./form/file";
import TestForm from "./form/test";
import AssessForm from "./form/assess";
export default {
  data() {
    return {
      editable: true,
      activeStep: 0,
      categorys:[],
      model: {
        modelInfo: {
          modelPermissions: null,
          category:0
        },
        modelViews: []
      },
      uploadConfig: {}
    };
  },
  props: {
    category: { required: true },
    modelId:{
      default:0
    }
  },
  components: {
    BaseForm,
    PropsForm,
    FileForm,
    TestForm,
    AssessForm
  },
  created() {
    this.model.modelInfo.category = this.category - 0;
    this.fetchConfig();
    this.fetchCategory();
    if(this.modelId && this.modelId > 0){
      this.fetchModel();
    }
  },
  methods: {
    mergeObject(first, second) {
      for (var key in second) {
        if (second[key]) {
          if (!first[key]) {
            first[key] = second[key];
          } else if (typeof second[key] === "object") {
            this.mergeObject(first[key], second[key]);
          } else {
            first[key] = second[key];
          }
        }
      }
      return first;
    },
    fetchData(modelFileId) {
      var vm = this;
      this.$axios
        .post(
          "/model/getModelInfoView?category=" +
            this.category +
            "&attachmentId=" +
            modelFileId
        )
        .then(data => {
          vm.model.modelInfo = Object.assign({}, vm.mergeObject(vm.model.modelInfo, data.modelInfo));
          vm.model.modelViews = [].concat(data.modelViews);
        });
    },
    fetchCategory(){
      var vm = this;
      this.$axios.get('/modelCategory/getRoot')
      .then(data => {
        vm.categorys = [].concat(data);
      })
    },
    fetchConfig() {
      this.$axios.get("/basicParam/get").then(data => {
        this.uploadConfig = Object.assign({}, data);
      });
    },
    fetchModel(){
      var vm = this;
      this.$axios.get('/model/getModelInfo?id='+this.modelId)
      .then(data => {
        vm.model = Object.assign({},data);
        vm.activeStep = data.modelInfo.modelStatus;
      })
    },
    createModelInfo(modelInfo) {
      this.model.modelInfo = Object.assign({}, modelInfo);
    },
    updateModelFileId(modelFileId) {
      var vm = this;
      this.fetchData(modelFileId);
    },
    nextStep(modelInfo) {
      this.activeStep++;
      if (modelInfo) {
        this.model.modelInfo = Object.assign({}, modelInfo);
      }
    }
  }
};
</script>
