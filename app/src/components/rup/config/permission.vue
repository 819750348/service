<template>
  <el-container>
    <el-header>
      <el-tabs v-model="activeName" @tab-click="changeTab">
        <el-tab-pane label="A级权限" name="A"></el-tab-pane>
        <el-tab-pane label="B级权限" name="B"></el-tab-pane>
        <el-tab-pane label="C级权限" name="C"></el-tab-pane>
        <el-tab-pane label="D级权限" name="D"></el-tab-pane>
      </el-tabs>
    </el-header>
    <el-main>
      <label class="category-title">资源基本信息</label>
      <!-- <el-row class="config-item">
        <el-col :span="4">
          <label class="item-title">资源名称</label>
        </el-col>
        <el-col :span="12">
          <label>允许用户查看资源名称、编号等</label>
        </el-col>
        <el-col :span="4">
          <el-switch
            v-model="currentPermission.resourcesName"
            @change="savePermission"
            inactive-text="未开放"
            active-text="已开放"
          ></el-switch>
        </el-col>
      </el-row> -->
      <el-row class="config-item">
        <el-col :span="4">
          <label class="item-title">开发人员信息</label>
        </el-col>
        <el-col :span="12">
          <label>允许用户查看开发人员单位、联系方式等</label>
        </el-col>
        <el-col :span="4">
          <el-switch
            v-model="currentPermission.developerInformation"
            @change="savePermission"
            inactive-text="未开放"
            active-text="已开放"
          ></el-switch>
        </el-col>
      </el-row>
      <label class="category-title">模型描述信息</label>
      <el-row class="config-item">
        <el-col :span="4">
          <label class="item-title">模型描述信息</label>
        </el-col>
        <el-col :span="12">
          <label>允许用户查看模型参数、功能函数、对外接口等</label>
        </el-col>
        <el-col :span="4">
          <el-switch
            v-model="currentPermission.viewModelDescription"
            @change="savePermission"
            inactive-text="未开放"
            active-text="已开放"
          ></el-switch>
        </el-col>
      </el-row>
      <!-- <el-row class="config-item">
        <el-col :span="4">
          <label class="item-title">参数取值范围</label>
        </el-col>
        <el-col :span="12">
          <label>允许用户查看模型参数取值范围</label>
        </el-col>
        <el-col :span="4">
          <el-switch
            v-model="currentPermission.parameterRange"
            @change="savePermission"
            inactive-text="未开放"
            active-text="已开放"
          ></el-switch>
        </el-col>
      </el-row>-->
      <!-- <el-row class="config-item">
        <el-col :span="4">
          <label class="item-title">模型描述文件</label>
        </el-col>
        <el-col :span="12">
          <label>允许用户下载模型描述文件</label>
        </el-col>
        <el-col :span="4">
          <el-switch
            v-model="currentPermission.downloadModelDescriptionFile"
            @change="savePermission"
            inactive-text="未开放"
            active-text="已开放"
          ></el-switch>
        </el-col>
      </el-row> -->
      <label class="category-title">模型开放下载</label>
      <el-row class="config-item">
        <el-col :span="4">
          <label class="item-title">模型文件</label>
        </el-col>
        <el-col :span="12">
          <label>允许用户下载模型文件、模型使用手册、模型测试报告、模型可信度评估报告等</label>
        </el-col>
        <el-col :span="4">
          <el-switch
            v-model="currentPermission.downLoadModelFile"
            @change="savePermission"
            inactive-text="未开放"
            active-text="已开放"
          ></el-switch>
        </el-col>
      </el-row>
      <!-- <el-row class="config-item">
        <el-col :span="4">
          <label class="item-title">模型上传流程</label>
        </el-col>
        <el-col :span="12">
          <label>允许用户查看模型上传者、第三方测试者、第三方评估者信息</label>
        </el-col>
        <el-col :span="4">
          <el-switch
            v-model="currentPermission.viewModelUsers"
            @change="savePermission"
            inactive-text="未开放"
            active-text="已开放"
          ></el-switch>
        </el-col>
      </el-row> -->
      <label class="category-title">参数开放下载</label>
      <!-- <el-row class="config-item">
        <el-col :span="4">
          <label class="item-title">参数取值</label>
        </el-col>
        <el-col :span="12">
          <label>允许用户查看参数取值</label>
        </el-col>
        <el-col :span="4">
          <el-switch
            v-model="currentPermission.viewParameterDescription"
            @change="savePermission"
            inactive-text="未开放"
            active-text="已开放"
          ></el-switch>
        </el-col>
      </el-row> -->
      <el-row class="config-item">
        <el-col :span="4">
          <label class="item-title">参数描述</label>
        </el-col>
        <el-col :span="12">
          <label>允许用户下载参数描述文件</label>
        </el-col>
        <el-col :span="4">
          <el-switch
            v-model="currentPermission.downloadParameterFile"
            @change="savePermission"
            inactive-text="未开放"
            active-text="已开放"
          ></el-switch>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>
<script>
export default {
  data() {
    return {
      activeName: "A",
      permissions: {
        A: {},
        B: {},
        C: {},
        D: {}
      },
      currentPermission: {
        permissionType: "A",
        resourcesName: false,
        developerInformation: false,
        viewModelDescription: false,
        parameterRange: false,
        downloadModelDescriptionFile: false,
        downLoadModelFile: false,
        viewModelUsers: false,
        viewParameterDescription: false,
        downloadParameterFile: false
      }
    };
  },
  created() {
    var vm = this;
    this.fetchPermission("A", () => {
      this.currentPermission = this.permissions["A"];
    });
    this.fetchPermission("B");
    this.fetchPermission("C");
    this.fetchPermission("D");
  },
  methods: {
    mergePermission(first, second) {
      for (var key in second) {
        if (second[key]) {
          first[key] = second[key];
        } else {
          first[key] = false;
        }
      }
      return first;
    },
    changeTab(tab) {
    //   this.mergePermission(this.currentPermission, this.permissions[tab.name]);
    this.currentPermission = this.permissions[tab.name];
    },
    fetchPermission(type, callback) {
      var vm = this;
      this.$axios
        .get("/dataPermission/getByPermissonType?permissonType=" + type)
        .then(data => {
          vm.permissions[type] = Object.assign({}, data);
          if (callback) callback();
        });
    },
    savePermission() {
      var vm = this;
      this.$axios
        .post("/dataPermission/save", this.currentPermission)
        .then(data => {
          vm.currentPermission = Object.assign({}, data);
          vm.permissions[data.permissionType] = Object.assign({}, data);
        });
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
</style>