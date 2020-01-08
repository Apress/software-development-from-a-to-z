<template>
  <div>
    <h1>Courses</h1>
    <div>{{courses}}</div>
  </div>
</template>
<script>
  import {mapActions, mapGetters} from 'vuex'

  export default {
    computed: mapGetters(['courses']),
    methods: {
      ...mapActions(['getCourses'])
    },
    mounted () {
      this.getCourses()
    }
  }
</script>