<template>
    <div>
      <el-row :gutter="20">
        <el-col :span="12">
        <el-tree :data="categorys" :props="treeProps" node-key="id" default-expand-all :expand-on-click-node="false">
          <span class="mananger-tree-node" slot-scope="{node,data}">
            <span>{{ node.label }}</span>
            <span>
              <el-button v-if="node.level < 4" type="text" size="mini" @click="addChild(node,data)">添加子分类</el-button>
              <el-button type="text" size="mini" @click="loadNode(data)">修改</el-button>
              <el-button v-if="node.level > 1" type="text" size="mini" @click="removeNode(node,data)">删除</el-button>
            </span>
          </span>
        </el-tree>
        </el-col>
        <el-col :span="12" v-show="formType != 'none'">
          <el-card>
            <div slot="header">
              <span>{{formType | chineseWord}}分类<span v-if="formType == 'create'"> : 上级分类 - {{currentCategory.name}}</span></span>
            </div>
            <div>
            <el-form label-width="80px" v-model="formCategory">
              <el-form-item label="名称">
                <el-input v-model="formCategory.name"/>
              </el-form-item>
              <el-form-item label="图标选项">
                <el-radio-group v-model="formCategory.icon">
                  <el-radio-button class="el-img-small-combat-action" label="el-img-category-combat-action"></el-radio-button>
                  <el-radio-button class="el-img-small-combat-platform" label="el-img-category-combat-platform"></el-radio-button>
                  <el-radio-button class="el-img-small-combat-command" label="el-img-category-combat-command"></el-radio-button>
                  <el-radio-button class="el-img-small-surrounding-nature" label="el-img-category-surrounding-nature"></el-radio-button>
                  <el-radio-button class="el-img-small-surrounding-human" label="el-img-category-surrounding-human"></el-radio-button>
                  <el-radio-button class="el-img-small-weapon-system" label="el-img-category-weapon-system"></el-radio-button>
                  <el-radio-button class="el-img-small-weapon-sensor" label="el-img-category-weapon-sensor"></el-radio-button>
                  <el-radio-button class="el-img-small-weapon-support" label="el-img-category-weapon-support"></el-radio-button>
                  <el-radio-button class="el-img-small-weapon-command" label="el-img-category-weapon-command"></el-radio-button>
                  <el-radio-button class="el-img-small-weapon-communication" label="el-img-category-weapon-communication"></el-radio-button>
                  <el-radio-button class="el-img-small-evaluaction" label="el-img-category-evaluaction"></el-radio-button>
                  <el-radio-button class="el-img-small-test-design" label="el-img-category-test-design"></el-radio-button>
                  <el-radio-button class="el-img-small-data-collection" label="el-img-category-data-collection"></el-radio-button>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="已选图标">
                <div v-if="formCategory.icon" :class="formCategory.icon"></div>
                <div v-else>无</div>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="addOrUpdate">保存</el-button>
              </el-form-item>
            </el-form>
            </div>
          </el-card>
        </el-col>
      </el-row>
    </div>
</template>
<script>
export default {
  data() {
    return {
      treeProps: {
        label: "name",
        children: "children"
      },
      expandOnClickNode: false,
      categorys: [],
      currentCategory: {},
      parentcategory: {},
      formCategoryId: 0,
      formCategory: {
        name: "",
        icon:"",
        parentId: 0
      },
      formType: "none"
    };
  },
  created() {
    this.fetchData();
  },
  filters:{
    chineseWord(type){
      return type == 'edit' ? "修改" : type == 'create' ? '新建' : '';
    }
  },
  methods: {
    fetchData() {
      var vm = this;
      this.$axios
        .get("/modelCategory/getAll")
        .then(data => {
          vm.formCategorys(data);
        })
    },
    formCategorys(data) {
      data.forEach(item => {
        item.children = data.filter(child => {
          return item.id == child.parentId;
        });
      });
      this.categorys = data.filter(item => {
          return item.parentId == 0;
        });
    },
    loadNode(data) {
      this.currentCategory = data;
      this.formCategory.name = data.name;
      this.formCategory.icon = data.icon;
      this.formCategory.parentId = data.parentId;
      this.formCategoryId = data.id;
      this.formType = "edit";
    },
    addChild(node, data) {
      this.currentCategory = data;
      this.formCategory.name = "";
      this.formCategory.icon = "";
      this.formCategory.parentId = data.id;
      this.formType = "create";
    },
    removeNode(node, category) {
      var vm = this;
      vm.$confirm("删除分类?", "提示")
        .then(() => {
          if (category.children && category.children.length > 0) {
            vm.$alert("当前分类存在子分类，不能删除!", "提示");
          } else {
            vm.$axios.get("/modelCategory/delete?id="+category.id)
              .then(data => {
                const parent = node.parent;
                const children = parent.data.children || parent.data;
                const index = children.findIndex(d => d.id === category.id);
                children.splice(index, 1);
              })
          }
        })
    },
    addOrUpdate() {
      this.formType == "edit"
        ? this.updateNode()
        : this.formType == "create"
          ? this.addNode()
          : () => {};
    },
    addNode() {
      var vm = this;
      vm.$axios
        .post("/modelCategory/add", vm.formCategory)
        .then(data => {
          if (!vm.currentCategory.children) {
            vm.$set(vm.currentCategory, "children", []);
          }
          vm.currentCategory.children.push(data);
          vm.formType = "none";
        })
        .catch(e => {
          console.error(e);
        });
    },
    updateNode() {
      var vm = this;
      vm.$axios
        .post(
          "/modelCategory/update",
          Object.assign({ id: this.formCategoryId }, this.formCategory)
        )
        .then(data => {
          vm.currentCategory.name = data.name;
          vm.currentCategory.icon = data.icon;
          vm.currentCategory.parentId = data.parentId;
          vm.formType = "none";
        })
    }
  }
};
</script>
<style scoped>
.mananger-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>