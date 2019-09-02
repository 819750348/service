<template>
  <div>
    <el-tabs v-model="activeName">
      <el-tab-pane label="参数资源" name="parameter">
        <parameter-resource :categoryIconMap="categoryIconMap"></parameter-resource>
      </el-tab-pane>
      <el-tab-pane label="入库审批" name="approval">
        <storage-approval :categoryIconMap="categoryIconMap"></storage-approval>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import ParameterResource from "./form/resource";
import StorageApproval from "./form/storage-approval";

export default {
  data() {
    return {
      activeName: "parameter",
      categoryIconMap: {}
    };
  },
  created() {
    this.setCategoryIconMap();
  },
  components: {
    ParameterResource,
    StorageApproval
  },
  methods: {
    setCategoryIconMap() {
      var vm = this;
      this.$axios.get("/modelCategory/getAll").then(data => {
        var iconMap = {};
        data.forEach(item => {
          if (item.icon) {
            iconMap["icon-" + item.id] = item.icon;
          }
        });
        vm.categoryIconMap = Object.assign({}, iconMap);
      });
    }
  }
};
</script>
