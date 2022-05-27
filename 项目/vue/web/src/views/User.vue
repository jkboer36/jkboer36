<template>

  <div style="margin-top:5px;">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item><p style="font-size:large">菜单</p></el-breadcrumb-item>

    </el-breadcrumb>
  </div>
  <el-input v-model="searchfoodname" size="large" placeholder="商品名称" style="width: 400px;" clearable></el-input>
  <el-input v-model="searchfoodtype" size="large" placeholder="类型" style="width: 200px;" clearable></el-input>
  <el-button @click="searchfood">
    <el-icon size="large"><Search /></el-icon>
  </el-button>
  <el-table
      :data="
      food.data
    "
      border
      style="width: 100%"
      stripe
  >
    <el-table-column prop="img_url" width="300">
      <template #default="scope">
        <div style="max-height:300px ">
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
        <el-button
          v-if="scope.row.food_rest !== 0"
          size="small"
          type="warning"
          round
          @click="buy(scope.row)"
          >下单</el-button>
        <el-dialog
          v-model="dialogVisible"
          title="下单"
          width="30%"
          :append-to-body="true"
          draggable
        >
          <el-form :model="form" label-position="top" label-width="25%">
            <el-form-item label="您需要的份数:">
              <el-input v-model="form.numbers" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="您的地址:">
              <el-input v-model="form.address" autocomplete="off" clearable />
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button round @click="dialogVisible = false">取消</el-button>
              <el-button
                round
                type="warning"
                @click="buyfood"
                >购买</el-button
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

export default {
  name: "ad_food",
  data() {
    return {
      total: "",
      currentPage: 1,
      pagesize: 6,
      searchfoodname: "",
      searchfoodtype: "",
      dialogVisible: false,
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
        food_id:"",
        numbers: "",
        address:"",
      },
    };
  },
  created() {
    this.getlist();
  },
  methods: {
    getlist(){
      axios.get('http://localhost:8081/foods/searchfood',
          {params:{pageNum:this.currentPage, pageSize:'6', foodname:'',foodtype:''}})
          .then(res=>{
            console.log(res);
            this.food.data=res.data.data;
            this.total=res.data.total;
          })
    },
    searchfood: function () {
      //搜索后跳转到第一页
      this.currentPage = 1;
      /*
      主体函数
      */
      axios.get('http://localhost:8081/foods/searchfood',
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
    buy(row){
      this.form.food_id = row.food_id;
      this.dialogVisible = true;
    },
    buyfood(){
      axios.post('http://localhost:8081/users/buy',
          {food_id:this.form.food_id ,user_id: window.localStorage.getItem('user_id'),numbers:this.form.numbers
          })
          .then(function (response){
            console.log(response)
            if(response.data.code === 200){
              ElMessage({
                message: '下单成功',
                type: 'success',
              })
            }else{
              ElMessage({
                message: '下单失败',
                type: 'warning',
              })
            }
          }).catch(error=>{
        alert("加载失败，请重新尝试")
      });
      this.dialogVisible = false;
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