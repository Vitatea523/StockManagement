<template>
  <div class="dashboard-container">
<!--    <div class="dashboard-text">name: {{ name }}</div>-->
    <h3 style="display: inline" v-if="this.role === 'manager'">Recent Notifications</h3>
    <el-link type="primary" v-if="this.role === 'manager'" style="margin-left: 20px" @click="handleSeeNoti()">See more details</el-link>
<!--    notification table-->
    <br><br>
    <el-table
      v-if="this.role === 'manager'"
      :data="tableData"
      style="width:90%"
      max-height="200px">
      <el-table-column
        width="50">
        <i class="el-icon-warning" style="color: red"></i>
      </el-table-column>
      <el-table-column
        prop="date"
        label="Date"
        width="230">
      </el-table-column>
      <el-table-column
        prop="message"
        label="Message"
        width="1000px">
      </el-table-column>
    </el-table>
<!--    dashboard-->
    <br><br><br>
    <h3 style="display: inline" v-if="this.role === 'manager'">Dashboard</h3>
    <el-divider  v-if="this.role === 'manager'"></el-divider>
    <el-row v-if="this.role === 'manager'">
<!--      card 1-->
      <el-col :span="6">
        <el-card shadow="hover" :body-style="{ padding: '0px' }" style="width: 350px; height: 320px; cursor: pointer" @click.native="handleClick1()">
          <img src="../../assets/stock_img.png" class="image">
          <div style="padding: 14px;">
            <span>Stock Management</span>
          </div>
        </el-card>
      </el-col>
<!--      card 2-->
      <el-col :span="6" style="margin-left: 100px">
        <el-card shadow="hover" :body-style="{ padding: '0px' }" style="width: 350px; height: 320px; cursor: pointer" @click.native="handleClick2()">
          <img src="../../assets/supplier_img.jpeg" class="image">
          <div style="padding: 14px;">
            <span>Supplier Management</span>
          </div>
        </el-card>
      </el-col>
<!--      card 3-->
      <el-col :span="6" style="margin-left: 100px">
        <el-card shadow="hover" :body-style="{ padding: '0px' }" style="width: 350px; height: 320px; cursor: pointer" @click.native="handleClick3()">
          <img src="../../assets/personnel_img.jpeg" class="image">
          <div style="padding: 14px;">
            <span>Personnel Management</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <br><br><br><br>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import api from '@/api/notification'

export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  },
  created() {
    if (this.$store.getters.roles.includes('manager')) {
      this.role = 'manager'
      api.getAllNotifications(this.$store.getters.token).then(response => {
        this.tableData = response.data.reverse()
      }).catch(error => {
        reject(error)
      })
    } else {
      this.role = 'cashier'
      this.$router.push('/cashier/checkOut')
    }
  },
  data() {
    return {
      role: '',
      tableData: []
    }
  },
  methods: {
    handleSeeNoti() {
      this.$router.push('/user/notification')
    },
    handleClick1() {
      this.$router.push('/goods')
    },
    handleClick2() {
      this.$router.push('/supplier')
    },
    handleClick3() {
      this.$router.push('/personnel')
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
.time {
   font-size: 13px;
   color: #999;
 }

.bottom {
  margin-top: 13px;
  line-height: 12px;
}

.button {
  padding: 0;
  float: right;
}

.image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}
</style>
