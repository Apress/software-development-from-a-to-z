<template>
  <v-container>
    <v-layout row wrap align-center>
      <v-flex xs12 sm6 text-xs-center>
        <v-form v-model="valid" @submit="submitForm">
          <v-text-field
              v-model="name"
              :rules="nameRules"
              :counter="10"
              label="Name"
              required
              autocomplete="name"
          ></v-text-field>
          <v-text-field
              v-model="email"
              :rules="emailRules"
              label="E-mail"
              required
              autocomplete="email"
          ></v-text-field>
          <v-text-field
              v-model="password"
              :append-icon="e1 ? 'visibility' : 'visibility_off'"
              :append-icon-cb="() => (e1 = !e1)"
              :type="e1 ? 'password' : 'text'"
              :rules="passwordRules"
              name="input-10-1"
              label="Enter your password"
              hint="At least 8 characters"
              min="8"
              counter
              autocomplete="password"
          ></v-text-field>
          <v-btn
              @click="submitForm"
              :disabled="!valid"
              right
          >Join us!</v-btn>
        </v-form>
      </v-flex>
      <v-flex xs12 sm6 text-xs-center>
        <div class="subheading">Already have an account?</div>
        <v-btn router :to="'login'" exact>
          Login
        </v-btn>
      </v-flex>
    </v-layout>
  </v-container>
</template>
<script>
  import { mapActions, mapGetters } from 'vuex'
  export default {
    middleware: 'notauthenticated',
    data: () => ({
      valid: false,
      name: '',
      nameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 32 || 'Name must be less than 32 characters'
      ],
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid'
      ],
      e1: true,
      password: '',
      passwordRules: [
        v => !!v || 'Password required',
        v => /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/.test(v) || 'Password must have at least 8 characters, at least one symbol and at least one number'
      ]
    }),
    computed: mapGetters(['isAuthenticated']),
    methods: {
      ...mapActions(['registerUser', 'isAuthenticated']),
      submitForm () {
        this.registerUser({name: this.name, username: this.email, password: this.password})
      }
    },
    watch: {
      isAuthenticated: function (val) {
        if (val) {
          this.$router.push('/dashboard')
        }
      }
    }
  }
</script>