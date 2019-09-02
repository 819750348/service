<template>
  <el-button :type="type" @click="handleClick">
    <slot></slot>
  </el-button>
</template>
<script>
export default {
  props: {
    type: {
      type: String,
      default: "button"
    },
    fileId: {
      type: Number,
      default: 0
    },
    fileName: {
      type: String,
      default: "download"
    }
  },
  methods: {
    handleClick() {
      if (this.fileId && this.fileId > 0) {
        this.$axios({
          method: "get",
          url: "/attachment/download/" + this.fileId,
          responseType: "blob"
        }).then(response => {
          let headers = response.headers;
          let blob = new Blob([response.data], {
            type: headers["content-type"]
          });
          let link = document.createElement("a");
          link.href = window.URL.createObjectURL(blob);
          link.download = this.fileName;
          link.click();
        });
      }
    }
  }
};
</script>
