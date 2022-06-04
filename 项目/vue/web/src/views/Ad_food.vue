<template>
  <div >
    <el-breadcrumb separator="/">
      <el-breadcrumb-item><p style="font-size:large">管理员</p></el-breadcrumb-item>
      <el-breadcrumb-item><p style="font-size:large">商品管理</p></el-breadcrumb-item>

    </el-breadcrumb>
  </div>

  <el-input v-model="searchfoodname" size="large" placeholder="商品名称" style="width: 400px;" clearable></el-input>
  <el-input v-model="searchfoodtype" size="large" placeholder="类型" style="width: 200px;" clearable></el-input>
    <el-button @click="searchfood">
      <el-icon size="large"><Search /></el-icon>
    </el-button>
  <el-button
      size="small"
      type="warning"
      round
      @click="addfood()"
  >添加</el-button>
  <el-table
      :data="
      food.data
    "
      border
      style="width: 100%"
      stripe
  >
    <el-table-column prop="img_url" label="图片" width="300">
      <template #default="scope">
        <div style="max-height:200px ">
          <el-image :src="scope.row.img_url"/>
        </div>

      </template>
    </el-table-column>
    <el-table-column
        v-for="(item, index) in food.col"
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
            <el-form-item label="food_name:">
              <el-input v-model="form.food_name" autocomplete="off" placeholder="Please input" />
            </el-form-item>
            <el-form-item label="food_picture:">
              <el-input v-model="form.img_url" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="food_price:">
              <el-input v-model="form.food_price" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="food_rest:">
              <el-input v-model="form.food_rest" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="food_type:">
              <el-input
                  v-model="form.food_type"
                  autocomplete="off"
                  clearable
              />
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button round @click="dialog1Visible = false">取消</el-button>
              <el-button
                  round
                  type="warning"
                  @click="changefood()"
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
              <el-button type="warning" round @click="Deletefood"
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
  name: "ad_food",
  data() {
    return {
      total: "",
      currentPage: 1,
      pagesize: 6,
      searchfoodname: "",
      searchfoodtype: "",
      dialog1Visible: false,
      dialog2Visible: false,
      food: {
        sel: null,
        col: [
          {
            prop: "food_name",
            label: "菜品名",
          },
          {
            prop: "food_price",
            label: "价格",
          },
          {
            prop: "food_rest",
            label: "剩余数量",
          },
          {
            prop: "food_type",
            label: "菜品类别",
          },
        ],
        data: [],
      },
      form: {
        food_id: "",
        food_name: "",
        img_url: "",
        food_price: "",
        food_rest:"",
        food_type:""
      },
    };
  },
  created() {
    if(window.localStorage.getItem('user_root') ===null || window.localStorage.getItem('user_root') !== '0'){
      router.push("/");
    }
    this.getlist();
  },
  methods: {
    getlist(){
      axios.get(globalconfig.axios_url+'/foods/searchfood',
          {params:{pageNum:this.currentPage, pageSize:'6', foodname:'',foodtype:''}})
          .then(res=>{
            console.log(res);
            this.food.data=res.data.data;
            this.total=res.data.total;
          })
    },
    addfood(){
      this.form.food_id=null;
      this.form.food_name=null;
      this.form.food_price=null;
      this.form.img_url=null;
      this.form.food_type=null;
      this.form.food_rest=null;
      this.dialog1Visible = true;
    },
    searchfood: function () {
      //搜索后跳转到第一页
      this.currentPage = 1;
      /*
      主体函数
      */
      axios.get(globalconfig.axios_url+'/foods/searchfood',
          {params:{pageNum:this.currentPage, pageSize:'6', foodname:this.searchfoodname,foodtype:this.searchfoodtype}})
          .then(res=>{
            console.log(res);
            this.food.data=res.data.data;
            this.total=res.data.total;
          })

      //搜索后清空输入框
      this.searchfoodname = "";
      this.searchfoodtype = "";
    },
    Delete: function (row) {
      this.form.food_id = row.food_id;
      this.dialog2Visible = true;
    },
    Deletefood(){
      const _this = this;
      axios.post(globalconfig.axios_url+'/foods/delete',
          {food_id:this.form.food_id
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
      this.form.food_id=row.food_id;
      this.form.food_name=row.food_name;
      this.form.food_price=row.food_price;
      this.form.img_url=row.img_url;
      this.form.food_type=row.food_type;
      this.form.food_rest=row.food_rest;
    },
    changefood(){
      const _this = this;
      axios.post(globalconfig.axios_url+'/foods/change',
          {food_id:this.form.food_id ,food_name:this.form.food_name , img_url:this.form.img_url,food_price:this.form.food_price, food_rest:this.form.food_rest, food_type:this.form.food_type
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
  height: 150px;
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