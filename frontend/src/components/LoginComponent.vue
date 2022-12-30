<template>
  <div>
    <v-container style="width: 500px; padding-top: 200px;">
      <v-card
          elevation="5"
          outlined
          shaped>
        <v-card-title>Dummy FlightSchedule</v-card-title>
        <v-card-subtitle>Login</v-card-subtitle>
        <v-form
            style="padding: 30px"
            ref="form"
            lazy-validation
        >
          <v-text-field
              v-model="email"
              :rules="emailRules"
              label="E-mail"
              required
          />

          <v-text-field
              v-model="password"
              :rules="passwordRules"
              label="Password"
              required
          />

          <v-row style="justify-content: center; align-self: center">
            <v-btn
                elevation="4"
                outlined
                raised
                @click="onSubmit"
            >
              Submit
            </v-btn>
          </v-row>

        </v-form>
      </v-card>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "LoginComponent",
  data() {
    return {
      valid: false,
      email: '',
      password: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid',
      ],
      passwordRules: [
        v => !!v || 'Password is required',
        v => v.length >= 8 || 'Min 8 characters',
      ],
    }
  },
  methods: {
    onSubmit() {

    }
  }
}
</script>

<style scoped>

</style>