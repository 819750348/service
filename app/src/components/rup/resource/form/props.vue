<template>
  <el-form :model="info" ref="propForm" :rules="formRules">
    <el-form-item v-if="editable" label="模型类型">
      <el-radio-group v-model="info.category">
        <el-radio
          v-for="item in categorys"
          :label="item.id"
          :disabled="info.category != item.id"
          :key="item.id"
        >{{item.name}}</el-radio>
      </el-radio-group>
    </el-form-item>
    <el-form-item label="模型描述文件" prop="modelFileName">
      <el-input v-if="editable" v-model="info.modelFileName" readonly>
        <upload-button v-if="editable" slot="append" :upload-success="uploadSuccess">点击上传</upload-button>
        <download-button
          :fileId="config.modelFileTemplateId"
          :fileName="config.modelFileTemplate && config.modelFileTemplate.name"
          slot="append"
        >模板下载</download-button>
      </el-input>
      <label v-else>{{info.modelFileName}}</label>
    </el-form-item>
    <el-form-item>
      <el-tabs>
        <el-tab-pane v-for="(view,viewIndex) in views" :label="view.name" :key="viewIndex">
          <el-form size="mini" label-position="left">
            <template v-if="view.isArray">
              <div v-for="(child,childIndex) in view.value" :key="view.name +'-' +child.name + '-' +childIndex">
                <div v-if="child.type !== 0" class="top-title form-item"><span class="title-head">|</span>{{child.name}}</div>
                <prop-value v-if="child.type === 0" :prop="child"></prop-value>
                <el-form-item class="form-item">
                  <el-form label-position="left">
                    <prop-object v-if="child.type === 1" :prop="child"></prop-object>
                    <prop-table v-if="child.type === 2" :prop="child"></prop-table>
                    <prop-complex v-if="child.type === 3" :prop="child"></prop-complex>
                  </el-form>
                </el-form-item>
              </div>
            </template>
            <template v-else>
              <div v-if="view.value.type !== 0" class="top-title form-item"><span class="title-head">|</span>{{view.value.name}}</div>
              <prop-value v-if="view.value.type === 0" :prop="view.value"></prop-value>
              <prop-object v-if="view.value.type === 1" :prop="view.value"></prop-object>
              <prop-table v-if="view.value.type === 2" :prop="view.value"></prop-table>
              <prop-complex v-if="view.value.type === 3" :prop="view.value"></prop-complex>
            </template>
          </el-form>
        </el-tab-pane>
      </el-tabs>
    </el-form-item>
    <el-form-item v-if="editable">
      <el-button @click="startModel">开始</el-button>
    </el-form-item>
  </el-form>
</template>
<script>
import PropValue from "./props-edit/value";
import PropObject from "./props-edit/object";
import PropTable from "./props-edit/table";
import PropComplex from "./props-edit/complex";

export default {
  data() {
    return {
      formRules: {
        modelFileName: [{ required: true, message: "请上传模型描述文件" }]
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
    views: {
      type: Array,
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
  components: {
    PropValue,
    PropObject,
    PropTable,
    PropComplex
  },
  filters: {
    enableWord(flag) {
      return flag ? "可配置周期" : "不可配置周期";
    }
  },
  methods: {
    startModel() {
      var vm = this;
      this.$refs["propForm"].validate(valid => {
        if (valid) {
          vm.$axios.post("/model/startModel", vm.info).then(data => {
            vm.$emit("createModelInfo", data);
            vm.$emit("nextStep");
          });
        }
      });
    },
    uploadSuccess(data, file) {
      this.$emit("updateFileId", data.id);
    }
  }
};
</script>
<style scoped>
.parent-form-item{
     padding-left:15px;
}
.top-title{
  font-size:15px!important;
  font-weight: bold!important;
}
.title-head{
  font-size: 15px;
  color: blue;
  margin-right: 5px;
}
.form-item {
  margin-bottom: 0 !important;
}
</style>
