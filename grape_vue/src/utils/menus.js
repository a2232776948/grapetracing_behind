import {getRequest} from "./api";

export const initMenu = (router, store) => {
    if (store.state.routes.length > 0) {
        return;
    }
    getRequest("/system/config/menu").then(data => {
        if (data) {
            let fmtRoutes = formatRoutes(data);
            router.addRoutes(fmtRoutes);
            store.commit('initRoutes', fmtRoutes);
            store.dispatch('connect');
        }
    })
};
export const formatRoutes = (routes) => {
    let fmRoutes = [];
    routes.forEach(router => {
        let {
            path,
            component,
            name,
            meta,
            iconCls,
            children
        } = router;
        if (children && children instanceof Array) {
            children = formatRoutes(children);
        }
        let fmRouter = {
            path: path,
            name: name,
            iconCls: iconCls,
            meta: meta,
            children: children,
            component(resolve) {
                if (component.startsWith("Home")) {
                    require(['../views/' + component + '.vue'], resolve);
                } else if (component.startsWith("PlantSys")) {
                    require(['../views/plants/' + component + '.vue'], resolve);
                } else if (component.startsWith("AreaSys")) {
                    require(['../views/farm/' + component + '.vue'], resolve);
                } else if (component.startsWith("FarmBusinessSys")) {
                    require(['../views/farmBusiness/' + component + '.vue'], resolve);
                } else if (component.startsWith("QualitySys")) {
                    require(['../views/quality/' + component + '.vue'], resolve);
                } else if (component.startsWith("LogisticSys")) {
                    require(['../views/logistic/' + component + '.vue'], resolve);
                } else if (component.startsWith("QRcodeSys")) {
                    require(['../views/QRcode/' + component + '.vue'], resolve);
                } else if (component.startsWith("BlockChainSys")) {
                    require(['../views/blockchain/' + component + '.vue'], resolve);
                } else if(component.startsWith("UserSys")){
                    require(['../views/sys/' + component + '.vue'], resolve);
                } else if (component.startsWith("GoodsSys")){
                    require(['../views/goods/' + component + '.vue'],resolve);
                }
            }
        };
        fmRoutes.push(fmRouter);
    });
    return fmRoutes;
};