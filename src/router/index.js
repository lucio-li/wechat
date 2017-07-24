import Vue from 'vue'
import Router from 'vue-router'

import Demo from '../views/Demo'
import Detail from '../views/Detail'
import Login from '../views/Login'
import Code from '../views/Code'
Vue.use(Router)

export default new Router({
    mode: 'hash',
    routes: [
        {
            path: '/',
            redirect: '/index'
        }, {
            path: '/index',
            name: 'index',
            component: Demo
        }, {
            path: '/detail',
            name: 'datail',
            component: Detail
        }, {
            path: '/login',
            name: 'login',
            component: Login
        }, {
            path: '/code',
            name: 'code',
            component: Code
        }
    ]
})