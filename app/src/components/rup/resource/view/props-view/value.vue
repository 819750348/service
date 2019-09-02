<template>
  <el-form-item class="form-item">
    <el-form label-width="100px" label-position="left">
      <el-form-item :label="prop.name" class="form-item">
        <span>{{prop.value}}</span>
      </el-form-item>
    </el-form>
  </el-form-item>
</template>
<script>
export default {
  name:'prop-value',
  props: {
    prop: {
      type: Object
    }
  }
};
</script>
<style scoped>
.form-item {
  margin-bottom: 0 !important;
}
</style>
