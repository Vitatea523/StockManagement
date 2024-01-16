<template>
  <div>
    <div align="center">
      <el-card class="box-card" align="center">
        <div slot="header" class="clearfix">
          <span><h1 id="title">Maintain supplier</h1></span>
        </div>
        <div class="text item">
          {{ "Supplier ID: " + formData.id }}
        </div>
        <div class="text item">
          {{ "Business Name: " + formData.businessName }}
        </div>
        <div class="text item">
          {{ "Contact Name: " + formData.contactName }}
        </div>
        <div class="text item">
          {{ "E-mail Address: " + formData.email }}
        </div>
        <div class="text item">
          {{ "Contact Number: " + formData.phoneNumber }}
        </div>
        <el-button @click="handleAddGood">Add Good</el-button>
      </el-card>
      <br>
    </div>

    <h2>Goods on offer</h2>
    <el-divider />
    <el-table :data="tableData" style="width: 100%" align="center" >
      <el-table-column label="Item ID" width="300">
        <template slot-scope="scope">
          <i class="el-icon-bank-card" />
          <span style="margin-left: 10px">{{ scope.row.id }}</span>
        </template>
      </el-table-column>
      <el-table-column label="Item Name" width="300">
        <template slot-scope="scope">
          <i class="el-icon-s-cooperation" />
          <span style="margin-left: 10px">{{ scope.row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column label="Price" width="250">
        <template slot-scope="scope">
          <div slot="reference" class="name-wrapper">
            <el-tag size="medium">${{ scope.row.price }}</el-tag>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="Operation" width="250">
        <template slot-scope="scope">
          <div class="grid-content bg-purple">
            <el-button
              type="danger"
              @click="handleOrder(scope.row.name,scope.row.id)"
            >Order
            </el-button>
          </div>
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
      name: 'MaintainSupplier',
      formData: {},
      tableData: []
    }
  },
  created() {
    this.fetchSupplierInfoById()
    this.fetchAllGoods()
  },
  methods: {
    fetchSupplierInfoById() {
      var sid = this.$route.params.sid
      api.fetchDataBySid(sid).then(response => {
        this.formData = response.data
      })
    },
    fetchAllGoods() {
      var sid = this.$route.params.sid
      api.goodsOnOffer(sid).then(response => {
        this.tableData = response.data
      })
    },
    handleOrder(iname, iid) {
      this.$router.push({
        name: 'AddOrder',
        params: { name: iname, sid: this.$route.params.sid, item_id: iid }
      })
    },
    handleAddGood() {
      this.$router.push('/supplier/AddSupplyGoods/' + this.$route.params.sid)
    },
  }
}
</script>
<style>
.text {
  font-size: 18px;
}

.item {
  padding: 18px 0;
}

.box-card {
  width: 480px;
}
</style>
