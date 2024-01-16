<template>
<div>
  <h2>Notifications</h2>
  <el-table
    :data="tableData.filter(data => !search || data.message.toLowerCase().includes(search.toLowerCase()))"
    style="width: 1120px">
    <el-table-column
      label="Date"
      prop="date"
      width="230px">
    </el-table-column>
    <el-table-column
      label="Message"
      prop="message"
      width="550px">
    </el-table-column>
    <el-table-column align="right" width="350px">
      <template slot="header" slot-scope="scope">
        <el-input
          v-model="search"
          size="mini"
          prefix-icon="el-icon-search"
          placeholder="Type in the key words"/>
      </template>
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleOrder(scope.$index, scope.row)">Order</el-button>
        <el-button
          size="mini"
          @click="handleSetting(scope.row)">Settings</el-button>
        <el-button
          size="mini"
          type="danger"
          @click="handleDelete(scope.$index, scope.row, tableData)">Delete</el-button>
      </template>
    </el-table-column>
  </el-table>

  <!-- dialog -->
  <el-dialog width="550px" title="Notification Settings" :visible.sync="dialogFormVisible">
    <!-- form -->
    <el-form id="setting" :inline="true" ref="form" :model="formData" label-width="80px">
      <!-- reminder switch -->
      <el-form-item label="Reminder" label-width="170px" prop="reminderOn">
        <el-switch v-model="formData.reminderOn" @change="handleSwitchReminder()"></el-switch>
      </el-form-item><br>
      <!-- reminder frequency -->
      <el-form-item label="Reminder frequency:" label-width="170px" prop="reminderFrequency">
        <el-time-select
          :disabled="reminder_frequency_disabled"
          v-model="formData.reminderFrequency"
          :picker-options="{
        start: '00:05',
        step: '00:05',
        end: '23:55'}"
          placeholder="HH:mm">
        </el-time-select>
      </el-form-item>
      <el-divider></el-divider>
      <!-- snooze switch -->
      <el-form-item label="Snooze" label-width="170px" prop="snoozeOn">
        <el-switch :disabled="snooze_disabled" v-model="formData.snoozeOn" @change="handleSwitchSnooze()"></el-switch>
      </el-form-item><br>
      <!-- snooze time -->
      <el-form-item label="Snooze time:" label-width="170px" prop="snoozeTime">
        <el-time-select
          :disabled="snooze_time_disabled"
          v-model="formData.snoozeTime"
          :picker-options="{
        start: '00:30',
        step: '00:30',
        end: '23:30'}"
          placeholder="HH:mm">
        </el-time-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <!-- confirm button -->
      <el-button type="primary" plain icon="el-icon-thumb" size="medium" @click="handleConfirm()"> Confirm </el-button>
      <el-button @click="dialogFormVisible = false">Close</el-button>
    </div>
  </el-dialog>

</div>
</template>

<script>
import api from '@/api/notification'
/* eslint-disable */
export default {
  name: 'Notification',
  inheritAttrs: false,
  components: {},
  props: [],
  created() {
    api.getAllNotifications(this.$store.getters.token).then(response => {
      this.tableData = response.data.reverse()
    }).catch(error => {
      reject(error)
    })
  },
  data() {
    return {
      tableData: [],
      search: '',

      formData: {
        reminderOn: false,
        reminderFrequency: '',
        snoozeOn: false,
        snoozeTime: '',
        token: '',
        goodsId: 0,
        type: ''
      },

      dialogTableVisible: false,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      reminder_frequency_disabled: true,
      snooze_disabled: true,
      snooze_time_disabled: true
    }
  },
  methods: {
    handleOrder(index, row) {
      this.$router.push({
          path: '../goods/Item',
          query: {
            sid: row.goodsId
          }
        }
      )
    },
    handleDelete(index, row, rows) {
      this.$confirm(
        'This operation will permanently remove the notification. Do you want to continue?',
        'Warning',
        {
          confirmButtonText: 'Delete anyway',
          cancelButtonText: 'Cancel',
          type: 'warning',
          center: true
        }
      ).then(() => {
        api.deleteNotification(row.goodsId, row.type).then(response => {
          rows.splice(index, 1)
          this.$message({
            type: 'success',
            message: 'Delete successfully!'
          })
        }).catch(error => {
          this.$message({
            type: 'error',
            message: error.message
          })
        })
      }).catch(() => {
        this.$message({
          type: 'error',
          message: 'removal cancelled'
        })
      })
    },
    onOpen() {},
    onClose() {
      this.$refs['form'].resetFields()
    },
    close() {
      this.$emit('update:visible', false)
    },
    handleConfirm() {
      api.makeNotificationSettings(this.formData).then(response => {
        this.$message({
          type: 'success',
          message: 'update settings successfully!'
        })
        this.dialogFormVisible = false

        api.getAllNotifications(this.$store.getters.token).then(response => {
          this.tableData = response.data.reverse()
        }).catch(error => {
          reject(error)
        })
      }).catch(error => {
        this.$message({
          type: 'error',
          message: error.message
        })
      })
    },
    handleSetting(row) {
      this.dialogFormVisible = true
      this.formData.reminderOn = row.reminderOn
      this.formData.reminderFrequency = row.reminderFrequency
      this.formData.snoozeOn = row.snoozeOn
      this.formData.snoozeTime = row.snoozeTime
      this.formData.token = this.$store.getters.token
      this.formData.goodsId = row.goodsId
      this.formData.type = row.type

      if (this.formData.reminderOn) {
        this.reminder_frequency_disabled = false
        this.snooze_disabled = false
      } else {
        this.reminder_frequency_disabled = true
        this.formData.snoozeOn = false
        this.snooze_disabled = true
        this.snooze_time_disabled = true
      }

      if (this.formData.snoozeOn) {
        this.snooze_time_disabled = false
      } else {
        this.snooze_time_disabled = true
      }
    },
    handleSwitchReminder() {
      if (this.formData.reminderOn) {
        this.reminder_frequency_disabled = false
        this.snooze_disabled = false
      } else {
        this.reminder_frequency_disabled = true
        this.snooze_disabled = true
        this.snooze_time_disabled = true
        this.formData.snoozeOn = false
      }
    },
    handleSwitchSnooze() {
      if (this.formData.snoozeOn) {
        this.snooze_time_disabled = false
      } else {
        this.snooze_time_disabled = true
      }
    },
  },
  computed: {},
  watch: {},
  mounted() {}
}
</script>
<style scoped>
</style>
