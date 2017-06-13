<template>
  <h1>header</h1>
  <router-link to="/foo">Go to Foo</router-link>
  <router-link to="/bar">Go to Bar</router-link>
  <!-- 路由出口 -->
  <!-- 路由匹配到的组件将渲染在这里 -->
  <router-view></router-view>
</template>

<script  type="text/ecmascript-6">
export default {
  name: 'header',
}
</script>

<style scoped>

</style>
