<template>
  <div class="input_info">
    <sui-form>
      <sui-form-field>
        <label>{{ postName }}</label>
        <sui-dropdown
            selection
            :options="postNameOpt"
            v-model="postName"
        />
      </sui-form-field>
    </sui-form>
  </div>
</template>

<script>
import {computed} from 'vue';
import {useStore} from "vuex";

function usePost() {
  const store = useStore();

  const postName = computed(() => store.state.post.postName);
  const postNameOpt = computed(() => store.state.post.postNameOpt);
  const postStatus = computed(() => store.state.post.postStatus);
  const postNumber = computed(() => store.state.post.postNumber);

  const changePostName = e => store.dispatch('post/changePostName', e.target.value);
  const changePostNameOpt = e => store.dispatch('post/changePostNameOpt', e.target.value);
  const changePostStatus = e => store.dispatch('post/changePostStatus', e.target.value);
  const changePostNumber = e => store.dispatch('post/changePostNumber', e.target.value);

  return {
    postName, postNameOpt, postStatus, postNumber,
    changePostName, changePostNameOpt, changePostStatus, changePostNumber,
  }
}

export default {
  name: "InputInfo",
  setup() {
    return {
      ...usePost()
    }
  },
}
</script>

<style scoped>

</style>