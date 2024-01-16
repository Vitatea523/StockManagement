<template>
  <div>
    <h2>Add Supply Good</h2>
    <el-form
      ref="supplyGoodsForm"
      :model="newSupplyGoods"
      :rules="rules"
      size="medium"
      label-width="100px"
      label-position="top"
      :style="{ width: '50%' }"
    >
      <el-form-item label="Supplier ID" prop="supplierId">
        <el-input
          v-model="newSupplyGoods.supplierId"
          placeholder="Please enter the supplier Id"
          readonly
          prefix-icon="el-icon-bank-card"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="Goods ID" prop="goodsId">
        <el-select
          v-model="newSupplyGoods.goodsId"
          placeholder="Choose the goods ID"
          size="medium"
          clearable
        >
          <el-option
            v-for="item in goodsIDs"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="CostPerCarton" prop="costPerCarton">
        <el-input
          v-model="newSupplyGoods.costPerCarton"
          placeholder="Please enter the costPerCarton"
          clearable
          prefix-icon="el-icon-money"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="CostPerUnit" prop="costPerUnit">
        <el-input
          v-model="newSupplyGoods.costPerUnit"
          placeholder="Please enter the the costPerUnit"
          clearable
          prefix-icon="el-icon-coin"
          :style="{ width: '100%' }"
        />
      </el-form-item>

      <el-form-item size="large">
        <el-button type="primary" @click="add">Add</el-button>
        <el-button @click="resetForm">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import api from '@/api/supplier'
import good from '@/api/item'
export default {
  name: "AddSupplyGoods",
  data() {
    return {
      goodsIDs:[],
      newSupplyGoods: {
        supplierId: this.$route.params.sid,
        goodsId: undefined,
        costPerCarton: undefined,
        costPerUnit: undefined,
      },
      rules: {
        goodsId: [
          {
            required: true,
            message: 'Please enter the goods Id',
            trigger: 'blur'
          }
        ],
        costPerCarton: [
          {
            required: true,
            message: 'Please enter costPerCarton',
            trigger: 'blur'
          }
        ],
        costPerUnit: [
          {
            required: true,
            message: 'Please enter costPerUnit',
            trigger: 'blur'
          }
        ],

      }
    }
  },

  created() {
    this.getGoodIDs()
  },
  methods: {
    getGoodIDs() {
      good.listItem().then(response => {
        const goods= response.data;
        goods.forEach((item) => {
          this.goodsIDs.push({
            label: item.goodId,
            value: item.goodId,
          });
        });
      })
    },
    add() {
      const _this = this
      this.$refs['supplyGoodsForm'].validate((valid) => {
        if (!valid) return
        api.addSupplyGoods(_this.newSupplyGoods).then(response => {
          if (response.code === 20000) {
            _this.$message({
              message: 'add successfully',
              type: 'success'
            })
          }else {
            _this.$message({
              message: 'Fail... Please check if already have this Item ID! ',
              type: 'danger'
            })
          }
          _this.$router.go(-1);
        })
      })
    },
    resetForm() {
      this.$refs['supplyGoodsForm'].resetFields()
    }
  }
}
</script>

<style></style>


