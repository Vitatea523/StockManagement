<template>
  <div>
    <h2>Stock Level Report</h2>
    <br>
    <!--  report table-->
    <el-table
      :data="tableData"
      style="width: 1100px"
      max-height="500px">
      <el-table-column
        prop="goodId"
        label="Item ID"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="Name"
        width="300">
      </el-table-column>
      <el-table-column
        :filters="[{text: 'Specials', value: 'Specials'}, {text: 'Fruit & Veg', value: 'Fruit & Veg'},{text:'Meat, Seafood & Deli',value:'Meat, Seafood & Deli'},
        {text:'Bakery',value:'Bakery'},{text:'Dairy, Eggs & Fridge',value:'Dairy, Eggs & Fridge'},{text:'Pantry',value:'Pantry'},{text:'Freezer',value:'Freezer'},{text:'Drinks',value:'Drinks'},
        {text:'Liquor',value:'Liquor'},{text:'Pet',value:'Pet'},{text:'Baby',value:'baby'},{text:'Health & Beauty',value:'Health & Beauty'},{text:'Household',value:'Household'}]"
        :filter-method="filterHandler"
        prop="categoryName"
        label="Category"
        width="160">
      </el-table-column>
      <el-table-column
        :filters="[{text: 'Full', value: 'Full'}, {text: 'High', value: 'High'}, {text: 'Medium', value: 'Medium'}, {text: 'Low', value: 'Low'}, {text: 'Very low', value: 'Very low'}]"
        sortable
        :sort-method="(a,b) => sortMethod(a ,b)"
        :filter-method="filterHandler"
        prop="standard"
        label="Stock Level"
        width="150">
      </el-table-column>
      <el-table-column
        prop="salesFrequency"
        label="Sales Frequency (last week)"
        width="220">
      </el-table-column>
      <el-table-column
        label=""
        width="120">
        <template slot-scope="scope">
          <el-button
            @click.native.prevent="handleOrder(scope.$index, tableData)"
            type="text"
            size="small">
            Order
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <br><br>
    <!--  button-->
    <el-button class="button" type="primary">
      <a href='http://localhost:8080/goods/generateStockLevelReport'> Export </a>
    </el-button>
  </div>
</template>

<script>
import api from '@/api/report'

/* eslint-disable */
export default {
  name: 'StockLevelReport',
  created() {
    this.fetchData()
  },
  data() {
    return {
      tableData: []
    }
  },
  methods: {
    fetchData() {
      api.displayStockLevelReport().then(response => {
        this.tableData = response.data
      }).catch(error => {
        reject(error)
      })
    },
    handleOrder(index, rows) {
      this.$router.push({
          path: '../goods/Item',
          query: {
            sid: rows[index].goodId
          }
        }
      )
    },
    filterHandler(value, row, column) {
      const property = column['property']
      return row[property] === value
    },

    sortMethod(obj1, obj2) {
      let a = 0
      let b = 0

      if (obj1.standard === 'Full') {
        a = 4
      } else if (obj1.standard === 'High') {
        a = 3
      } else if (obj1.standard === 'Medium') {
        a = 2
      } else if (obj1.standard === 'Low') {
        a = 1
      } else if (obj1.standard === 'Very low') {
        a = 0
      }

      if (obj2.standard === 'Full') {
        b = 4
      } else if (obj2.standard === 'High') {
        b = 3
      } else if (obj2.standard === 'Medium') {
        b = 2
      } else if (obj2.standard === 'Low') {
        b = 1
      } else if (obj2.standard === 'Very low') {
        b = 0
      }
      return a - b
    }
  }
}
</script>

<style scoped>
.button {
  margin-left: 400px;
}
</style>
