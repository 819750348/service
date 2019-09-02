<template>
  <el-tabs v-model="activeName">
    <el-tab-pane label="待审核">
      <el-table :data="disabledData.users" style="width:100%">
        <el-table-column prop="id" label="ID" min-width="60"/>
        <el-table-column prop="username" label="登录名" min-width="100"/>
        <el-table-column prop="name" label="姓名" min-width="100"/>
        <el-table-column prop="department" label="部门" min-width="80"/>
        <el-table-column prop="label" label="标签" min-width="100"/>
        <el-table-column prop="enabled" label="激活" min-width="80">
          <template slot-scope="scope">{{scope.row.enabled | yesOrNo}}</template>
        </el-table-column>
        <el-table-column prop="isAdmin" label="管理员" min-width="80">
          <template slot-scope="scope">{{scope.row.isAdmin | yesOrNo}}</template>
        </el-table-column>
        <el-table-column prop="isTester" label="测试人员" min-width="80">
          <template slot-scope="scope">{{scope.row.isTester | yesOrNo}}</template>
        </el-table-column>
        <el-table-column prop="isEvaluator" label="评估人员" min-width="80">
          <template slot-scope="scope">{{scope.row.isEvaluator | yesOrNo}}</template>
        </el-table-column>
        <el-table-column prop="isLeader" label="审核人员" min-width="80">
          <template slot-scope="scope">{{scope.row.isLeader | yesOrNo}}</template>
        </el-table-column>
        <el-table-column prop="remark" label="备注" min-width="100"/>
        <el-table-column label="操作" min-width="100">
          <template slot-scope="scope">
            <el-button
              type="text"
              size="small"
              @click="beenEnabled(scope.row)"
              icon="el-icon-edit"
            >审核通过</el-button>
          </template>
        </el-table-column>
      </el-table>
      <el-pagination
        background
        layout="prev, pager, next"
        :total="disabledData.total"
        :page-size="disabledData.size"
        @current-change="fetchDisabledUser"
      ></el-pagination>
    </el-tab-pane>
    <el-tab-pane label="已审核" name="user">
      <el-container>
        <el-aside width="200px">
          <el-card>
            <div slot="header">
              <span>标签</span>
              <el-button style="float: right; padding: 3px 0" type="text" @click="createLabel">新建标签</el-button>
            </div>
            <el-table
              :data="tags"
              :show-header="false"
              highlight-current-row
              @current-change="selectLabel"
            >
              <el-table-column prop="label" label="标签">
                <template slot-scope="scope">
                  <span>{{scope.row.name}}({{scope.row.count}})</span>
                </template>
              </el-table-column>
            </el-table>
          </el-card>
        </el-aside>
        <el-main>
          <el-header>
            <el-input type="text" v-model="searchKey" placeholder="姓名">
              <el-button
                slot="append"
                type="primary"
                icon="el-icon-search"
                @click="fetchData(1)"
              >Search</el-button>
            </el-input>
          </el-header>
          <el-table :data="users" style="width:100%">
            <el-table-column prop="id" label="ID" min-width="60"/>
            <el-table-column prop="username" label="登录名" min-width="100"/>
            <el-table-column prop="name" label="姓名" min-width="100"/>
            <el-table-column prop="department" label="部门" min-width="80"></el-table-column>
            <el-table-column prop="label" label="标签" min-width="124">
              <template slot-scope="scope">
                <el-select
                  v-model="scope.row.label"
                  @change="changeLabel(scope.row,$event)"
                  value-key="value"
                >
                  <el-option
                    v-for="tag in labels"
                    :value="tag.name"
                    :label="tag.name"
                    :key="tag.id"
                  ></el-option>
                </el-select>
              </template>
            </el-table-column>
            <el-table-column prop="enabled" label="激活" min-width="80">
              <template slot-scope="scope">
                {{scope.row.enabled | yesOrNo}}
                <!-- <el-switch v-model="scope.row.enabled" @change="changeEnabled(scope.row,$event)" /> -->
              </template>
            </el-table-column>
        <el-table-column prop="isAdmin" label="管理员" min-width="80">
          <template slot-scope="scope">{{scope.row.isAdmin | yesOrNo}}</template>
        </el-table-column>
        <el-table-column prop="isTester" label="测试人员" min-width="80">
          <template slot-scope="scope">{{scope.row.isTester | yesOrNo}}</template>
        </el-table-column>
        <el-table-column prop="isEvaluator" label="评估人员" min-width="80">
          <template slot-scope="scope">{{scope.row.isEvaluator | yesOrNo}}</template>
        </el-table-column>
        <el-table-column prop="isLeader" label="审核人员" min-width="80">
          <template slot-scope="scope">{{scope.row.isLeader | yesOrNo}}</template>
        </el-table-column>
            <el-table-column prop="remark" label="备注" min-width="100"/>
            <el-table-column label="操作" min-width="280">
              <template slot-scope="scope">
                <el-button
                  type="text"
                  size="small"
                  @click="changeRemark(scope.row)"
                >修改备注</el-button>
                <el-button
                  type="text"
                  size="small"
                  @click="changeDepartment(scope.row)"
                >修改部门</el-button>
                <el-button
                  type="text"
                  size="small"
                  @click="resetPassword(scope.row)"
                >重置密码</el-button>
                <el-button
                  type="text"
                  size="small"
                  @click="beenDisabled(scope.row)"
                >加入黑名单</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-pagination
            background
            layout="prev, pager, next"
            :total="total"
            :page-size="size"
            @current-change="fetchData"
          ></el-pagination>
        </el-main>
      </el-container>
    </el-tab-pane>
  </el-tabs>
