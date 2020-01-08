<template>
  <v-app>
    <v-toolbar fixed app :clipped-left="clipped">
      <v-toolbar-side-icon>logo</v-toolbar-side-icon>
      <v-toolbar-title v-text="title"></v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn v-if="!isAuthenticated" flat router :to="'login'" exact>
        Login
      </v-btn>
      <v-btn v-if="!isAuthenticated" flat router :to="'register'" exact>
        Register
      </v-btn>
    </v-toolbar>
    <v-content>
      <v-container>
        <nuxt />
      </v-container>
    </v-content>
    <v-footer class="pa-4">
      <v-spacer></v-spacer>
      <div>&copy; {{ new Date().getFullYear() }}</div>
    </v-footer>
  </v-app>
</template>

<script>
  import {mapGetters} from 'vuex'
  export default {
    data () {
      return {
        clipped: false,
        drawer: true,
        fixed: false,
        items: [
          { icon: 'apps', title: 'Welcome', to: '/' },
          { icon: 'bubble_chart', title: 'Inspire', to: '/inspire' }
        ],
        miniVariant: false,
        right: true,
        rightDrawer: false,
        title: 'Platform'
      }
    },
    computed: mapGetters(['isAuthenticated'])
  }
</script>
