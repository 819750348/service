<template>
  <el-card>
    <div slot="header">
      <span style="font-weight:800;">相关下载</span>
    </div>
    <el-table :data="downloads" :show-header="false">
      <el-table-column>
        <template slot-scope="scope">
          <p>{{scope.row.title}}</p>
          <p>
            <download-button
              type="text"
              :fileId="scope.row.id"
              :fileName="scope.row.name"
            >{{scope.row.name}}</download-button>
          </p>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
</template>
<script>
export default {
  props: {
    info: {
      type: Object,
      default: {}
    }
  },
  computed: {
    downloads() {
      return [
        {
          title: "模型描述文件",
          name: this.info.modelFileName,
          id: this.info.modelFileId
        },
        {
          title: "模型测试报告",
          name: this.info.modelTestReportName,
          id: this.info.modelTestReportId
        },
        {
          title: "模型可信度评估报告",
          name: this.info.modelAssessmentReportName,
          id: this.info.modelAssessmentReportId
        }
      ];
    }
  }
};
</script>