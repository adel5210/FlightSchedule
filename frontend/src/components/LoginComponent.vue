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
              v-model="user.username"
              :rules="usernameRules"
              label="Username"
              required
          />

          <v-text-field
              v-model="user.password"
              :rules="passwordRules"
              label="Password"
              type="password"
              required
          />

          <v-row style="justify-content: center; align-self: center">
            <v-btn
                elevation="4"
                outlined
                raised
                :loading="loginSubmitLoading"
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
import User from "@/model/User";

export default {
  name: "LoginComponent",
  data() {
    return {
      valid: false,
      loginSubmitLoading: false,
      user: new User('',''),
      usernameRules: [
        v => !!v || 'Username is required'
      ],
      passwordRules: [
        v => !!v || 'Password is required',
        v => v.length >= 1 || 'Min 8 characters',
      ],
    }
  },
  methods: {
    onSubmit() {
      this.loginSubmitLoading = true;
      if(this.user.username && this.user.password){
        this.$store.dispatch('login', this.user)
        .then(() => {
          this.$router.push('dashboard');
        }, err => {
          console.error(err);
        }).finally(() => {
          this.loginSubmitLoading = false;
        })
      }
    }
  },
  computed:{
    loggedIn(){
      return this.$store.state.status.loggedIn;
    }
  },
  created() {
    if(this.loggedIn){
      this.$router.push('dashboard');
    }
  }
}
</script>

<style scoped>

</style>