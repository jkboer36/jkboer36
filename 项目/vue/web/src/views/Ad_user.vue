<template>

  <div>
    <el-breadcrumb separator="/">
      <el-breadcrumb-item><p style="font-size:large">管理员</p></el-breadcrumb-item>
      <el-breadcrumb-item><p style="font-size:large">用户管理</p></el-breadcrumb-item>

    </el-breadcrumb>
  </div>
  <el-input v-model="searchusername" size="large" placeholder="搜索用户名" clearable>
    <template #append>
      <el-button @click="searchuser">
        <el-icon size="large"><Search /></el-icon>
      </el-button>
    </template>
  </el-input>
  <el-button
      size="small"
      type="warning"
      round
      @click="adduser()"
  >添加</el-button>
  <el-table
    :data="
      user.data
    "
    border
    style="width: 100%"
    stripe
  >
    <el-table-column
      v-for="(item, index) in user.col"
      :label="item.label"
      :prop="item.prop"
    >
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button size="small" round @click="change(scope.row)"
          >修改</el-button
        >
        <el-button
          size="small"
          type="warning"
          round
          @click="Delete(scope.row)"
          >删除</el-button
        >
        <el-dialog
          v-model="dialog1Visible"
          title="修改"
          width="30%"
          :append-to-body="true"
          draggable
        >
          <el-form :model="form" label-position="top" label-width="25%">
            <el-form-item label="用户名:">
              <el-input v-model="form.user_name" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="账号:">
              <el-input v-model="form.user_account" autocomplete="off" clearable/>
            </el-form-item>
            <el-form-item label="密码:">
              <el-input v-model="form.user_password" autocomplete="off" clearable/>
            </el-form-item>
            <el-form-item label="权限:">
              <el-input v-model="form.user_root" autocomplete="off" clearable />
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button round @click="dialog1Visible = false">取消</el-button>
              <el-button
                round
                type="warning"
                @click="changeuser"
                >确定</el-button
              >
            </span>
          </template>
        </el-dialog>
        <el-dialog
          v-model="dialog2Visible"
          title="删除"
          width="30%"
          :append-to-body="true"
          draggable
        >
          <span>确定删除此项吗？</span>
          <template #footer>
            <span class="dialog-footer">
              <el-button round @click="dialog2Visible = false"
                >取消</el-button
              >
              <el-button type="warning" round @click="Deleteuser"
                >确定</el-button
              >
            </span>
          </template>
        </el-dialog>
      </template>
    </el-table-column>
  </el-table>
  <div class="example-pagination-block">
    <el-pagination
        background
        class="pagiantion"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pagesize"
        layout="total, prev, pager, next, jumper"
        style="float: right;"
        :total="total">
    </el-pagination>
  </div>
</template>

<script>
import axios from "axios";
import {ElMessage} from "element-plus";
import router from "@/router";
import globalconfig from "@/globalconfig";

export default {
  name: "ad_user",
  data() {
    return {
      total: "",
      currentPage: 1,
      pagesize: 6,
      searchusername: "",
      dialog1Visible: false,
      dialog2Visible: false,
      user: {
        sel: null,
        col: [
          {
            prop: "user_id",
            label: "编号",
          },
          {
            prop: "user_name",
            label: "用户",
          },
          {
            prop: "user_account",
            label: "账号",
          },
          {
            prop: "user_password",
            label: "密码",
          },
          {
            prop: "user_root",
            label: "权限",
          },
        ],
        data: [],
      },
      form: {
        user_id: "",
        user_name: "",
        user_account: "",
        user_password:"",
        use_root: "",
      },
    };
  },
  created() {
    if(window.localStorage.getItem('user_root') ===null || window.localStorage.getItem('user_root')!== '0'){
      router.push("/");
    }
    this.getlist();
  },
  methods: {
    getlist(){
      axios.get(globalconfig.axios_url+'/users/searchuser',
          {params:{pageNum:this.currentPage, pageSize:'6', username:""}})
          .then(res=>{
            console.log(res);
            this.user.data=res.data.data;
            this.total=res.data.total;
          })
    },
    adduser(){
      this.form.user_id=null;
      this.form.user_name=null;
      this.form.user_account=null;
      this.form.user_password=null;
      this.form.user_root=null;
      this.dialog1Visible = true;
    },
    searchuser: function () {
      //搜索后跳转到第一页
      this.currentPage = 1;
      /*
      主体函数
      */
      axios.get(globalconfig.axios_url+'/users/searchuser',
          {params:{pageNum:this.currentPage, pageSize:'6', username:this.searchusername}})
          .then(res=>{
            console.log(res);
            this.user.data=res.data.data;
            this.total=res.data.total;
          })

      //搜索后清空输入框
      this.searchusername = "";
    },
    Delete: function (row) {
      this.form.user_id = row.user_id;
      this.dialog2Visible = true;
    },
    Deleteuser(){
      const _this = this;
      axios.post('http://localhost:8081/users/delete',
          {user_id:this.form.user_id
          })
          .then(function (response){
            console.log(response)
            if(response.data.code === 200){
              ElMessage({
                message: '删除成功',
                type: 'success',
              })
            }else{
              ElMessage({
                message: '删除失败',
                type: 'warning',
              })
            }
            _this.dialog2Visible = false;
            _this.currentPage = 1;//更新当前页码
            _this.getlist(); //重新查询
          }).catch(error=>{
        alert("加载失败，请重新尝试")
      });

    },
    change: function (row) {
      this.dialog1Visible = true;
      this.form.user_id = row.user_id;
      this.form.user_name = row.user_name;
      this.form.user_account=row.user_account;
      this.form.user_password=row.user_password;
      this.form.user_root=row.user_root;
    },
    changeuser(){
      const _this =this;
      axios.post(globalconfig.axios_url+'/users/change',
          {user_id:this.form.user_id ,user_name:this.form.user_name ,user_account:this.form.user_account, user_password:this.form.user_password, user_root:this.form.user_root
          })
          .then(function (response){
            console.log(response)
            if(response.data.code === 200){
              ElMessage({
                message: '修改成功',
                type: 'success',
              })
            }else{
              ElMessage({
                message: '修改失败',
                type: 'warning',
              })
            }
            _this.dialog1Visible = false;
            _this.currentPage = 1;//更新当前页码
            _this.getlist(); //重新查询
          }).catch(error=>{
        alert("加载失败，请重新尝试")
      });

    },
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage;//更新当前页码
      this.getlist(); //重新查询
    },
  },
};
</script>

<style>
.el-table {
  margin-top: 3%;
  --el-table-header-bg-color:orange;
  --el-table-header-text-color:white;
  font-size:16px;
}

.el-table__inner-wrapper::before {
  height: 0;
}

.el-table--border::before {
  width: 0;
}

.example-pagination-block {
  margin-left: auto;
  margin-right: auto;
  margin-top: 30px;
  display: table;
}

.dialog-footer button:first-child {
  margin-right: 10px;
}

.el-pagination {
  --el-pagination-hover-color: orange;
}

.el-pagination.is-background .el-pager li:not(.is-disabled).is-active {
  background-color: orange;
}

.el-dialog__title {
  color: orange;
  font-weight: bold;
  font-size:24px;
}
</style>