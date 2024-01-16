<template>
  <div>
    <h2>Check Out</h2>
    <!--    checkout table-->
    <el-table
      :data="tableData"
      border
      :summary-method="getSummaries"
      show-summary
      style="width: 900px">
      <el-table-column
        prop="goodId"
        label="Item ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="Item Name"
        width="240">
      </el-table-column>
      <el-table-column
        prop="money"
        label="Price ($)"
        width="180">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="Amount"
        width="200">
        <template slot-scope="scope">
          <el-input-number v-model="scope.row.amount" size="mini" @change="handleChange(scope.$index, tableData)" :min="0" :max="100" label="description"></el-input-number>
        </template>
      </el-table-column>
      <el-table-column
        prop="total"
        label="Total ($)"
        width="200">
      </el-table-column>
    </el-table>
    <br><br>
    <!--    Button -->
    <div style="margin-left: 600px; height: 300px">
      <el-button type="info" @click="handleReset">Reset</el-button>
      <el-button type="primary" @click="handleAddItem">Manual</el-button>
      <el-button type="danger" @click="handleCheckOut">Check Out</el-button>
    </div>
    <!-- dialog -->
    <el-dialog width="400px" height="400px" title="Check Out Methods" :visible.sync="dialogFormVisible">
      <!-- form -->
      <el-form id="setting" :inline="true" ref="form" :model="formData" label-width="80px">
        <el-select style="width: 300px" v-model="value" placeholder="Select a method">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" plain icon="el-icon-thumb" size="medium" @click="handlePay"> Confirm </el-button>
        <el-button @click="dialogFormVisible = false">Cancel</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import Store from './store.js'
import api from '@/api/checkOut'
export default {
  name: 'CheckOut',
  created() {
    this.tableData = Store.fetch()
    Store.save(this.tableData)
    for (let i = 0; i < this.tableData.length; i++) {
      this.handleChange(i, this.tableData)
    }
  },
  data() {
    return {
      totalPayment: 0,
      tableData: [],
      dialogFormVisible: false,
      formData: {
        method: ''
      },
      options: [{
        value: 'cash',
        label: 'Pay by Cash'
      }, {
        value: 'card',
        label: 'Pay by Card'
      }],
      value: ''
    }
  },
  methods: {
    getSummaries(param) {
      const { columns, data } = param
      const sums = []
      columns.forEach((column, index) => {
        if (index === 0) {
          sums[index] = 'Sum'
          return
        }
        if (index >= 1 && index <= 3) {
          sums[index] = ''
          return
        }
        const values = data.map(item => Number(item[column.property]))
        if (!values.every(value => isNaN(value))) {
          sums[index] = values.reduce((prev, curr) => {
            const value = Number(curr)
            if (!isNaN(value)) {
              return prev + curr
            } else {
              return prev
            }
          }, 0)
          this.totalPayment = sums[index]
          this.totalPayment.toFixed(2)
        } else {
          sums[index] = 'N/A'
        }
      })
      return sums
    },

    handleChange(index, tableData) {
      tableData[index].total = tableData[index].money * tableData[index].amount
      Store.save(this.tableData)
    },
    handleReset() {
      this.$confirm(
        'This operation will clear all the selected items. Do you want to continue?',
        'Warning',
        {
          confirmButtonText: 'Reset anyway',
          cancelButtonText: 'Cancel',
          type: 'warning',
          center: true
        }
      ).then(() => {
        this.tableData = []
        Store.save(this.tableData)
        this.$message({
          type: 'success',
          message: 'Reset successfully!'
        })
      }).catch(() => {
        this.$message({
          type: 'error',
          message: 'Reset cancelled'
        })
      })
    },
    handleAddItem() {
      this.$router.push('/cashier/addItem')
    },
    handleCheckOut() {
      if (this.tableData.length > 0) {
        this.dialogFormVisible = true
      } else {
        this.$message({
          type: 'error',
          message: 'No items selected'
        })
      }
    },
    handlePay() {
      if (this.value === 'card') {
        const list = []
        for (let i = 0; i < this.tableData.length; i++) {
          const element = {}
          element.goodsId = this.tableData[i].goodId
          element.amount = this.tableData[i].amount
          element.soldDate = null
          element.money = this.tableData[i].money
          list.push(element)
        }
        api.checkOut(list).then(response => {
          this.$message({
            type: 'success',
            message: 'Pay by card successfully!'
          })
          this.dialogFormVisible = false
          Store.save([])
          this.tableData = []
        }).catch(error => {
          this.$message({
            type: 'error',
            message: error.msg
          })
        })
      } else if (this.value === 'cash') {
        this.$router.push('/cashier/cash')
      } else {
        this.$message({
          type: 'error',
          message: 'Please select a payment method.'
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
