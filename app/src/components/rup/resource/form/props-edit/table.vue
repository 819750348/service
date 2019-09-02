<template>
  <el-form-item class="parent-form-item form-item">
    <el-table :data="prop.value">
      <el-table-column v-for="col in prop.columns" :prop="col" :label="col" :key="col">
        <template slot-scope="scope">
          <el-input v-model="scope.row[col]"></el-input>
        </template>
      </el-table-column>
      <el-table-column>
        <template slot="header">
          <el-button type="text" @click="addRow">添加</el-button>
        </template>
        <template slot-scope="scope">
          <el-button type="text" @click="deleteRow(scope.row,scope.$index)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <div>
      <el-button @click="addRow">添加一条新数据</el-button>
    </div>
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
