<template>
  <div class="home">
    <h2>欢迎回来，{{ user.name }}</h2>
    <button @click="logout">退出登录</button>
  </div>
</template>

<script>
export default {
  name: 'HomeView',
  data() {
    return {
      user: JSON.parse(localStorage.getItem('user')) || {}
    }
  },
  methods: {
    logout() {
      localStorage.removeItem('user')
      this.$router.push('/login')
    }
  }
}
</script>

<style scoped>
.home {
  text-align: center;
  margin-top: 120px;
}
button {
  margin-top: 20px;
  background-color: #ff4d4f;
  color: white;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
}
</style>
