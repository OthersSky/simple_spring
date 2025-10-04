import { createRouter, createWebHistory } from 'vue-router'
import LoginView from '@/views/LoginView.vue'

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/login', component: LoginView },
  {
    path: '/home',
    component: () => import('@/views/HomeView.vue') // 登录后跳转页
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
