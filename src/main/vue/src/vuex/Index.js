import {createStore} from 'vuex';
import {post} from './modules/Post';

export default createStore({
    modules: {post}
})
