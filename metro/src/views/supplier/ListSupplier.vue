<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="4">
        <h2>Supplier List</h2>
      </el-col>
      <el-col :span="6">
        <el-button type="success" @click="handleAdd()">Add</el-button>
      </el-col>
      <el-col :span="8">
        <el-form
          ref="supplierSearchForm"
          size="medium"
          label-width="120px"
          label-position="left"
        >
          <el-form-item label="Supplier name">
            <el-input
              v-model="search_name"
              placeholder="Please enter the supplier name"
              prefix-icon="el-icon-bank-card"
              :style="{ width: '120%' }"
            />
          </el-form-item>
        </el-form>
      </el-col>

    </el-row>

    <el-table
      v-loading="listLoading"
      :data="list.filter(data => !search_name || data.businessName.toLowerCase().includes(search_name.toLowerCase()))"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      :style="{ width: '80%' }"
      @row-click="handleCheck"
    >
      <!--     点edit也会走 @row-click="handleCheck"-->
      <el-table-column label="Supplier ID" width="250">
        <template slot-scope="scope">
          <i class="el-icon-bank-card" />
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Business name" width="300">
        <template slot-scope="scope">
          <span>{{ scope.row.businessName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Maintain">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click.native.stop="handleEdit( scope.row.id)"
          >Edit
          </el-button>
          <el-button
            size="mini"
            type="danger"
            @click.native.stop="handleDelete(scope.row.id)"
          >Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import api from '@/api/supplier'

export default {

  data() {
    return {
      list: [],
      listLoading: true,
      search_name: ''
    }
  },
  created() {
    this.fetchData()
  },

  methods: {
    fetchData() {

      this.listLoading = true
      api.listSupplier().then(response => {
        this.list = response.data
        this.listLoading = false
      })
    },
    handleAdd() {
      this.$router.push('/supplier/AddSupplier')
    },
    handleEdit(sid) {
      this.$router.push('/supplier/EditSupplier/' + sid)
    },
    handleCheck(row) {
      this.$router.push('/supplier/MaintainSupplier/' + row.id)
    },
    handleDelete(supplier_id) {
      this.$confirm('Delete anyway?', 'Confirmation', {
        confirmButtonText: 'Yes',
        cancelButtonText: 'Cancel',
        type: 'warning'
      }).then(() => {
        api.deleteById(supplier_id).then(response => {
          this.$message({
            type: response.code === 20000 ? 'success' : 'error',
            message: response.msg
          })
        })
        this.fetchData()
      }).catch(() => {
        // cancel activity
      })
    }
  }
}
</script>
