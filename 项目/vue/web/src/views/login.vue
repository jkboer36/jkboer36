<template>
  <div class="backcont">
    <el-form :rules="rules" :model="loginForm" ref="ruleForm" class="loginRoot">
      <h3 class="loginTitle">系统登陆</h3>
      <el-form-item v-if="regi === '登录'" label="用户名:" prop="user_name">
        <el-input type="text" v-model="loginForm.user_name" auto-complete="off" placeholder="请输入用户名"/>
      </el-form-item>
      <el-form-item label="账号     ：" prop="user_account">
        <el-input type="text" v-model="loginForm.user_account" auto-complete="off" placeholder="请输入账号"/>
      </el-form-item>
      <el-form-item label="密码     ：" prop="user_password">
        <el-input type="text" v-model="loginForm.user_password" auto-complete="off" placeholder="请输入密码"/>
      </el-form-item>
      <div style="height: 40px">
        <p style="width: 40px;cursor: pointer;float: right" @click="regi = regi === '注册' ? '登录':'注册'">{{regi}}</p>
      </div>
      <div style="text-align: center">
        <el-button v-if="regi === '注册'" type="primary" style="width: 60%" @click = "login">登陆</el-button>
        <el-button v-else type="primary" style="width: 60%" @click = "register">注册</el-button>
      </div>
    </el-form>
  </div>
</template>

<script>
import axios from "axios";
import {ElMessage} from "element-plus";
import globalconfig from "@/globalconfig";
import router from "@/router";

export default {
  name: "Login",
  data() {
    return {
      loginForm: {
        user_account: '',
        user_password: '',
        user_name:""
      },
      rules: {
        user_name: [{required: true, message: '请输入用户名', trigger: 'blur'}],
        user_account: [{required: true, message: '请输入账号', trigger: 'blur'}],
        user_password: [{required: true, message: '请输入密码', trigger: 'blur'}],
      },
      regi:'注册'
    }
  },
  methods:{
    login(){
      const _this = this;
      axios.post(globalconfig.axios_url+"/users/login",
          {user_account:this.loginForm.user_account, user_password:this.loginForm.user_password
          })
          .then(function (response){
            console.log(response);
            if(response.data.code === "200"){
              window.localStorage.setItem('user_id',response.data.data.user_id)
              window.localStorage.setItem('user_root',response.data.data.user_root)
              if(response.data.data.user_root === 0){
                router.push("/Ad_food");
              }
              if(response.data.data.user_root === 1){
                router.push("/User");
              }
              if(response.data.data.user_root === 2){
                router.push("/B1");
              }
            }else{
              ElMessage({
                message: '账号或密码错误',
                type: 'warning',
              })
              _this.loginForm.user_password = "";
            }
          })
    },
    register(){
      axios.post(globalconfig.axios_url+"/users/register",
          {user_account:this.loginForm.user_account, user_password:this.loginForm.user_password , user_name:this.loginForm.user_nmae
          })
          .then(function (response){
            console.log(response);
            if(response.data.code === "200"){
              ElMessage({
                message: '注册成功',
                type: 'success',
              })
            }else{
              ElMessage({
                message: '账号已存在',
                type: 'warning',
              })
            }
          })
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

.backcont{
  background-image: url(/src/assets/back.jpg);
  display: flex;
  flex-direction: column;
  background-size: 100% 100%;
  background-attachment: fixed;

  width: 100%;
  height: 100%;
  min-width: 800px;
  min-height: 600px;

  justify-content: center;
  align-items: center;
}
</style>
