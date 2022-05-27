<template>
  <el-header style="text-align: right; background-color: rgb(48, 65, 86);height: 70px" >
    <div class="toolbar">
      <el-dropdown split-button type="success" plain >
        个人中心
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="exit" style="font-weight:bold">退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </el-header>
</template>

<script>
import router from "@/router";

export default {
  name: "AdminHeader",
  methods:{
    exit(){
      window.localStorage.removeItem("user_id")
      window.localStorage.removeItem("user_root")
      router.push("/");
    },
  }
}
</script>

<style scoped>
.toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
  font-size: 20px;
  font-weight: bold;
}

</style>