<template>
  <el-form-item class="parent-form-item form-item">
    <el-form v-for="(item,index) in prop.value" :key="prop.name + '-' + index">
      <el-form-item v-if="item.type !== 0" :label="item.name" class="sub-title form-item"/>
      <prop-value v-if="item.type === 0" :prop="item" class="sub-title"></prop-value>
      <el-form-item class="parent-form-item form-item">
        <el-form label-position="left">
          <prop-object v-if="item.type === 1" :prop="item"></prop-object>
          <prop-table v-if="item.type === 2" :prop="item"></prop-table>
        </el-form>
      </el-form-item>
    </el-form>
  </el-form-item>
</template>
<script>
import PropValue from "./value";
import PropObject from "./object";
import PropTable from "./table";
export default {
  components: {
    PropValue,
    PropObject,
    PropTable
  },
  props: {
    prop: {
      type: Object
    }
  }
};
</script>
<style scoped>
.parent-form-item {
  padding-left: 15px;
}
.form-item {
  margin-bottom: 0 !important;
}
.sub-title {
  font-size: 14px !important;
  font-weight: bold !important;
}
</style>
