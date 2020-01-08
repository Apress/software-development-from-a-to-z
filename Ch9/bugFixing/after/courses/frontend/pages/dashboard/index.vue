<template>
  <v-container>
    <h1>Dashboard - my courses</h1>
    <v-card>
      <v-container
          fluid
          style="min-height: 0;"
          grid-list-lg
      >
        <v-layout row wrap>
          <user-course-item v-for="course in userCourses" :id="course.id" :course="course"></user-course-item>
        </v-layout>
      </v-container>
    </v-card>
  </v-container>
</template>
<script>
  import { mapGetters } from 'vuex'
  import UserCourseItem from '~/components/UserCourseItem'

  export default {
    computed: mapGetters(['isAuthenticated', 'userCourses']),
    middleware: 'authenticated',
    components: {
      UserCourseItem
    },
    watch: {
      isAuthenticated: function (val) {
        if (!val) {
          this.$router.push('/')
        }
      }
    }
  }
</script>