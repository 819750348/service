<template>
    <div>
        <el-tabs v-model="activeName">
            <el-tab-pane label="我的上传" name="upload">
                <self-resource-upload :categoryIconMap="categoryIconMap"></self-resource-upload>
            </el-tab-pane>
            <el-tab-pane label="我的下载" name="download">
                <self-resource-download :categoryIconMap="categoryIconMap"></self-resource-download>
            </el-tab-pane>
            <el-tab-pane label="我的收藏" name="collection">
                <self-resource-collection :categoryIconMap="categoryIconMap"></self-resource-collection>
            </el-tab-pane>
            <el-tab-pane label="我的任务" name="task">
                <self-resource-task></self-resource-task>
            </el-tab-pane>
            <el-tab-pane label="我的申请" name="application">
                <self-resource-application></self-resource-application>
            </el-tab-pane>
        </el-tabs>
    </div>
</template>
<script>
import SelfResourceUpload from "./self/upload";
import SelfResourceDownload from "./self/download";
import SelfResourceCollection from "./self/collection";
import SelfResourceTask from "./self/task";
import SelfResourceApplication from "./self/application";

export default {
  data() {
    return {
        activeName:this.type,
        categoryIconMap:{}
    };
  },
  props:{
      type:{
          type:String,
          default:'upload'
      }
  },
  components: {
    SelfResourceUpload,
    SelfResourceDownload,
    SelfResourceCollection,
    SelfResourceTask,
    SelfResourceApplication
  },
  created(){
      this.setCategoryIconMap()
  },
  methods:{
    setCategoryIconMap() {
      var vm = this;
      this.$axios.get("/modelCategory/getAll").then(data => {
        var iconMap = {};
        data.forEach(item => {
            if(item.icon){
                iconMap['icon-'+item.id] = item.icon;
            }
        })
        vm.categoryIconMap = Object.assign({},iconMap);
      });
    }
  }
};
</script>
