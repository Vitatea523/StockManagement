<template>
  <div>
    <h2>Edit Supplier</h2>
    <el-form
      ref="supplierEditForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
      label-position="top"
      :style="{ width: '75%' }"
    >
      <el-form-item label="Supplier ID">
        <el-input
          v-model="formData.id"
          readonly
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="Business name" prop="businessName">
        <el-input
          v-model="formData.businessName"
          placeholder="Please enter the business name"
          clearable
          prefix-icon="el-icon-s-custom"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="Contact Name" prop="contactName">
        <el-input
          v-model="formData.contactName"
          placeholder="Please enter the contact name"
          clearable
          prefix-icon="el-icon-s-custom"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="E-mail address" prop="email">
        <el-input
          v-model="formData.email"
          placeholder="Please enter the the E-mail address"
          clearable
          prefix-icon="el-icon-s-promotion"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="Contact number" prop="phoneNumber">
        <el-input
          v-model="formData.phoneNumber"
          placeholder="Please enter the contact number"
          clearable
          prefix-icon="el-icon-phone"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="updateSupplier">Edit</el-button>
        <el-button @click="resetForm">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import api from '@/api/supplier'

export default {
  data() {
    return {
      formData: {},
      rules: {
        id: [
          {
            required: true,
            message: 'Please enter the supplier id',
            trigger: 'blur'
          }
        ],
        businessName: [
          {
            required: true,
            message: 'Please enter the business name',
            trigger: 'blur'
          }
        ],
        contactName: [
          {
            required: true,
            message: 'Please enter the business name',
            trigger: 'blur'
          }
        ],
        email: [
          {
            required: true,
            message: 'Please enter the the E-mail address',
            trigger: 'blur'
          }
        ],
        phoneNumber: [
          {
            required: true,
            message: 'Please enter the contact number',
            trigger: 'blur'
          }
        ]
      }
    }
  },
  created() {
    this.fetchDataBySid()
  },
  mounted() {
  },
  methods: {
    fetchDataBySid() {
      var sid = this.$route.params.sid
      api.fetchDataBySid(sid).then(response => {
        this.formData = response.data
      })
    },
    updateSupplier() {
      const _this = this
      this.$refs['supplierEditForm'].validate((valid) => {
        if (!valid) return
        api.editSupplier(_this.formData).then(response => {
          _this.$router.push('/supplier/ListSupplier')
        })
      })
    },
    resetForm() {
      this.$refs['supplierEditForm'].resetFields()
    }
  }
}
</script>

<style></style>
