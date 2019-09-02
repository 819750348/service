<template>
  <el-form ref="baseForm" :model="info" :rules="formRules">
    <el-form-item label="模型名称">
      <el-input v-if="editable" v-model="info.modelName" readonly></el-input>
      <label v-else>{{info.modelName}}</label>
    </el-form-item>
    <el-form-item label="模型标签">
      <span v-for="tag in modelTags" :key="tag">
        <el-tag type="success">{{tag}}</el-tag>
      </span>
      <!-- <el-tag type="success" class="el-icon-plus"></el-tag> -->
    </el-form-item>
    <el-form-item label="模型类型">
      <div v-if="editable">
      <el-radio-group v-model="info.category">
        <el-radio v-for="item in categorys" :label="item.id" :disabled="info.category != item.id" :key="item.id">{{item.name}}</el-radio>
      </el-radio-group>
      </div>
      <div v-else>
        <span v-for="item in categorys" :key="item.id" v-if="info.category == item.id">{{item.name}}</span>
      </div>
    </el-form-item>
    <el-form-item label="模型开发人员">
      <el-input v-if="editable" v-model="info.developer" readonly></el-input>
      <label v-else>{{info.developer}}</label>
    </el-form-item>
    <el-form-item label="模型上传单位">
      <el-input v-if="editable" v-model="info.modelDepartment" readonly></el-input>
      <label v-else>{{info.modelDepartment}}</label>
    </el-form-item>
    <el-form-item label="联系方式">
      <el-input v-if="editable" v-model="info.contactInformation" readonly>
        <el-checkbox
          slot="append"
          v-model="info.contactInformationIsvisible"
          :disabled="!editable"
        >下载者可见</el-checkbox>
      </el-input>
      <label v-else>{{info.contactInformation}}</label>
    </el-form-item>
    <el-form-item label="模型开放权限" prop="modelPermissions">
      <el-select v-if="editable"
        v-model="info.modelPermissions"
        placeholder="请选择"
        value-key="value"
        :disabled="!editable"
        style="min-width:300px;"
      >
        <el-option :value="1" label="A级(所有用户均可下载)"></el-option>
        <el-option :value="2" label="B级(注册用户均可下载)"></el-option>
        <el-option :value="3" label="C级(注册用户可预览，需申请下载权限)"></el-option>
        <el-option :value="4" label="D级(仅管理员可下载)"></el-option>
      </el-select>
      <label v-else>{{info.modelPermissions | permissionWord}}</label>
    </el-form-item>
    <el-form-item v-if="editable">
        <el-button @click="$emit('prevStep')">上一步</el-button>
        <el-button @click="saveModel">保存</el-button>
        <el-button type="primary" @click="saveAndNext">保存并下一步</el-button>
        <el-button @click="$emit('preview')">预览</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
export default {
  data() {
    return {
      formRules: {
        modelPermissions: [
          { required: true, message: "请选择模型开放权限", trigger: "blur" }
        ]
      }
    };
  },
  props: {
    model: {
      type: Object
    },
    info: {
      type: Object,
      required: true
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
  computed: {
    modelTags() {
      return this.info.modelLabel ? this.info.modelLabel.split("|") : [];
    }
  },
  filters: {
    permissionWord(num){
      switch (num) {
        case 1:
          return "A级";
        case 2:
          return "B级";
        case 3:
          return "C级";
        case 4:
          return "D级";
      }
    }
  },
  methods: {
    saveModel() {
      var vm = this;
      this.$refs["baseForm"].validate(valid => {
        if (valid) {
          vm.$axios.post("/model/saveModel", vm.info).then(response => {});
        }
      });
    },
    saveAndNext() {
      var vm = this;
      this.$refs["baseForm"].validate(valid => {
        if (valid) {
          vm.$axios.post("/model/nextModel?stepId=2", vm.info).then(data => {
            vm.$emit("nextStep", data);
          });
        }
      });
    }
  }
};
</script>
<style>
</style>
