<template>
    <div>
        <el-tabs v-model="activeName">
            <el-tab-pane label="模型资源" name="model">
                <model-resource :categorys="categorys"></model-resource>
            </el-tab-pane>
            <el-tab-pane label="入库审批" name="approval">
                <storage-approval :categorys="categorys"></storage-approval>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>
<script>
import ModelResource from "./resource";
import StorageApproval from "./storage-approval";

export default {
  data() {
    return {
      activeName: "model",
      categorys:[]
    };
  },
  components: {
    ModelResource,
    StorageApproval
  },
  created(){
      this.fetchCategory();
  },
  methods:{
      fetchCategory(){
      var vm = this;
      this.$axios.get('/modelCategory/getRoot')
      .then(data => {
        vm.categorys = [].concat(data);
      })
      }
  }
};
</script>
