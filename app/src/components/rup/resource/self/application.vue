<template>
  <div>
    <el-container>
      <el-aside width="150">
        <el-tabs v-model="activeName" tab-position="left" @tab-click="handleClick">
          <el-tab-pane label="未完成" name="undone">
            
          </el-tab-pane>
          <el-tab-pane label="已完成" name="completed">

          </el-tab-pane>
        </el-tabs>
      </el-aside>
      <el-main>
        <el-table :data="applications" :show-header="true" :fit="true">
          <el-table-column prop="modelCode" label="模型编号" min-width="100"></el-table-column>
          <el-table-column prop="modelName" label="模型名称" min-width="100"></el-table-column>
          <el-table-column prop="flowName" label="流程说明" min-width="100"></el-table-column>
          <el-table-column label="申请类型" min-width="100">
            <template slot-scope="scope">
              <div>
                <el-button type="text" @click="toModelView(scope.row)" :disabled="scope.row.flowType != 2">下载</el-button>
              </div>
            </template>
          </el-table-column>
          <el-table-column min-width="100" label="操作">
            <template slot-scope="scope">
              <div>
                <el-button type="text" @click="toFlow(scope.row)">详细信息</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      activeName:"undone",
      flowType:1,
      applications:[],
      total:0,
      pageSize:15
    };
  },
  created(){
    this.fetchData(1);
  },
  methods:{
    fetchData(pageNum){
      var vm = this;
      this.$axios.get('/flow/myApplies?pageNum='+pageNum+"&pageSize="+this.pageSize+"&type="+this.flowType)
      .then(data =>{
        vm.applications = [].concat(data.list);
        vm.pageSize = data.pageSize;
        vm.total = data.total;
      })
    },
    handleClick(tab,event){
      if(tab.name == 'undone'){
        this.flowType = 1;
      }else if(tab.name == 'completed'){
        this.flowType = 2;
      }
      this.fetchData(1);
    },
    toFlow(row){
      this.$router.push({
        path:"/model-view-approval-process-"+row.modelId+"-"+row.flowType
      });
    },
    toModelView(row){
      this.$router.push({
        path:"/model-resource-preview-"+row.modelId
      });
    }
  },
  filters:{
    statusWord(s) {
      switch (s) {
        case 0:
          return "草稿;";
        case 1:
          return "待审核";
        case 2:
          return "2审核通过";
        case 3:
          return "驳回";
        default:
          return "";
      }
    }
  }
};
</script>

<style scoped>
</style>