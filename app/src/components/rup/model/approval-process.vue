<template>
  <el-container>
    <el-header>
      <el-steps :active="activeStep" :space="150">
        <el-step v-for="item in approvals" :title="item.stepName" :key="item.id"></el-step>
      </el-steps>
    </el-header>
    <el-main>
      <el-form :model="currentApproval" ref="approvalForm" :rules="formRules">
        <el-form-item>
          <el-table :data="approvals">
            <el-table-column prop="stepName" label="节点名称"></el-table-column>
            <el-table-column prop="waitUser" label="处理人"></el-table-column>
            <el-table-column prop="approvalOpinion" label="意见"></el-table-column>
            <el-table-column prop="stepStatus" label="状态">
              <template slot-scope="scope">{{scope.row.stepStatus | statusWord}}</template>
            </el-table-column>
            <el-table-column prop="createTime" label="开始时间"></el-table-column>
            <el-table-column prop="completeTime" label="完成时间"></el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item v-if="needProcess && category > 0" prop="modelType">
          <el-cascader
            placeholder="请选择模型类型"
            v-model="currentApproval.modelType"
            :props="{value:'id',label:'name'}"
            :options="categoryOptions"
            :show-all-levels="false"
            @change="typeChange"
          ></el-cascader>
        </el-form-item>
        <el-form-item v-if="needProcess" prop="approvalOpinion">
          <el-input type="textarea" v-model="currentApproval.approvalOpinion" placeholder="请输入处理意见"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="$router.go(-1)">返回</el-button>
          <el-button v-if="needProcess" @click="approve">通过</el-button>
          <el-button v-if="needProcess" @click="refuse">不通过</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>
<script>
export default {
  data() {
    return {
      approvals: [],
      currentApproval: {},
      categoryOptions: [],
      formRules: {
        modelType: [{ required: true, message: "请选择模型分类" }],
        approvalOpinion: [{ required: true, message: "请输入处理意见" }]
      }
    };
  },
  props: {
    modelId: { required: true },
    flowType: { required: true },
    category: {
      default: 0
    }
  },
  computed: {
    activeStep() {
      for (var i = 0; i < this.approvals.length; i++) {
        if (this.approvals[i].stepStatus == 2) {
          continue;
        } else {
          return i;
        }
      }
      return this.approvals.length - 1;
    },
    needProcess() {
      return this.currentApproval && this.currentApproval.waitUserId;
    }
  },
  filters: {
    statusWord(s) {
      switch (s) {
        case 0:
          return "";
        case 1:
          return "待处理";
        case 2:
          return "审核通过";
        case 3:
          return "审核驳回";
      }
    }
  },
  created() {
    this.fetchData();
    if (this.category && this.category > 0) {
      this.fetchCategory();
    }
  },
  methods: {
    fetchData() {
      var vm = this;
      this.$axios
        .get(
          "/flow/getFlowStep?id=" + this.modelId + "&flowType" + this.flowType
        )
        .then(data => {
          vm.approvals = [].concat(data);
          vm.getCurrentApproval();
        });
    },
    fetchCategory() {
      var vm = this;
      this.$axios.get("/modelCategory/getAll").then(data => {
        vm.getCurrentCategory(data);
      });
    },
    getCurrentCategory(data) {
      var vm = this;
      data.forEach(item => {
        var children = data.filter(child => {
          return child.parentId == item.id;
        });
        if (children && children.length > 0) {
          item.children = children;
        }
      });
      this.categoryOptions = data.filter(item => {
        return item.id == vm.category;
      });
    },
    typeChange(types) {
      if (types && types.length > 0) {
        this.$axios.post(
          "/model/setType?id=" + this.modelId + "&type=" + types.pop()
        );
      }
    },
    getCurrentApproval() {
      var loginUserId = localStorage.getItem("ms_user_id");
      var currents = this.approvals.filter(item => {
        return (
          item.stepStatus == 1 && item.waitUserId.indexOf(loginUserId) >= 0
        );
      });
      if (currents && currents.length > 0) {
        this.currentApproval = Object.assign({}, currents[0]);
      } else {
        this.currentApproval = {};
      }
    },
    approve() {
      var vm = this;
      this.$refs["approvalForm"].validate(valid => {
        if (valid) {
          vm.$axios.post("/flow/approveFlow", vm.currentApproval).then(data => {
            vm.fetchData();
          });
        }
      });
    },
    refuse() {
      var vm = this;
      this.$refs["approvalForm"].validate(valid => {
        if (valid) {
          vm.$axios.post("/flow/refuseFlow", vm.currentApproval).then(data => {
            vm.fetchData();
          });
        }
      });
    }
  }
};
</script>
