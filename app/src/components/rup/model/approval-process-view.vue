<template>
  <el-container>
    <el-header>
      <el-steps :active="activeStep" :space="150">
        <el-step v-for="item in approvals" :title="item.stepName" :key="item.id"></el-step>
      </el-steps>
    </el-header>
    <el-main>
      <el-form>
        <el-form-item>
          <el-table :data="approvals">
            <el-table-column prop="stepName" label="节点名称"></el-table-column>
            <el-table-column prop="waitUser" label="处理人"></el-table-column>
            <el-table-column prop="approvalOpinion" label="意见"></el-table-column>
            <el-table-column prop="approvalOpinion" label="状态">
              <template slot-scope="scope">{{scope.row.stepStatus | statusWord}}</template>
            </el-table-column>
            <el-table-column prop="createTime" label="开始时间"></el-table-column>
            <el-table-column prop="completeTime" label="完成时间"></el-table-column>
          </el-table>
        </el-form-item>
        <el-form-item>
          <el-button @click="$router.go(-1)">返回</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>
<script>
export default {
  data() {
    return {
      approvals: []
    };
  },
  props: {
    modelId: { required: true },
    flowType: { required: true }
  },
  computed: {
    activeStep() {
      for (var i = 0; i < this.approvals.length; i++) {
        if (this.approvals[i].stepStatus == 2) {
          continue;
        } else if (this.approvals[i].stepStatus == 1) {
          return i;
        } else {
          return 0;
        }
      }
      return 0;
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
  },
  methods: {
    fetchData() {
      var vm = this;
      this.$axios.get("/flow/getFlowStep?id=" + this.modelId+"&flowType"+this.flowType).then(data => {
        vm.approvals = [].concat(data);
      });
    }
  }
};
</script>
