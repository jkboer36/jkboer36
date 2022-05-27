<template>
  <el-container class="container" style="width: 100%; height: 100%">
    <el-header height="10%">
      <AdminHeader></AdminHeader>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <AdminLeftmenu></AdminLeftmenu>
      </el-aside>
      <el-main>
        <!--引入跳转路由-->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>



import AdminHeader from "@/components/AdminHeader";
import AdminLeftmenu from "@/components/AdminLeftmenu";

export default {
  name: "adminhome",
  components: {
    AdminHeader,
    AdminLeftmenu,
  },
};
</script>

<style scoped>
.container .el-header {
}

.container .el-aside {
}

</style>