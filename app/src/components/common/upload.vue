<template>
      <el-button :type="type" @click="handleClick">
          <slot></slot>
        <input class="el-upload__input" type="file" ref="input" :name="name" @change="handleChange" :multiple="multiple" :accept="accept" />
      </el-button>
</template>
<script>
export default {
  props: {
    type: String,
    name: {
      type: String,
      default: "file"
    },
    multiple: {
      type: Boolean,
      default: true
    },
    accept: String,
    uploadSuccess: Function
  },
  methods: {
    handleClick() {
      this.$refs.input.value = null;
      this.$refs.input.click();
    },
    handleChange(ev) {
      const files = ev.target.files;
      if (!files) return;
      let postFiles = Array.prototype.slice.call(files);
      if (!this.multiple) { postFiles = postFiles.slice(0, 1); }
      if (postFiles.length === 0) { return; }
      postFiles.forEach(each => {
        this.uploadFile(each);
      });
    },
    uploadFile(file) {
      this.$refs.input.value = null;
      var vm = this;
      var form = new FormData();
      form.append("file", file);
      this.$axios
        .post("/upload/", form)
        .then(data => {
          vm.$emit("uploaded",{data:data,file:file});
          if (vm.uploadSuccess) {
            vm.uploadSuccess(data, file);
          }
        })
        .catch(e => {
          vm.$alert("上传文件失败", "提示");
        });
    }
  }
};
</script>
