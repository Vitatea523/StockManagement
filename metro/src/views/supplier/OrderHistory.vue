<template>
  <div class="app-container">
    <h2>Order History</h2>
    <el-col :span="11">
      <el-form
        ref="orderSearchForm"
        size="medium"
        label-width="120px"
        label-position="left"
      >
        <el-form-item label="Search bar">
          <el-input
            v-model="searchWord"
            placeholder="Search supplier ID or order ID"
            prefix-icon="el-icon-bank-card"
            :style="{ width: '80%' }"
          />
        </el-form-item>
      </el-form>
    </el-col>
    <div class="block">
      <span class="demonstration" />
      <el-date-picker
        v-model="filterDates"
        type="datetimerange"
        :picker-options="pickerOptions"
        range-separator="--"
        start-placeholder="Start Date"
        end-placeholder="End Date"
        align="right"
      />
    </div>

    <el-table
      v-loading="listLoading"
      :data="list.filter(data => !searchWord || data.supplierId==searchWord||data.id==searchWord)"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      :style="{ width: '100%' }"
      :row-class-name="tableRowClassName"
    >
      <el-table-column align="center" label="Order ID" width="95">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column label="Supplier ID" width="95">
        <template slot-scope="scope">
          {{ scope.row.supplierId }}
        </template>
      </el-table-column>
      <el-table-column label="Placed Date" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.date }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Goods ID" width="95" align="center">
        <template slot-scope="scope">
          {{ scope.row.goodsId }}
        </template>
      </el-table-column>
      <el-table-column label="Amount" width="75" align="center">
        <template slot-scope="scope">
          {{ scope.row.amount }}        </template>
      </el-table-column>
      <el-table-column align="center" prop="created_at" label="currentCostPerCarton" width="120">
        <template slot-scope="scope">
          <i class="el-icon-time" />
          <span>{{ scope.row.currentCostPerCarton }}</span>
        </template>
      </el-table-column>
      <el-table-column label="expireDate" width="110" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.expireDate }}</span>
        </template>
      </el-table-column>
      <el-table-column label="currentCostPerUnit" width="95" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.currentCostPerUnit }}</span>
        </template>
      </el-table-column>
      <el-table-column class-name="status-col" label="Status" width="110" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column class-name="operation" label="Operation" width="300" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            :disabled="scope.row.status === 'Deleted'"
            :type="scope.row.status === 'Deleted'?'info':'warning'"
            icon="el-icon-check"
            @click="onOpen(scope.row)"
          >Confirm</el-button>
          <el-dialog title="Confirmation" :visible="dialogFormVisible">
            <el-row :gutter="20">
              <el-form
                ref="confirmForm"
                :model="formData"
                :rules="rules"
                size="medium"
                label-width="100px"
                label-position="left"
              >
                <el-col :span="20">
                  <el-form-item label="Expire date" prop="expireDate">
                    <el-date-picker
                      v-model="formData.expireDate"
                      format="yyyy-MM-dd"
                      value-format="yyyy-MM-dd"
                      :style="{width: '100%'}"
                      placeholder="Please choose expire date"
                    />
                  </el-form-item>
                </el-col>
              </el-form>
            </el-row>
            <div>
              <el-divider><i class="el-icon-warning" />Notification</el-divider>
              <span>You need to make sure that you receive this order.</span><br>
              <span>Once you confirm, this order's status will change to 'Done'. </span><br>
              <span>Then the order item will automatically recorded in the inventory.</span>
              <el-divider><i class="el-icon-mobile-phone" /></el-divider>
            </div>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">Cancel</el-button>
              <el-button type="primary" @click="confirmOrder(scope.row)" >Confirm</el-button>
            </div>
          </el-dialog>
          <el-button
            size="mini"
            :disabled="scope.row.status === 'Deleted'"
            :type="scope.row.status === 'Deleted'?'info':'danger'"
            icon="el-icon-delete"
            @click="deleteOrder(scope.row)"
            style="margin-left: 20px"
          >Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import api from '@/api/orderHistory'

export default {
  filters: {
    statusFilter(status) {
      const statusMap = {
        Done: 'success',
        InProgress: 'gray',
        Deleted: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      pickerOptions: {
        shortcuts: [{
          text: 'Last week',
          onClick(pick) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 7)
            pick.$emit('pick', [start, end])
          }
        }, {
          text: 'Last month',
          onClick(pick) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30)
            pick.$emit('pick', [start, end])
          }
        }, {
          text: 'Last three months',
          onClick(pick) {
            const end = new Date()
            const start = new Date()
            start.setTime(start.getTime() - 3600 * 1000 * 24 * 90)
            pick.$emit('pick', [start, end])
          }
        }]
      },
      value1: [new Date(2000, 10, 10, 10, 10), new Date(2000, 10, 11, 10, 10)],
      filterDates: null,
      name: 'OrderHistory',
      searchWord: '',
      list: [],
      listLoading: true,
      dialogFormVisible: false,

      formData: {
        oid: null,
        expireDate: null
      },
      rules: {
        expireDate: [{
          required: true,
          message: 'Please choose expire date',
          trigger: 'change'
        }]
      }
    }
  },
  watch: {
    filterDates(val) {
      if (val) {
        this.list = this.list.filter(item => new Date(item.date).getTime() >= new Date(val[0]).getTime() && new Date(item.date).getTime() <= new Date(val[1]).getTime())
      } else {
        this.fetchData()
      }
    }
  },
  created() {
    this.fetchData()
  },

  methods: {
    tableRowClassName({ row }) {
      if (row.status === 'Done') {
      } else if (row.status === 'Deleted') {
        return 'warning-row'
      }
      return ''
    },

    onOpen(row) {
      if (row.status === 'Deleted') {
        return
      }
      this.dialogFormVisible = true
    },
    onClose() {
      this.$refs['confirmForm'].resetFields()
      this.dialogFormVisible = false
    },
    fetchData() {
      this.listLoading = true
      api.orderHistory().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    confirmOrder(row) {
      this.$refs['confirmForm'].validate(valid => {
        if (!valid) return
        this.formData.oid = row.id
        api.confirmOrder(this.formData.oid,this.formData.expireDate).then(response => {
          this.$message({
            type: response.code === 20000 ? 'success' : 'error',
            message: response.msg
          })
        })
        this.dialogFormVisible = false
        this.fetchData()
      })
    },
    deleteOrder(row) {
      if (row.status === 'Deleted') {
        return
      }
      this.$confirm('Delete this order?', 'Confirmation', {
        confirmButtonText: 'Yes',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        api.deleteOrder(row).then(response => {
          this.$message({
            type: response.code === 20000 ? 'success' : 'error',
            message: response.msg
          })
        })
        this.fetchData()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Cancel Delete'
        })
        // cancel activity
      })
    }
  }
}
</script>

<style>
.el-table .warning-row {
  background: #eee;
}

.el-table .success-row {
  background: #f0f9eb;
}

</style>
