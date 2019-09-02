<template>
  <el-form-item class="form-item">
    <el-form label-width="100px" label-position="left">
      <el-form-item :label="prop.name" class="form-item">
        <el-input v-model="prop.value"></el-input>
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
.form-item {
  margin-bottom: 0 !important;
}
</style>
