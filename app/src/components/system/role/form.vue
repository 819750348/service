<template>
<div>
<el-form ref="form" :model="role" label-width="100px">
  <el-form-item label="Name">
    <el-input v-model="role.name"/>
  </el-form-item>
  <el-form-item label="Code">
    <el-input v-model="role.code"/>
  </el-form-item>
  <el-form-item label="Layout">
    <el-select v-model="role.layout">
      <el-option value="manage" label="manage"/>
      <el-option value="custom" label="custom"/>
    </el-select>
  </el-form-item>
  <el-form-item label="Status">
    <el-switch v-model="role.status"/>
  </el-form-item>
  <el-form-item label="CreateTime">
    <el-date-picker v-model="role.createTime" type="datetime" placeholder="please select datetime"/>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="doUpdate">Save</el-button>
    <el-button type="primary" @click="goBack">Back</el-button>
  </el-form-item>
</el-form>
</div>
</template>
<script>
export default{
  data(){
    return {
      role:{
        status: false
      },
    }
  },
  props:["id"],
  created(){
    if(this.id && this.id > 0){
      this.fetchData();
    }
  },
  methods:{
    fetchData(){
      var vm = this;
      this.$axios.get("/api/role/"+this.id)
      .then(response => {
        vm.role = Object.assign({},{},response.data);
      })
      .catch(e => {
        console.log(e);
      });
    },
    doUpdate(){
      this.$axios.post("/api/role",this.role)
      .then(response => {
        console.log(JSON.stringify(response));
      })
      .catch(exception => {
        console.log(JSON.stringify(exception));
      })
    },
    goBack(){
      this.$router.go(-1);
    }
  }
}
</script>
