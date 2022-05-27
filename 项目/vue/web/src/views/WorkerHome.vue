<template>
  <el-container class="container" style="width: 100%; height: 100%">
    <el-header height="10%">
      <Header></Header>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <left-menu></left-menu>
      </el-aside>
      <el-main>
        <!--引入跳转路由-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Header from "@/components/Header";
import LeftMenu from "@/components/LeftMenu";


export default {
  name: "workerhome",
  components: {
    Header,
    LeftMenu
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