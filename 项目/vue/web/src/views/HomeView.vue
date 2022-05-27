<template>
  <div id="HomeView">
    <el-container>
      <el-header className="el-header" style="background-color: black">
        <home-header></home-header>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <!--引入自定义左侧菜单栏-->
          <left-menu></left-menu>
        </el-aside>
        <el-main>
          <!--引入跳转路由-->
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import HomeHeader from '../components/Header'
import LeftMenu from '../components/LeftMenu'

export default {
  name: "HomeView",
  components: {
    HomeHeader,
    LeftMenu
  }
}
</script>

<style>
</style>
