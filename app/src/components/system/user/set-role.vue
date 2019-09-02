<template>
    <div>
        <el-checkbox-group v-model="checkedRoles">
            <el-checkbox v-for="role in roles" :label="role.id" :key="role.id" @change="changeUserRole(role,$event)">{{role.name}}({{role.code}})</el-checkbox>
        </el-checkbox-group>
    </div>
</template>
<script>
import Vue from "vue";

export default {
  data() {
    return {
      roles: [],
      userRoles: [],
      checkedRoles: []
    };
  },
  props: ["userId"] ,
  created() {
    if (this.userId && this.userId > 0) {
      this.fetchData();
    }
  },
  methods: {
    fetchData() {
      var vm = this;
      this.$axios
        .get("/api/role")
        .then(response => {
          vm.roles = vm.roles.concat(response.data._embedded.roles);
        })
        .catch(e => {
          console.log(e);
        });
      this.$axios
        .get("/api/user_role/search/findByUserId?userId=" + this.userId)
        .then(response => {
          this.userRoles = response.data._embedded.userRoles;
          this.computeCheckeds(this.userRoles);
        })
        .catch(e => {
          console.log(e);
        });
    },
    computeCheckeds(userRoles) {
      userRoles.forEach(each => {
        this.checkedRoles.push(each.roleId);
      });
    },
    changeUserRole(role, checked) {
      var vm = this;
      var userRole = {
        userId: this.userId,
        roleId: role.id
      };
      if (checked === true) {
        vm.$confirm(
          "add role {name}({code}) ?"
            .replace("{name}", role.name)
            .replace("{code}", role.code)
        )
          .then(() => {
            vm.$axios
              .post("/api/user_role", userRole)
              .then(response => {
                vm.userRoles.push(response.data);
              })
              .catch(e => {
                console.error(e);
              });
          })
          .catch(e => {
            vm.checkedRoles.pop();
            console.error(e);
          });
      } else {
        vm.$confirm(
          "remove role {name}({code}) ?"
            .replace("{name}", role.name)
            .replace("{code}", role.code)
        )
          .then(() => {
            var remove_item = vm.userRoles
              .filter(item => {
                return (
                  item.roleId == userRole.roleId &&
                  item.userId == userRole.userId
                );
              })
              .pop();
            vm.$axios({
              url: remove_item._links.self.href,
              method: "delete"
            })
              .then(response => {
                console.log(JSON.stringify(response));
              })
              .catch(e => {
                console.error(e);
              });
          })
          .catch(e => {
            vm.checkedRoles.push(role.id);
            console.error(e);
          });
      }
    },
    goBack() {
      this.$router.go(-1);
    }
  }
};
</script>
