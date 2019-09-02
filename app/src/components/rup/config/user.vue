<template>
  <div>
    <el-tabs>
      <el-tab-pane label="管理员">
        <el-form :model="searchKey" ref="adminForm" :rules="searchRules">
          <el-form-item prop="admin">
            <el-input placeholder="ID" v-model.number="searchKey.admin" @keyup.enter="searchForAdmin">
              <el-button slot="append" @click="searchForAdmin">查询</el-button>
            </el-input>
            <el-table
              v-if="searchData.admins.length > 0"
              :data="searchData.admins"
              :show-header="false"
            >
              <el-table-column prop="id" label="ID"></el-table-column>
              <el-table-column prop="username" label="登录名"></el-table-column>
              <el-table-column prop="name" label="姓名"></el-table-column>
              <el-table-column prop="department" label="部门"></el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <span v-if="scope.row.isAdmin">已绑定</span>
                  <el-button v-else type="text" @click="addToAdmin(scope.row)">绑定</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
          <el-form-item>
            <el-table :data="admins" :show-header="true">
              <el-table-column prop="id" label="ID"></el-table-column>
              <el-table-column prop="username" label="登录名"></el-table-column>
              <el-table-column prop="name" label="姓名"></el-table-column>
              <el-table-column prop="department" label="部门"></el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button type="text" @click="cancelAdmin(scope.row.id)">解除绑定</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="测试人员">
        <el-form :model="searchKey" ref="testerForm" :rules="searchRules">
          <el-form-item prop="tester">
            <el-input placeholder="ID" v-model.number="searchKey.tester" @keyup.enter="searchForTester">
              <el-button slot="append" @click="searchForTester">查询</el-button>
            </el-input>
            <el-table
              v-if="searchData.testers.length > 0"
              :data="searchData.testers"
              :show-header="false"
            >
              <el-table-column prop="id" label="ID"></el-table-column>
              <el-table-column prop="username" label="登录名"></el-table-column>
              <el-table-column prop="name" label="姓名"></el-table-column>
              <el-table-column prop="department" label="部门"></el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <span v-if="scope.row.isTester">已绑定</span>
                  <el-button v-else type="text" @click="addToTester(scope.row)">绑定</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
          <el-form-item>
            <el-table :data="testers" :show-header="true">
              <el-table-column prop="id" label="ID" min-width="50"></el-table-column>
              <el-table-column prop="username" label="登录名" min-width="100"></el-table-column>
              <el-table-column prop="name" label="姓名" min-width="100"></el-table-column>
              <el-table-column prop="department" label="部门" min-width="100"></el-table-column>
              <el-table-column label="测试范围" min-width="420">
                <template slot-scope="scope">
                  <div>
                    <el-checkbox v-model="scope.row.testEquipment" @change="updateUserTest(scope.row)">装备</el-checkbox>
                    <el-checkbox v-model="scope.row.testCambot" @change="updateUserTest(scope.row)">作战</el-checkbox>
                    <el-checkbox v-model="scope.row.testEnvironment" @change="updateUserTest(scope.row)">环境</el-checkbox>
                    <el-checkbox v-model="scope.row.testEvaluation" @change="updateUserTest(scope.row)">评估</el-checkbox>
                    <el-checkbox v-model="scope.row.testSupport" @change="updateUserTest(scope.row)">支撑</el-checkbox>
                  </div>
                </template>
              </el-table-column>
              <el-table-column label="操作" min-width="100">
                <template slot-scope="scope">
                  <el-button type="text" @click="cancelTester(scope.row.id)">解除绑定</el-button>
                </template>
              </el-table-column>
            </el-table>
            <label>测试者可对即将入库的模型进行第三方测试</label>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="评估人员">
        <el-form :model="searchKey" ref="evaluatorForm" :rules="searchRules">
          <el-form-item>
            <el-input placeholder="ID" v-model.number="searchKey.evaluator" @keyup.enter="searchForEvaluator">
              <el-button slot="append" @click="searchForEvaluator">查询</el-button>
            </el-input>
            <el-table
              v-if="searchData.evaluators.length > 0"
              :data="searchData.evaluators"
              :show-header="false"
            >
              <el-table-column prop="id" label="ID"></el-table-column>
              <el-table-column prop="username" label="登录名"></el-table-column>
              <el-table-column prop="name" label="姓名"></el-table-column>
              <el-table-column prop="department" label="部门"></el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <span v-if="scope.row.isEvaluator">已绑定</span>
                  <el-button v-else type="text" @click="addToEvaluator(scope.row)">绑定</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
          <el-form-item>
            <el-table :data="evaluators" :show-header="true">
              <el-table-column prop="id" label="ID" min-width="50"></el-table-column>
              <el-table-column prop="username" label="登录名" min-width="100"></el-table-column>
              <el-table-column prop="name" label="姓名" min-width="100"></el-table-column>
              <el-table-column prop="department" label="部门" min-width="100"></el-table-column>
              <el-table-column label="评估范围" min-width="420">
                <template slot-scope="scope">
                  <div>
                    <el-checkbox v-model="scope.row.evaluateEquipment" @change="updateUserEvaluate(scope.row)">装备</el-checkbox>
                    <el-checkbox v-model="scope.row.evaluateCambot" @change="updateUserEvaluate(scope.row)">作战</el-checkbox>
                    <el-checkbox v-model="scope.row.evaluateEnvironment" @change="updateUserEvaluate(scope.row)">环境</el-checkbox>
                    <el-checkbox v-model="scope.row.evaluateEvaluation" @change="updateUserEvaluate(scope.row)">评估</el-checkbox>
                    <el-checkbox v-model="scope.row.evaluateSupport" @change="updateUserEvaluate(scope.row)">支撑</el-checkbox>
                  </div>
                </template>
              </el-table-column>
              <el-table-column label="操作" min-width="100">
                <template slot-scope="scope">
                  <el-button type="text" @click="cancelEvaluator(scope.row.id)">解除绑定</el-button>
                </template>
              </el-table-column>
            </el-table>
            <label>评估者可对即将入库的模型进行第三方评估.</label>
          </el-form-item>
        </el-form>
      </el-tab-pane>
      <el-tab-pane label="审核人员">
        <el-form :model="searchKey" ref="leaderForm" :rules="searchRules">
          <el-form-item>
            <el-input placeholder="ID" v-model.number="searchKey.leader" @keyup.enter="searchForLeader">
              <el-button slot="append" @click="searchForLeader">查询</el-button>
            </el-input>
            <el-table
              v-if="searchData.leaders.length > 0"
              :data="searchData.leaders"
              :show-header="false"
            >
              <el-table-column prop="id" label="ID"></el-table-column>
              <el-table-column prop="username" label="登录名"></el-table-column>
              <el-table-column prop="name" label="姓名"></el-table-column>
              <el-table-column prop="department" label="部门"></el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <span v-if="scope.row.isLeader">已绑定</span>
                  <el-button v-else type="text" @click="addToLeader(scope.row)">绑定</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
          <el-form-item>
            <el-table :data="leaders" :show-header="true">
              <el-table-column prop="id" label="ID"></el-table-column>
              <el-table-column prop="username" label="登录名"></el-table-column>
              <el-table-column prop="name" label="姓名"></el-table-column>
              <el-table-column prop="department" label="部门"></el-table-column>
              <el-table-column label="操作">
                <template slot-scope="scope">
                  <el-button type="text" @click="cancelLeader(scope.row.id)">解除绑定</el-button>
                </template>
              </el-table-column>
            </el-table>
          </el-form-item>
        </el-form>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>
