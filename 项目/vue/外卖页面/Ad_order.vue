<template>
  <el-input v-model="input" size="large" placeholder="Please input" clearable>
    <template #append>
      <el-button @click="searchorder_all">
        <el-icon size="large"><Search /></el-icon>
      </el-button>
    </template>
  </el-input>
  <el-table
    :data="
      Order.data.slice((currentPage - 1) * pagesize, currentPage * pagesize)
    "
    border
    style="width: 100%"
    max-height="250"
    stripe
  >
    <el-table-column
      v-for="(item, index) in Order.col"
      :label="item.label"
      :prop="item.prop"
    >
    </el-table-column>
    <el-table-column fixed="right" label="操作">
      <template #default="scope">
        <el-button size="small" round @click="dialog1Visible = true"
          >修改</el-button
        >
        <el-button
          size="small"
          type="warning"
          round
          @click="dialog2Visible = true"
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
            <el-form-item label="order_id:">
              <el-input v-model="form.order_id" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="user_id:">
              <el-input v-model="form.user_id" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="food_id:">
              <el-input v-model="form.food_id" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="number:">
              <el-input v-model="form.number" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="worker_id:">
              <el-input v-model="form.worker_id" autocomplete="off" clearable />
            </el-form-item>
            <el-form-item label="order_state:">
              <el-input
                v-model="form.order_state"
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
                @click="(dialog1Visible = false), changeorder"
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
              <el-button round @click="Delete, (dialog2Visible = false)"
                >取消</el-button
              >
              <el-button type="warning" round @click="dialog2Visible = false"
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
      v-model:currentPage.sync="currentPage"
      :page-size="pagesize"
      background
      layout="total,prev, pager, next, jumper"
      :total="total"
      @current-page="handleCurrentChange"
    />
  </div>
</template>

<script>
export default {
  name: "ad_Order",
  data() {
    return {
      total: 50,
      currentPage: 1,
      pagesize: 10,
      input: "",
      dialog1Visible: false,
      dialog2Visible: false,
      Order: {
        sel: null,
        col: [
          {
            prop: "order_id",
            label: "订单编号",
          },
          {
            prop: "user_id",
            label: "账号",
          },
          {
            prop: "food_id",
            label: "菜品编号",
          },
          {
            prop: "number",
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
        data: [
          {
            order_id: "1234",
            user_id: "5678",
            food_id: "910",
            number: "2",
            worker_id:"233",
            order_state:"已送达"
          },
          ],
      },
      form: {
        order_id: "",
        user_id: "",
        food_id: "",
        number: "",
        worker_id:"",
        order_state:""
      },
    };
  },
  methods: {
    searchorder_all: function () {
      //搜索后跳转到第一页
      this.currentPage = 1;
      /*
      主体函数
      */

      //搜索后清空输入框
      this.input = "";
    },
    Delete: function () {},
    changeorder: function () {},
    handleCurrentChange: function (currentPage) {
      this.currentPage = currentPage;
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