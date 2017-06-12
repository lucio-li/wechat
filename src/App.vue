<template>
  <div>
    <c-header></c-header>
    <keep-alive>
      <router-view></router-view>
    </keep-alive>
  </div>
</template>

<script>
import header from 'components/common/header.vue'
export default {
  name: 'app',
  components: {
    "c-header": header
  }
}
</script>

<style>
</style>
