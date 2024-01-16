<template>
<div>
  <h2>Account Management</h2>
  <!-- account table -->
  <el-table
    :data="tableData.filter(data => (!search || data.name.toLowerCase().includes(search.toLowerCase())) && (data.title.includes(value)))"
    style="width: 90%"
    max-height="600">
<!--    staff id-->
    <el-table-column
      label="Staff ID"
      prop="id"
      width="180">
    </el-table-column>
<!--    name-->
    <el-table-column
      label="Name"
      prop="name">
    </el-table-column>
<!--    role-->
    <el-table-column
      label="Role"
      prop="title">
    </el-table-column>
<!--    filter-->
    <el-table-column
      align="left">
      <template slot="header" slot-scope="scope">
        <el-select v-model="value" clearable size="mini" placeholder="Select role">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </template>
    </el-table-column>
<!--    search bar-->
    <el-table-column
      align="right"
       width="190px">
      <template slot="header" slot-scope="scope">
        <el-input
          v-model="search"
          size="mini"
          prefix-icon="el-icon-search"
          placeholder="Search the username"/>
      </template>
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row, tableData)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
</div>
</template>

<script>
import { getAccounts, deleteAccount } from '@/api/user'
/* eslint-disable */
export default {
  name: 'ManageAccount',
  created() {
    getAccounts().then(response => {
      this.tableData = response.data
    }).catch(error => {
      reject(error)
    })
  },
  data() {
    return {
      tableData: [],
      search: '',

      options: [{
        value: 'manager',
        label: 'manager'
      }, {
        value: 'cashier',
        label: 'cashier'
      }],
      value: ''
    }
  },
  methods: {
    handleDelete(index, row, rows) {
      this.$confirm(
        'This operation will permanently delete the account. Do you want to continue?',
        'Warning',
        {
          confirmButtonText: 'Delete anyway',
          cancelButtonText: 'Cancel',
          type: 'warning',
          center: true
        }
      ).then(() => {
        deleteAccount(row.id).then(response => {
          rows.splice(index, 1)
          this.$message({
            type: 'success',
            message: 'Delete successfully!'
          })
        }).catch(error => {
          reject(error)
        })
      }).catch(() => {
        this.$message({
          type: 'error',
          message: 'removal cancelled'
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
