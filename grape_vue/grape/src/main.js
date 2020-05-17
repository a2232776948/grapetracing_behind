import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/css/global.css'
import {initMenu} from "./utils/menus";
import store from "@/store";


import {postJson, postRequest, postKeyValueRequest, putRequest, deleteRequest, getRequest} from "./utils/api";
import moment from "moment";
import echarts from 'echarts'


Vue.use(ElementUI, {size: 'small'});
Vue.config.productionTip = false;

Vue.prototype.$echarts = echarts;

Vue.prototype.postRequest = postRequest;
Vue.prototype.postKeyValueRequest = postKeyValueRequest;
Vue.prototype.putRequest = putRequest;
Vue.prototype.deleteRequest = deleteRequest;
Vue.prototype.getRequest = getRequest;
Vue.prototype.postJson = postJson;

Vue.prototype.$moment = moment;

router.beforeEach(((to, from, next) => {
    if (to.path === '/login') {
        next();
    } else if (to.path === '/'){
        next({path: '/home'});
    }else {
      // if (window.sessionStorage.getItem("user")){
      if (window.localStorage.getItem("user")){
            initMenu(router, store);
             next();
      }else {
          next('/login?redirect='+to.path);
      }
    }
}));

new Vue({
    router,
    store,
    render: h => h(App)
}).$mount('#app');
