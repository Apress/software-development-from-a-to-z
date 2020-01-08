<template>
  <v-list-group prepend-icon="folder" value="true">
    <v-list-tile slot="activator">
      <v-list-tile-title>{{name}}</v-list-tile-title>
    </v-list-tile>
    <v-list-group prepend-icon="create" sub-group no-action value="true">
      <v-list-tile slot="activator">
        <v-list-tile-title>Lectures</v-list-tile-title>
      </v-list-tile>
      <v-list-group v-for="lecture in lectures" :key="lecture.id" sub-group>
        <v-list-tile slot="activator">
          <v-list-tile-title>{{lecture.name}}</v-list-tile-title>
        </v-list-tile>
        <v-list-tile>
          <v-list-tile-title>
            Media
          </v-list-tile-title>
          <v-list-tile-action>
            <v-icon>perm_media</v-icon>
          </v-list-tile-action>
        </v-list-tile>
        <v-list-tile>
          <v-list-tile-title>
            Test
          </v-list-tile-title>
          <v-list-tile-action>
            <v-icon>playlist_add</v-icon>
          </v-list-tile-action>
        </v-list-tile>
      </v-list-group>
    </v-list-group>
    <v-list-group sub-group prepend-icon="check_box">
      <v-list-tile slot="activator">
        <v-list-tile-title>Quiz</v-list-tile-title>
      </v-list-tile>
    </v-list-group>
  </v-list-group>
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
  export default {
    props: ['lectures', 'name', 'description', 'id', 'durationMinutes']
  }
</script>