export const SET_COURSES = 'SET_COURSES'
export const SET_USER = 'SET_USER'
export const SET_AUTH = 'SET_AUTH'
export const SET_TOKEN = 'SET_TOKEN'

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
  }
}
