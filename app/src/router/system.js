export default {
    routeList: [{
            path: '/user-list',
            component: resolve => require(['../components/system/user/list.vue'], resolve),
            meta: {
                title: '用户列表'
            }
        },
        {
            name: 'user-edit',
            path: '/user-edit-:id',
            props: true,
            component: resolve => require(['../components/system/user/form.vue'], resolve),
            meta: {
                title: '修改用户'
            }
        },
        {
            name: 'user-set-role',
            path: '/user-set-role-:userId',
            props: true,
            component: resolve => require(['../components/system/user/set-role.vue'], resolve),
            meta: {
                title: '设置角色'
            }
        },
        {
            path: '/user-add',
            component: resolve => require(['../components/system/user/form.vue'], resolve),
            meta: {
                title: '新建用户'
            }
        },
        {
            path: '/role-list',
            component: resolve => require(['../components/system/role/list.vue'], resolve),
            meta: {
                title: '角色列表'
            }
        },
        {
            name: 'role-edit',
            path: '/role-edit-:id',
            props: true,
            component: resolve => require(['../components/system/role/form.vue'], resolve),
            meta: {
                title: '修改角色'
            }
        },
        {
            path: '/role-add',
            component: resolve => require(['../components/system/role/form.vue'], resolve),
            meta: {
                title: '新建角色'
            }
        },
        {
            name: 'role-set-permission',
            path: '/role-set-permission-:roleId',
            props: true,
            component: resolve => require(['../components/system/role/set-permission.vue'], resolve),
            meta: {
                title: '设置权限'
            }
        },
        {
            name: 'role-set-menu',
            path: '/role-set-menu-:roleId',
            props: true,
            component: resolve => require(['../components/system/role/set-menu.vue'], resolve),
            meta: {
                title: '设置菜单'
            }
        },
        {
            name: 'role-set-index',
            path: '/role-set-index-:roleId',
            props: true,
            component: resolve => require(['../components/system/role/set-index.vue'], resolve),
            meta: {
                title: '设置首页'
            }
        },
        {
            path: '/menu-manager',
            component: resolve => require(['../components/system/menu/manager.vue'], resolve),
            meta: {
                title: '菜单列表'
            }
        },
        {
            path: '/permission-manager',
            component: resolve => require(['../components/system/permission/manager.vue'], resolve),
            meta: {
                title: '权限列表'
            }
        }
    ]
}
