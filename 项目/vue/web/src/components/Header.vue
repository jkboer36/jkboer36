<template>
  <div class="header">
    <div style="width: 15%"><el-avatar :size="50" :fit="fill" :src="url" style="margin:20px 10px 10px 30px"/></div>
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
export default {
  name:"Header",
  data(){
    return{
      url:require("../assets/avatar1.png")
    }
  },
  methods:{
    search:function(){

    }
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