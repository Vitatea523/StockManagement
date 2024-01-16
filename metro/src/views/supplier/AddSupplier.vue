<template>
  <div>
    <h2>Add Supplier</h2>
    <el-form
      ref="supplierAddForm"
      :model="newSupplier"
      :rules="rules"
      size="medium"
      label-width="100px"
      label-position="top"
      :style="{ width: '50%' }"
    >
      <el-form-item label="Contact Name" prop="contactName">
        <el-input
          v-model="newSupplier.contactName"
          placeholder="Please enter the contact name"
          clearable
          prefix-icon="el-icon-bank-card"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="Business name" prop="businessName">
        <el-input
          v-model="newSupplier.businessName"
          placeholder="Please enter the business name"
          clearable
          prefix-icon="el-icon-s-custom"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="E-mail address" prop="email">
        <el-input
          v-model="newSupplier.email"
          placeholder="Please enter the the E-mail address"
          clearable
          prefix-icon="el-icon-s-promotion"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item label="Contact number" prop="phoneNumber">
        <el-input
          v-model="newSupplier.phoneNumber"
          placeholder="Please enter the contact number"
          clearable
          prefix-icon="el-icon-phone"
          :style="{ width: '100%' }"
        />
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="add">Add</el-button>
        <el-button @click="resetForm">Reset</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import api from '@/api/supplier'
export default {
  name: 'AddSupplier',
  data() {
    return {
      newSupplier: {
        id: undefined,
        contactName: undefined,
        businessName: undefined,
        email: undefined,
        phoneNumber: undefined
      },
      rules: {
        contactName: [
          {
            required: true,
            message: 'Please enter the contact name',
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
  },
  methods: {
    add() {
      const _this = this
      this.$refs['supplierAddForm'].validate((valid) => {
        if (!valid) return
        api.addSupplier(_this.newSupplier).then(response => {
          if (response.msg === 'success') {
            _this.$message({
              message: 'add successfully',
              type: 'success'
            })
          }
          _this.$router.push('/supplier/ListSupplier')
        })
      })
    },
    resetForm() {
      this.$refs['supplierAddForm'].resetFields()
    }
  }
}
</script>

<style></style>
