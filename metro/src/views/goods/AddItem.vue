<template>
  <div>
    <h2>Add Item</h2>
    <el-row :gutter="18">
      <el-form
        ref="itemAddForm"
        :model="newItem"
        :rules="rules"
        size="mini"
        label-width="100px"
        label-position="left"
      >
        <el-col :span="12">
          <el-form-item label="APN" prop="apn">
            <el-input
              v-model="newItem.apn"
              placeholder="please enter the APN "
              :maxlength="10"
              clearable
              prefix-icon="el-icon-shopping-bag-2"
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="UOM" prop="uom">
            <el-input
              v-model="newItem.uom "
              placeholder="please enter the UOM"
              clearable
              prefix-icon="el-icon-user"
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Name" prop="name">
            <el-input
              v-model="newItem.name"
              placeholder="please enter the name"
              show-word-limit
              clearable
              prefix-icon="el-icon-star-on"
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Threshold" prop="threshold">
            <el-input
              v-model="newItem.threshold"
              placeholder="please enter the threshold"
              clearable
              prefix-icon="el-icon-time"
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Category" prop="categoryName">
            <div id="app">
              <select id="status" v-model="newItem.categoryName" style="background-color:lightsteelblue;border: none;height:38px">
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
        </el-col>
        <el-col :span="12">
        </el-col>
        <el-col :span="12">
          <el-form-item label="Unit" prop="unit">
            <el-input-number v-model="newItem.amount " placeholder="number" :step="1" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Price per unit " prop="priceunit">
            <el-input
              v-model="newItem.price"
              placeholder="AU$"
              clearable
              prefix-icon="el-icon-coin"
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Stock Level Standard-Full" prop="full">
            <el-input
              v-model="newItem.fullStandard"
              placeholder="please enter the full standard"
              clearable
              prefix-icon="el-icon-s-data"
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Stock Level Standard-High" prop="high">
            <el-input
              v-model="newItem.highStandard"
              placeholder="please enter the high standard"
              clearable
              prefix-icon="el-icon-s-data"
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Stock Level Standard-Medium" prop="medium">
            <el-input
              v-model="newItem.mediumStandard"
              placeholder="please enter the medium standard"
              clearable
              prefix-icon="el-icon-s-data"
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Stock Level Standard-Low" prop="low">
            <el-input
              v-model="newItem.lowStandard"
              placeholder="please enter the low standard"
              clearable
              prefix-icon="el-icon-s-data"
              :style="{width: '100%'}"
            />
          </el-form-item>
        </el-col>
        <el-col :span="24">
          <el-form-item size="large">
            <el-button type="primary" @click="add">Add</el-button>
            <el-button @click="resetForm">Reset</el-button>
          </el-form-item>
        </el-col>
      </el-form>
    </el-row>
  </div>
</template>
<script>
import api from '@/api/item'
export default {
  name: 'AddItem',

  // components: {},
  // props: [],
  data() {
    return {
      selected: '',
      newItem: {
        apn: undefined,
        uom: undefined,
        name: undefined,
        threshold: undefined,
        categoryName: undefined,
        amount: 0,
        price: undefined,
        fullStandard: undefined,
        highStandard: undefined,
        mediumStandard: undefined,
        lowStandard: undefined
      },
      rules: {
        apn: [
          {
            required: true,
            message: 'Please enter the apn',
            trigger: 'blur'
          }
        ],
        uom: [
          {
            required: true,
            message: 'Please enter the uom',
            trigger: 'blur'
          }
        ],
        name: [
          {
            required: true,
            message: 'Please enter the name',
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
  created() {},
  methods: {
    add() {
      const _this = this
      this.$refs['itemAddForm'].validate((valid) => {
        if (!valid) return
        console.log(_this.newItem)
        api.addItem(_this.newItem).then(response => {
          console.log(response)
          if (response.msg === 'success') {
            _this.$message({
              message: 'add successfully',
              type: 'success'
            })
          }
          console.log(response)
          _this.$router.push('/goods/listItem')
        })
      })
    },
    resetForm() {
      this.$refs['itemAddForm'].resetFields()
    }
  }
}

</script>
<style>
.el-dropdown-link {
  cursor: pointer;
  color: #409EFF;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>
