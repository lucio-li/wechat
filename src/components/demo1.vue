<template>
    <h1>demo1</h1>
</template>

<script>
export default {
    name:'demo1',
    data() {
        return {
            positionY: 0,
            timer: null,
        }
    },
    methods: {
        func1:function(){
            window.alert($(window))
            console.log($(window))
        }
    },
    props: ['signinUp', 'headTitle', 'goBack'],
    computed: {
        // 计算属性是基于它们的依赖进行缓存的，而method不会。
        fullName: {
            // getter
            get: function () {
                return this.firstName + ' ' + this.lastName
            },
            // setter，计算属性默认只有 getter ，不过在需要时你也可以提供一个 setter
            set: function (newValue) {
                var names = newValue.split(' ')
                this.firstName = names[0]
                this.lastName = names[names.length - 1]
            }
        }
    },
    watch: {
        // 如果 question 发生改变，这个函数就会运行
        question: function () {
            console.log("question");
        }
    },
    components: {},
    // 生命周期
    beforeCreate() {
        // 配置数据观测（编译模版前）的工作
    },

    created() {
        // 编译模版后的工作
    },

    beforeMount() {
        // 挂载实例到DOM之前的工作
    },

    mounted() {
        // 挂载实例到DOM之后的工作
    },
    beforeUpdate() {
        // 在数据变化时前的工作 
    },
    updated() {
        // 在数据变化时后的工作 
    },
    beforeDestroy() {

    },
    destory() { }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style lang="scss">
</style>
