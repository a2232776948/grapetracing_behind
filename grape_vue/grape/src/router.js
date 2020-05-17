import Vue from 'vue'
import Router from 'vue-router'
import Login from './views/Login'
import Home from "./views/Home";
import UserInfo from "./views/sys/UserInfo";
/*import PlantSys from "./views/plants/PlantSys";
import AreaSys from "./views/farm/AreaSys";
import LogisticSys from "./views/logistic/LogisticSys";
import QRcodeSys from "./views/QRcode/QRcodeSys";
import QualitySys from "./views/quality/QualitySys";
import DigitFarm from "./views/digitFarm/DigitFarm";
import FarmBusinessSys from "./views/farmBusiness/FarmBusinessSys";
import BlockChainSys from "./views/blockchain/BlockChainSys";*/

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/login',
            name: 'Login',
            component: Login,
            hidden: true
        },
        {
            path: '/home',
            name: 'Home',
            component: Home,
            hidden: true,
            meta:{
                roles:['admin','user']
            },
            children:[
                {
                    path: '/userInfo',
                    name: '个人中心',
                    component: UserInfo
                }
            ]
            /*children:[
                {path: '/plantSys', name: '植株信息', component: PlantSys},
                {path: '/areaSys', name: '农田信息', component: AreaSys},
                {path: '/farmBusinessSys', name: '农事信息', component:FarmBusinessSys},
                {path: '/qualitySys', name: '质检信息', component:QualitySys},
                {path: '/QRcodeSys', name: '二维码信息', component:QRcodeSys},
                {path: '/logisticSys', name: '物流信息', component: LogisticSys},
                {path: '/blockchainSys', name: '区块链信息', component: BlockChainSys},
                {path: '/digitFarm', name: '数字农场', component:DigitFarm}
            ]*/
        }
    ]
})