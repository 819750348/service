export default {
    routeList: [{
            path: '/model-custom-search',
            component: resolve => require(['../components/rup/search-custom.vue'], resolve),
            meta: {
                title: '模型搜索'
            }
        },
        {
            path: '/model-self-resource-:type',
            component: resolve => require(['../components/rup/resource/self.vue'], resolve),
            meta: {
                title: '我的资源'
            },
            props:true
        },
        {
            path: '/model-upload',
            component: resolve => require(['../components/rup/upload.vue'], resolve),
            meta: {
                title: '模型上传'
            }
        },
        {
            path: '/model-resource-upload-:category',
            component: resolve => require(['../components/rup/resource/upload.vue'], resolve),
            meta: {
                title: '模型资源上传'
            },
            props:true
        },
        {
            path: '/model-resource-preview-:modelId',
            component: resolve => require(['../components/rup/resource/preview.vue'], resolve),
            meta: {
                title: '模型资源预览'
            },
            props:true
        },
        {
            path: '/model-resource-edit-:modelId-:category',
            component: resolve => require(['../components/rup/resource/upload.vue'], resolve),
            meta: {
                title: '模型资源上传'
            },
            props:true
        },
        {
            path: '/model-parameter-upload',
            component: resolve => require(['../components/rup/parameter/upload.vue'], resolve),
            meta: {
                title: '模型参数上传'
            },
            props:true
        },
        {
            path:'/model-user-manage',
            component: resolve => require(['../components/rup/user/page.vue'], resolve),
            meta: {
                title: '用户管理'
            }
        },
        {
            path:'/model-manage',
            component: resolve => require(['../components/rup/model/manage.vue'], resolve),
            meta: {
                title: '模型管理'
            }
        },
        {
            path:'/parameter-manage',
            component: resolve => require(['../components/rup/parameter/manage.vue'], resolve),
            meta: {
                title: '参数管理'
            }
        },
        {
            path:'/system-config',
            component: resolve => require(['../components/rup/config/system.vue'], resolve),
            meta: {
                title: '系统设置'
            }
        },
        {
            path:'/user-config',
            component: resolve => require(['../components/rup/config/user.vue'], resolve),
            meta: {
                title: '人员设置'
            }
        },
        {
            path:'/permission-config',
            component: resolve => require(['../components/rup/config/permission.vue'], resolve),
            meta: {
                title: '权限设置'
            }
        },
        {
            path:'/category-manage',
            component: resolve => require(['../components/rup/config/category.vue'], resolve),
            meta: {
                title: '分类管理'
            }
        },
        {
            path:'/model-approval-process-:modelId-:flowType',
            component: resolve => require(['../components/rup/model/approval-process.vue'], resolve),
            meta: {
                title: '审核状态'
            },
            props:true
        },
        {
            path:'/model-storage-approval-process-:modelId-:flowType-:category',
            component: resolve => require(['../components/rup/model/approval-process.vue'], resolve),
            meta: {
                title: '审核状态'
            },
            props:true
        },
        {
            path:'/model-view-approval-process-:modelId-:flowType',
            component: resolve => require(['../components/rup/model/approval-process-view.vue'], resolve),
            meta: {
                title: '审核状态'
            },
            props:true
        }
    ]
}