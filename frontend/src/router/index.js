import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/components/LoginComponent')
  },
  {
    path: '/signup',
    name: 'signup',
    component: () => import('@/components/SignUpComponent')
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    component: () => import('@/components/DashboardComponent')
  },
]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router