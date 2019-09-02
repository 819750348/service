import axios from 'axios';
import Promise from 'promise';
import router from '../router';
import {
    Loading
} from 'element-ui';
import {
    MessageBox
} from 'element-ui';
import {
    ErrorDialog
} from '../components/error/dialog.js';

var loadinginstace;
var instance = axios.create({
    // baseURL: "http://192.168.3.72:8080/"
    baseURL: "http://localhost:8080/"
    //baseURL:"/rest-service-0.1.0/"

});
instance.interceptors.request.use(
    config => {
        loadinginstace = Loading.service({
            lock: true,
            text: 'Loading',
            spinner: 'el-icon-loading',
            background: 'rgba(0,0,0,0.3)',
            customClass: 'osloading',
            fullscreen: true
        });
        if (localStorage.JWT_TOKEN && localStorage.JWT_TOKEN != null) {
            config.headers.Authorization = `token ${localStorage.JWT_TOKEN}`;
        }
        return config;
    },
    error => {
        loadinginstace.close();
        return Promise.reject(error);
    }
)

instance.interceptors.response.use(
    response => {
        loadinginstace.close();
        if (response.data.code !== undefined && response.data.data !== undefined) {
            if (response.data.code == '401') {
                router.replace({
                    path: '/login',
                    // query:{redirect:router.currentRoute.fullPath}
                });
            } else if (response.data.code === 0) {
                return Promise.resolve(response.data.data);
            } else {
                return Promise.reject(response.data);
            }
        }
        return Promise.resolve(response);
    },
    error => {
        console.info('axios interceptor error : ');
        console.error(error);
        loadinginstace.close();
        if (error.response) {
            switch (error.response.status) {
                case 401:
                    //Failure Authorize  登录时认证失败
                    {
                        MessageBox.alert("请登录", "tips")
                        .then(() => {
                            router.replace({
                                path: '/login',
                                // query:{redirect:router.currentRoute.fullPath}
                            });
                            localStorage.removeItem("JWT_TOKEN");
                        })
                        .catch(e => {
                            console.error(e);
                        });
                        break;
                    }
                case 403:
                    {
                        //Access Denied  登陆后访问未授权的资源
                        MessageBox.alert("未授权的资源", "tips")
                        break;
                    }
                case 500:
                    {
                        ErrorDialog.alert(error.response.data);
                        break;
                    }
            }
        }
        return Promise.reject(error);
    }
)

export default instance;
