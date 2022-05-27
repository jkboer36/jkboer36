<template>
  <el-aside width="200px" style="border-right: 1px solid black; height: 100vh; background-color: rgb(48, 65, 86);margin-left: 20px">
    <el-scrollbar>
      <el-menu :default-openeds="['1','2', '3']" style="min-height:100%;"
               background-color="rgb(48,65,86)"
               text-color="#fff"
               active-text-color="#ffd04b"
               default-active="1">
        <div style="height:60px; line-height:60px; border-bottom: 1px black solid;text-align:center; font-size: 25px; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;">
          <b style="color:white">管理系统</b>
        </div>
        <el-sub-menu index="1">
          <template #title>
            <el-icon><message /></el-icon>首页
          </template>
          <el-menu-item @click="tofood" index="1-1">全部商品</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="2">
          <template #title>
            <el-icon><user /></el-icon>用户管理
          </template>
          <el-menu-item @click="touser" index="2-1">用户</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="3">
          <template #title>
            <el-icon><bicycle /></el-icon>订单管理
          </template>
          <el-menu-item  @click="toorder" index="3-2">商品订单</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </el-scrollbar>
  </el-aside>
</template>

<script>
import router from "@/router";

export default {
  name: "adminLeftMenu",
  methods:{
    tofood(){
      router.push("/Ad_food");
    },
    touser(){
      router.push("/Ad_user");
    },
    toorder(){
      router.push("/Ad_order");
    },
  }
}
</script>

<style scoped>

</style>