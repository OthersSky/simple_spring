import axios from 'axios'

const service = axios.create({
  baseURL: 'http://localhost:8080', // Spring Boot 地址
  timeout: 5000
})

// 请求拦截器（可选）
service.interceptors.request.use(
  config => {
    return config
  },
  error => Promise.reject(error)
)

// 响应拦截器
service.interceptors.response.use(
  response => response.data, // 直接返回 data
  error => Promise.reject(error)
)

export default service
