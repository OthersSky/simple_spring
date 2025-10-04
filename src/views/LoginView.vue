<template>
  <div class="login-container">
    <h2>用户登录</h2>
    <div class="form-group">
      <input v-model="name" placeholder="用户名" />
    </div>
    <div class="form-group">
      <input v-model="password" type="password" placeholder="密码" />
    </div>
    <button @click="handleLogin">登录</button>

    <p v-if="message" class="message">{{ message }}</p>
  </div>
</template>

<script>
import request from '@/api/index'

export default {
  name: 'LoginView',
  data() {
    return {
      name: '',
      password: '',
      message: ''
    }
  },
  methods: {
    async handleLogin() {
      if (!this.name || !this.password) {
        this.message = '请输入用户名和密码'
        return
      }

      try {
        const res = await request.post('/login', {
          name: this.name,
          password: this.password
        })

        if (res.success) {
          this.message = res.message
          // 保存用户信息（不保存密码）
          localStorage.setItem('user', JSON.stringify(res.user))
          this.$router.push('/home') // 登录成功跳转
        } else {
          this.message = res.message
        }
      } catch (error) {
        console.error(error)
        this.message = '服务器连接失败'
      }
    }
  }
}
</script>

<style scoped>
.login-container {
  width: 300px;
  margin: 120px auto;
  text-align: center;
}
input {
  width: 100%;
  padding: 8px;
  margin: 8px 0;
  box-sizing: border-box;
}
button {
  width: 100%;
  padding: 10px;
  background-color: #42b983;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}
.message {
  margin-top: 10px;
  color: red;
}
</style>
