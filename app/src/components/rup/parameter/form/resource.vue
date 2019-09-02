<template>
  <div>
    <el-container>
      <el-aside width="150">
        <el-tabs v-model="activeName" tab-position="left">
          <el-tab-pane label="全部模型" name="all"></el-tab-pane>
          <el-tab-pane label="装备模型" name="equipment"></el-tab-pane>
          <el-tab-pane label="作战模型" name="combat"></el-tab-pane>
          <el-tab-pane label="环境模型" name="condition"></el-tab-pane>
          <el-tab-pane label="评估模型" name="assess"></el-tab-pane>
          <el-tab-pane label="支撑类模型" name="support"></el-tab-pane>
        </el-tabs>
      </el-aside>
      <el-main>
        <el-form>
          <el-form-item>
            <el-input placeholder="模型名称/编号">
              <el-button slot="append">搜索</el-button>
            </el-input>
          </el-form-item>
          <el-form-item label="全部">
            <el-table :data="models" :show-header="false" :fit="true">
              <el-table-column min-width="140">
                <template slot-scope="scope">
                  <div :class="getCategoryIcon(scope.row.category,scope.row.type)"></div>
                </template>
              </el-table-column>
              <el-table-column min-width="500">
                <template slot-scope="scope">
                  <!--列表-->
                  <div>
                    <div style="width:100%; float:left">
                      <div class="Biaoti">{{scope.row.modelName}}</div>
                      <div class="canshu">
                        <router-link :to="'/model-approval-process-'+scope.row.id">查看流程</router-link>
                      </div>
                    </div>
                    <p class="text">
                      <a href="#">{{scope.row.description}}</a>
                    </p>
                    <div class="can_div">
                      <div class="can_left">型号参数：</div>
                      <div class="can_right">
                        标签：
                        <span v-if="scope.row.modelLabel">
                          <h4
                            v-for="tag in scope.row.modelLabel.split('|')"
                            class="biaoshi"
                            :key="tag"
                          >{{tag}}</h4>
                        </span>
                      </div>
                    </div>
                    <div
                      class="subit"
                    >上传者：{{scope.row.updateUser}} 单位：{{scope.row.modelDepartment}}&nbsp; 上传时间：{{scope.row.createTime.substring(0,10)}}</div>
                    <div class="subit">阅读量：{{scope.row.pv}} 下载量：{{scope.row.downloadCount}}</div>
                  </div>
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      models: [],
      activeName: "all"
    };
  },
  created() {
    this.fetchData();
  },
  props:{
    categoryIconMap: {
      type: Object,
      default:{},
      required: true
    }
  },
  methods: {
    fetchData() {
      var vm = this;
      this.$axios.get("/model/getMyUploadList").then(data => {
        vm.models = [].concat(data);
      });
    },
    getCategoryIcon(category, type) {
      var icon = this.categoryIconMap["icon-" + type];
      if (!icon) {
        icon = this.categoryIconMap["icon-" + category];
        if (!icon) {
          icon = "el-img-category-data-collection";
        }
      }
      return icon;
    }
  }
};
</script>

<style scoped>
.m_left {
  width: 140px;
  height: 140px;
  float: left;
  margin-right: 15px;
}
.m_right {
  float: left;
  width: 520px;
}
.Biaoti {
  float: left;
  font-size: 18px;
  font-weight: bold;
  color: #348aff;
}
.canshu {
  float: right;
  color: #999;
  font-size: 12px;
}
.canshu a:hover {
  color: #fe8a2d;
}
.canshu a {
  color: #999;
  text-decoration: none;
}
.text {
  width: 100%;
  display: block;
  float: left;
  height: 38px;
  line-height: 38px;
}
.text a {
  text-decoration: none;
}
.text a:hover {
  text-decoration: underline;
}
.can_div {
  width: 100%;
  display: block;
  float: left;
  height: 28px;
  line-height: 28px;
}
.can_left {
  float: left;
  color: #999999;
  font-size: 14px;
}
.can_right {
  float: right;
  color: #999999;
  font-size: 14px;
}
.biaoshi {
  background: #f0f0f0;
  display: inline;
  padding: 3px 10px;
  font-size: 12px;
  font-weight: normal;
  color: #999999;
}
.subit {
  width: 100%;
  color: #999;
  display: block;
  font-size: 14px;
  height: 28px;
  line-height: 28px;
  float: left;
}
.line {
  background: #f0f0f0;
  height: 1px;
  width: 100%;
  float: left;
  margin-top: 15px;
  margin-bottom: 25px;
}
</style>