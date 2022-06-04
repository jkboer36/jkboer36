<template>

  <div style="margin-top:5px;">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item><p style="font-size:large">个人中心</p></el-breadcrumb-item>
      <el-breadcrumb-item><p style="font-size:large">购物车</p></el-breadcrumb-item>

    </el-breadcrumb>
  </div>
  <el-table
      :data="
      order.data
    "
      border
      style="width: 100%"
      stripe
  >
    <el-table-column prop="img_url" width="300">
      <template #default="scope">
        <div style="max-height:300px ">
          <el-image :src="scope.row.food.img_url"/>
        </div>
      </template>
    </el-table-column>

    <el-table-column prop="food.food_name" label="菜名">
      <template #default="scope">
      </template>
    </el-table-column>
    <el-table-column
        v-for="(item, index) in order.col"
        :label="item.label"
        :prop="item.prop"
    >
    </el-table-column>
    <el-table-column prop="orders.order_state" label="订单状态">
      <template #default="scope">
        <p>待支付</p>
      </template>
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button size="small" round @click="pay(scope.row)"
        >支付</el-button
        >
        <el-button
            size="small"
            type="warning"
            round
            @click="Return(scope.row)"
        >退单</el-button
        >
        <el-dialog
            v-model="dialog1Visible"
            title="支付"
            width="30%"
            :append-to-body="true"
            draggable
        >
          <span>确认支付吗？</span>
          <template #footer>
            <span class="dialog-footer">
              <el-button round @click="dialog1Visible = false"
              >取消</el-button
              >
              <el-button type="warning" round @click="payorder"
              >确定</el-button
              >
            </span>
          </template>
        </el-dialog>

        <el-dialog
            v-model="dialog2Visible"
            title="退单"
            width="30%"
            :append-to-body="true"
            draggable
        >
          <span>要取消该订单吗？</span>
          <template #footer>
            <span class="dialog-footer">
              <el-button round @click="dialog2Visible = false"
              >取消</el-button
              >
              <el-button type="warning" round @click="Returnorder"
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
  name: "U1",
  data() {
    return {
      total: "",
      currentPage: 1,
      pagesize: 6,
      dialog1Visible: false,
      dialog2Visible: false,
      order: {
        sel: null,
        col: [
          {
            prop: "orders.order_id",
            label: "订单编号",
          },
          {
            prop: "orders.numbers",
            label: "数量",
          },
        ],
        data: [],
      },
      form: {
        order_id: "",
      },
    };
  },
  created() {
    if(window.localStorage.getItem('user_root') ===null || window.localStorage.getItem('user_root')!== '1'){
      router.push("/");
    }
    this.getlist();
  },
  methods: {
    getlist(){
      axios.get(globalconfig.axios_url+'/orders/searchorderU',
          {params:{pageNum:this.currentPage, pageSize:'6',userid:window.localStorage.getItem('user_id'),orderstate:"0"}})
          .then(res=>{
            console.log(res);
            this.order.data=res.data.data;
            this.total=res.data.total;
          })
    },
    Return: function (row) {
      this.form.order_id = row.orders.order_id;
      this.dialog2Visible = true;
    },
    Returnorder(){
      const _this = this;
      axios.post(globalconfig.axios_url+'/users/returnorder',
          {order_id:this.form.order_id
          })
          .then(function (response){
            console.log(response)
            if(response.data.code === 200){
              ElMessage({
                message: '退单成功',
                type: 'success',
              })
            }else{
              ElMessage({
                message: '退单失败',
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
    pay: function (row) {
      this.dialog1Visible = true;
      this.form.order_id=row.orders.order_id;
    },
    payorder(){
      const _this = this;
      axios.post(globalconfig.axios_url+'/users/pay',
          {order_id:this.form.order_id})
          .then(function (response){
            console.log(response)
            if(response.data.code === 200){
              ElMessage({
                message: '支付成功',
                type: 'success',
              })
            }else{
              ElMessage({
                message: '支付失败',
                type: 'warning',
              })
            }
            _this.dialog1Visible = false;
            _this.currentPage = 1;//更新当前页码
            _this.getlist(); //重新查询
          }).catch(error=>{
        alert("加载失败，请重新尝试")
      });
      //
      // this.dialog1Visible = false;
      // this.currentPage = 1;//更新当前页码
      // this.getlist(); //重新查询
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