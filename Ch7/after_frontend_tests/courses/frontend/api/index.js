import Vue from 'vue'
import VueResource from 'vue-resource'
import courses from './fakeCourses.json'

Vue.use(VueResource)

const PublicCoursesResource = Vue.resource(`/api/v1/public/courses{/id}`)
const PrivateCoursesResource = Vue.resource(`/api/v1/secured/courses{/id}?include=modules`)
const UsersResource = Vue.resource(`/api/v1/public/users{/id}`)

Vue.http.interceptors.push((request) => {
  let storage = localStorage.getItem('vuex')
  storage = storage || ''
  storage = JSON.parse(storage)
  // TODO introduce refresh token
  if (storage && storage.token) {
    request.headers.set('Authorization', `Bearer ${storage.token}`)
  }
})

export default {
  // TODO replace with not fake thing after Backend is done
  fetchCourses: () => {
    return PublicCoursesResource.get().then(() => {
      return courses
    })
  },
  fetchCourse: (id) => {
    return PublicCoursesResource.get({id}).then(() => {
      return courses.data.content[parseInt(id) - 1]
    }).catch(err => {
      console.log(err)
      return courses.data.content[parseInt(id) - 1]
    })
  },
  enrollToCourse: (courseId) => {
    return Vue.http.post('/api/v1/secured/usercourses', {courseId})
  },
  fetchCourseForUser: (id) => {
    // TODO replace with id when the server is ready to have more than 1 course
    return PrivateCoursesResource.get({id: 1})
  },
  createUser: (data) => {
    return UsersResource.save(data)
  },
  authenticateUser: (data) => {
    var {username, password} = data
    return Vue.http.post(
      '/oauth/token',
      `grant_type=password&username=${username}&password=${password}`,
      {headers: {
        'Content-type': 'application/x-www-form-urlencoded',
        'Authorization': 'Basic d2ViYXBwOnRlc3Q='
      }})
  },
  getUser: () => {
    return Vue.http.get(`/api/v1/secured/users/me`, {headers: {'Content-type': 'application/json'}})
  }
}
