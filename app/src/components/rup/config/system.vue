<template>
  <div>
    <el-tabs v-model="activeName">
      <el-tab-pane label="资源上传设置" name="upload">
        <label class="category-title">模型信息要求</label>
        <el-row class="config-item">
          <el-col :span="12">
            <label class="item-title">模型基本信息</label>
          </el-col>
          <el-col :span="12">
            <el-switch
              v-model="systemConfig.isBasicInfo"
              @change="saveSystemConfig"
              inactive-text="未使用"
              active-text="已使用"
            ></el-switch>
          </el-col>
        </el-row>
        <el-row class="config-item">
          <el-col :span="12">
            <label class="item-title">模型描述信息</label>
          </el-col>
          <el-col :span="12">
            <el-switch
              v-model="systemConfig.isDescription"
              @change="saveSystemConfig"
              inactive-text="未使用"
              active-text="已使用"
            ></el-switch>
          </el-col>
        </el-row>
        <label class="category-title">模型上传流程</label>
        <el-row class="config-item">
          <el-col :span="12">
            <label class="item-title">使用第三方测试</label>
          </el-col>
          <el-col :span="6">
            <el-switch
              v-model="systemConfig.isTest"
              @change="saveSystemConfig"
              inactive-text="未使用"
              active-text="已使用"
            ></el-switch>
          </el-col>
          <el-col :span="6">
            <span>第三方测试人数</span>
            <el-select
              value-key="value"
              size="mini"
              v-model="systemConfig.testNumber"
              @change="saveSystemConfig"
            >
              <el-option v-for="c in userCounts" :value="c" :label="c" :key="c"></el-option>
            </el-select>
          </el-col>
        </el-row>
        <el-row class="config-item">
          <el-col :span="12">
            <label class="item-title">使用第三方评估</label>
          </el-col>
          <el-col :span="6">
            <el-switch
              v-model="systemConfig.isEvaluation"
              @change="saveSystemConfig"
              inactive-text="未使用"
              active-text="已使用"
            ></el-switch>
          </el-col>
          <el-col :span="6">
            <span>第三方评估人数</span>
            <el-select
              value-key="value"
              size="mini"
              v-model="systemConfig.evaluationNumber"
              @change="saveSystemConfig"
            >
              <el-option v-for="c in userCounts" :value="c" :label="c" :key="c"></el-option>
            </el-select>
          </el-col>
        </el-row>
        <!-- <label class="category-title">在线服务设置</label>
                <el-row class="config-item">
                    <el-col :span="6">
                        <label class="item-title">在线测试服务</label>
                    </el-col>
                    <el-col :span="6">
                        <span v-if="systemConfig.onlineTestService">已开放</span>
                        <span v-else size="mini">未开放</span>
                    </el-col>
                    <el-col :span="6">
                        <el-button v-if="systemConfig.onlineTestService" size="mini" @click="changeOnlineTestService">停止服务</el-button>
                        <el-button v-else size="mini" @click="changeOnlineTestService">开放服务</el-button>
                    </el-col>
                </el-row>
                <el-row class="config-item">
                    <el-col :span="6">
                        <label class="item-title">在线评估服务</label>
                    </el-col>
                    <el-col :span="6">
                        <span v-if="systemConfig.onlineAssessService">已开放</span>
                        <span v-else size="mini">未开放</span>
                    </el-col>
                    <el-col :span="6">
                        <el-button v-if="systemConfig.onlineAssessService" size="mini" @click="changeOnlineAssessService">停止服务</el-button>
                        <el-button v-else size="mini" @click="changeOnlineAssessService">开放服务</el-button>
                    </el-col>
        </el-row>-->
        <!-- <el-row class="config-item">
          <el-col :span="6">
            <label class="item-title" @click="download(systemConfig.modelFileTemplate)">模型描述文档模板</label>
          </el-col>
          <el-col :span="6">
            <upload-button :upload-success="uploadedModel">更改</upload-button>
          </el-col>
          <el-col :span="6">
            <label class="item-title" @click="download(systemConfig.modelFileXml)">模型描述文档XML</label>
          </el-col>
          <el-col :span="6">
            <upload-button :upload-success="uploadedModelXml">更改</upload-button>
          </el-col>
        </el-row>-->
        <label class="category-title">模型使用手册</label>
        <el-row class="config-item">
          <el-col :span="6">
            <label class="item-title" @click="download(systemConfig.manualXml)">XML</label>
          </el-col>
          <el-col :span="6">
            <upload-button :upload-success="uploadedManualXml">更改</upload-button>
          </el-col>
          <el-col :span="6">
            <label class="item-title" @click="download(systemConfig.manualTemplate)">模板</label>
          </el-col>
          <el-col :span="6">
            <upload-button :upload-success="uploadedManual">更改</upload-button>
          </el-col>
        </el-row>
        <label class="category-title">模型测试报告</label>
        <el-row class="config-item">
          <el-col :span="6">
            <label class="item-title" @click="download(systemConfig.testReportXml)">XML</label>
          </el-col>
          <el-col :span="6">
            <upload-button :upload-success="uploadedTestReportXml">更改</upload-button>
          </el-col>
          <el-col :span="6">
            <label class="item-title" @click="download(systemConfig.testReportTemplate)">模板</label>
          </el-col>
          <el-col :span="6">
            <upload-button :upload-success="uploadedTestReport">更改</upload-button>
          </el-col>
        </el-row>
        <label class="category-title">模型可信度评估报告</label>
        <el-row class="config-item">
          <el-col :span="6">
            <label class="item-title" @click="download(systemConfig.assessmentReportXml)">XML</label>
          </el-col>
          <el-col :span="6">
            <upload-button :upload-success="uploadedAssessmentReportXml">更改</upload-button>
          </el-col>
          <el-col :span="6">
            <label class="item-title" @click="download(systemConfig.assessmentReportTemplate)">模板</label>
          </el-col>
          <el-col :span="6">
            <upload-button :upload-success="uploadedAssessmentReport">更改</upload-button>
          </el-col>
        </el-row>
        <label class="category-title">模型描述文档模板</label>
        <el-tree
          :data="categoryConfig"
          :props="treeProps"
          node-key="id"
          default-expand-all
          :expand-on-click-node="false"
        >
          <span slot-scope="{node,data}" class="mananger-tree-node">
            <span>{{ node.label }}<span v-if="data.file" style="margin:0 5px 0 5px;">(附件:{{data.file.oldName}})</span></span>
            <upload-button
              v-if="!data.children"
              type="text"
              size="mini"
              @uploaded="changeCategory($event,data)"
            >上传附件</upload-button>
          </span>
        </el-tree>
      </el-tab-pane>
      <el-tab-pane label="资源下载设置" name="download">
        <el-radio-group v-model="systemConfig.resourceDownloadSet" @change="saveSystemConfig">
          <el-radio :label="0">申请人-->上传者</el-radio>
          <el-radio :label="1">申请人-->管理员</el-radio>
          <el-radio :label="2">申请人-->上传者-->管理员</el-radio>
        </el-radio-group>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  data() {
    return {
      activeName: "upload",
      onlineTestService: false,
      onlineAssessService: false,
      userCounts: [1, 2, 3, 4],
      systemConfig: {},
      categoryConfig: [],
      treeProps: {
        label: "name",
        children: "children"
      }
    };
  },
  created() {
    this.fetchData();
    this.fetchCategoryConfig();
  },
  methods: {
    fetchData() {
      this.$axios.get("/basicParam/get").then(data => {
        this.systemConfig = Object.assign({}, data);
      });
    },
    fetchCategoryConfig() {
      var vm = this;
      this.$axios.get("/modelCategory/getDescriptionFile").then(data => {
        vm.formCategory(data);
      });
    },
    formCategory(data) {
      data.forEach(item => {
        var children = data.filter(child => {
          return child.parentId == item.id;
        });
        if (children && children.length > 0) {
          item.children = children;
        }
      });
      this.categoryConfig = data.filter(item => {
        return item.parentId == 0;
      });
    },
    saveSystemConfig() {
      var vm = this;
      this.$axios.post("/basicParam/save", this.systemConfig).then(data => {
        vm.systemConfig = Object.assign({}, data);
      });
    },
    uploadedModel(attachment) {
      this.systemConfig.modelFileTemplateId = attachment.id;
      this.systemConfig.modelFileTemplate = attachment;
      this.saveSystemConfig();
    },
    uploadedModelXml(attachment) {
      this.systemConfig.modelFileXmlId = attachment.id;
      this.systemConfig.modelFileXml = attachment;
      this.saveSystemConfig();
    },
    uploadedManual(attachment) {
      this.systemConfig.manualTemplateId = attachment.id;
      this.systemConfig.manualTemplate = attachment;
      this.saveSystemConfig();
    },
    uploadedManualXml(attachment) {
      this.systemConfig.manualXmlId = attachment.id;
      this.systemConfig.manualXml = attachment;
      this.saveSystemConfig();
    },
    uploadedTestReport(attachment) {
      this.systemConfig.testReportTemplateId = attachment.id;
      this.systemConfig.testReportTemplate = attachment;
      this.saveSystemConfig();
    },
    uploadedTestReportXml(attachment) {
      this.systemConfig.testReportXmlId = attachment.id;
      this.systemConfig.testReportXml = attachment;
      this.saveSystemConfig();
    },
    uploadedAssessmentReport(attachment) {
      this.systemConfig.assessmentReportTemplateId = attachment.id;
      this.systemConfig.assessmentReportTemplate = attachment;
      this.saveSystemConfig();
    },
    uploadedAssessmentReportXml(attachment) {
      this.systemConfig.assessmentReportXmlId = attachment.id;
      this.systemConfig.assessmentReportXml = attachment;
      this.saveSystemConfig();
    },
    changeCategory(param, row) {
      row.fileId = param.data.id;
      this.$axios.post("/modelCategory/update", row).then(data => {
        row.file = param.data;
      });
    },
    download(attachment) {
      if (attachment && attachment.id && attachment.name) {
        this.$axios({
          method: "get",
          url: "/attachment/download/" + attachment.id,
          responseType: "blob"
        }).then(response => {
          let headers = response.headers;
          let blob = new Blob([response.data], {
            type: headers["content-type"]
          });
          let link = document.createElement("a");
          link.href = window.URL.createObjectURL(blob);
          link.download = attachment.name;
          link.click();
        });
      }
    }
  }
};
</script>
<style scoped>
.category-title {
  text-align: right;
  font-size: 14px;
  color: #606266;
  line-height: 40px;
  padding: 0 12px 0 0;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;

  font-family: "PingFang SC", "Helvetica Neue", Helvetica, "microsoft yahei",
    arial, STHeiTi, sans-serif;

  cursor: default;
}
.config-item {
  margin: 5px 5px 5px 25px;
}
.mananger-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>

