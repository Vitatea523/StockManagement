<template>
<div>
  <h2>Grant Privileges</h2>
  <template>
    <el-input
      v-model="search"
      size="medium"
      prefix-icon="el-icon-search"
      style="width: 25%; margin-left: 650px"
      placeholder="Search the username"/>
  </template>
<!--  privilege table-->
  <el-table
    :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
    style="width: 90%"
    height="450px">
    <el-table-column
      fixed
      prop="cashierID"
      label="Cashier ID"
      width="120">
    </el-table-column>
    <el-table-column
      fixed
      prop="name"
      label="Name"
      width="140">
    </el-table-column>
    <el-table-column
      align="center"
      prop="privilege1"
      label="Privilege 1"
      width="150">
      <template slot-scope="scope">
        <el-checkbox v-model="scope.row.privilege1"></el-checkbox>
      </template>
    </el-table-column>
    <el-table-column
      align="center"
      prop="privilege2"
      label="Privilege 2"
      width="150">
      <template slot-scope="scope">
        <el-checkbox v-model="scope.row.privilege2"></el-checkbox>
      </template>
    </el-table-column>
    <el-table-column
      align="center"
      prop="privilege3"
      label="Privilege 3"
      width="150">
      <template slot-scope="scope">
        <el-checkbox v-model="scope.row.privilege3"></el-checkbox>
      </template>
    </el-table-column>
    <el-table-column
      align="center"
      prop="privilege4"
      label="Privilege 4"
      width="150">
      <template slot-scope="scope">
        <el-checkbox v-model="scope.row.privilege4"></el-checkbox>
      </template>
    </el-table-column>
    <el-table-column
      align="center"
      prop="privilege5"
      label="Privilege 5"
      width="150">
      <template slot-scope="scope">
        <el-checkbox v-model="scope.row.privilege5"></el-checkbox>
      </template>
    </el-table-column>
  </el-table>
  <br><br>
<!--  button-->
  <el-button class="button" type="primary" @click="handleConfirm('tableData')">Confirm</el-button>
</div>
</template>

<script>
/* eslint-disable */
export default {
  name: 'GrantPrivilege',
  data() {
    return {
      search: '',
      tableData: [{
        cashierID: '001',
        name: 'Qiushi Chen',
        privilege1: false,
        privilege2: true,
        privilege3: false,
        privilege4: false,
        privilege5: true,
      }, {
        cashierID: '002',
        name: 'Yuyun Liu',
        privilege1: false,
        privilege2: true,
        privilege3: true,
        privilege4: false,
        privilege5: false,
      }, {
        cashierID: '003',
        name: 'Mahuirong Du',
        privilege1: true,
        privilege2: true,
        privilege3: false,
        privilege4: false,
        privilege5: false,
      }, {
        cashierID: '004',
        name: 'Nan Xiao',
        privilege1: false,
        privilege2: false,
        privilege3: false,
        privilege4: true,
        privilege5: false,
      }, {
        cashierID: '005',
        name: 'Shuhan Yang',
        privilege1: true,
        privilege2: false,
        privilege3: false,
        privilege4: false,
        privilege5: true,
      }, {
        cashierID: '006',
        name: 'Jiayi Li',
        privilege1: false,
        privilege2: true,
        privilege3: true,
        privilege4: false,
        privilege5: false,
      }]
    }
  },
  methods: {
    handleConfirm(tableData) {
      console.log(tableData)
      this.$message({
        type: 'success',
        message: 'Update successfully!'
      })
    }
  }
}
</script>

<style scoped>
.button {
  margin-left: 460px;
}
</style>
