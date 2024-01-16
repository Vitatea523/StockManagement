import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'
// eslint-disable-next-line no-unused-vars
import ListSupplier from '@/views/supplier/ListSupplier'

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },

  {
    path: '/404',
    component: () => import('@/views/404'),
    hidden: true
  },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: 'Dashboard', icon: 'dashboard' }
    }] }
  // 404 page must be placed at the end !!!
  // { path: '*', redirect: '/404', hidden: true }

  // {
  //   path: '/example',
  //   component: Layout,
  //   redirect: '/example/table',
  //   name: 'Example',
  //   meta: { title: 'Example', icon: 'el-icon-s-help' },
  //   hidden: true,
  //   children: [
  //     {
  //       path: 'table',
  //       name: 'Table',
  //       component: () => import('@/views/table/index'),
  //       meta: { title: 'Table', icon: 'table' }
  //     },
  //     {
  //       path: 'tree',
  //       name: 'Tree',
  //       component: () => import('@/views/tree/index'),
  //       meta: { title: 'Tree', icon: 'tree' }
  //     }
  //   ]
  // },

  // {
  //   path: '/form',
  //   component: Layout,
  //   children: [
  //     {
  //       path: 'index',
  //       name: 'Form',
  //       component: () => import('@/views/form/index'),
  //       meta: { title: 'Form', icon: 'form' }
  //     }
  //   ]
  // },
]

export const asyncRoutes = [
  {
    path: '/cashier',
    component: Layout,
    // component: () => import ('@/views/cashier/CheckOut'),
    name: 'Cashier',
    redirect: '/cashier/checkOut',
    meta: { title: 'Cashier', icon: 'el-icon-s-platform', roles: ['cashier'] },
    children: [
      {
        path: 'checkOut',
        name: 'CheckoutOut',
        component: () => import ('@/views/cashier/CheckOut'),
        meta: { title: 'Check Out', roles: ['cashier'] }
      }, {
        path: 'addItem',
        name: 'AddItem',
        component: () => import ('@/views/cashier/AddItem'),
        hidden: true,
        meta: { title: 'Add Item', roles: ['cashier'] }
      }, {
        path: 'cash',
        name: 'Cash',
        component: () => import ('@/views/cashier/PayByCash'),
        hidden: true,
        meta: { title: 'Pay by cash', roles: ['cashier'] }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    meta: { roles: ['manager', 'cashier'] },
    children: [{
      path: 'notification',
      name: 'Notification',
      component: () => import('@/views/user/Notification'),
      meta: { roles: ['manager', 'cashier'] }
    }, {
      path: 'profile',
      name: 'Profile',
      component: () => import('@/views/user/Profile'),
      meta: { roles: ['manager', 'cashier'] }
    },
    ]
  },
  {
    path: '/supplier',
    component: Layout,
    redirect: '/supplier/listSupplier',
    name: 'Supplier',
    meta: { title: 'Supplier', icon: 'el-icon-user-solid', roles: ['manager'] },
    children: [
      {
        path: 'listSupplier',
        name: 'ListSupplier',
        component: () => import ('@/views/supplier/ListSupplier'),
        meta: { title: 'All supplier', roles: ['manager'] }
      },
      {
        path: 'addSupplier',
        name: 'AddSupplier',
        component: () => import ('@/views/supplier/AddSupplier'),
        meta: { title: 'Add supplier', roles: ['manager'] }
      }, {
        path: 'orderHistory',
        name: 'OrderHistory',
        component: () => import ('@/views/supplier/OrderHistory'),
        meta: { title: 'Order History', roles: ['manager'] }
      }, {
        path: 'editSupplier/:sid',
        name: 'EditSupplier',
        component: () => import ('@/views/supplier/EditSupplier'),
        hidden: true,
        meta: { roles: ['manager'] }
      }, {
        path: 'maintainSupplier/:sid',
        name: 'MaintainSupplier',
        component: () => import ('@/views/supplier/MaintainSupplier'),
        meta: { roles: ['manager'] },
        hidden: true
      }, {
        path: 'addSupplyGoods/:sid',
        name: 'addSupplyGoods',
        component: () => import ('@/views/supplier/AddSupplyGoods'),
        meta: { roles: ['manager'] },
        hidden: true
      }
    ]
  },
  {
    path: '/order',
    component: Layout,
    name: 'Order',
    meta: { roles: ['manager'] },
    children: [
      {
        path: 'addOrder',
        name: 'AddOrder',
        component: () => import ('@/views/order/AddOrder'),

      }
    ]
  }, {
    path: '/goods',
    component: Layout,
    redirect: '/goods/listItem',
    name: 'Goods',
    meta: { title: 'Goods', icon: 'el-icon-sell', roles: ['manager'] },
    children: [
      {
        path: 'listItem',
        name: 'ListItem',
        component: () => import ('@/views/dashboard/Home'),
        meta: { title: 'All items', roles: ['manager'] }
      },
      {
        path: 'item',
        name: 'Item',
        hidden: true,
        component: () => import ('@/views/goods/Item'),
        meta: { title: 'Item details', roles: ['manager'] }
      },
      {
        path: 'addItem',
        name: 'AddItem',
        component: () => import ('@/views/goods/AddItem'),
        meta: { title: 'Add items', roles: ['manager'] }
      }, {
        path: 'editItem',
        name: 'EditItem',
        component: () => import ('@/views/goods/EditItem'),
        meta: { roles: ['manager'] },
        hidden: true
      }, {
        path: 'stockLevelReport',
        name: 'stockLevelReport',
        component: () => import ('@/views/goods/StockLevelReport'),
        meta: { title: 'Stock Level Report', roles: ['manager'] }
      }
    ]
  }, {
    path: '/personnel',
    component: Layout,
    redirect: '/personnel/addAccount',
    name: 'Personnel',
    meta: { title: 'Personnel', icon: 'el-icon-s-platform', roles: ['manager'] },
    children: [
      {
        path: 'addAccount',
        name: 'AddAccount',
        component: () => import ('@/views/personnel/Register'),
        meta: { title: 'Add account', roles: ['manager'] }
      },
      {
        path: 'manageAccount',
        name: 'ManageAccount',
        component: () => import ('@/views/personnel/ManageAccount'),
        meta: { title: 'Manage Account', roles: ['manager'] }
      },
      {
        path: 'grantPrivilege',
        name: 'GrantPrivilege',
        hidden: true,
        component: () => import ('@/views/personnel/GrantPrivilege'),
        meta: { title: 'Grant Privilege', roles: ['manager'] }
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

const createRouter = () => new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})

const router = createRouter()

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router
