import Vue from 'vue'
import VueRouter from 'vue-router'
import DashboardComponent from "@/components/DashboardComponent";
import PageNotFound from "@/components/PageNotFound";

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
        component: DashboardComponent,
        beforeEnter(to, from, next) {
            if(to.name !== 'login' && !this.$store.state.status.loggedIn){
                next({
                    path: 'login',
                    replace: true
                })
            }else{
                next()
            }
        }
    },

]

const router = new VueRouter({
    mode: "history",
    routes
})

export default router
