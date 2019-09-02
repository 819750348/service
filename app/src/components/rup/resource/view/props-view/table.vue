<template>
  <el-form-item class="parent-form-item form-item">
    <el-table :data="prop.value">
      <el-table-column v-for="col in prop.columns" :prop="col" :label="col" :key="col">
      </el-table-column>
    </el-table>
  </el-form-item>
</template>
<script>
export default {
  props: {
    prop: {
      type: Object
    }
  },
  methods: {
    addRow() {
      var obj = {};
      this.prop.columns.forEach(c => {
        obj[c] = "";
      });
      this.prop.value.push(obj);
    },
    deleteRow(row, index) {
      this.prop.value.splice(index, 1);
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
</style>
