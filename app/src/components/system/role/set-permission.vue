<template>
    <div>
        <el-card>
            <div slot="header" class="clearfix">
              <span>Check Permission</span>
              <el-button type="text" style="float:right;padding:3px 0" @click="saveRolePermission">save</el-button>
            </div>
            <div>
            <el-tree ref="permissionTree" :data="permissions" :props="treeProps" node-key="id" :check-on-click-node="true" :check-strictly="true" :expand-on-click-node="false" show-checkbox default-expand-all >
                <span class="mananger-tree-node" slot-scope="{node,data}">
                <span>{{ node.label }}({{data.pattern}} - {{data.method}})</span>
                </span>
            </el-tree>
            </div>
        </el-card>
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
      permissions: [],
      permissionRoles: []
    };
  },
  props: ["roleId"],
  created() {
    if (this.roleId && this.roleId > 0) {
      this.fetchData();
    }
  },
  methods: {
    fetchData() {
      var vm = this;
      this.$axios
        .get("/api/permission")
        .then(response => {
          vm.formData(response.data._embedded.permissions);
        })
        .catch(error => {
          console.log(error);
        });
    },
    formData(data) {
      data.forEach(item => {
        item.children = data.filter(child => {
          return item.id == child.parentId;
        });
      });
      this.permissions = data.filter(item => {
        return item.parentId == 0;
      });
      this.fetchCheckeds();
    },
    fetchCheckeds() {
      var vm = this;
      this.$axios
        .get("/api/permission_role/search/findByRoleId?roleId=" + this.roleId)
        .then(response => {
          vm.permissionRoles = response.data._embedded.permissionRoles;
          vm.formCheckeds(vm.permissionRoles);
        })
        .catch(e => {
          console.log(e);
        });
    },
    formCheckeds(checkeds) {
      var vm = this;
      var checkedPermissionIds = [];
      checkeds.forEach(each => {
        checkedPermissionIds.push(each.permissionId);
      });
      this.$refs.permissionTree.setCheckedKeys(checkedPermissionIds);
    },
    saveRolePermission() {
      var vm = this;
      this.$confirm("save role permission setting ? ", "tips")
        .then(() => {
          var checkedKeys = this.$refs.permissionTree.getCheckedKeys();
          var addKeys = checkedKeys.filter(item => {
            return (
              vm.permissionRoles.findIndex(check => {
                return item == check.permissionId;
              }) < 0
            );
          });
          var removePermissions = vm.permissionRoles.filter(item => {
            return (
              checkedKeys.findIndex(check => {
                return item.permissionId == check;
              }) < 0
            );
          });
          addKeys.forEach(item => {
            vm.$axios
              .post("/api/permission_role", {
                roleId: vm.roleId,
                permissionId: item
              })
              .then(response => {
                vm.permissionRoles.push(response.data);
              })
              .catch(e => {
                log.error(e);
              });
          });
          removePermissions.forEach(removePermission => {
            var removeIndex = vm.permissionRoles.findIndex(item => {
              return item.permissionId == removePermission.permissionId;
            });
            if (removeIndex >= 0) {
              var url = vm.permissionRoles[removeIndex]._links.self.href;
              vm.$axios({ url: url, method: "delete" })
                .then(response => {
                  vm.permissionRoles.splice(removeIndex, 1);
                })
                .catch(e => {
                  log.error(e);
                });
            }
          });
        })
        .catch(e => {
          console.error(e);
        });
    }
  }
};
</script>
<style>
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
</style>
