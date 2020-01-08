<template>
  <v-layout column justify-center align-center>
    <v-spacer></v-spacer>
    <v-btn color="primary" outline block nuxt :to="'/courses'">Check existing courses</v-btn>
    <v-btn color="primary" outline block nuxt :to="goTo">Go to my courses</v-btn>
  </v-layout>
</template>
<script>
  import { mapGetters } from 'vuex'
  export default {
    computed: {
      ...mapGetters(['isAuthenticated']),
      goTo () {
        return this.isAuthenticated ? '/dashboard' : '/login'
      }
    }
  }
</script>