<template>
  <div>
    <h2>Edit Item</h2>
    <el-form
      ref="itemEditForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
      label-position="top"
    >
      <el-form-item label="Item ID">
        <el-input
          v-model="formData.goodId"
          readonly
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="Name" prop="name">
        <el-input
          v-model="formData.name"
          placeholder="Please enter the the Item Name"
          clearable
          prefix-icon="el-icon-s-goods"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="APN" prop="apn">
        <el-input
          v-model="formData.apn"
          placeholder="Please enter the APN"
          clearable
          prefix-icon="el-icon-s-custom"
          :style="{ width: '100%' }"
        />
      </el-form-item>

      <el-form-item label="Category" prop="category">
        <div id="app">
          <select name="status" id="status" v-model="formData.categoryName" style="background-color:lightsteelblue;border: none;height:38px">
            <option value="">Category</option>

            <option value="Specials">Specials</option>
            <option value="Fruit & Veg">Fruit & Veg</option>
            <option value="Meat, Seafood & Deli">Meat, Seafood & Deli</option>
            <option value="Bakery">Bakery</option>
            <option value="Dairy, Eggs & Fridge">Dairy, Eggs & Fridge</option>
            <option value="Pantry">Pantry</option>
            <option value="Freezer">Freezer</option>
            <option value="Drinks">Drinks</option>
            <option value="Liquor">Liquor</option>
            <option value="Pet">Pet</option>
            <option value="Baby">Baby</option>
            <option value="Health & Beauty">Health & Beauty</option>
            <option value="Household">Household</option>

          </select>
        </div>
      </el-form-item>
      <el-form-item label="Amount(Unit)">
        <el-input
          v-model="formData.amount"
          placeholder="Please enter the Amount"
          clearable
          prefix-icon="el-icon-document"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="Threshold">
        <el-input
          v-model="formData.threshold"
          placeholder="Please enter the threshold"
          clearable
          prefix-icon="el-icon-edit"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      </el-form-item>
      <el-form-item label="Stock Level Standard-Full">
        <el-input
          v-model="formData.fullStandard"
          placeholder="Please enter the full standard"
          clearable
          prefix-icon="el-icon-s-data"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="Stock Level Standard-High">
        <el-input
          v-model="formData.highStandard"
          placeholder="Please enter the high standard"
          clearable
          prefix-icon="el-icon-s-data"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="Stock Level Standard-Medium">
        <el-input
          v-model="formData.mediumStandard"
          placeholder="Please enter the medium standard"
          clearable
          prefix-icon="el-icon-s-data"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="Stock Level Standard-Low">
        <el-input
          v-model="formData.lowStandard"
          placeholder="Please enter the low standard"
          clearable
          prefix-icon="el-icon-s-data"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="UOM">
        <el-input
          v-model="formData.uom"
          placeholder="Please enter the UOM"
          clearable
          prefix-icon="el-icon-time"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="Price">
        <el-input
          v-model="formData.price"
          placeholder="Please enter the Price"
          clearable
          prefix-icon="el-icon-coin"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="updateItem">Edit</el-button>
        <el-button @click="resetForm">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import api from '@/api/item'

export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        goodId: ' ',
        apn: ' ',
        name: ' ',
        categoryName: ' ',
        threshold: ' ',
        amount: ' ',
        price: ' ',
        uom: '',
        timeframe: ' ',
        fullStandard: ' ',
        highStandard: ' ',
        mediumStandard: ' ',
        lowStandard: ' '
      },
      rules: {
        item_id: [
          {
            required: true,
            message: 'Please enter the item id',
            trigger: 'blur'
          }
        ],
        apn: [
          {
            required: true,
            message: 'Please enter the APN',
            trigger: 'blur'
          }
        ],
        name: [
          {
            required: true,
            message: 'Please enter the the name',
            trigger: 'blur'
          }
        ],
        threshold: [
          {
            required: true,
            message: 'Please enter the threshold',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  computed: {},
  watch: {},
  created() {
    this.fetchDataBySid()
  },
  mounted() {
  },
  methods: {
    fetchDataBySid() {
      var sid = this.$route.query.sid
      api.fetchDataBySid(sid).then(response => {

        this.formData = response.data
        console.log(response.data)
      })
    },
    updateItem() {
      const _this = this
      this.$refs['itemEditForm'].validate((valid) => {
        console.log(_this.formData)
        if (!valid) return
        api.editItem(_this.formData).then(response => {
          console.log(response)
          _this.$router.push('/goods/listItem')
        })
      })
    },
    resetForm() {
      this.$refs['itemEditForm'].resetFields()
    }
  }
}
</script>

<style></style>
