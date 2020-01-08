<template>
  <v-layout>
    <v-flex xs12>
      <v-card>
        <v-card-media class="blue-grey lighten-2 white--text">
          <v-container fill-height fluid>
            <v-layout fill-height wrap>
              <v-flex xs12 sm4 md4>
                <v-card-title primary-title>
                  <div class="headline">{{ course.name }}</div>
                </v-card-title>
                <v-card-title>
                  <span>Duration: {{ course.durationHours }} hours</span><br>
                </v-card-title>
                <v-card-title primary-title>
                  <div>{{ course.description }}</div>
                </v-card-title>
              </v-flex>
              <v-flex xs12 sm8 md8 layout justify-end flexbox>
                <iframe type="text/html"
                        :width="videoWidth"
                        :height="videoHeight"
                        :src="videoSource"
                        frameborder="0"
                        showinfo="0"
                        modestbranding="1"
                >
                </iframe>
              </v-flex>
            </v-layout>
          </v-container>
        </v-card-media>
        <v-card-actions>
          <v-btn v-if="course.enrolled" flat color="red" @click="onClickUnenroll">Unenroll</v-btn>
          <v-btn v-if="!isAuthenticated" :to="'/login'" flat color="blue">Login and Enroll</v-btn>
          <v-btn v-if="isAuthenticated && !course.enrolled" flat color="blue" @click="onClickEnroll">Enroll</v-btn>
          <v-spacer></v-spacer>
          <v-btn icon>
            <v-icon>favorite</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon>bookmark</v-icon>
          </v-btn>
          <v-btn icon>
            <v-icon>share</v-icon>
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
  </v-layout>
</template>
<script>
  import { mapActions, mapGetters } from 'vuex'
  export default {
    data () {
      return {
        videoId: 'bfRswQjdFN0'
      }
    },
    computed: {
      videoSource () {
        return 'https://www.youtube.com/embed/' + this.videoId
      },
      videoWidth () {
        switch (this.$vuetify.breakpoint.name) {
          case 'xs': return '100%'
          case 'sm': return '400px'
          case 'md': return '640px'
          case 'lg': return '640px'
        }
      },
      videoHeight () {
        return '360px'
      },
      ...mapGetters(['course'])
    },
    methods: {
      ...mapActions(['getCourse', 'enroll', 'unenroll']),
      onClickUnenroll () {
        this.unenroll(this.course.id)
      },
      onClickEnroll () {
        this.enroll(this.course.id)
      }
    },
    mounted () {
      this.getCourse({id: this.$route.params.id})
    }
  }
</script>
<style>
  youtube {
    width: 100%;
  }
</style>