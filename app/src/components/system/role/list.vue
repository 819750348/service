<template>
    <div>
        <el-button-group>
            <el-button type="primary" icon="el-icon-back" @click="$router.go(-1)">Back</el-button>
            <el-button type="primary" icon="el-icon-plus" @click="addRow">New Item</el-button>
            <el-button type="primary" icon="el-icon-refresh" @click="refreshResource">Refresh Resource</el-button>
        </el-button-group>
        <el-table :data="roles" style="width:100%">
            <el-table-column prop="name" label="Name" min-width="80" />
            <el-table-column prop="code" label="Code" min-width="80" />
            <el-table-column prop="layout" label="Layout" min-width="50" />
            <el-table-column prop="status" label="Status" min-width="50">
                <template slot-scope="scope">
                    <el-switch v-model="scope.row.status" @change="changeEnabled(scope.row,$event)" />
                </template>
            </el-table-column>
            <el-table-column prop="createTime" label="CreateTime" width="200" />
            <el-table-column label="Option" width="280">
                <template slot-scope="scope">
                    <el-button type="text" size="small" @click="setIndex(scope.row)" icon="el-icon-edit">Index</el-button>
                    <el-button type="text" size="small" @click="setMenu(scope.row)" icon="el-icon-edit">Menu</el-button>
                    <el-button type="text" size="small" @click="setPermission(scope.row)" icon="el-icon-edit">Permission</el-button>
                    <el-button type="text" size="small" @click="editRow(scope.row)" icon="el-icon-edit">Edit</el-button>
                    <!-- <el-button type="text" size="small" @click="deleteRow(scope.$index,scope.row)" icon="el-icon-remove">Remove</el-button> -->
                </template>
            </el-table-column>
        </el-table>
        <el-pagination background layout="prev, pager, next" :total="total" :page-size="size" @current-change="fetchData"></el-pagination>
    </div>
</template>
<script>
export default {
  data() {
    return {
      roles: [],
      total: 0,
      size: 0
    };
  },
  created() {
    this.fetchData(1);
  },
  methods: {
    fetchData(pageNum) {
      var vm = this;
      this.$axios
        .get("/role/page?page=" + (pageNum - 1) + "&size=" + this.size)
        .then(data => {
          vm.roles = [].concat(data.content);
          vm.total = data.totalElements;
          vm.size = data.size;
        })
        .catch(error => {
          console.log("person list error:" + error);
        });
    },
    editRow(row) {
      this.$router.push({
        path: "/role-edit-" + row.id
        // name: "role-edit",
        // params: { id: row.id }
      });
    },
    addRow() {
      this.$router.push({
        path: "/role-add"
      });
    },
    refreshResource() {
      var vm = this;
      vm.$confirm("refresh resource , sure ?", "tips")
        .then(() => {
          vm.$axios
            .post("/resource/refresh")
            .then(data => {
              vm.$message({
                type: "info",
                message: "refresh resource successful !!! "
              });
            })
            .catch(e => {
              vm.$message({
                type: "error",
                message: "refresh resource error !!! "
              });
            });
        })
        .catch(e => {});
    },
    deleteRow(index, row) {
      var vm = this;
      this.$confirm("delele row ? sure", "tips", {
        type: "warning",
        confirmButtonText: "sure",
        cancelButtonText: "cancel"
      })
        .then(() => {
          vm.$axios({
            url: row._links.self.href,
            method: "delete"
          })
            .then(response => {
              this.roles.splice(index, 1);
              this.$message({
                type: "info",
                message: "remove successs"
              });
            })
            .catch(error => {});
        })
        .catch(e => {
          console.log(e);
          this.$message({
            type: "info",
            message: "cancel successs"
          });
        });
    },
    setIndex(row) {
      this.$router.push({
        path: "/role-set-index-" + row.id
        // name:'role-set-permission',
        // params:{roleId:row.id}
      });
    },
    setPermission(row) {
      this.$router.push({
        path: "/role-set-permission-" + row.id
        // name:'role-set-permission',
        // params:{roleId:row.id}
      });
    },
    setMenu(row) {
      this.$router.push({
        path: "/role-set-menu-" + row.id
        // name: "role-set-menu",
        // params: { roleId: row.id }
      });
    }
  }
};
</script>