</template>
<script>
export default {
  data() {
    return {
      activeName: "user",
      searchKey: "",
      users: [],
      total: 0,
      totalCount: 0,
      size: 20,
      labels: [],
      disabledData: {
        users: [],
        total: 0,
        size: 20
      }
    };
  },
  created() {
    this.fetchData(1, "");
    this.fetchLabels();
    this.fetchDisabledUser(1);
  },
  computed: {
    tags() {
      var count = this.totalCount;
      this.labels.forEach(item => {
        count -= item.count;
      });
      return this.labels.concat([{ id: -1, name: "其他", count: count }]);
    }
  },
  filters: {
    yesOrNo(yes) {
      return yes ? "是" : "否";
    }
  },
  methods: {
    fetchLabels() {
      var vm = this;
      this.$axios.get("/businessuser/getlabels").then(data => {
        vm.labels = [].concat(data);
      });
    },
    fetchData(pageNum, label) {
      var vm = this;
      this.$axios
        .post(
          "/businessuser/getLabelUsers?pageNum=" +
            pageNum +
            "&pageSize=" +
            this.size,
          { label: label, keys: this.searchKey }
        )
        .then(data => {
          vm.users = [].concat(data.list);
          vm.total = data.total;
          vm.size = data.size;
          if (vm.totalCount === 0) {
            vm.totalCount = vm.total;
          }
        });
    },
    fetchDisabledUser(pageNum) {
      var vm = this;
      this.$axios
        .post(
          "/businessuser/getNotEnabledUsers?pageNum=" +
            pageNum +
            "&pageSize=" +
            this.disabledData.size
        )
        .then(data => {
          vm.disabledData.users = [].concat(data.list);
          vm.disabledData.total = data.total;
          vm.disabledData.size = data.size;
        });
    },
    selectLabel(row) {
      if (row) this.fetchData(1, row.name);
    },
    changeEnabled(row, enabled) {
      var vm = this;
      this.$confirm(enabled ? "激活用户?" : "禁用用户?", "tips", {
        type: "waring"
      })
        .then(() => {
          var url = enabled ? "/user/enable" : "/user/disable";
          vm.$axios.post(url + "?id=" + row.id);
        })
        .catch(e => {
          row.enabled = !enabled;
        });
    },
    beenEnabled(row) {
      var vm = this;
      this.$axios.post("/user/enable?id=" + row.id).then(data => {
        row.enabled = true;
        vm.disabledData.users = vm.disabledData.users.filter(item => {
          return row.id != item.id;
        });
        vm.users.push(row);
      });
    },
    beenDisabled(row) {
      var vm = this;
      this.$axios.post("/user/disable?id=" + row.id).then(data => {
        row.enabled = false;
        vm.users = vm.users.filter(item => {
          return row.id != item.id;
        });
        vm.disabledData.users.push(row);
      });
    },
    changeIsLeader(row, isLeader) {
      var vm = this;
      this.$confirm(isLeader ? "设置为领导?" : "取消领导?", "tips", {
        type: "waring"
      })
        .then(() => {
          var url = isLeader
            ? "/businessuser/setLeader"
            : "/businessuser/cancelLeader";
          vm.$axios.post(url + "?id=" + row.id);
        })
        .catch(e => {
          row.isLeader = !isLeader;
          console.log(e);
        });
    },
    changeIsAdmin(row, isAdmin) {
      var vm = this;
      this.$confirm(isAdmin ? "设置为管理员?" : "取消管理员?", "tips", {
        type: "waring"
      })
        .then(() => {
          var url = isAdmin
            ? "/businessuser/setAdmin"
            : "/businessuser/cancelAdmin";
          vm.$axios.post(url + "?id=" + row.id);
        })
        .catch(e => {
          row.isAdmin = !isAdmin;
          console.log(e);
        });
    },
    changeIsEvaluator(row, isEvaluator) {
      var vm = this;
      this.$confirm(isEvaluator ? "设置为评估人员?" : "取消评估人员?", "tips", {
        type: "waring"
      })
        .then(() => {
          var url = isEvaluator
            ? "/businessuser/setEvaluator"
            : "/businessuser/cancelEvaluator";
          vm.$axios.post(url + "?id=" + row.id);
        })
        .catch(e => {
          row.isEvaluator = !isEvaluator;
          console.log(e);
        });
    },
    changeIsTester(row, isTester) {
      var vm = this;
      this.$confirm(isTester ? "设置为测试人员?" : "取消测试人员?", "tips", {
        type: "waring"
      })
        .then(() => {
          var url = isTester
            ? "/businessuser/setTester"
            : "/businessuser/cancelTester";
          vm.$axios.post(url + "?id=" + row.id);
        })
        .catch(e => {
          row.isTester = !isTester;
          console.log(e);
        });
    },
    resetPassword(row) {
      var vm = this;
      this.$confirm("确认重置用户密码？", "提示").then(({ value }) => {
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
    changeRemark(row) {
      var vm = this;
      this.$prompt("备注").then(({ value }) => {
        vm.$axios
          .post("/businessuser/setRemark", { id: row.id, remark: value })
          .then(data => {
            row.remark = value;
          });
      });
    },
    changeLabel(row, label) {
      this.$axios
        .post("/businessuser/setLabel", { id: row.id, label: label })
        .then(data => {
          row.remark = value;
        });
    },
    changeDepartment(row) {
      var vm = this;
      this.$prompt("部门").then(({ value }) => {
        vm.$axios
          .post("/businessuser/setDepartment", {
            id: row.id,
            department: value
          })
          .then(data => {
            row.department = value;
          });
      });
    },
    createLabel() {
      var vm = this;
      this.$prompt("新建标签").then(( {value} ) => {
        var label = {name: value };
        vm.$axios.post("/base/addBaseLabel", {label}).then(data => {
          vm.labels.push(data);
        });
      });
    }
  }
};
</script>
