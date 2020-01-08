export default {
  courses: state => state.courses,
  isAuthenticated: state => !!state.token,
  auth: state => state.auth,
  user: state => state.user
}
