let proxyObj = {};
// proxyObj['/ws'] = {
//     ws: true,
//     target: "http://localhost:8545/",
//     pathRewrite: {
//         '^/ws' : ''
//     }
// };
proxyObj['/'] = {
    ws: false,
    // target: 'http://193.112.178.124:8081/',
    target: 'http://127.0.0.1:8081/',
    changeOrigin: true,
    pathRewrite: {
        '^/': ''
    }
};

module.exports = {
    devServer: {
        host: 'localhost',
        port: 8080,
        proxy:proxyObj
    },
    lintOnSave:false
};
