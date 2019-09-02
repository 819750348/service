<template>
  <el-form-item class="parent-form-item form-item">
    <el-form v-model="prop.value" label-width="150px" label-position="left">
      <el-form-item v-for="(value,name) in prop.value" :key="name" :label="name" class="form-item">
        <span>{{value}}</span>
      </el-form-item>
    </el-form>
  </el-form-item>
</template>
<script>
export default {
  props: {
    prop: {
      type: Object
    }
  }
};
</script>
<style scoped>
.parent-form-item{
     padding-left:15px;
}
.form-item {
  margin-bottom: 0 !important;
}
</style>
