export default{
    routeList:[
        {
            path:'/test-model-upload',
            component:resolve => require(['../components/test/upload.vue'], resolve),
            meta: {
                title: '上传/下载'
            }
        },
        {
            path:'/test-java-coding',
            component:resolve => require(['../components/test/java.vue'], resolve),
            meta: {
                title: 'java编程'
            }
        }
    ]
}