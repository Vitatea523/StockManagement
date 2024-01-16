<template>
  <div>
    <el-form
      ref="orderEditInf0Form"
      :model="formData"
      :rules="rules"
      size="small"
      label-width="100px"
      label-position="top"
    >
      <el-form-item label="Item" prop="item">
        <el-input v-model="this.$route.params.name " readonly prefix-icon="el-icon-goods" :style="{width: '100%'}" />
      </el-form-item>
      <el-form-item label="Supplier" prop="supplier">
        <el-input
          v-model="supplierData.businessName"
          readonly
          prefix-icon="el-icon-user-solid"
          :style="{width: '100%'}"
        />
      </el-form-item>
      <el-form-item label="Contact number" prop="contact_number">
        <el-input
          v-model="supplierData.phoneNumber"
          readonly
          prefix-icon="el-icon-phone-outline"
          :style="{width: '100%'}"
        />
      </el-form-item>
      <el-form-item label="E-mail address" prop="email">
        <el-input v-model="supplierData.email" readonly prefix-icon="el-icon-message" :style="{width: '100%'}" />
      </el-form-item>
      <el-form-item label="Unit" prop="amount">
        <el-input-number v-model="formData.amount" :step="1" :max="5000" />
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submit">Submit</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script>
import api from '@/api/supplier'
import addOrder from '@/api/orderHistory'
export default {
  components: {},
  props: [],
  data() {
    return {
      formData: {
        goodsId: undefined,
        supplierId: undefined,
        amount: undefined
      },
      supplierData: [],
      rules: {
        item: [],
        supplier: [],
        contact_number: [],
        email: [],
        amount: [{
          required: true,
          message: '',
          trigger: 'blur'
        }]
      }
    }
  },
  computed: {},
  watch: {},
  created() {
    this.formData.goodsId = parseInt(this.$route.params.item_id)
    this.formData.supplierId = parseInt(this.$route.params.sid)
    this.fetchDataBySid()
  },
  mounted() {},
  methods: {

    fetchDataBySid() {
      var name = this.$route.params.name
      var sid = this.$route.params.sid
      api.fetchDataBySid(sid).then(response => {
        this.supplierData = response.data
      })
    },
    submit() {
      this.$confirm('You need to make sure that you wanna email the supplier for this order detail.', 'Warning', {
        cancelButtonText: 'Cancel',
        confirmButtonText: 'Send E-mail',
        type: 'warning'
      }).then(() => {
        addOrder.email(this.formData)
        this.$message({
          type: 'success',
          message: 'The Order is in Progress!'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: 'Cancel Order'
        })
      })
    },
  }
}

</script>
<style>
</style>
