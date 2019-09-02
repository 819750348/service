<template>
    <div>
      <el-row :gutter="20">
        <el-col :span="12">
        <el-tree :data="menus" :props="treeProps" node-key="id" default-expand-all :expand-on-click-node="false" draggable
        @node-drag-end="handleDragEnd"
        :allow-drag="allowDrag"
        :allow-drop="allowDrop">
          <span class="mananger-tree-node" slot-scope="{node,data}">
            <span><i v-if="node.icon" :class="node.icon"></i>{{ node.label }}({{data.code}})</span>
            <span>
              <el-button v-if="node.level < 4" type="text" size="mini" @click="addChild(node,data)">Add Child</el-button>
              <el-button v-if="node.level > 1" type="text" size="mini" @click="loadNode(data)">Edit</el-button>
              <el-button v-if="node.level > 1" type="text" size="mini" @click="removeNode(node,data)">Remove</el-button>
            </span>
          </span>
        </el-tree>
        </el-col>
        <el-col :span="12" v-show="formType != 'none'">
          <el-card>
            <div slot="header">
              <span>{{formType}} Menu<span v-if="formType == 'create'"> : parent - {{currentMenu.name}}({{currentMenu.code}})</span></span>
            </div>
            <div>
            <el-form label-width="80px" v-model="formMenu">
              <el-form-item label="Name">
                <el-input v-model="formMenu.name"/>
              </el-form-item>
              <el-form-item label="Code">
                <el-input v-model="formMenu.code"/>
              </el-form-item>
              <el-form-item label="Icon">
                <el-input v-model="formMenu.icon"/>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="addOrUpdate">Save</el-button>
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
        code: "code",
        children: "children"
      },
      expandOnClickNode: false,
      menus: [],
      currentMenu: {},
      parentMenu: {},
      formMenuId: 0,
      formMenu: {
        name: "",
        code: "",
        icon: "",
        parentId: 0,
        level: 0
      },
      formType: "none"
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      var vm = this;
      this.$axios
        .get("/api/menu?size=1000")
        .then(response => {
          vm.formMenus(response.data._embedded.menus);
        })
        .catch(error => {
        });
    },
    formMenus(data) {
      data.forEach(item => {
        item.children = data.filter(child => {
          return item.id == child.parentId;
        });
      });
      var rootNode = {
        id: 0,
        name: "系统菜单",
        code: "system-menu",
        children: data.filter(item => {
          return item.parentId == 0;
        })
      };
      this.menus = [rootNode];
    },
    loadNode(data) {
      this.currentMenu = data;
      this.formMenu.name = data.name;
      this.formMenu.code = data.code;
      this.formMenu.icon = data.icon;
      this.formMenu.parentId = data.parentId;
      this.formMenu.level = data.level;
      this.formMenuId = data.id;
      this.formType = "edit";
    },
    addChild(node, data) {
      this.currentMenu = data;
      this.formMenu.name = "";
      this.formMenu.code = "";
      this.formMenu.icon = "";
      this.formMenu.parentId = data.id;
      this.formMenu.level = node.level + 1;
      this.formType = "create";
    },
    removeNode(node, data) {
      var vm = this;
      vm.$confirm("remove node ?", "tips")
        .then(() => {
          if (data.children && data.children.length > 0) {
            vm.$alert("remove node has children,can not delete!", "tips");
          } else {
            vm.$axios({
              method: "delete",
              url: data._links.self.href
            })
              .then(response => {
                const parent = node.parent;
                const children = parent.data.children || parent.data;
                const index = children.findIndex(d => d.id === data.id);
                children.splice(index, 1);
              })
              .catch(e => {
                console.error(e);
              });
          }
        })
        .catch(e => {
          console.error(e);
        });
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
        .post("/api/menu", vm.formMenu)
        .then(response => {
          if (!vm.currentMenu.children) {
            vm.$set(vm.currentMenu, "children", []);
          }
          vm.currentMenu.children.push(response.data);
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
          "/api/menu",
          Object.assign({ id: this.formMenuId }, this.formMenu)
        )
        .then(response => {
          vm.currentMenu.name = response.data.name;
          vm.currentMenu.code = response.data.code;
          vm.currentMenu.icon = response.data.icon;
          vm.currentMenu.level = response.data.level;
          vm.currentMenu.parentId = response.data.parentId;
          vm.formType = "none";
        })
        .catch(e => {
        });
    },
    handleDragEnd(draggingNode, dropNode, dragType, ev) {
      var parentId = 0;
      var vm = this;
      if (dragType == "inner") {
        parentId = dropNode.data.id;
      } else if (dragType == "before" || dragType == "after") {
        parentId = dropNode.data.parentId;
      }
      this.$confirm("move node ?", "tips")
        .then(() => {
          draggingNode.data.parentId = parentId;
          vm.$axios
            .post("/api/menu", draggingNode.data)
            .then(response => {})
            .catch(e => {
              console.error(e);
            });
        })
        .catch(e => {
          console.error(e);
        });
    },
    allowDrag(draggingNode) {
      return draggingNode.level > 1;
    },
    allowDrop(draggingNode, type) {
      return draggingNode.level > 1;
    }
  }
};
</script>
<style>
.mananger-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
}
</style>