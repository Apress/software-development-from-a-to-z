import Vue from 'vue'
import VueResource from 'vue-resource'

Vue.use(VueResource)

const PublicCoursesResource = Vue.resource(`/api/v1/public/courses{/id}`)
const UsersResource = Vue.resource(`/api/v1/public/users{/id}`)
// const UserResource = Vue.resource(`/api/v1/secured/users/me`)

Vue.http.interceptors.push((request) => {
  if (request.url.indexOf('me') === -1) {
    return
  }
  let storage = localStorage.getItem('vuex')
  storage = storage || ''
  storage = JSON.parse(storage)
  if (storage && storage.token) {
    console.log(storage.token)
    request.headers.set('Authorization', `Bearer ${storage.token}`)
  }
})

export default {
  fetchCourses: () => {
    return PublicCoursesResource.get()
  },
  createUser: (data) => {
    return UsersResource.save(data)
  },
  authenticateUser: (data) => {
    var {username, password} = data
    return Vue.http.post(
      '/oauth/token',
      `grant_type=password&username=${username}&password=${password}`,
      {headers: {'Content-type': 'application/x-www-form-urlencoded'}})
  },
  getUser: () => {
    return Vue.http.get(`/api/v1/secured/users/me`, {headers: {'Content-type': 'application/json'}})
  }
}
