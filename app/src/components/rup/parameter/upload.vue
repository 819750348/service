<template>
<div>
    <base-form v-if="currentStep == 0" @nextStep="currentStep ++"></base-form>
    <props-form v-if="currentStep == 1" @preStep="currentStep --"></props-form>
</div>
</template>
<script>
import BaseForm from "./form/base";
import PropsForm from "./form/props";
export default {
  data() {
    return {
      currentStep: 0
    };
  },
  components: {
    BaseForm,
    PropsForm
  },
  methods: {
    saveAndNext() {
    //   this.$router.push({ path: "/model-parameter-upload-second" });
    },
    fixUI() {
      this.$nextTick(() => {
        this.$comUtil.formElementUpload();
      });
    }
  }
};
</script>
<style>
</style>
