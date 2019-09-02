<template>
  <el-container>
    <el-aside>
      <view-info :info="model.modelInfo" :categoryName="categoryName"></view-info>
      <view-file :info="model.modelInfo"></view-file>
    </el-aside>
    <el-main>
      <view-props :views="model.modelViews"></view-props>
    </el-main>
  </el-container>
</template>
<script>
import ViewInfo from "./view/info";
import ViewProps from "./view/props";
import ViewFile from "./view/file";

export default {
  data() {
    return {
      uploadConfig: {},
      categorys: [],
      model: {
        modelInfo: {},
        modelViews: []
      }
    };
  },
  props: {
    modelId: {
      required: true
    }
  },
  components: {
    ViewInfo,
    ViewProps,
    ViewFile
  },
  created() {
    this.fetchData();
    this.fetchCategory();
  },
  computed: {
    categoryName() {
      var array = this.categorys.filter(item => {
        return this.model.modelInfo.id == item.id;
      });
      if(array && array.length > 0){
        return array[0].name;
      }
      return '';
    }
  },
  methods: {
    fetchData() {
      var vm = this;
      this.$axios.get("/model/getModelInfo?id=" + this.modelId).then(data => {
        vm.model = Object.assign({}, data);
      });
    },
    fetchCategory() {
      var vm = this;
      this.$axios.get("/modelCategory/getRoot").then(data => {
        vm.categorys = [].concat(data);
      });
    },
    back() {
      this.$router.go(-1);
    }
  }
};
</script>
