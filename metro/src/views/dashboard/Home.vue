<template>
  <div>
    <el-row :gutter="180">
      <el-col :span="8"><div class="grid-content bg-purple">
        <el-input
          v-model="search"
          suffix-icon="el-icon-search"
          placeholder="Search by name"
        />
      </div></el-col>
      <el-col :span="15"><div class="grid-content bg-purple">
        <el-row :gutter="20">
          <el-col :span="8"><div class="grid-content bg-purple">
            <div id="app">
              <el-select v-model="value" placeholder="Sort" @change="getSelectvalue">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.goodId">
                </el-option>
              </el-select>
            </div>
          </div></el-col>
          <el-col :span="8"><div class="grid-content bg-purple">
            <el-button type="success" icon="el-icon-circle-plus-outline" @click="handleAdd()">Add</el-button>
          </div></el-col>
        </el-row>
      </div></el-col>
    </el-row>
    <br><br>
    <el-table
      v-loading="listLoading"

      :data="list.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
      style="width: 1300px"
      @row-click="handleCheck"
    >

      <el-table-column label="Item Id" width="68px">
        <template slot-scope="scope">

          <span>{{ scope.row.goodId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Name" width="250px">
        <template slot-scope="scope">

          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column
        width="200px"
        label="APN (Australia Product Number)"
      >
        <template slot-scope="scope">

          <span>{{ scope.row.apn }}</span>
        </template>
      </el-table-column>
      <el-table-column
        width="120px"
        label="UOM (Unit of Measurement)"
      >
        <template slot-scope="scope">

          <span>{{ scope.row.uom }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="Amount(Unit)"
        width="120px"
      >
        <template slot-scope="scope">

          <span>{{ scope.row.amount }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="Category"
        prop="categoryName"
        width="120"
        column-key="date"
        :filters="[{text: 'Specials', value: 'Specials'}, {text: 'Fruit & Veg', value: 'Fruit & Veg'},{text:'Meat, Seafood & Deli',value:'Meat, Seafood & Deli'},
                   {text:'Bakery',value:'Bakery'},{text:'Dairy, Eggs & Fridge',value:'Dairy, Eggs & Fridge'},{text:'Pantry',value:'Pantry'},{text:'Freezer',value:'Freezer'},{text:'Drinks',value:'Drinks'},
                   {text:'Liquor',value:'Liquor'},{text:'Pet',value:'Pet'},{text:'Baby',value:'baby'},{text:'Health & Beauty',value:'Health & Beauty'},{text:'Household',value:'Household'}]"
        :filter-method="filterHandler"
      >
        <template slot-scope="scope">

          <span>{{ scope.row.categoryName }}</span>
        </template>
      </el-table-column>
      <el-table-column
        width="120px"
        label="Threshold"
      >
        <template slot-scope="scope">

          <span>{{ scope.row.threshold }}</span>
        </template>
      </el-table-column>
      <el-table-column
        align="right"
      >
        <template slot="header" slot-scope="scope" />
        <template slot-scope="scope" width="18">
          <el-button
            size="mini"
            type="warning"
            @click.native.stop="handleEdit(scope.row.goodId)"
          >Edit</el-button>
          <el-button
            size="mini"
            type="danger"
            @click.native.stop="handleDelete(scope.row.goodId)"
          >Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import api from '@/api/item'

export default {
  data() {
    return {
      slowList:[],
      list: [],
      needFilterlist:[], //
      listLoading: true,
      search: '',
      selected: '',
      date: "",
      value:"",
      options:[{
        goodId:1,
        label:'Slow Item',
        goodids:[141,142]
      },{
        goodId:2,
        label:'Fast Selling Item'
      },{
        goodId:3,
        label:'Most Profitable Item'
      }]
    }
  },

  created() {
    this.fetchData()

  },


  methods: {
    getSelectvalue(){

      if(this.value===1){ // slow
        this.slowData();
      }else if(this.value===2){
        this.fastData();
      }else{
        this.mostData();
      }

      console.log('value=====',this.value,this.list)
    },
    slowData() {
      this.listLoading = true
      api.slowItem().then(response => {
        let list = [];
        response.data.forEach(slow=>{
          this.needFilterlist.forEach(res=>{
            if(slow===res.goodId){
              list.push(res)
            }
          })
        })
        this.list = list
        // this.list = response.data
        // this.listLoading = false
      }).catch(()=>{

      })
      this.listLoading = false
    },
    fastData() {
      this.listLoading = true
      api.fastItem().then(response => {
        let list = [];
        response.data.forEach(slow=>{
          this.needFilterlist.forEach(res=>{
            if(slow===res.goodId){
              list.push(res)
            }
          })
        })
        this.list = list
        // this.list = response.data
        // this.listLoading = false
      }).catch(()=>{

      })
      this.listLoading = false
    },
    mostData() {
      this.listLoading = true
      api.mostItem().then(response => {
        let list = [];
        response.data.forEach(slow=>{
          this.needFilterlist.forEach(res=>{
            if(slow===res.goodId){
              list.push(res)
            }
          })
        })
        this.list = list
        // this.list = response.data
        // this.listLoading = false
      }).catch(()=>{

      })
      this.listLoading = false
    },
    fetchData() {
      this.listLoading = true
      api.listItem().then(response => {
        this.list = response.data
        this.needFilterlist =  response.data
        this.listLoading = false
      })
    },
    handleCheck(row) {
      this.$router.push({
          path: '../goods/Item',
          query: {
            sid: row.goodId
          }
        }
      )
      console.log(row)
    },
    handleAdd() {
      this.$router.push('../goods/AddItem')
    },
    handleEdit(sid) {
      this.$router.push({
          path: '../goods/EditItem',
          query: {
            sid: sid
          }
        }
      )
    },
    clearFilter() {
      this.$refs.filterTable.clearFilter()
    },
    filterHandler(value, row, column) {
      const property = column['property']
      return row[property] === value
    },
    handleDelete(item_id) {
      console.log(item_id)
      this.$confirm('This operation will permanently delete the file. Do you want to continue?', 'mention', {
        confirmButtonText: 'yes',
        cancelButtonText: 'cancel',
        type: 'warning'
      }).then(() => {
        api.deleteById(item_id).then(response => {
          console.log(response.code)
          this.$message({
            type: response.code === 20000 ? 'success' : 'error',
            message: response.msg
          })
        })
        this.fetchData()
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'cancelled delete'
        })
      })
    }
  }
}
</script>
