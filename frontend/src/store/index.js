import Vue from 'vue'
import Vuex from 'vuex'
import UserProfileHttp from '@/api/userProfileHttp'

Vue.use(Vuex)

let KEY_TOKEN_PROPERTY = 'data';
let user = JSON.parse(localStorage.getItem(KEY_TOKEN_PROPERTY));
let initialState = user ? {status: {loggedIn: true}, user}
    : {status: {loggedIn: false}, user: null};

export default new Vuex.Store({
    state: initialState,
    getters: {},
    mutations: {
        loginPass(state, data) {
            state.status.loggedIn = true;
            state.user = data;
        },
        loginFail(state) {
            state.status.loggedIn = false;
            state.user = null;
        },
        logout(state) {
            state.status.loggedIn = false;
            state.user = null;
        }
    },
    actions: {
        login({commit}, data) {
            return UserProfileHttp.signIn(data)
                .then(data => {
                    commit('loginPass', data);
                    return Promise.resolve(data);
                }, err => {
                    commit('loginFail');
                    return Promise.reject(err);
                });
        },
        logout({commit}, data) {
            UserProfileHttp.signOut(data).then(() => {
                commit('logout');
            });
        }
    },
    modules: {}
})
