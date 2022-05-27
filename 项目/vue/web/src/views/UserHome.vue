<template>
  <el-container class="container" style="width: 100%; height: 100%">
    <el-header height="10%">
      <user-header></user-header>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <user-leftmenu></user-leftmenu>
      </el-aside>
      <el-main>
        <!--引入跳转路由-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>



import UserHeader from "@/components/UserHeader";
import UserLeftmenu from "@/components/UserLeftmenu";

export default {
  name: "userhome",
  components: {
    UserHeader,
    UserLeftmenu,
  },
};
</script>

<style scoped>
.container .el-header {
  position: relative;
  background-color: bisque;
}

.container .el-aside {
}

</style>