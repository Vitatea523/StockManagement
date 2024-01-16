<template>
  <div>
    <el-card class="box-card">
      <!-- title -->
      <div slot="header" class="clearfix">
        <span><h1 id="title">Create a New Account</h1></span>
        <!-- Register form -->
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="150px" class="demo-ruleForm">
          <br>
          <!-- name -->
          <el-form-item label="Username" prop="name">
            <el-input v-model="ruleForm.name" placeholder="e.g. John Smith"></el-input>
          </el-form-item>

          <!-- role -->
          <el-form-item label="Role" prop="role">
            <el-select v-model="ruleForm.role" placeholder="Please select your role">
              <el-option label="Manager" value="manager"></el-option>
              <el-option label="Cashier" value="cashier"></el-option>
            </el-select>
          </el-form-item>

          <!-- password -->
          <el-form-item label="Password" prop="pass">
            <el-input type="password" v-model="ruleForm.pass" autocomplete="new-password" placeholder="Please enter a combination of at lease six numbers"></el-input>
          </el-form-item>

          <!-- check password -->
          <el-form-item label="Confirm password" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" placeholder="Please repeat the above password"></el-input>
          </el-form-item>

          <!-- confirm button -->
          <el-form-item style="margin-left:85px; margin-top:50px;">
            <el-button type="primary" @click="submitForm('ruleForm')">Create</el-button>
            <el-button @click="resetForm('ruleForm')">Reset</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-card>
  </div>
</template>

<script>
import { createAccount } from '@/api/user'
/* eslint-disable */
export default {
  data() {
    /* check password*/
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please enter your password'));
      } else if (value.length < 6) {
        callback(new Error('Invalid password!'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };

    /* double check password*/
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please confirm your password'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('Different password!'));
      } else {
        callback();
      }
    };

    return {
      ruleForm: {
        name: '',
        role: '',
        pass: '',
        checkPass: '',
      },

      user: {
        id: 0,
        name: '',
        title: '',
        password: '',
      },

      rules: {
        name: [
          {required: true, message: 'Please enter your name', trigger: 'blur' },
        ],
        role: [
          {required:true, message: 'Please select your role', trigger: 'blur'},
        ],
        pass: [
          {required: true, validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          {required: true, validator: validatePass2, trigger: 'blur' }
        ],
      }
    };
  },

  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {

          this.user.id = '';
          this.user.name = this.ruleForm.name;
          this.user.title = this.ruleForm.role;
          this.user.password = this.ruleForm.pass;

          createAccount(this.user).then(() => {
            console.log("success")
            this.$message({
              type: 'success',
              message: 'Account created successfully!'
            })
          }).catch(error => {})

        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<style>
h1 {
  font-family:Arial;
  font-size:25px;
  font-weight:lighter;
  color:dimgray;
  text-align: center;
}

.demo-ruleForm{
  text-align: left; /*让div内部文字居中*/
  background-color: #fff;
  border-radius: 20px;
  width: 550px;
  height: 300px;
  position: absolute;
  top: 220px;
  left: 110px;
  right: 100px;
  bottom: 0;
}

.box-card {
  margin-top:50px;
  margin-left: 80px;
  height: 500px;
  width: 700px;
}

</style>
