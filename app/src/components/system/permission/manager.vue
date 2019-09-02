<template>
    <div>
      <el-row :gutter="20">
        <el-col :span="12">
        <el-tree :data="permissions" :props="treeProps" node-key="id" default-expand-all :expand-on-click-node="false" >
          <span class="mananger-tree-node" slot-scope="{node,data}">
          <span>{{ node.label }}({{data.pattern}} - {{data.method}})</span>
          <span>
            <el-button type="text" size="mini" @click="addChild(node,data)">Add Child</el-button>
            <el-button v-if="node.level > 1" type="text" size="mini" @click="loadNode(data)">Edit</el-button>
            <el-button v-if="node.level > 1" type="text" size="mini" @click="removeNode(node,data)">Remove</el-button>
          </span>
        </span>
        </el-tree>
        </el-col>
        <el-col :span="12" v-show="formType != 'none'">
          <el-card>
            <div slot="header">
              <span>{{formType}} Permission<span v-if="formType == 'create'"> : parent - {{currentPermission.name}}({{currentPermission.pattern}})</span></span>
            </div>
            <div>
          <el-form label-width="80px" v-model="form.permission">
            <el-form-item label="Name">
              <el-input v-model="form.permission.name"/>
            </el-form-item>
            <el-form-item label="Pattern">
              <el-input v-model="form.permission.pattern"/>
            </el-form-item>
            <el-form-item label="Method">
              <el-select v-model="form.permission.method" placeholder="请选择">
                <el-option v-for="item in httpMethods" :key="item" :label="item" :value="item" />
              </el-select>
            </el-form-item>
            <el-form-item label="Description">
              <el-input type="textarea" rows="5" v-model="form.permission.description"/>
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
        pattern: "pattern",
        children: "children"
      },
      httpMethods:["ALL","GET","POST","PUT","DELETE","HEAD","PATCH","OPTIONS","TRACE"],
      expandOnClickNode: false,
      permissions: [],
      currentPermission: {},
      parentPermission: {},
      form: {
        permission: {
          name: "",
          pattern: "",
          method: "",
          parentId: 0,
          description: ""
        }
      },
      formType: "none",
      formPermissionId: 0
    };
  },
  created() {
    this.fetchData();
  },
  methods: {
    fetchData() {
      var vm = this;
      this.$axios
        .get("/api/permission")
        .then(response => {
          vm.formPermissions(response.data._embedded.permissions);
        })
        .catch(error => {
          console.log(error);
        });
    },
    formPermissions(data) {
      data.forEach(item => {
        item.children = data.filter(child => {
          return item.id == child.parentId;
        });
      });
      var rootNode = {
        id: 0,
        name: "系统权限",
        pattern: "system-permission",
        children: data.filter(item => {
          return item.parentId == 0;
        })
      };
      this.permissions = [rootNode];
    },
    loadNode(data) {
      this.currentPermission = data;
      this.form.permission.name = data.name;
      this.form.permission.pattern = data.pattern;
      this.form.permission.method = data.method;
      this.form.permission.parentId = data.parentId;
      this.form.permission.level = data.level;
      this.form.permission.description = data.description;
      this.formPermissionId = data.id;
      this.formType = "edit";
    },
    addChild(node, data) {
      this.currentPermission = data;
      this.form.permission.name = "";
      this.form.permission.pattern = "";
      this.form.permission.method = "";
      this.form.permission.description = "";
      this.form.permission.parentId = data.id;
      this.form.permission.level = data.level + 1;
      this.formType = "create";
    },
    removeNode(node, data) {
      var vm = this;
      vm.$confirm("remove node ?", "tips")
        .then(() => {
          if (data.children && data.children.length > 0) {
            vm.$alert('remove node has children,can not delete!','tips');
          } else {
            vm.$axios({
              method: "delete",
              url: data._link.self.href
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
        .post("/api/permission", vm.form.permission)
        .then(response => {
          if (!vm.currentPermission.children) {
            vm.$set(vm.currentPermission, "children", []);
          }
          vm.currentPermission.children.push(response.data);
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
          "/api/permission",
          Object.assign({ id: this.formPermissionId }, this.form.permission)
        )
        .then(response => {
          vm.currentPermission.name = response.data.name;
          vm.currentPermission.method = response.data.method;
          vm.currentPermission.pattern = response.data.pattern;
          vm.currentPermission.description = response.data.description;
          vm.currentPermission.parentId = response.data.parentId;
          vm.currentPermission.level = response.data.level;
          vm.formType = "none";
        })
        .catch(e => {
          console.error(e);
        });
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