import {createStore} from 'vuex'

export const store = createStore({
    state: {
        postNumber: "",
        postName: "",
        postStatus: {},
    },
    getters: {
        getPostNumber(state) {
            return state.postNumber;
        },
        getPostName(state) {
            return state.postName;
        },
        getPostStatus(state) {
            return state.postStatus;
        }
    },
    mutations: {
        setPostNumber(state, value) {
            state.postNumber = value;
        },
        setPostName(state, value) {
            state.postName = value;
        },
        setPostStatus(state, value) {
            state.postStatus = value;
        }
    },
    actions: {},
})