import { createRouter, createWebHistory } from 'vue-router'
import AdminHome from "@/views/AdminHome";
import WorkerHome from "@/views/WorkerHome";
import UserHome from "@/views/UserHome";

const routes = [
  {
    path: '/userhome',
    name: 'userhome',
    component: UserHome,
    children:[
      {path: '/User',
        name: 'User',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "main" */ '../views/User')
      },
      {path: '/U2',
        name: 'U2',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "main" */ '../views/U2')

      },
      {path: '/U1',
        name: 'U1',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "main" */ '../views/U1')

      },
    ]
  },
  {
    path: '/adminhome',
    name: 'adminhome',
    component: AdminHome,
    children:[
      {
        path: '/Ad_food',
        name: 'ad_food',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "main" */ '../views/Ad_food')
      },
      {
        path: '/Ad_order',
        name: 'Ad_order',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "main" */ '../views/Ad_order')
      },
      {
        path: '/Ad_user',
        name: 'Ad_user',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "main" */ '../views/Ad_user')
      },
    ]
  },
  {
    path: '/workerhome',
    name: 'workerhome',
    component: WorkerHome,
    children:[
      {
        path: '/B1',
        name: 'B1',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "main" */ '../views/B1')
      },
      {
        path: '/B2',
        name: 'B2',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "main" */ '../views/B2')
      },
    ]
  },
  {
    path: '/',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/login.vue')
  },
  {
    path: '/:catchAll(.*)',
    name: '/404',
    component: () => import(/* webpackChunkName: "about" */ '../views/404.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
