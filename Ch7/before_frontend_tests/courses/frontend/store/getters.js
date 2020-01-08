import { find } from 'lodash'

export default {
  courses: state => state.courses,
  isAuthenticated: state => !!state.token,
  auth: state => state.auth,
  user: state => state.user,
  course: state => find(state.courses, { id: state.courseId }) || {},
  userCourse: state => state.userCourse,
  userCourses: state => state.courses.filter(course => course.enrolled === true)
}
