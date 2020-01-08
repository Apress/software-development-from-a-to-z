import getters from '../../../store/getters'

describe('Vuex Store Getters Tests', () => {
  let courses
  beforeEach(() => {
    courses = [{
      id: 1,
      name: 'Course 1',
      enrolled: true
    }, {
      id: 2,
      name: 'Course 2'
    }, {
      id: 3,
      name: 'Course 3'
    }]
  })
  describe('courses', () => {
    it('should return courses exactly as they are passed', () => {
      expect(getters.courses({courses})).toEqual(courses)
    })
  })
  describe('isAuthenticated', () => {
    it('should return false if token doesn\'t exist', () => {
      expect(getters.isAuthenticated({})).toBeFalsy()
    })
    it('should return true if token exists', () => {
      expect(getters.isAuthenticated({token: 'asd'})).toBeTruthy()
    })
  })
  describe('course', () => {
    it('should return the course item with the passed ID', () => {
      expect(getters.course(
        {courses: courses, courseId: 1}
      )).toEqual(courses[0])
    })
  })
  describe('userCourses', () => {
    it('should return an array of courses which flag enrolled is set to true', () => {
      expect(getters.userCourses({courses: courses})).toEqual([{
        id: 1,
        name: 'Course 1',
        enrolled: true
      }])
    })
  })
})
