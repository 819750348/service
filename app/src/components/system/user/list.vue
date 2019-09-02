<template>
    <div>
        <el-button-group>
            <el-button type="primary" icon="el-icon-back" @click="$router.go(-1)">Back</el-button>
            <el-button type="primary" icon="el-icon-plus" @click="addRow">New Item</el-button>
        </el-button-group>
        <el-table :data="users" style="width:100%">
            <el-table-column prop="username" label="Username" min-width="100" />
            <el-table-column prop="enabled" label="Enabled" min-width="80">
                <template slot-scope="scope">
                    <el-switch v-model="scope.row.enabled" @change="changeEnabled(scope.row,$event)" />
                </template>
            </el-table-column>
            <el-table-column prop="createTime" label="CreateTime" min-width="100" />
            <el-table-column prop="updateTime" label="UpdateTime" min-width="100" />
            <el-table-column label="Option" min-width="200">
                <template slot-scope="scope">
                    <el-button type="text" size="small" @click="resetPassword(scope.row)" icon="el-icon-edit">ResetPassword</el-button>
                    <el-button type="text" size="small" @click="setRole(scope.row)" icon="el-icon-edit">SetRole</el-button>
                    <el-button type="text" size="small" @click="editRow(scope.row)" icon="el-icon-edit">Edit</el-button>
                    <el-button type="text" size="small" @click="deleteRow(scope.$index,scope.row)" icon="el-icon-remove">Remove</el-button>
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
      users: [],
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
        .get("/user/page?page=" + (pageNum - 1) + "&size=" + this.size)
        .then(data => {
          vm.users = [].concat(data.content);
          vm.total = data.totalElements;
          vm.size = data.size;
        })
        .catch(error => {
          console.log("person list error:" + error);
        });
    },
    editRow(row) {
      this.$router.push({
        path: "/user-edit-" + row.id
        // name: 'user-edit',
        // params:{id:row.id}
      });
    },
    addRow() {
      this.$router.push({
        path: "/user-add"
      });
    },
    deleteRow(index, row) {
      var vm = this;
      this.$confirm("delele row ? sure", "tips", {
        type: "warning",
        confirmButtonText: "sure",
        cancelButtonText: "cancel"
      }).then(() => {
        vm.$axios({
          url: row._links.self.href,
          method: "delete"
        })
          .then(response => {
            this.users.splice(index, 1);
            this.$message({
              type: "info",
              message: "remove successs"
            });
          })
          .catch(error => {});
      });
    },
    changeEnabled(row, enabled) {
      var vm = this;
      this.$confirm("change user enabled ?", "tips", {
        type: "waring"
      })
        .then(() => {
          var url = enabled ? "/user/enable" : "/user/disable";
          vm.$axios.post(url + "?id=" + row.id);
        })
        .catch(e => {
          row.enabled = !enabled;
          console.log(e);
        });
    },
    resetPassword(row) {
      var vm = this;
      this.$confirm("reset password to username", "tips").then(({ value }) => {
        vm.$axios
          .post("/user/resetPassword?id=" + row.id)
          .then(response => {
            vm.$alert("reset password successful");
          })
          .catch(e => {
            console.log(e);
          });
      });
    },
    setRole(row) {
      this.$router.push({
        path: "/user-set-role-" + row.id
        //    name: "user-set-role",
        //    params: { userId: row.id }
      });
    }
  }
};
</script>
