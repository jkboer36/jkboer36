<template>
  <el-menu class="el-menu-vertical-demo"
           background-color="#E9EEF3"
           text-color="black">
    <el-submeu index="1" >
      <template slot="title"><i class="el-icon-message"></i>导航一</template>
      <el-menu-item-group>
        <!--<template slot="title">分组一</template>-->
        <el-menu-item index="1-1">点餐</el-menu-item>
        <el-menu-item index="1-2">历史订单</el-menu-item>
        <!--<el-menu-item-group title="分组2">-->
        <el-menu-item index="1-3">订单状态</el-menu-item>
        <el-submenu index="1-4">
          <template slot="title">测试4</template>
          <el-menu-item index="1-4-1">我的主页</el-menu-item>
        </el-submenu>
      </el-menu-item-group>
    </el-submeu>
  </el-menu>
</template>

<style>
.el-menu-vertical-demo{
  height: 645px
}
</style>

<script>
export default {
  name: 'LeftMenu'
}

</script>