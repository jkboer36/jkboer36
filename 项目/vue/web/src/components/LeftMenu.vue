<template>
  <div>
    <el-menu default-active="1" text-color="#ffb3a7" active-text-color="#f05654">
      <el-menu-item index="1" @click="togetorder">
        <el-icon :size="20"><User /></el-icon>
        <span>待接订单</span>
      </el-menu-item>
      <el-menu-item index="2" @click="tomyorder">
        <el-icon :size="20"><Tickets /></el-icon>
        <span @click="tomyorder">我的接单</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "Ad_aside",
  methods: {
    togetorder(){
      router.push("/B1");
    },
    tomyorder(){
      router.push("/B2");
    }
  }
};
</script>

<style>
.el-menu{
  background-color:rgba(255, 255, 255, 0);
}

.el-menu-item:hover{
  background-color: #ffa60084;
}

.el-menu-item{
  font-size: 25px;
  font-weight: bold;
  height: 200px;
}
</style>