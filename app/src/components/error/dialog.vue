<template>
<div>
<el-dialog :title="title" :visible.sync="visible" width="50%">
<span>{{ subtitle }}</span>
<span>{{ message }}</span>
<p>{{ details }}</p>
<span class="dialog-footer">
  <el-button type="primary" @click="visible = false">关闭</el-button>
</span>
</el-dialog>
</div>
</template>
<script>
export default{
  data(){
    return {
      title:"错误提示",
      subtitle:"服务器异常！",
      visible:false,
      message:"",
      details:""
    }
  },
  created(){
  },
  methods:{
    show(){
      this.visible = true;
    },
    hide(){
      this.visible = false;
    }
  }
}
</script>
