<template>
<div>
<el-form ref="form" :model="user" label-width="100px">
  <el-form-item label="Userame">
    <el-input v-model="user.username"/>
  </el-form-item>
  <el-form-item label="CreateTime">
    <el-date-picker v-model="user.createTime" type="datetime" placeholder="please select datetime"/>
  </el-form-item>
  <el-form-item label="UpdateTime">
    <el-date-picker v-model="user.updateTime" type="datetime" placeholder="please select datetime"/>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="doUpdate">Save</el-button>
    <el-button type="primary" @click="goBack">Back</el-button>
  </el-form-item>
</el-form>
</div>
</template>
<script>
import Vue from "vue";

export default {
  data() {
    return {
      user: {}
    };
  },
  props: ["id"],
  created() {
    if (this.id && this.id > 0) {
      this.fetchData();
    }
  },
  methods: {
    fetchData() {
      var vm = this;
      this.$axios
        .get("/api/user/" + this.id)
        .then(response => {
          vm.user = Object.assign({}, {}, response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    doUpdate() {
      this.$axios
        .post("/api/user", this.user)
        .then(response => {
          console.log(JSON.stringify(response));
        })
        .catch(exception => {
          console.log(JSON.stringify(exception));
        });
    },
    goBack() {
      this.$router.go(-1);
    }
  }
};
</script>
