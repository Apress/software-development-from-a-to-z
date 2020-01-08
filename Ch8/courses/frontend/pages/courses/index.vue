<template>
  <v-container>
    <h1>Courses</h1>
    <v-container fluid grid-list-sm>
      <v-layout row wrap>
        <v-flex v-for="course in courses" :key="course.id" xs12 sm6 md4 order-md1 order-sm3>
          <course-item :course="course"></course-item>
        </v-flex>
      </v-layout>
    </v-container>
    <v-container>
      <v-layout row wrap justify-space-between>
      </v-layout>
    </v-container>
  </v-container>
</template>
<script>
  import { mapActions, mapGetters } from 'vuex'
  import CourseItem from '~/components/CourseItem'

  export default {
    computed: mapGetters(['courses']),
    methods: {
      ...mapActions(['getCourses'])
    },
    components: {
      CourseItem
    },
    mounted () {
      if (this.courses.length === 0) {
        this.getCourses()
      }
    }
  }
</script>