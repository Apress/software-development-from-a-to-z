import { find } from 'lodash'
export const SET_COURSES = 'SET_COURSES'
export const SET_USER = 'SET_USER'
export const SET_AUTH = 'SET_AUTH'
export const SET_TOKEN = 'SET_TOKEN'
export const SET_COURSE = 'SET_COURSE'
export const SET_USER_COURSE = 'SET_USER_COURSE'
export const SET_USER_COURSES = 'SET_USER_COURSES'
export const SET_ENROLLED = 'SET_ENROLLED'

export default {
  [SET_COURSES] (state, courses) {
    state.courses = courses
  },
  [SET_USER] (state, user) {
    state.user = user
  },
  [SET_AUTH] (state, auth) {
    state.auth = auth
  },
  [SET_TOKEN] (state, token) {
    state.token = token
  },
  [SET_COURSE] (state, courseId) {
    state.courseId = courseId
  },
  [SET_USER_COURSE] (state, course) {
    state.userCourse = course
  },
  [SET_USER_COURSES] (state, courses) {
    state.userCourses = courses
  },
  [SET_ENROLLED] (state, data) {
    let { courseId, enrolled } = data
    find(state.courses, {id: courseId}).enrolled = enrolled
  }
}
