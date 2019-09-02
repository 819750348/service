<template>
  <div>
    <el-card>
        <div slot="header">
            <span style="font-weight:800;">模型信息</span>
            <span style="float:right;">模型编号：{{info.modelCode}}</span>
        </div>
      <h3>{{info.modelName}}</h3>
      <p>{{info.description}}</p>
      <p>
        <span v-if="info.modelLabel">
          <h4 v-for="tag in info.modelLabel.split('|')" class="tag" :key="tag">{{tag}}</h4>
        </span>
      </p>
      <el-table :data="details" :show-header="false">
        <el-table-column prop="0"/>
        <el-table-column prop="1"/>
      </el-table>
    </el-card>
  </div>
</template>
<script>
export default {
  props: {
    info: {
      type: Object,
      default: {}
    },
    categoryName:{
        type:String
    }
  },
  computed: {
    details() {
      return [
        [
          "模型类型",
          this.categoryName,
        ],
        [
          "模型开发人员",
          this.info.developer,
        ],
        [
          "模型上传单位",
          this.info.modelDepartment,
        ],
        [
          "联系方式",
          this.info.contactInformation,
        ],
        [
          "模型开放权限",
          this.info.modelPermissions,
        ],
        [
          "模型执行模式",
          this.info.modelExecuteType,
        ],
        [
          "模型开发平台",
          this.info.modelDeveloperPlatform
        ],
        [
          "模型运行平台",
          this.info.modelRuntimePlatform
        ]
      ];
    }
  }
};
</script>
<style scoped>
.tag {
  background: #f0f0f0;
  display: inline;
  padding: 3px 10px;
  font-size: 12px;
  font-weight: normal;
  color: #999999;
}
</style>
