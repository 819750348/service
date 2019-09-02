<template>
  <div>
    <el-row v-for="(value,key) in keyWord" :key="key">
      <el-button-group>
        <el-button v-for="word in value" :key="word.code" v-if="word.show" @click="addWord(word.code)">{{word.code}}</el-button>
      </el-button-group>
    </el-row>
    <el-input type="textarea" v-model="java" readonly></el-input>
    <el-button-group>
      <el-button @click="cleanWord">clean</el-button>
    </el-button-group>
  </div>
</template>
<script>
import Data from "./data";

export default {
  data() {
    return {
      keyWord: Data.keyWord,
      package: "",
      java: ""
    };
  },
  methods: {
    addWord(word) {
      this.java = this.java.concat(" ", word);
    },
    cleanWord() {
      this.java = "";
    }
  }
};
</script>
