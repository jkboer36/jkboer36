<template>

  <div style="margin-top:5px;">
    <el-breadcrumb separator="/">
      <el-breadcrumb-item><p style="font-size:large">骑手</p></el-breadcrumb-item>
      <el-breadcrumb-item><p style="font-size:large">我的接单</p></el-breadcrumb-item>

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
    <el-table-column
        v-for="(item, index) in order.col"
        :label="item.label"
        :prop="item.prop"
    >
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button
            size="small"
            type="warning"
            round
            @click="sure(scope.row)"
        >送达</el-button
        >
        <el-dialog
            v-model="dialog2Visible"
            title="接单"
            width="30%"
            :append-to-body="true"
            draggable
        >
          <span>确认已送达该订单吗？</span>
          <template #footer>
            <span class="dialog-footer">
              <el-button round @click="dialog2Visible = false"
              >取消</el-button
              >
              <el-button type="warning" round @click="sureorder"
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
  name: "B2",
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
            prop: "order_id",
            label: "订单编号",
          },
          {
            prop: "user_id",
            label: "用户编号",
          },
          {
            prop: "food_id",
            label: "菜品编号",
          },
          {
            prop: "numbers",
            label: "数量",
          },
        ],
        data: [],
      },
      form:{
        order_id:"",
      },
    };
  },
  created() {
    if(window.localStorage.getItem('user_root') ===null || window.localStorage.getItem('user_root')!== '2'){
      router.push("/");
    }
    this.getlist();
  },
  methods: {
    getlist(){
      axios.get(globalconfig.axios_url+'/orders/searchorderR',
          {params:{pageNum:this.currentPage, pageSize:'6', workerid:window.localStorage.getItem('user_id'),orderstate:'2'}})
          .then(res=>{
            console.log(res);
            this.order.data=res.data.data;
            this.total=res.data.total;
          })
    },
    sure: function (row) {
      this.dialog2Visible = true;
      this.form.order_id=row.order_id;
    },
    sureorder(){
      const _this = this;
      axios.post(globalconfig.axios_url+'/workers/sureorder',
          {order_id:this.form.order_id
          })
          .then(function (response){
            console.log(response)
            if(response.data.code === 200){
              ElMessage({
                message: '成功',
                type: 'success',
              })
            }else{
              ElMessage({
                message: '失败',
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