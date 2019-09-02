<template>
  <div class="wrapper">
    <v-head></v-head>
      <v-sidebar v-if="isManageLayout"></v-sidebar>
      <div class="content-box" :class="{'content-collapse':collapse,'content-max':!isManageLayout}">
        <v-tags v-if="isManageLayout"></v-tags>
        <div class="content">
          <transition name="move" mode="out-in">
            <keep-alive :include="tagsList">
              <router-view></router-view>
            </keep-alive>
          </transition>
        </div>
      </div>
  </div>
</template>

<script>
import vHead from "./Header.vue";
import vSidebar from "./Sidebar.vue";
import vTags from "./Tags.vue";
import bus from "./bus";
export default {
  data() {
    return {
      tagsList: [],
      collapse: false,
      isManageLayout: true
    };
  },
  components: {
    vHead,
    vSidebar,
    vTags
  },
  created() {
    this.isManageLayout = ("manage" === localStorage.getItem("role_layout"));
    if (this.isManageLayout) {
      bus.$on("collapse", msg => {
        this.collapse = msg;
      });

      // 只有在标签页列表里的页面才使用keep-alive，即关闭标签之后就不保存到内存中了。
      bus.$on("tags", tags => {
        let arr = [];
        for (let i = 0, len = tags.length; i < len; i++) {
          tags[i].name && arr.push(tags[i].name);
        }
        this.tagsList = arr;
      });
    }
  }
};
</script>
