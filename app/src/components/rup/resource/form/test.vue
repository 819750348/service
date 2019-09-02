<template>
  <div>
    <el-form ref="testForm" :model="info" :rules="formRules">
      <el-form-item v-if="editable" label="模型名称">
        <el-input v-model="info.modelName" readonly></el-input>
      </el-form-item>
      <el-form-item v-if="editable" label="模型测试工具">
        <el-button>测试工具下载</el-button>
      </el-form-item>
      <el-form-item label="模型测试报告" prop="modelTestReportName">
        <el-input v-if="editable" v-model="info.modelTestReportName" readonly>
          <upload-button v-if="editable" slot="append" :upload-success="uploadSuccess">点击上传</upload-button>
          <download-button
            :fileId="config.testReportTemplateId"
            :fileName="config.testReportTemplate && config.testReportTemplate.name"
            slot="append"
          >模板下载</download-button>
        </el-input>
        <label v-else>{{info.modelTestReportName}}</label>
      </el-form-item>
      <!-- <el-form-item label="第三方测试人员" prop="testers">
        <el-tag v-if="!editable" v-for="user in info.testers" :key="user.id">{{user.userName}}</el-tag>
        <el-table v-if="editable" :data="testers">
          <el-table-column>
            <template slot-scope="scope">
              <el-checkbox @change="selectedChange($event,scope.row)" :checked="checkedUser(scope.row)" :disabled="!editable"></el-checkbox>
            </template>
          </el-table-column>
          <el-table-column prop="name" label="姓名"></el-table-column>
          <el-table-column prop="department" label="部门"></el-table-column>
          <el-table-column prop="status" label="状态"></el-table-column>
        </el-table>
      </el-form-item> -->
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
      testers: [],
      formRules:{
        modelTestReportName:[{required:true,message:'请上传模型测试报告'}],
        testers:[
          {required:true,message:'请选择第三方测试人员'}
        ]
      }
    };
  },
  props: {
    info: {
      type: Object,
      required: true
    },
    config:{
      type:Object,
      default:{}
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
  created() {
    // this.fetchTester();
  },
  methods: {
    fetchTester() {
      var vm = this;
      this.$axios.get("/businessuser/getTesters").then(data => {
        vm.testers = [].concat(data);
      });
    },
    checkedUser(user){
      for(var i in this.info.testers){
        if(this.info.testers[i].userId == user.id){
          return true;
        }
      }
      return false;
    },
    saveModel() {
      var vm = this;
      this.$refs["testForm"].validate(valid => {
        if (valid) {
          vm.$axios.post("/model/saveModel", vm.info).then(data => {});
        }
      });
    },
    saveAndNext() {
      var vm = this;
      this.$refs["testForm"].validate(valid => {
        if (valid) {
          vm.$axios.post("/model/nextModel?stepId=4", vm.info).then(data => {
            vm.$emit("nextStep", data);
          });
        }
      });
    },
    uploadSuccess(data, file) {
      this.info.modelTestReportId = data.id;
      this.info.modelTestReportName = data.oldName;
    },
    selectedChange(selected, user) {
      var url = selected ? '/model/addModelUser' : '/model/deleteModelUser';
      var data = {
        userId:user.id,
        modelId:this.info.id,
        userType:1,
        userName:user.name
      };
      this.$axios.post(url,data)
      .then(data => {
        selected ? this.info.testers.push(data) : this.info.testers = this.info.testers.filter(item => {
          return item.userId != data.userId;
        });
      })
    }
  }
};
</script>