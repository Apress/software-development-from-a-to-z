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
          ></v-text-field>
          <v-text-field
              v-model="email"
              :rules="emailRules"
              label="E-mail"
              required
          ></v-text-field>
          <v-text-field
              v-model="password"
              :append-icon="e1 ? 'visibility' : 'visibility_off'"
              :append-icon-cb="() => (e1 = !e1)"
              :type="e1 ? 'password' : 'text'"
              name="input-10-1"
              label="Enter your password"
              hint="At least 8 characters"
              min="8"
              counter
          ></v-text-field>
          <v-text-field
              v-model="password"
              :append-icon="e1 ? 'visibility' : 'visibility_off'"
              :append-icon-cb="() => (e1 = !e1)"
              :type="e1 ? 'password' : 'text'"
              name="input-10-1"
              label="Enter your password"
              hint="At least 8 characters"
              min="8"
              counter
          ></v-text-field>
          <v-btn
              @click="submitForm"
              :disabled="!valid"
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
  import {mapActions} from 'vuex'
  export default {
    data: () => ({
      valid: false,
      name: '',
      nameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 10 || 'Name must be less than 10 characters'
      ],
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid'
      ],
      e1: false,
      e2: false,
      e3: false,
      e4: false,
      password: '',
      passwordRules: [
        v => !!v || 'Password required',
        v => /^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$/ || 'Password must be valid'
      ]
    }),
    methods: {
      ...mapActions(['registerUser']),
      submitForm () {
        this.registerUser({username: this.email, password: this.password})
      }
    }
  }
</script>