<template>
  <v-container>
    <v-card :to="goTo" hover>
      <v-card-media
          class="white--text"
          width="100%"
          src="http://via.placeholder.com/350x150?text=..."
      >
        <v-container fill-height fluid>
          <v-layout fill-height>
            <v-flex xs12 align-end flexbox>
              <span class="headline">{{ course.name }}</span>
            </v-flex>
          </v-layout>
        </v-container>
      </v-card-media>
      <v-card-title>
        <div>
          <span class="grey--text">Duration: {{ course.durationHours }} hours</span><br>
          <span>{{ course.description }}</span>
        </div>
      </v-card-title>
      <v-card-actions>
        <v-btn flat color="grey" :to="goTo">Explore</v-btn>
        <v-btn v-if="!isAuthenticated" :to="'/login'" flat color="grey">Enroll</v-btn>
        <v-btn v-if="isAuthenticated && !course.enrolled" flat color="grey" @click="onClickEnroll">Enroll</v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>
<script>
  import { mapActions, mapGetters } from 'vuex'
  export default {
    props: ['course'],
    computed: {
      ...mapGetters(['isAuthenticated']),
      goTo () {
        return '/courses/' + this.course.id
      }
    },
    methods: {
      ...mapActions(['enroll']),
      onClickEnroll (ev) {
        ev.stopPropagation()
        ev.preventDefault()
        this.enroll(this.course.id)
      }
    }
  }
</script>