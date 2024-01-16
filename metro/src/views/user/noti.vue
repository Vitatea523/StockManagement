<template>
  <div>
  </div>
</template>

<script>
import api from '@/api/notification'

export default {
  data() {
    return {
      notifications: [],
      timer: undefined,
      overlapTimer: undefined
    }
  },
  computed: {
    notificationsData() {
      return this.notifications
    }
  },
  created() {
    this.timer = setInterval(() => {
      setTimeout(this.open1, 0)
    }, 10000)
    this.$once('hook:beforeDestroy', () => {
      clearInterval(this.timer)
    })
  },
  methods: {
    startTime() {
      this.timer = setInterval(() => {
        setTimeout(this.open1, 0)
      }, 10000)//10 seconds
    },
    closeTime() {
      clearInterval(this.timer)
    },
    getNotifications() {
      // polling notification
      api.getPopUpNotifications(this.$store.getters.token).then(response => {
        this.notifications = response.data
      })
    },
    async open1() {
      this.getNotifications()
      for (var i = 0; i < this.notifications.length; i++) {
        var context = this.notifications[i]
        await this.$notify({
          title: 'Warning',
          message: context,
          type: 'warning',
          duration: 0
        })
      }
    }
  }
}
</script>

<style scoped>

</style>
