import api from '../api'
import {
  SET_COURSES,
  SET_USER,
  SET_AUTH,
  SET_TOKEN,
  SET_COURSE,
  SET_USER_COURSE,
  SET_ENROLLED
} from './mutations'

export default {
  getCourses: ({ commit }) => {
    return api.fetchCourses().then(response => {
      commit(SET_COURSES, response.data.content)
    })
  },
  getCourse: ({ commit }, data) => {
    return api.fetchCourse(data.id).then(response => {
      commit(SET_COURSE, response.id)
    })
  },
  getSecuredCourse: ({ commit }, data) => {
    return api.fetchCourseForUser(data.id).then(response => {
      commit(SET_USER_COURSE, response.body)
    })
  },
  registerUser: ({ commit, dispatch }, data) => {
    return api.createUser(data).then(response => {
      commit(SET_USER, response.data)
      return dispatch('authenticateUser', data)
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
  logout: ({ commit }) => {
    commit(SET_TOKEN, '')
  },
  getUser: ({ commit }) => {
    return api.getUser().then(response => {
      commit(SET_USER, response.data)
    })
  },
  enroll: ({ commit }, id) => {
    commit(SET_ENROLLED, {courseId: id, enrolled: true})
  },
  unenroll: ({ commit }, id) => {
    commit(SET_ENROLLED, {courseId: id, enrolled: false})
  }
}
