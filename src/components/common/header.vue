<template>
  <div class="tab">
    <div class="tab-item">
      <router-link to="/all">全部</router-link>
    </div>
    <div class="tab-item">
      <router-link to="/cream">精华</router-link>
    </div>
    <div class="tab-item">
      <router-link to="/qa">问答</router-link>
    </div>
  </div>
</template>

<script  type="text/ecmascript-6">
export default {
  name: 'header',
}
</script>

<style scoped>
.tab{
    display: flex;
    width: 100%;
    height: 40px;
    line-height: 40px;
    border-bottom: 1px solid rgba(7, 17, 27, 0.1);
}
    
.tab-item{
    flex: 1;
    text-align: center;
}

</style>
