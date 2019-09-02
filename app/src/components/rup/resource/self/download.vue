<template>
  <div>
    <el-container>
      <el-aside width="150">
        <el-tabs v-model="activeName" tab-position="left" @tab-click="tabChange">
          <el-tab-pane label="全部" name="all">
            
          </el-tab-pane>
          <el-tab-pane label="完整模型" name="complete">

          </el-tab-pane>
          <el-tab-pane label="模型组件" name="component">

          </el-tab-pane>
          <el-tab-pane label="模型数据" name="data">

          </el-tab-pane>
        </el-tabs>
      </el-aside>
      <el-main>
        <el-table :data="models" :show-header="false" :fit="true">
          <el-table-column min-width="140">
            <template slot-scope="scope">
              <div :class="getCategoryIcon(scope.row.category,scope.row.type)"></div>
            </template>
          </el-table-column>
          <el-table-column min-width="500">
            <template slot-scope="scope">
              <!-- 模板 -->
              <div>
                  <div style="width:100%; float:left">
                    <div class="Biaoti">{{scope.row.modelName}}</div>
                    <div class="canshu">
                    </div>
                  </div>
                  <p class="text"><a href="#">{{scope.row.description}}</a></p>
                  <div class="can_div">
                    <div class="can_left">型号参数： </div>
                    <div class="can_right">标签：
                        <span v-if="scope.row.modelLabel">
                            <h4 v-for="tag in scope.row.modelLabel.split('|')" class="biaoshi" :key="tag">{{tag}}</h4>
                        </span>
                    </div>
                  </div>
                  <div class="subit">上传者：{{scope.row.updateUser}}   单位：{{scope.row.modelDepartment}}&nbsp; 上传时间：{{scope.row.createTime.substring(0,10)}}</div>
                  <div class="subit">阅读量：{{scope.row.pv}}      下载量：{{scope.row.downloadCount}}</div>
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
      </el-main>
    </el-container>
  </div>
</template>
<script>
export default {
  data() {
    return {
      models:[],
      total:0,
      pageSize:15,
      activeName:"all"
    };
  },
  created(){
    this.fetchData(1);
  },
  props:{
    categoryIconMap:{
      type:Object,
      default:{},
      required:true
    }
  },
  methods:{
    fetchData(pageNum){
      var vm = this;
      this.$axios.get("/model/myDownloads?pageNum="+pageNum+"&pageSize="+vm.pageSize)
      .then(data => {
          vm.models = [].concat(data.list);
          vm.total = data.total;
          vm.pageSize = data.pageSize;
      })
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
    tabChange(tab){
      if(tab.name == 'all'){

      }else if(tab.name == ''){

      }
    }
  }
};
</script>

<style scoped>
.m_left{width:140px; height:140px; float:left; margin-right:15px;}
.m_right{float:left; width:520px;}
.Biaoti{float:left; font-size:18px; font-weight:bold; color:#348aff;}
.canshu{float:right; color:#999; font-size:12px;} .canshu a:hover{ color:#fe8a2d;}
.canshu a{ color:#999; text-decoration:none;}
.text{width:100%; display:block; float:left; height:38px; line-height:38px;}
.text a{ text-decoration:none;} .text a:hover{ text-decoration:underline;}
.can_div{width:100%; display:block; float:left; height:28px; line-height:28px;}
.can_left{float:left; color:#999999; font-size:14px;}
.can_right{float:right; color:#999999; font-size:14px;}
.biaoshi{ background:#f0f0f0;; display:inline; padding:3px 10px; font-size:12px; font-weight:normal; color:#999999; }
.subit{width:100%; color:#999; display:block; font-size:14px; height:28px; line-height:28px; float:left}
.line{background:#f0f0f0; height:1px; width:100%; float:left; margin-top:15px; margin-bottom:25px;}
</style>