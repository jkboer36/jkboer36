<template>

  <div style="margin-top:5px;">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item><p style="font-size:large">个人中心</p></el-breadcrumb-item>
      <el-breadcrumb-item><p style="font-size:large">历史订单</p></el-breadcrumb-item>

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
        <p v-if="scope.row.orders.order_state === 0">未支付</p>
        <p v-if="scope.row.orders.order_state === 1 || scope.row.orders.order_state === 2 ">已支付</p>
        <p v-if="scope.row.orders.order_state === 3">送达</p>
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
import router from "@/router";
import globalconfig from "@/globalconfig";

export default {
  name: "U1",
  data() {
    return {
      total: "",
      currentPage: 1,
      pagesize: 6,
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
          {params:{pageNum:this.currentPage, pageSize:'6',userid:window.localStorage.getItem('user_id'),orderstate:''}})
          .then(res=>{
            console.log(res);
            this.order.data=res.data.data;
            this.total=res.data.total;
          })
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