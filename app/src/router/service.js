export default {
    routeList: [{
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: { title: '首页' }
                },

                {
                    path: '/servicebrowsing',
                    component: resolve => require(['../components/page/Servicebrowsing.vue'], resolve),
                    meta: { title: '服务浏览' }
                },
                {
                    path: '/serviceregistration',
                    component: resolve => require(['../components/page/Serviceregistration.vue'], resolve),
                    meta: { title: '服务注册' }
                },
                {
                    path: '/servicedevelopment',
                    component: resolve => require(['../components/page/Servicedevelopment.vue'], resolve),
                    meta: { title: '服务开发' }
                },
                {
                    path: '/servicemanagement',
                    component: resolve => require(['../components/page/Servicemanagement.vue'], resolve),
                    meta: { title: '服务管理' }
                },
                {
                    path: '/nodebrowsing',
                    component: resolve => require(['../components/page/Nodebrowsing.vue'], resolve),
                    meta: { title: '服务浏览' }
                },
                {
                    path: '/logsbrowsing',
                    component: resolve => require(['../components/page/Logsbrowsing.vue'], resolve),
                    meta: { title: "日志浏览" }
                },
                {
                    path: '/detail',
                    component: resolve => require(['../components/page/Detail.vue'], resolve),
                    meta: { title: '注册明细' }
                },
                {
                    path: '/devdetail',
                    component: resolve => require(['../components/page/Devdetail.vue'], resolve),
                    meta: { title: '开发明细' }
                },
                {
                    path: '/mandetail',
                    component: resolve => require(['../components/page/Mandetail.vue'], resolve),
                    meta: { title: '管理明细' }
                },
                {
                    path: '/nodedetail',
                    component: resolve => require(['../components/page/NodeDetail.vue'], resolve),
                    meta: { title: '节点明细' }
                },
                {
                    path: '/browsingdetail',
                    component: resolve => require(['../components/page/BrowsingDetail'], resolve),
                    meta: { title: '浏览明细' }
                }

    ]
}
