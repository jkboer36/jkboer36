<template>

  <div >
    <el-breadcrumb separator="/">
      <el-breadcrumb-item><p style="font-size:large">管理员</p></el-breadcrumb-item>
      <el-breadcrumb-item><p style="font-size:large">订单管理</p></el-breadcrumb-item>

    </el-breadcrumb>
  </div>
  <el-button
      size="small"
      type="warning"
      round
      @click="addorder()"
  >添加</el-button>
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
            <el-form-item label="user_id:">
              <el-input v-model="form.user_id" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="food_id:">
              <el-input v-model="form.food_id" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="number:">
              <el-input v-model="form.numbers" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="worker_id:">
              <el-input v-model="form.worker_id" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="order_state:">
              <el-input v-model="form.order_state" autocomplete="off" clearable/>
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button round @click="dialog1Visible = false">取消</el-button>
              <el-button
                round
                type="warning"
                @click="changeorder()"
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
              <el-button type="warning" round @click="Deleteorder"
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
  name: "ad_Order",
  data() {
    return {
      total: "",
      currentPage: 1,
      pagesize: 6,
      searchusername: "",
      dialog1Visible: false,
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
          {
            prop: "worker_id",
            label: "骑手编号",
          },
          {
            prop: "order_state",
            label: "订单状态",
          },
        ],
        data: [],
      },
      form: {
        order_id: "",
        user_id: "",
        food_id: "",
        numbers: "",
        worker_id:"",
        order_state:""
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
      axios.get(globalconfig.axios_url+'/orders/searchorderAll',
          {params:{pageNum:this.currentPage, pageSize:'6'}})
          .then(res=>{
            console.log(res);
            this.order.data=res.data.data;
            this.total=res.data.total;
          })
    },
    addorder(){
      this.form.order_id=null;
      this.form.user_id=null;
      this.form.worker_id=null;
      this.form.numbers=null;
      this.form.order_state=null;
      this.form.food_id=null;
      this.dialog1Visible = true;
    },
    Delete: function (row) {
      this.form.order_id = row.order_id;
      this.dialog2Visible = true;
    },
    Deleteorder(){
      const _this = this;
      axios.post(globalconfig.axios_url+'/orders/delete',
          {order_id:this.form.order_id
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
      this.form.order_id=row.order_id;
      this.form.user_id=row.user_id;
      this.form.worker_id=row.worker_id;
      this.form.numbers=row.numbers;
      this.form.order_state=row.order_state;
      this.form.food_id=row.food_id;
    },
    changeorder(){
      const _this = this;
      axios.post(globalconfig.axios_url+'/orders/change',
          {order_id:this.form.order_id, user_id:this.form.user_id ,food_id:this.form.food_id, worker_id:this.form.worker_id, numbers:this.form.numbers, order_state:this.form.order_state
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