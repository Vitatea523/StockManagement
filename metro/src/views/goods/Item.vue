<template>
  <el-container>
    <el-container>
      <el-aside width="600px">
        <el-descriptions
          class="margin-top"
          :column="1"
          border
        >
          <template slot="title" style="margin-left: 10px">
            {{ formData.name }}
          </template>

          <template slot="extra">
            <el-button
              size="mini"
              type="warning"
              @click="handleEdit(formData.goodId)"
            >Edit</el-button>
            <el-button
              size="mini"
              type="danger"
              @click="handleDelete(formData.goodId)"
            >Delete</el-button>
          </template>
          <el-descriptions-item label="ID">{{ formData.goodId }}</el-descriptions-item>
          <el-descriptions-item label="APN (Australia Product Number)">{{ formData.apn }}</el-descriptions-item>
          <el-descriptions-item label="Name">{{ formData.name }}</el-descriptions-item>
          <el-descriptions-item label="Category">{{ formData.categoryName }}</el-descriptions-item>
          <el-descriptions-item label="Amount(Unit)">{{ formData.amount }}</el-descriptions-item>
          <el-descriptions-item label="UOM (Unit of Measurement)">{{ formData.uom }}</el-descriptions-item>
          <el-descriptions-item label="Price per unit">{{ formData.price}}
          </el-descriptions-item>
          <el-descriptions-item label="Threshold">{{ formData.threshold }}</el-descriptions-item>
<!--          <el-descriptions-item label="Discount">{{ formData.discount }}</el-descriptions-item>-->
          <el-descriptions-item label="Stock Percentage">{{ formData.standard }}</el-descriptions-item>
          <el-descriptions-item label="GP%">{{ formData.gp }}</el-descriptions-item>
        </el-descriptions>

      </el-aside>
      <el-main>
        <el-table
          :data="tableData"
          height="250"
          border
          style="width: 100%"
        >
          <el-table-column

            label="Index"
            width="100"
          ><template slot-scope="scope">

            <span style="margin-left: 10px">{{ scope.row.id }}</span>
          </template>
          </el-table-column>
          <el-table-column
            label="Cost Per Carton"
            width="130"
          >
            <template slot-scope="scope">

              <span style="margin-left: 10px">{{ scope.row.costPerCarton }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="Cost Per Unit"
            width="130"
          >
            <template slot-scope="scope">

              <span style="margin-left: 10px">{{ scope.row.costPerUnit }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="Counts"
            width="100"
          >
            <template slot-scope="scope">

            <span style="margin-left: 10px">{{ scope.row.amount }}</span>
          </template>
          </el-table-column>
          <el-table-column
            prop="date"
            label="Expire Date"
            width="300"
          >
            <template slot-scope="scope">

              <span style="margin-left: 10px">{{ scope.row.expireDate }}</span>
            </template>
          </el-table-column>
        </el-table>

        <el-table
          :data="orderdata"
          border
          style="width: 100%"
          :default-sort="{prop: 'priceunit', order: 'descending'}"
        >
          <el-table-column
            label="Supplier Id"
            width="100"
          >
            <template slot-scope="scope">

              <span style="margin-left: 10px">{{ scope.row.id }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="Business name"
            width="130"
          >
            <template slot-scope="scope">

              <span style="margin-left: 10px">{{ scope.row.businessName }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="Email"
            width="100"
          >
            <template slot-scope="scope">

              <span style="margin-left: 10px">{{ scope.row.email }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="Phone Number"
            width="130"
          >
            <template slot-scope="scope">

              <span style="margin-left: 10px">{{ scope.row.phoneNumber }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="Price per unit"
            prop="priceunit"
            sortable
            width="140"
          >
            <template slot-scope="scope">

              <span style="margin-left: 10px">{{ scope.row.costPerUnit }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="Price per carton"
            prop="pricecarton"
            sortable
            width="160"
          >
            <template slot-scope="scope">

              <span style="margin-left: 10px">{{ scope.row.costPerCarton }}</span>
            </template>
          </el-table-column>

          <el-table-column label="Action">
            <template slot-scope="scope">
              <el-button
                size="mini"
                @click="handleOrder(scope.row.id)"
              >Order</el-button>
            </template>
          </el-table-column>
        </el-table>

      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import api from '@/api/item'
export default {
  data() {
    return {
      formData: {},
      tableData: [],
      orderdata: []

    }
  },
  created() {
    this.fetchItemInfoById()
    this.supplierById()
    this.fetchlistById()
  },
  methods: {
    fetchItemInfoById() {
      var sid = this.$route.query.sid
      api.fetchDataBySid(sid).then(response => {
        this.formData = response.data
        console.log(response.data)
      })
    },
    supplierById() {
      var sid = this.$route.query.sid
      api.suppliergood(sid).then(response => {
        this.orderdata = response.data
      })
    },
    fetchlistById() {
      var sid = this.$route.query.sid
      api.fetchlist(sid).then(response => {
        this.tableData = response.data
      }).catch(function(err) {
        console.log(err)
      })
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
    handleOrder(supplier_id) {
      this.$router.push({
        name: 'AddOrder',
        params: { name: this.formData.name, sid: supplier_id, item_id: this.formData.goodId }
      })
    },
    handleDelete(item_id) {
      const _this = this
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
        _this.$router.push('/goods/listItem')
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
