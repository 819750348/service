<template>
  <div>
    <el-tabs tab-position="right">
      <el-tab-pane v-for="(view,viewIndex) in views" :label="view.name" :key="viewIndex">
        <el-form size="mini" label-position="left">
          <template v-if="view.isArray">
            <div v-for="(child,childIndex) in view.value" :key="view.name +'-' +child.name + '-' +childIndex">
              <div v-if="child.type !== 0" class="top-title form-item">
                <span class="title-head">|</span>
                {{child.name}}
              </div>
              <view-value v-if="child.type === 0" :prop="child"></view-value>
              <el-form-item class="form-item">
                <el-form label-position="left">
                  <view-object v-if="child.type === 1" :prop="child"></view-object>
                  <view-table v-if="child.type === 2" :prop="child"></view-table>
                  <view-complex v-if="child.type === 3" :prop="child"></view-complex>
                </el-form>
              </el-form-item>
            </div>
          </template>
          <template v-else>
            <div v-if="view.value.type !== 0" class="top-title form-item">
              <span class="title-head">|</span>
              {{view.value.name}}
            </div>
            <view-value v-if="view.value.type === 0" :prop="view.value"></view-value>
            <view-object v-if="view.value.type === 1" :prop="view.value"></view-object>
            <view-table v-if="view.value.type === 2" :prop="view.value"></view-table>
            <view-complex v-if="view.value.type === 3" :prop="view.value"></view-complex>
          </template>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
import ViewValue from "./props-view/value";
import ViewObject from "./props-view/object";
import ViewTable from "./props-view/table";
import ViewComplex from "./props-view/complex";
export default {
  props: {
    views: {
      type: Array,
      default: []
    }
  },
  components: {
    ViewValue,
    ViewObject,
    ViewTable,
    ViewComplex
  }
};
</script>
