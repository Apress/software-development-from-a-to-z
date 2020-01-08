<template>
  <v-app>
    <v-toolbar fixed app :clipped-left="clipped">
      <v-toolbar-side-icon router :to="'/'"><v-icon>home</v-icon></v-toolbar-side-icon>
      <v-toolbar-title v-text="title">
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items v-if="!isAuthenticated">
        <v-btn  flat router :to="'/login'" exact>
          Login
        </v-btn>
        <v-btn flat router :to="'/register'" exact>
          Register
        </v-btn>
      </v-toolbar-items>
      <v-toolbar-items v-if="isAuthenticated">
        <v-btn active-class flat :to="'/dashboard'" exact>
          <v-avatar color="grey">
            <span class="white--text headline">{{ user && user.name && user.name.charAt(0) }}</span>
          </v-avatar>&nbsp;
          Hello {{user.name}}
        </v-btn>
        <v-btn  flat @click="logout" router :to="'/login'" exact>Logout</v-btn>
      </v-toolbar-items>
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
  import {mapGetters, mapActions} from 'vuex'
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
    computed: mapGetters(['isAuthenticated', 'user']),
    methods: mapActions(['logout'])
  }
</script>
