<template>
  <div>

    <v-container style="width: 500px; padding-top: 200px;">
      <v-alert
          color="red"
          dense
          dismissible
          outlined
          type="error"
          cl
          v-model="isErrorMsg"
      >{{errorMsg}}</v-alert>
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
              label="Username/Email"
              required
          />

          <v-text-field
              v-model="user.password"
              :rules="passwordRules"
              label="Password"
              type="password"
              required
          />
          <v-row  style="justify-content: center; align-self: center; padding-top: 5px; padding-bottom: 5px" >
            <v-btn
                small
                text
                @click="onSignUp"
            >No account yet? Create one now</v-btn>
          </v-row>
          <v-row  style="justify-content: center; align-self: center; padding-top: 5px; padding-bottom: 5px" >
            <v-btn
                small
                text
                @click="onForgetPassword"
            >Forget Password?</v-btn>
          </v-row>

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
      errorMsg:'',
      isErrorMsg: false
    }
  },
  methods: {
    onSubmit() {
      if(!this.$refs.form.validate()){
        return;
      }
      this.isErrorMsg = false;
      this.loginSubmitLoading = true;
      if(this.user.username && this.user.password){
        this.$store.dispatch('login', this.user)
        .then(() => {
          this.$router.push('dashboard');
        }, err => {
          console.error(err);
          this.errorMsg = err.response.data.message;
          this.isErrorMsg = true;
        }).finally(() => {
          this.loginSubmitLoading = false;
        })
      }
    },
    onForgetPassword(){
      this.$router.replace('forget-password');
    },
    onSignUp(){
      this.$router.replace('signup');
    }
  },
  computed:{
    loggedIn(){
      return this.$store.state.status.loggedIn;
    }
  },
  beforeMount() {
    if(this.loggedIn){
      this.$router.replace('dashboard');
    }
  }
}
</script>

<style scoped>

</style>