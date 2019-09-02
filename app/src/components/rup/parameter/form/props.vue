<template>
    <el-form v-model="info">
        <el-form-item label="输入方式">
            <el-radio-group v-model="info.inputType">
                <el-radio :label="1">表格输入</el-radio>
                <el-radio :label="2">文件上传</el-radio>
            </el-radio-group>
        </el-form-item>
        <el-form-item label="模型描述文件">
            <el-input v-model="info.modelDescript">
                <upload-button slot="append">点击上传</upload-button>
                <download-button slot="append">模板下载</download-button>
            </el-input>
        </el-form-item>
        <el-form-item label="模型想定参数">
            <el-table :data="info.theoryProps">
                <el-table-column prop="id" label="序号"></el-table-column>
                <el-table-column prop="title" label="参数项"></el-table-column>
                <el-table-column prop="name" label="参数名称"></el-table-column>
                <el-table-column prop="type" label="参数类型"></el-table-column>
                <el-table-column prop="during" label="取值范围"></el-table-column>
                <el-table-column prop="unit" label="单位"></el-table-column>
            </el-table>
        </el-form-item>
        <el-form-item label="模型性能参数">
            <el-table :data="info.realProps">
                <el-table-column prop="id" label="序号"></el-table-column>
                <el-table-column prop="title" label="参数项"></el-table-column>
                <el-table-column prop="name" label="参数名称"></el-table-column>
                <el-table-column prop="type" label="参数类型"></el-table-column>
                <el-table-column prop="during" label="取值范围"></el-table-column>
                <el-table-column prop="unit" label="单位"></el-table-column>
            </el-table>
        </el-form-item>
        <el-form-item>
            <el-button>预览</el-button>
            <el-button>保存</el-button>
            <el-button type="primary" @click="saveAndNext">完成</el-button>
        </el-form-item>
    </el-form>
</template>
<script>
export default {
  data() {
    return {
      info: {
        id: 1,
        modelType: 1,
        modelLevel: 2,
        inputType: 1,
        modelDescript: "",
        dataStructures: [
          {
            id: 1,
            structure: "ModelInfo",
            structureItem: "Model",
            type: "double",
            description: "描述"
          },
          {
            id: 2,
            structure: "ModelInfo2",
            structureItem: "Model2",
            type: "double",
            description: "描述"
          }
        ],
        theoryProps: [
          {
            id: 1,
            title: "雷达部署经度",
            name: "Radarintl.on",
            type: "double",
            during: "-180~180",
            unit: "度"
          },
          {
            id: 2,
            title: "雷达部署经度",
            name: "Radarintl.on",
            type: "double",
            during: "-180~180",
            unit: "度"
          }
        ],
        realProps: []
      }
    };
  },
  created() {
    var vm = this;
    this.info.theoryProps.forEach(item => {
      vm.info.realProps.push(item);
    });
  },
  methods: {
    uploadFile(param) {
      var vm = this;
      var form = new FormData();
      form.append("file", param.file);
      var headers = {
        headers: {
          "Content-Type": "multipart/form-data"
          // Expect: "100-continue"
        }
      };
      this.$axios
        .post("/upload/", form, headers)
        .then(data => {
          vm.uploadFileSuccess(data, param.file);
        })
        .catch(e => {
          this.$alert("upload file failure !!!", "tips");
        });
    },
    uploadFileSuccess(data, file) {
      this.$alert("upload file successful", "tips");
    },
    saveAndNext() {
      // this.$router.push({ path: "/model-parameter-upload-second" });
    }
  }
};
</script>
