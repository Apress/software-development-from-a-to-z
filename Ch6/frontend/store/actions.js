import api from '../api'
import {SET_COURSES, SET_USER, SET_AUTH, SET_TOKEN} from './mutations'

export default {
  getCourses: ({ commit }) => {
    return api.fetchCourses().then(response => {
      commit(SET_COURSES, response.data.content)
    })
  },
  registerUser: ({ commit }, data) => {
    return api.createUser(data).then(response => {
      commit(SET_USER, response.data)
    }).catch(err => {
      console.log(err)
    })
  },
  authenticateUser: ({ commit, dispatch }, data) => {
    return api.authenticateUser(data).then(response => {
      commit(SET_AUTH, response.data)
      commit(SET_TOKEN, response.data.access_token)
      dispatch('getUser')
    })
  },
  getUser: ({ commit }) => {
    return api.getUser().then(response => {
      commit(SET_USER, response.data)
    })
  }
}
