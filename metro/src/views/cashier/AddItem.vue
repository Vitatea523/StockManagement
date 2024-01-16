<template>
  <div>
    <el-row :gutter="180">
      <el-input
        v-model="search"
        suffix-icon="el-icon-search"
        placeholder="Search Item Name"
        style="width: 200px; left: 100px"
      />
    </el-row>
    <el-table
      ref="filterTable"
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 900px"
      max-height="650">

      <el-table-column
        width="100px"
        label="Item Id"
        prop="goodId"/>
      <el-table-column
        width="350px"
        label="Name"
        prop="name"/>
      <el-table-column
        width="150px"
        label="Price ($)"
        prop="price"/>
      <el-table-column
        label="Category"
        prop="categoryName"
        width="180"
        column-key="date"
        :filters="[{text: 'Specials', value: 'Specials'}, {text: 'Fruit & Veg', value: 'Fruit & Veg'},{text:'Meat, Seafood & Deli',value:'Meat, Seafood & Deli'},
        {text:'Bakery',value:'Bakery'},{text:'Dairy, Eggs & Fridge',value:'Dairy, Eggs & Fridge'},{text:'Pantry',value:'Pantry'},{text:'Freezer',value:'Freezer'},{text:'Drinks',value:'Drinks'},
        {text:'Liquor',value:'Liquor'},{text:'Pet',value:'Pet'},{text:'Baby',value:'baby'},{text:'Health & Beauty',value:'Health & Beauty'},{text:'Household',value:'Household'}]"
        :filter-method="filterHandler"/>
      <el-table-column
        align="right">
        <template slot="header" slot-scope="scope" />
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            icon="el-icon-plus"
            circle
            @click="handleAdd(scope.row)"/>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import Store from './store.js'
import api from '@/api/checkOut'

export default {
  name: 'AddItem',
  created() {
    this.fetchData()
  },
  data() {
    return {
      selected: '',
      tableData: [],
      search: ''
    }
  },
  methods: {
    fetchData() {
      api.addCheckOutItem().then(response => {
        this.tableData = response.data
      })
    },
    filterHandler(value, row, column) {
      const property = column['property']
      return row[property] === value
    },
    handleAdd(row) {
      this.$confirm(
        'This operation will manually add the selected item. Do you want to continue?',
        'Warning',
        {
          confirmButtonText: 'Add anyway',
          cancelButtonText: 'Cancel',
          type: 'warning',
          center: true
        }
      ).then(() => {
        const data = Store.fetch()
        // check if already exist
        let exist = false
        for (let i = 0; i < data.length; i++) {
          if (Number(data[i].goodId) === Number(row.goodId)) {
            exist = true
            break
          }
        }
        if (exist === false) {
          const itemText = '{ "goodId" : ' + row.goodId + ', "name": "' + row.name + '", "money": ' + row.price + ', "amount": 1, "total": ' + row.price + '}'
          const item = JSON.parse(itemText)
          data.push(item)
          Store.save(data)
          this.$router.push('/cashier/checkOut')
          this.$message({
            type: 'success',
            message: 'Item added successfully!'
          })
        } else {
          this.$message({
            type: 'info',
            message: 'Item already exists in the check out page.'
          })
        }
      }).catch(() => {
        this.$message({
          type: 'error',
          message: 'Add item cancelled'
        })
      })
    }
  }
}
</script>

<style scoped>

</style>
