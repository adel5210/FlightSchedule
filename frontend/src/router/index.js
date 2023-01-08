import Vue from 'vue'
import VueRouter from 'vue-router'
import DashboardComponent from "@/components/DashboardComponent";
import PageNotFound from "@/components/PageNotFound";
import store from '@/store/index'

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
        path: '/forget-password',
        name: 'forget-password',
        component: () => import('@/components/ForgetPasswordComponent')
    },
    {
        path: '*',
        name: 'PageNotFound',
        component: PageNotFound
    },
    {
        path: '/dashboard',
        alias: "/",
        name: 'dashboard',
        component: DashboardComponent
    },

]

const router = new VueRouter({
    mode: "history",
    routes
})

router.beforeEach((to, from, next) => {
    if (from.name === 'dashboard' && !store.state.status.loggedIn) {
        next({
            path: 'login',
            replace: true
        })
    } else {
        next()
    }
});

export default router
