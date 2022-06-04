<template>
  <div class="header">
    <div style="width: 15%"><el-avatar :size="50" :fit="fill" :src="heard_url" style="margin:20px 10px 10px 30px"/></div>
    <div style="flex:1"></div>
    <div>
        <el-dropdown>
          <el-avatar :size="50" :fit="fill" :src="url" style="margin:20px 10px 10px 30px"/>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item @click="exit" style="font-weight:bold">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
    </div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name:"Header",
  data(){
    return{
      url:require("../assets/avatar1.png"),
      heard_url:"https://ss0.baidu.com/94o3dSag_xI4khGko9WTAnF6hhy/baike/s=220/sign=db224fab45a98226bcc12c25ba80b97a/622762d0f703918f900af014573d269758eec411.jpg"
    }
  },
  methods:{
    exit(){
      window.localStorage.removeItem("user_id")
      window.localStorage.removeItem("user_root")
      router.push("/");
    },
  }
};
</script>

<style scope>
.header {
  display: flex;
  height: 80px;
  line-height: 80px;
}
</style>