// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import all from 'components/all.vue'
import cream from 'components/cream.vue'
import qa from 'components/qa.vue'

import './assets/bootstrap/css/bootstrap.min.css'
// 添加了一行注释
import $ from 'jquery'
console.log($(document))

Vue.use(VueRouter)
Vue.config.productionTip = false

const routes = [
  { path: '/', component: all },
  { path: '/all', component: all },
  { path: '/cream', component: cream },
  { path: '/qa', component: qa }
]
const router = new VueRouter({
  linkActiveClass: 'active',
  routes // （缩写）相当于 routes: routes
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})

