<template>
  <div class="main">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span><h1>Personal Information</h1> </span>
      </div>

      <div class="text item">
        <p><i class="el-icon-user"></i>  &nbsp; Username: {{this.profile.name}}</p>
        <p><i class="el-icon-s-cooperation"></i> &nbsp; Role: <el-tag> {{this.profile.title}} </el-tag></p>
        <p><i class="el-icon-coordinate"></i> &nbsp; ID: {{this.profile.id}}</p>
        <p><i class="el-icon-key"></i>  &nbsp; Password: &nbsp; <el-button type="primary" @click="dialogFormVisible = true" round size="small">Reset</el-button></p>

          <!-- form -->
          <el-dialog title="Reset Password" :visible.sync="dialogFormVisible" width="550px">
            <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
              <!-- password -->
              <el-form-item label="New Password: " :label-width="formLabelWidth" prop="pass">
                <el-input type="password" v-model="ruleForm.pass" autocomplete="new-password" placeholder="Please enter the new password"></el-input>
                <p style="line-height:110%"><i class="el-icon-warning"></i> Please enter a combination of at lease six numbers</p>
              </el-form-item>

              <!-- check password -->
              <el-form-item label="Confirm password: "  :label-width="formLabelWidth" prop="checkPass">
                <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off" placeholder="Please repeat the above password"></el-input>
              </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
<!--              dialogFormVisible = false-->
              <el-button @click="resetForm('ruleForm')">Cancel</el-button>
              <el-button type="primary" @click="submitForm('ruleForm')">Confirm</el-button>
            </div>
        </el-dialog>

      </div>
    </el-card>
  </div>
</template>

<script>
import { getInfo, resetPassword } from '@/api/user'
/* eslint-disable */
export default {
  name: "Profile",
  created() {
    getInfo(this.$store.getters.token).then(response => {
      this.profile = response.data
    }).catch(error => {
      reject(error)
    })
  },
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
      //profile
      profile: {},

      //dialog
      dialogFormVisible: false,
      ruleForm: {
        pass: '',
        checkPass: '',
      },
      formLabelWidth: '150px',

      rules: {
        pass: [
          {required: true, validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          {required: true, validator: validatePass2, trigger: 'blur' }
        ],
      }
    }
  },

  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          resetPassword(this.profile.id, this.ruleForm.checkPass).then(response => {
            this.$message({
              type: 'success',
              message: 'Reset password successfully!'
            })
          }).catch(error => {
            reject(error)
          })
          this.$refs[formName].resetFields();
          this.dialogFormVisible = false;

        } else {
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      this.dialogFormVisible = false;
    }
  }
}
</script>

<style scoped>
h1 {
  font-family:Arial;
  font-size:20px;
  font-weight:lighter;
  color:dimgray;
}

.text {
  font-size: 18px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 550px;
  height: 400px;
  margin-left: 80px;
  margin-top: 60px;
}
</style>
