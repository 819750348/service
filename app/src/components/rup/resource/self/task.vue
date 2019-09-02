<template>
  <div>
    <el-container>
      <el-aside width="150">
        <el-tabs v-model="activeName" tab-position="left" @tab-click="handleClick">
          <el-tab-pane label="待处理" name="todo">
          </el-tab-pane>
          <el-tab-pane label="已处理" name="done">
          </el-tab-pane>
        </el-tabs>
      </el-aside>
      <el-main>
        <el-table :data="flows" :show-header="true" :fit="true">
          <el-table-column prop="modelCode" label="模型编号" min-width="100"></el-table-column>
          <el-table-column prop="modelName" label="模型名称" min-width="100"></el-table-column>
          <el-table-column prop="flowName" label="流程说明" min-width="100"></el-table-column>
          <el-table-column v-if="flowType == 2" prop="flowName" label="状态" min-width="100">
            <template slot-scope="scope">
              <div>
                <span>{{scope.row.flowStatus | statusWord}}</span>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="操作" min-width="100">
            <template slot-scope="scope">
              <div>
                <el-button v-if="scope.row.flowStatus < 2" type="text" @click="toFlow(scope.row)">审核</el-button>
                <el-button v-else type="text" @click="toFlow(scope.row)">详情</el-button>
              </div>
            </template>
          </el-table-column>
        </el-table>
      <el-pagination
        background
        layout="prev, pager, next"
        :total="total"
        :page-size="pageSize"
        @current-change="fetchData"
      ></el-pagination>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      activeName:"todo",
      flowType:1,
      flows:[],
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
      this.$axios.get('/flow/myFlows?pageNum='+pageNum+"&pageSize="+this.pageSize+"&type="+this.flowType)
      .then(data =>{
        vm.flows = [].concat(data.list);
        vm.pageSize = data.pageSize;
        vm.total = data.total;
      })
    },
    handleClick(tab,event){
      if(tab.name == 'todo'){
        this.flowType = 1;
      }else if(tab.name == 'done'){
        this.flowType = 2;
      }
      this.fetchData(1);
    },
    toFlow(row){
      this.$router.push({
        path:"/model-approval-process-"+row.modelId+"-"+row.flowType
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
          return "审核通过";
        case 3:
          return "已驳回";
        default:
          return "";
      }
    }
  }
};
</script>

<style scoped>
</style>