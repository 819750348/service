<template>
  <div>
    <el-row>
      <el-col :span="19">
        <el-container>
          <el-header>
            <el-input type="text" v-model="keys" placeholder="名称/标签/上传人员/上传部门/描述">
              <el-button slot="append" icon="el-icon-search"></el-button>
            </el-input>
          </el-header>
          <el-main>
            <el-row :gutter="5">
              <el-col :span="3">
                <el-table :data="categorys" :show-header="false" :fit="true" style="width:100%;">
                  <el-table-column>
                    <template slot-scope="scope">
                      <span>{{ scope.row.name }}</span>
                      <div v-if="scope.row.children.length > 0" style="padding: 5px;">
                        <el-popover
                          v-for="(second,index) in scope.row.children"
                          v-if="second.children.length > 0"
                          :key="second.id"
                          placement="right"
                          width="400"
                          trigger="click"
                        >
                          <div v-for="third in second.children" :key="third.id" style="margin:5px">
                            <div>
                              <span @click="searchType(third.id)">{{third.name}}</span>
                              <hr>
                            </div>
                            <p>
                              <el-tag
                                v-for="forth in third.children"
                                @click="searchType(forth.id)"
                                :key="forth.id"
                                style="margin:2px"
                              >
                                <span>{{ forth.name }}</span>
                              </el-tag>
                            </p>
                          </div>
                          <el-tag slot="reference" :index="second + index">{{ second.name }}</el-tag>
                        </el-popover>
                        <el-tag
                          v-for="(second,index) in scope.row.children"
                          v-if="second.children.length == 0"
                          :key="second.id"
                          :index="second + index"
                        >{{ second.name }}</el-tag>
                      </div>
                    </template>
                  </el-table-column>
                </el-table>
              </el-col>
              <el-col :span="21">
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
                          <div class="Biaoti">
                            <router-link
                              :to="'/model-resource-preview-'+scope.row.id"
                            >{{scope.row.modelName}}</router-link>
                          </div>
                          <div class="canshu">
                            <i class="el-icon-sm-authority"></i>
                            <span>申请权限</span>
                            <el-button
                              type="text"
                              v-if="scope.row.isStarOn"
                              @click="starOff(scope.row)"
                            >
                              <i class="el-icon-sm-star-on"></i>
                              <span>取消收藏</span>
                            </el-button>
                            <el-button type="text" v-else @click="starOn(scope.row)">
                              <i class="el-icon-sm-star-off"></i>
                              <span>收藏</span>
                            </el-button>
                          </div>
                        </div>
                        <hr>
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
                <el-pagination
                  background
                  layout="prev, pager, next"
                  :total="total"
                  :page-size="pageSize"
                  @current-change="fetchData"
                ></el-pagination>
              </el-col>
            </el-row>
          </el-main>
        </el-container>
      </el-col>
      <el-col :span="5">
        <el-card>
          <div class="el-img-sm-user"></div>
          <el-form style="min-width:100px;width:50%;float:right;">
            <el-form-item label="姓名">{{username}}</el-form-item>
            <el-form-item label="角色">{{rolename}}</el-form-item>
          </el-form>
        </el-card>
        <el-card>
          <div slot="header">
            <span>任务</span>
            <el-button
              v-if="flows.length > 0"
              style="float: right; padding: 3px 0"
              type="text"
              @click="toMyTask"
            >更多</el-button>
          </div>
          <el-table :data="flows" :show-header="false" :fit="true" style="width:100%;">
            <el-table-column prop="modelCode" label="模型编号" min-width="50"></el-table-column>
            <el-table-column prop="modelName" label="模型名称" min-width="50"></el-table-column>
            <el-table-column prop="flowName" label="流程说明" min-width="50"></el-table-column>
            <el-table-column label="操作" min-width="50">
              <template slot-scope="scope">
                <div>
                  <el-button type="text" @click="toFlow(scope.row)">审核</el-button>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
        <el-card>
          <div slot="header">
            <span>通知</span>
          </div>
          <span>预览配置</span>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default {
  data() {
    return {
      keys: "",
      modelTypeId: 1,
      models: [],
      collections: [],
      total: 0,
      pageSize: 20,
      flows: [],
      categorys: [],
      categoryIconMap: {}
    };
  },
  created() {
    this.fetchData(1);
    this.fetchMyTask();
    this.fetchCategory();
  },
  computed: {
    username() {
      return localStorage.getItem("ms_username");
    },
    rolename() {
      return "待定 //todo";
    }
  },
  methods: {
    fetchData(pageNum) {
      var vm = this;
      this.$axios
        .post(
          "/model/getAllModelInfoByKeys?pageNum=" +
            pageNum +
            "&pageSize=" +
            this.pageSize +
            "&type=" +
            this.modelTypeId,
          { keys: this.keys }
        )
        .then(data => {
          vm.models = [].concat(data.list);
          vm.total = data.total;
          vm.pageSize = data.pageSize;
          if (vm.collections.length == 0) {
            vm.fetchCollection();
          }
        });
    },
    searchType(typeId) {
      this.modelTypeId = typeId;
      this.fetchData();
    },
    fetchCollection() {
      var vm = this;
      this.$axios.get("/model/myCollections").then(data => {
        vm.collections = [].concat(data.list);
        vm.checkModels();
      });
    },
    fetchCategory() {
      var vm = this;
      this.$axios.get("/modelCategory/getAll").then(data => {
        vm.formCategorys(data);
      });
    },
    formCategorys(data) {
      var iconMap = {};
      data.forEach(item => {
        if (item.icon) {
          iconMap["icon-" + item.id] = item.icon;
        }
        item.children = data.filter(child => {
          return item.id == child.parentId;
        });
      });
      this.categoryIconMap = Object.assign({}, iconMap);
      this.categorys = data.filter(item => {
        return item.parentId == 0;
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
    },
    fetchMyTask() {
      var vm = this;
      this.$axios.get("/flow/myFlows?pageNum=1&type=1").then(data => {
        vm.flows = [].concat(data.list);
      });
    },
    toMyTask() {
      this.$router.push({
        path: "/model-self-resource-task"
      });
    },
    checkModels() {
      for (var i = 0; i < this.models.length; i++) {
        var starOn = this.collections.filter(item => {
          return this.models[i].id == item.id;
        });
        this.$set(this.models[i], "isStarOn", starOn && starOn.length > 0);
      }
    },
    starOn(model) {
      var vm = this;
      this.$axios
        .get("/model/modelCollection?modelId=" + model.id)
        .then(data => {
          model.isStarOn = true;
          vm.collections.push(model);
        });
    },
    starOff(model) {
      this.$axios
        .get("/model/modelCancelCollection?modelId=" + model.id)
        .then(data => {
          model.isStarOn = false;
          vm.collections = vm.collections.filter(item => {
            return model.id != item;
          });
        });
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