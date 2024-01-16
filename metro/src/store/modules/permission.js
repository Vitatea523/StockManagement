import { asyncRoutes, constantRoutes } from '@/router'

function hasPermission(roles, route) {
  if (route.meta && route.meta.roles) {
    return roles.some(role => route.meta.roles.indexOf(role) >= 0)
  } else {
    return true
  }
}

const state = {
  routers: constantRoutes,
  addRouters: []
}
const mutations = {
  SET_ROUTERS: (state, routers) => {
    state.addRouters = routers
    state.routers = constantRoutes.concat(routers)
  }
}
const actions = {
  generateRoutes({ commit }, data) {
    return new Promise(resolve => {
      const roles = data
      const accessedRouters = asyncRoutes.filter(v => {
        // if (roles.indexOf('manager') >= 0) return true
        if (hasPermission(roles, v)) {
          if (v.children && v.children.length > 0) {
            v.children = v.children.filter(child => {
              if (hasPermission(roles, child)) {
                return child
              }
              return false
            })
            return v
          } else {
            return v
          }
        }
        return false
      })
      commit('SET_ROUTERS', accessedRouters)
      resolve()
    })
  }
}

export default {
  state,
  mutations,
  actions
}
