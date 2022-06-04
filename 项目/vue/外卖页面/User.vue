<template>
  <el-input v-model="input" size="large" placeholder="Please input" clearable>
    <template #append>
      <el-button @click="searchfood">
        <el-icon size="large"><Search /></el-icon>
      </el-button>
    </template>
  </el-input>
  <el-table
    :data="
      food.data.slice((currentPage - 1) * pagesize, currentPage * pagesize)
    "
    border
    style="width: 100%"
    max-height="250"
    stripe
  >
    <el-table-column
      v-for="(item, index) in food.col"
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
          @click="dialogVisible = true"
          >下单</el-button
        >
        <el-dialog
          v-model="dialogVisible"
          title="下单"
          width="30%"
          :append-to-body="true"
          draggable
        >
          <el-form :model="form" label-position="top" label-width="25%">
            <el-form-item label="您需要的份数:">
              <el-input v-model="form.number" autocomplete="off" clearable />
            </el-form-item>
          </el-form>
          <template #footer>
            <span class="dialog-footer">
              <el-button round @click="dialogVisible = false">取消</el-button>
              <el-button
                round
                type="warning"
                @click="(dialogVisible = false), buy"
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
  name: "ad_food",
  data() {
    return {
      total: 50,
      currentPage: 1,
      pagesize: 10,
      input: "",
      dialogVisible: false,
      food: {
        sel: null,
        col: [
          {
            prop: "food_id",
            label: "菜品编号",
          },
          {
            prop: "food_name",
            label: "菜品名",
          },
          {
            prop: "food_picture",
            label: "菜品图片",
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
        data: [
          {
            food_id: "1234",
            food_name: "披萨",
            food_picture: "",
            food_price: "30",
            food_rest:"233",
            food_type:"西餐"
          },
          ],
      },
      form: {
        number: "",
      },
    };
  },
  methods: {
    searchfood: function () {
      //搜索后跳转到第一页
      this.currentPage = 1;
      /*
      主体函数
      */

      //搜索后清空输入框
      this.input = "";
    },
    buy:function(){},
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