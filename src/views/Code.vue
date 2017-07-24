<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="邮箱" prop="email">
            <el-input v-model="ruleForm.email"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="code">
            <el-input type="password" v-model="ruleForm.code"></el-input>
        </el-form-item>
        <el-form-item label-width="100px">
            <el-button type="primary" @click="submitForm('ruleForm')">立即验证</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
            {{ruleForm.imageUrl}}
        </el-form-item>
    </el-form>
</template>

<script>

export default {
    data() {
        var validatePass = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请输入密码'));
            } else {
                if (this.ruleForm.checkpassword !== '') {
                    this.$refs.ruleForm.validateField('checkpassword');
                }
                callback();
            }
        };
        var validatePass2 = (rule, value, callback) => {
            if (value === '') {
                callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm.password) {
                callback(new Error('两次输入密码不一致!'));
            } else {
                callback();
            }
        };


        return {
            msg: 'I am Demo.vue',
            ruleForm: {
                email: '',
                code: ''
            },
            rules: {

            }
        }
    },
    methods: {
        submitForm(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    this.code()
                } else {
                    console.log('error submit!!');
                    return false;
                }
            });
        },
        resetForm(formName) {
            this.$refs[formName].resetFields();
            this.ruleForm.imageUrl = '';
        },
        code() {
            return this.axios.post('/register/code', {
                email: this.ruleForm.email,
                code: this.ruleForm.code
            })
                .then(function (res) {
                    console.log(res.data)
                }.bind(this))
                .catch(function (error) {
                    console.log("服务器错误")
                    if (error.response) {
                        //请求已经发出，但是服务器响应返回的状态吗不在2xx的范围内
                        console.log(error.response.data);
                        console.log(error.response.status);
                        console.log(error.response.header);
                    } else {
                        //一些错误是在设置请求的时候触发
                        console.log('Error', error.message);
                    }
                    console.log(error.config);
                }.bind(this));
        }
    },
    props: [],
    computed: {
    },
    components: {
    },

    // 生命周期
    beforeCreate() {
        // 配置数据观测（编译模版前）的工作
    },
    created() {

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
        // 在销毁前的工作
    },
    destory() {
        // 在销毁后的工作
    }
}
</script>

<style lang="sass" scoped>

</style>
