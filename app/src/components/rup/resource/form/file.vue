<template>
  <div>
    <el-form :model="info" ref="fileForm" :rules="formRules">
      <el-form-item label="模型名称" v-if="editable">
        <el-input v-model="info.modelName"></el-input>
      </el-form-item>
      <el-form-item label="模型执行模式" prop="modelExecuteType">
        <el-select
          v-if="editable"
          v-model="info.modelExecuteType"
          value-key="value"
          placeholder="请选择"
          :disabled="!editable"
        >
          <el-option value="Debug" label="Debug"></el-option>
          <el-option value="Release" label></el-option>
          <el-option value="Debug/Release" label></el-option>
        </el-select>
        <label v-else>{{info.modelExecuteType}}</label>
      </el-form-item>
      <el-form-item label="模型开发平台" prop="modelDeveloperPlatform">
        <el-select placeholder="请选择"
          v-if="editable"
          v-model="info.modelDeveloperPlatform"
          value-key="value"
          :disabled="!editable"
        >
          <el-option value="visual Studio2006" label="visual Studio2006"></el-option>
          <el-option value="visual Studio2008" label="visual Studio2008"></el-option>
          <el-option value="visual Studio2010" label="visual Studio2010"></el-option>
          <el-option value="visual Studio2015" label="visual Studio2015"></el-option>
          <el-option value="其他" label="其他"></el-option>
        </el-select>
        <label v-else>{{info.modelDeveloperPlatform}}</label>
      </el-form-item>
      <el-form-item label="模型使用平台" prop="modelRuntimePlatform">
        <div v-if="editable">
        <el-select placeholder="请选择"
          v-model="info.modelRuntimePlatform"
          value-key="value"
          :disabled="!editable"
          style="float:left;"
        >
          <el-option value="win32" label="win32"></el-option>
          <el-option value="win64" label="win64"></el-option>
          <el-option value="linux" label="linux"></el-option>
          <el-option value="其他" label="其他"></el-option>
        </el-select>
        <el-form-item :rules="{required:info.modelRuntimePlatform == '其他',message:'必须输入其他平台'}" prop="otherRemark" style="width:200px;margin:0px 0px 0px 5px;float:left;">
        <el-input v-if="info.modelRuntimePlatform == '其他'" v-model="info.otherRemark" placeholder="其他平台"></el-input>
        </el-form-item>
        </div>
        <label v-else>{{info.modelRuntimePlatform}}</label>
      </el-form-item>
      <el-form-item label="模型使用手册" prop="modelManualName">
        <el-input v-if="editable" v-model="info.modelManualName" readonly>
          <upload-button v-if="editable" slot="append" :upload-success="uploadSuccess">点击上传</upload-button>
          <download-button
            :fileId="config.manualTemplateId"
            :fileName="config.manualTemplate && config.manualTemplate.name"
            slot="append"
          >模板下载</download-button>
        </el-input>
        <label v-else>{{info.modelManualName}}</label>
      </el-form-item>
      <el-form-item v-if="editable">
        <el-button @click="$emit('prevStep')">上一步</el-button>
        <el-button @click="saveModel">保存</el-button>
        <el-button type="primary" @click="saveAndNext">保存并下一步</el-button>
        <el-button @click="$emit('preview')">预览</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formRules: {
        modelExecuteType: [{ required: true, message: "请选择模型执行模式" }],
        modelDeveloperPlatform: [
          { required: true, message: "请选择模型开发平台" }
        ],
        modelRuntimePlatform: [
          { required: true, message: "请选择模型使用平台" }
        ],
        modelManualName: [{ required: true, message: "请上传模型使用手册" }]
      }
    };
  },
  props: {
    info: {
      type: Object,
      required: true
    },
    config: {
      type: Object,
      default: {}
    },
    categorys: {
      type: Array,
      default: []
    },
    editable: {
      type: Boolean,
      default: true
    }
  },
  methods: {
    saveModel() {
      var vm = this;
      this.$refs["fileForm"].validate(valid => {
        if (valid) {
          vm.$axios.post("/model/saveModel", vm.info).then(response => {});
        }
      });
    },
    saveAndNext() {
      var vm = this;
      this.$refs["fileForm"].validate(valid => {
        if (valid) {
          vm.$axios.post("/model/nextModel?stepId=3", vm.info).then(data => {
            vm.$emit("nextStep", data);
          });
        }
      });
    },
    uploadSuccess(data, file) {
      this.info.modelManualId = data.id;
      this.info.modelManualName = data.oldName;
    }
  }
};
</script>