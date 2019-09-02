<template>
    <div>
        <el-card>
            <div slot="header" class="clearfix">
              <span>Check Menu</span>
              <el-button type="text" style="float:right;padding:3px 0" @click="saveRoleMenu">save</el-button>
            </div>
            <div>
            <el-tree ref="menuTree" :data="menus" :props="treeProps" node-key="id" :check-on-click-node="true" :check-strictly="true" :expand-on-click-node="false" show-checkbox default-expand-all >
                <span class="mananger-tree-node" slot-scope="{node,data}">
                <span>{{ node.label }}({{data.code}})</span>
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
      menus: [],
      menuRoles: []
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
        .get("/api/menu?size=1000")
        .then(response => {
          vm.formData(response.data._embedded.menus);
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
      this.menus = data.filter(item => {
        return item.parentId == 0;
      });
      this.fetchCheckeds();
    },
    fetchCheckeds() {
      var vm = this;
      this.$axios
        .get("/api/menu_role/search/findByRoleId?roleId=" + this.roleId)
        .then(response => {
          vm.menuRoles = response.data._embedded.menuRoles;
          vm.formCheckeds(vm.menuRoles);
        })
        .catch(e => {
          console.log(e);
        });
    },
    formCheckeds(checkeds) {
      var vm = this;
      var checkedMenuIds = [];
      checkeds.forEach(each => {
        checkedMenuIds.push(each.menuId);
      });
      this.$refs.menuTree.setCheckedKeys(checkedMenuIds);
    },
    saveRoleMenu() {
      var vm = this;
      this.$confirm("save role menu setting ? ", "tips")
        .then(() => {
          var checkedKeys = this.$refs.menuTree.getCheckedKeys();
          var addKeys = checkedKeys.filter(item => {
            return (
              vm.menuRoles.findIndex(check => {
                return item == check.menuId;
              }) < 0
            );
          });
          var removeMenus = vm.menuRoles.filter(item => {
            return (
              checkedKeys.findIndex(check => {
                return item.menuId == check;
              }) < 0
            );
          });
          addKeys.forEach(item => {
            vm.$axios
              .post("/api/menu_role", { roleId: vm.roleId, menuId: item })
              .then(response => {
                vm.menuRoles.push(response.data);
              })
              .catch(e => {
                log.error(e);
              });
          });
          removeMenus.forEach(removeMenu => {
            var removeIndex = vm.menuRoles.findIndex(item => {
              return item.menuId == removeMenu.menuId;
            });
            if (removeIndex >= 0) {
              var url = vm.menuRoles[removeIndex]._links.self.href;
              vm.$axios({ url: url, method: "delete" })
                .then(response => {
                  vm.menuRoles.splice(removeIndex, 1);
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
