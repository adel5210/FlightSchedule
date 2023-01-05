import Vue from 'vue'
import VueRouter from 'vue-router'
import DashboardComponent from "@/components/DashboardComponent";
import AirlineRoutesComponent from "@/components/AirlineRoutesComponent";
import AirlinesComponent from "@/components/AirlinesComponent";
import AirportComponent from "@/components/AirportComponent";
import CitiesComponent from "@/components/CitiesComponent";
import CountriesComponent from "@/components/CountriesComponent";
import HistoryComponent from "@/components/HistoryComponent";
import SearchFlightComponent from "@/components/SearchFlightComponent";
import TrackFlightConponent from "@/components/TrackFlightConponent";
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
        children: [
            {
                path: 'airlines',
                component: {
                    containedView: AirlinesComponent
                }
            },
            {
                path: 'airline-routes',
                component: {
                    containedView: AirlineRoutesComponent
                }
            },
            {
                path: 'airports',
                component: {
                    containedView: AirportComponent
                }
            },
            {
                path: 'cities',
                component: {
                    containedView: CitiesComponent
                }
            },
            {
                path: 'countries',
                component: {
                    containedView: CountriesComponent
                }
            },
            {
                path: 'history',
                component: {
                    containedView: HistoryComponent
                }
            },
            {
                path: 'search-flights',
                component: {
                    containedView: SearchFlightComponent
                }
            },
            {
                path: 'track-flights',
                component: {
                    containedView: TrackFlightConponent
                }
            },
        ]
    },

]

const router = new VueRouter({
    mode: "history",
    routes
})

export default router
