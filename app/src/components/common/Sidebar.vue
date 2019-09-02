<template>
  <div class="sidebar">
    <el-menu
      class="sidebar-el-menu"
      :default-active="onRoutes"
      :collapse="collapse"
      background-color="#324157"
      text-color="#bfcbd9"
      active-text-color="#20a0ff"
      unique-opened
      router
    >
      <template v-for="item in items">
        <template v-if="item.subs">
          <el-submenu :index="item.index" :key="item.index">
            <template slot="title">
              <i :class="item.icon"></i>
              <span slot="title">{{ item.title }}</span>
            </template>
            <template v-for="subItem in item.subs">
              <el-submenu v-if="subItem.subs" :index="subItem.index" :key="subItem.index">
                <template slot="title">{{ subItem.title }}</template>
                <el-menu-item
                  v-for="(threeItem,i) in subItem.subs"
                  :key="i"
                  :index="threeItem.index"
                >{{ threeItem.title }}</el-menu-item>
              </el-submenu>
              <el-menu-item v-else :index="subItem.index" :key="subItem.index">{{ subItem.title }}</el-menu-item>
            </template>
          </el-submenu>
        </template>
        <template v-else>
          <el-menu-item :index="item.index" :key="item.index">
            <i :class="item.icon"></i>
            <span slot="title">{{ item.title }}</span>
          </el-menu-item>
        </template>
      </template>
    </el-menu>
  </div>
</template>

<script>
import bus from "../common/bus";
export default {
  data() {
    return {
      collapse: false,
      items: [
        {
          icon: "el-icon-lx-cascades",
          title: "服务",
          subs: [
            {
              index: "dashboard",
              title: "首页"
            },
            {
              index: "servicebrowsing",
              title: "服务浏览"
            },
            {
              index: "serviceregistration",
              title: "服务注册"
            },
            {
              index: "servicedevelopment",
              title: "服务开发"
            },
            {
              index: "servicemanagement",
              title: "服务管理"
            },
            {
              index: "nodebrowsing",
              title: "节点浏览"
            },
            {
              index: "logsbrowsing",
              title: "日志浏览"
            }
          ]
        },

        {
          icon: "el-icon-lx-copy",
          title: "系统管理",
          subs: [
            {
              title: "用户管理"
            },
            {
              title: "角色管理"
            },
            {
              title: "权限管理"
            },
            {
              index: "detail",
              title: "明细"
            },
            {
              index: "devdetail",
              title: "服务开发明细"
            },
            {
              index: "mandetail",
              title: "管理明细"
            },
            {
              index: "nodedetail",
              title: "节点明细"
            },
            {
              index: "browsingdetail",
              title: "浏览明细"
            }
          ]
        }
      ]
    };
  },
  computed: {
    onRoutes() {
      return this.$route.path.replace("/", "");
    }
  },
  created() {
    // 通过 Event Bus 进行组件间通信，来折叠侧边栏
    bus.$on("collapse", msg => {
      this.collapse = msg;
    });
  }
};
</script>

<style scoped>
.sidebar {
  display: block;
  position: absolute;
  left: 0;
  top: 70px;
  bottom: 0;
  overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
  width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
  width: 250px;
}
.sidebar > ul {
  height: 100%;
}
</style>
