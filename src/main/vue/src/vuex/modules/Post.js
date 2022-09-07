import {POST} from "../MutationTypes"

export const post = {
    state: {
        postNumber: "",
        postName: "?",
        postStatus: {},
        postNameOpt: [
            {text:"전체" , value:"all"},
            {text:"우체국" , value:"kor_post"},
            {text:"CJ" , value:"kor_cj"},
            {text:"한진" , value:"kor_hanjin"},
            {text:"CU" , value:"kor_cu"},
        ],
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
        [POST.SET_POST_NUMBER](state, value) {
            state.postNumber = value;
        },
        [POST.SET_POST_NAME](state, value) {
            state.postName = value;
        },
        [POST.SET_POST_STATUS](state, value) {
            state.postStatus = value;
        },
        [POST.SET_POST_NAME_OPT](state, value) {
            state.postNameOpt = value;
        }
    },
    actions: {
        changePostName({commit}, value) {
            commit(POST.SET_POST_NAME, value);
        },
        changePostNameOpt({commit}, value) {
            commit(POST.SET_POST_NAME_OPT, value);
        },
        changePostNumber({commit}, value) {
            commit(POST.SET_POST_NUMBER, value);
        },
        changePostStatus({commit}, value) {
            commit(POST.SET_POST_STATUS, value);
        },
    }
}