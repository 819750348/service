<template>
<div>
<el-form ref="form" :model="index" label-width="100px">
  <el-form-item label="Name">
    <el-input v-model="index.name"/>
  </el-form-item>
  <el-form-item label="Path">
    <el-input v-model="index.path"/>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="doUpdate">Save</el-button>
    <el-button type="primary" @click="goBack">Back</el-button>
  </el-form-item>
</el-form>
</div>
</template>
<script>
export default {
  data() {
    return {
      index: {},
      role: {
        status: false
      }
    };
  },
  props: ["roleId"],
  created() {
    if (this.roleId && this.roleId > 0) {
      this.fetchData();
    }
  },
  methods: {
    fetchData() {
      var vm = this;
      this.$axios
        .get("/api/index/search/findByRoleId?roleId=" + this.roleId)
        .then(response => {
          var array = response.data._embedded.indexes;
          if (array && array.length > 0) {
            vm.index = Object.assign({}, {}, array[0]);
          }
        })
        .catch(e => {
          console.error(e);
        });
    },
    doUpdate() {
      var vm = this;
      vm.index.roleId = vm.roleId;
      this.$axios
        .post("/api/index", vm.index)
        .then(response => {
            vm.index = response.data
        })
        .catch(e => {});
    },
    goBack() {
      this.$router.go(-1);
    }
  }
};
</script>