<script>
export default {
  data() {
    return {
      searchKey: {
        admin: null,
        tester: null,
        evaluator: null,
        leader: null
      },
      searchData: {
        admins: [],
        testers: [],
        evaluators: [],
        leaders: []
      },
      searchRules: {
        admin: [{ type: "number", message: "ID必须是数字" }],
        tester: [{ type: "number", message: "ID必须是数字" }],
        evaluator: [{ type: "number", message: "ID必须是数字" }],
        leader: [{ type: "number", message: "ID必须是数字" }]
      },
      admins: [],
      testers: [],
      evaluators: [],
      leaders: []
    };
  },
  created() {
    this.fetchAdmin();
    this.fetchTester();
    this.fetchEvaluator();
    this.fetchLeader();
  },
  methods: {
    fetchAdmin() {
      var vm = this;
      this.$axios.get("/businessuser/getAdmins").then(data => {
        vm.admins = [].concat(data);
      });
    },
    fetchTester() {
      var vm = this;
      this.$axios.get("/businessuser/getTesters").then(data => {
        vm.testers = [].concat(data);
      });
    },
    fetchEvaluator() {
      var vm = this;
      this.$axios.get("/businessuser/getEvaluators").then(data => {
        vm.evaluators = [].concat(data);
      });
    },
    fetchLeader() {
      var vm = this;
      this.$axios.get("/businessuser/getLeaders").then(data => {
        vm.leaders = [].concat(data);
      });
    },
    searchForAdmin() {
      var vm = this;
      this.searchData.admins = [];
      this.searchKey.admin &&
        this.$refs["adminForm"].validate(valid => {
          if (valid) {
            vm.$axios
              .get("/businessuser/getUserById?id=" + vm.searchKey.admin)
              .then(data => {
                vm.searchData.admins = [data];
              });
          }
        });
    },
    searchForTester() {
      var vm = this;
      this.searchData.testers = [];
      this.searchKey.tester &&
        this.$refs["testerForm"].validate(valid => {
          if (valid) {
            vm.$axios
              .get("/businessuser/getUserById?id=" + vm.searchKey.tester)
              .then(data => {
                vm.searchData.testers = [data];
              });
          }
        });
    },
    searchForEvaluator() {
      var vm = this;
      this.searchData.evaluators = [];
      this.searchKey.evaluator &&
        this.$refs["evaluatorForm"].validate(valid => {
          if (valid) {
            vm.$axios
              .get("/businessuser/getUserById?id=" + vm.searchKey.evaluator)
              .then(data => {
                vm.searchData.evaluators = [data];
              });
          }
        });
    },
    searchForLeader() {
      var vm = this;
      this.searchData.leaders = [];
      this.searchKey.leader &&
        this.$refs["leaderForm"].validate(valid => {
          if (valid) {
            vm.$axios
              .get("/businessuser/getUserById?id=" + vm.searchKey.leader)
              .then(data => {
                vm.searchData.leaders = [data];
              });
          }
        });
    },
    addToAdmin(row) {
      var vm = this;
      this.$axios.post("/businessuser/setAdmin?id=" + row.id).then(data => {
        vm.admins.push(row);
        vm.searchKey.admin = null;
        vm.searchData.admins = [];
      });
    },
    addToTester(row) {
      var vm = this;
      this.$axios.post("/businessuser/setTester?id=" + row.id).then(data => {
        vm.testers.push(row);
        vm.searchKey.tester = null;
        vm.searchData.testers = [];
      });
    },
    addToEvaluator(row) {
      var vm = this;
      this.$axios.post("/businessuser/setEvaluator?id=" + row.id).then(data => {
        vm.evaluators.push(row);
        vm.searchKey.evaluator = null;
        vm.searchData.evaluators = [];
      });
    },
    addToLeader(row) {
      var vm = this;
      this.$axios.post("/businessuser/setLeader?id=" + row.id).then(data => {
        vm.leaders.push(row);
        vm.searchKey.leader = null;
        vm.searchData.leaders = [];
      });
    },
    cancelAdmin(id) {
      var vm = this;
      this.$confirm("解除管理员").then(() => {
        this.$axios.get("/businessuser/cancelAdmin?id=" + id).then(data => {
          vm.admins = vm.admins.filter(item => {
            return item.id !== id;
          });
        });
      });
    },
    cancelTester(id) {
      var vm = this;
      this.$confirm("解除测试人员").then(() => {
        this.$axios.get("/businessuser/cancelTester?id=" + id).then(data => {
          vm.testers = vm.testers.filter(item => {
            return item.id !== id;
          });
        });
      });
    },
    cancelEvaluator(id) {
      var vm = this;
      this.$confirm("解除评估人员").then(() => {
        this.$axios.get("/businessuser/cancelEvaluator?id=" + id).then(data => {
          vm.evaluators = vm.evaluators.filter(item => {
            return item.id !== id;
          });
        });
      });
    },
    cancelLeader(id) {
      var vm = this;
      this.$confirm("解除审核人员").then(() => {
        this.$axios.get("/businessuser/cancelLeader?id=" + id).then(data => {
          vm.leaders = vm.leaders.filter(item => {
            return item.id !== id;
          });
        });
      });
    },
    updateUserTest(user){
      this.$axios.post("/businessuser/setTestRange",user)
      .then(data => {})
    },
    updateUserEvaluate(user){
      this.$axios.post("/businessuser/setEvaluationRange",user)
      .then(data => {})
    }
  }
};
</script>
