<template>
  <v-flex xs12>
    <v-card color="blue-grey darken-2" class="white--text">
      <v-card-title primary-title>
        <div class="headline">{{ course.name }}</div>
      </v-card-title>
      <v-card-title>
        <div>{{ course.description }}</div>
      </v-card-title>
      <v-card-actions>
        <v-btn flat dark :to="goTo">Go To Course</v-btn>
      </v-card-actions>
    </v-card>
  </v-flex>
</template>
<script>
  export default {
    props: ['course'],
    data () {
      return {
        goTo: '/dashboard/' + this.course.id
      }
    }
  }
</script>