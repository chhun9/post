import {createApp} from 'vue'
import App from './App.vue'
import store from "./vuex/Index";

createApp(App).use(store).mount('#app')