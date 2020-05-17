import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        routes: [],
        sessions: {},
        users: [],
        currentSession: null,
        currentUser: JSON.parse(window.localStorage.getItem("user")),
        filterKey: '',
        isDot: {}
    },
    mutations: {
        INIT_CURRENTUSER(state, user) {
            state.currentUser = user;
        },
        initRoutes(state, data) {
            console.log(data);
            state.routes = data;
        },
        changeCurrentSession(state, currentSession) {
            Vue.set(state.isDot, state.currentUser.username + "#" + currentSession.username, false);
            state.currentSession = currentSession;
        }
    }
});

store.watch(function (state) {
    return state.sessions
}, function (val) {
    localStorage.setItem('vue-chat-session', JSON.stringify(val))
}, {
    deep: true
});

export default store;