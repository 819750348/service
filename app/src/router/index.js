import Vue from 'vue';
import Router from 'vue-router';

import old from './old';
import system from './system';
import model from './model';
import test from './test';
import service from './service';

Vue.use(Router);

var routes = [{
    path: '/home',
    component: resolve => require(['../components/common/Home.vue'], resolve),
    meta: {
        title: '自述文件'
    },
    children: old.routeList.concat(system.routeList).concat(model.routeList).concat(test.routeList).concat(service.routeList)
},
{
    path: '/login',
    component: resolve => require(['../components/page/LoginForm.vue'], resolve)
},
{
    path:'/register',
    component: resolve => require(['../components/page/register.vue'], resolve),
    meta: {
        title: '用户注册'
    }
},
{
    path: '*',
    redirect: '/404'
}
];

export default new Router({
    model: 'history',
    routes: routes
})
