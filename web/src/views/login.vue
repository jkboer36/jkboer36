<template>
  <div>
    <el-form :rules="rules" :model="loginForm" class="loginRoot">
      <h3 class="loginTitle">系统登陆</h3>
      <el-form-item prop="username">
        <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="请输入用户名"/>
      </el-form-item>
      <el-form-item prop="password">
        <el-input type="text" v-model="loginForm.password" auto-complete="off" placeholder="请输入密码"/>
      </el-form-item>
      <div style="height: 40px">
        <p style="width: 40px;cursor: pointer;float: right" @click="regi = regi == '注册' ? '登录':'注册'">{{regi}}</p>
      </div>
      <div style="text-align: center">
        <el-button v-if="regi == '注册'" type="primary" style="width: 60%" @click = "login">登陆</el-button>
        <el-button v-else type="primary" style="width: 60%" @click = "register">注册</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        password: [{required: true, message: '请输入密码', trigger: 'blur'}],
      },
      regi:'注册'
    }
  },
  methods:{
    login(){
      axios.post('http://localhost:9090/User/login',
          {user_account:'111', user_password:'123'
          })
          .then(function (response){
            console.log(response);
            alert(response.data.data.user_name)
          })
    },
    register(){

    }
  }

}
</script>

<style>
.loginRoot {
  border-radius: 15px;
  background-clip: padding-box;
  margin: 150px auto;
  width: 500px;
  padding: 15px 35px;
  background: aliceblue;
  border: 1px solid #eaeaea;
  box-shadow: 0 0 25px #c6c6c6;
}
.loginTitle {
  margin: 15px auto 20px auto;
  text-align: center;
  color: #505050;
}
.right {
  float: right;
  width: 60px;
}
</style>
