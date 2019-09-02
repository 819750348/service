<template>
<div class="back-upload">
    <div class="upload-resource-btn-group">
    <el-select v-model="reousrceCategoryId" value-key="value">
        <el-option v-for="item in categorys" :value="item.id" :label="item.name" :key="item.id"></el-option>
    </el-select>
    <el-button-group>
        <el-button @click="toUploadResource">上传</el-button>
    </el-button-group>
    </div>
    <div class="upload-parameter-btn-group">
    <el-button-group>
        <el-button @click="toUploadParameter">上传</el-button>
    </el-button-group>
    </div>
</div>
</template>
<script>
export default {
  data() {
    return {
      categorys:[],
      reousrceCategoryId: 1
    };
  },
  created(){
    this.fetchCategory();
  },
  methods: {
    fetchCategory(){
      var vm = this;
      this.$axios.get('/modelCategory/getRoot')
      .then(data => {
        vm.categorys = [].concat(data);
      })
    },
    toUploadResource(){
      this.$router.push({
        path:"/model-resource-upload-" + this.reousrceCategoryId
      });
    },
    toUploadParameter(data, file) {
      this.$router.push({
        path:"/model-parameter-upload"
      });
    }
  }
};
</script>
<style scoped>
.upload-resource-btn-group {
  position: relative;
  top: 40%;
  text-align: center;
}
.upload-parameter-btn-group {
  position: relative;
  top: 85%;
  text-align: center;
}
</style>
