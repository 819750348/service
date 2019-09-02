<template>
  <div>
    <el-form ref="assessForm" :model="info" :rules="formRules">
      <el-form-item v-if="editable" label="模型名称">
        <el-input v-model="info.modelName" readonly></el-input>
      </el-form-item>
      <el-form-item v-if="editable" label="模型可信度评估工具">
        <el-button>可信度工具下载</el-button>
      </el-form-item>
      <el-form-item label="模型可信度评估报告" prop="modelAssessmentReportName">
        <el-input v-if="editable" v-model="info.modelAssessmentReportName" readonly>
          <upload-button v-if="editable" slot="append" :upload-success="uploadSuccess">点击上传</upload-button>
          <download-button
            :fileId="config.assessmentReportTemplateId"
            :fileName="config.assessmentReportTemplate && config.assessmentReportTemplate.name"
            slot="append"
          >模板下载</download-button>
        </el-input>
        <label v-else>{{info.modelAssessmentReportName}}</label>
      </el-form-item>
      <!-- <el-form-item label="第三方评估人员" prop="evaluators">
        <el-tag v-if="!editable" v-for="user in info.evaluators" :key="user.id">{{user.userName}}</el-tag>
        <el-table v-if="editable" :data="evaluators">
          <el-table-column>
            <template slot-scope="scope">
              <el-checkbox
                @change="selectedChange($event,scope.row)"
                :checked="checkedUser(scope.row)"
                :disabled="!editable"
              ></el-checkbox>
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
        <el-button type="primary" @click="saveModel">完成&提交审核</el-button>
        <el-button @click="$emit('preview')">预览</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      evaluators: [],
      formRules: {
        modelAssessmentReportName: [
          { required: true, message: "请上传模型可信度评估报告" }
        ],
        evaluators: [{ required: true, message: "请选择第三方评估人员" }]
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
  created() {
    // this.fetchEvaluator();
  },
  methods: {
    fetchEvaluator() {
      var vm = this;
      this.$axios.get("/businessuser/getEvaluators").then(data => {
        vm.evaluators = [].concat(data);
      });
    },
    checkedUser(user) {
      for (var i in this.info.evaluators) {
        if (this.info.evaluators[i].userId == user.id) {
          return true;
        }
      }
      return false;
    },
    saveModel() {
      var vm = this;
      this.$refs["assessForm"].validate(valid => {
        if (valid) {
          vm.$axios.post("/model/commitModel", vm.info).then(data => {
            vm.$router.push({
              path: "/model-custom-search"
            });
          });
        }
      });
    },
    uploadSuccess(data, file) {
      this.info.modelAssessmentReportId = data.id;
      this.info.modelAssessmentReportName = data.oldName;
    },
    selectedChange(selected, user) {
      var url = selected ? "/model/addModelUser" : "/model/deleteModelUser";
      var data = {
        userId: user.id,
        modelId: this.info.id,
        userType: 0,
        userName: user.name
      };
      this.$axios.post(url, data).then(data => {
        selected
          ? this.info.evaluators.push(data)
          : (this.info.evaluators = this.info.evaluators.filter(item => {
              return item.userId != data.userId;
            }));
      });
    }
  }
};
</script>