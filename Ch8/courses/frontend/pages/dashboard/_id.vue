<template>
  <v-container>
    <v-navigation-drawer fixed hide-overlay app :mini-variant.sync="mini">
      <v-toolbar flat class="transparent">
        <v-list>
          <v-list-tile>
            <v-list-tile-title class="title">
              {{ userCourse.name }}
            </v-list-tile-title>
          </v-list-tile>
        </v-list>
        <v-list-tile-action>
          <v-btn icon @click.native.stop="mini = !mini">
            <v-icon>chevron_left</v-icon>
          </v-btn>
        </v-list-tile-action>
      </v-toolbar>
      <v-divider></v-divider>
      <v-list>
        <course-module
            v-for="module in userCourse.modules"
            :key="module.id"
            :name="module.name"
            :description="module.description"
            :durationMinutes="module.durationMinutes"
            :id="module.id"
            :lectures="module.lectures"
        >
        </course-module>
      </v-list>
    </v-navigation-drawer>
    <span class="subheading">{{ userCourse.modules[0].lectures[0].description }}</span>
    <v-divider class="my-3"></v-divider>
    <iframe type="text/html"
            :width="videoWidth"
            :height="videoHeight"
            :src="'https://www.youtube.com/embed/C0DPdy98e4c'"
            frameborder="0"
            showinfo="0"
            modestbranding="1"
    >
    </iframe>
    <v-divider></v-divider>
    <h4>Test</h4>
    <div v-for="(question, i) in userCourse.modules[0].lectures[0].questionAnswers" :key="question.id">
      <p>{{ question.question }}</p>
      <v-radio-group v-model="answers[i]" column :mandatory="false">
        <v-radio v-for="(answer, index) in question.answer" :key="index">
          :label="answer"
          :value="answer"
        </v-radio>
      </v-radio-group>
    </div>
  </v-container>
</template>
<script>
  //  var course = {
  //    "id": 1,
  //    "name": "Test Dev",
  //    "description": "Course to test during development",
  //    "durationHours": 36,
  //    "active": false,
  //    "modules": [{
  //      "id": 1,
  //      "courseId": 1,
  //      "moduleId": 1,
  //      "name": "Test Module",
  //      "description": "A module to test the development",
  //      "durationMinutes": 180,
  //      "number": 1,
  //      "startTime": null,
  //      "finishTime": null,
  //      "lectures": [{
  //        "id": 1,
  //        "name": "Test Lecture",
  //        "description": "A lecture for development",
  //        "durationMinutes": null,
  //        "mediaSource": "https://www.youtube.com/watch?v=C0DPdy98e4c",
  //        "number": 1,
  //        "questionAnswers": [{
  //          "id": 1,
  //          "question": "How many times can we hear the beep sound?",
  //          "answer": [1, 2, 3, 4],
  //          "lectureId": 1
  //        }, {
  //          "id": 2,
  //          "question": "From where does the count down start?",
  //          "answer": [3, 10, 15, 5],
  //          "lectureId": 1
  //        }, {
  //          "id": 3,
  //          "question": "What letters appear during the count down?",
  //          "answer": ["A and B", "C and F", "D and F", "B and F"],
  //          "lectureId": 1
  //        }]
  //      }]
  //    }]
  //  }
  import { mapActions, mapGetters } from 'vuex'
  import CourseModule from '~/components/CourseModule'
  export default {
    data () {
      return {
        mini: false,
        radio: 1,
        radios: 'radio-1'
      }
    },
    computed: {
      ...mapGetters(['userCourse']),
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
      answers () {
        return [
          this.userCourse.modules[0].lectures[0].questionAnswers[0].answer[0],
          this.userCourse.modules[0].lectures[0].questionAnswers[1].answer[0],
          this.userCourse.modules[0].lectures[0].questionAnswers[2].answer[0]
        ]
      }
    },
    methods: {
      ...mapActions(['getSecuredCourse'])
    },
    components: {
      CourseModule
    },
    mounted () {
      this.getSecuredCourse({id: this.$route.params.id})
    }
  }
</script>
