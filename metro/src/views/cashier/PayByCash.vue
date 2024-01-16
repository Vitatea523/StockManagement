<template>
  <div>
    <h2>Pay by Cash</h2>
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
        width="265">
      </el-table-column>
      <el-table-column
        prop="money"
        label="Price ($)"
        width="180">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="Amount"
        width="180">
      </el-table-column>
      <el-table-column
        prop="total"
        label="Total ($)"
        width="180">
      </el-table-column>
    </el-table>
    <br><br>
    <div>
      <el-input v-model="input" style="width: 300px" placeholder="Please enter the cash amount"></el-input>
      <el-button type="primary" style="margin-left: 20px;" @click="handleConfirm">Confirm</el-button>
      <el-input
        style="margin-left: 50px; width: 200px"
        placeholder="change"
        v-model="change"
        :disabled="true">
      </el-input>
      <el-button type="danger" style="margin-left: 50px;" @click="handleBack">Back</el-button>
    </div>
  </div>
</template>

<script>
import Store from '@/views/cashier/store'
import api from '@/api/checkOut'

export default {
  name: 'PayByCash',
  created() {
    this.tableData = Store.fetch()
  },
  data() {
    return {
      totalPayment: 0,
      tableData: [],
      input: '',
      change: 0
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
          this.totalPayment = sums[index].toFixed(2)
        } else {
          sums[index] = 'N/A'
        }
      })
      return sums
    },
    handleConfirm() {
      this.change = this.input - this.totalPayment
      if (!isNaN(this.change)) {
        if (this.change < 0) {
          this.$message({
            type: 'error',
            message: 'Not enough money!'
          })
          this.change = ''
        } else {
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
              message: 'Cash payment successful!'
            })
          }).catch(error => {
            this.$message({
              type: 'error',
              message: error.msg
            })
          })
          Store.save([])
        }
      }
    },
    handleBack() {
      this.$router.push('/cashier/checkOut')
    }
  }
}
</script>

<style scoped>

</style>
