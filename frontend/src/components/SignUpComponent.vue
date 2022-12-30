<template>
  <div>
    <v-container style="width: 500px; padding-top: 200px;">
      <v-card
          v-if="isStage1"
          elevation="5"
          outlined
          shaped>
        <v-card-title>Dummy FlightSchedule</v-card-title>
        <v-card-subtitle>Sign-Up</v-card-subtitle>
        <v-form
            style="padding: 30px"
            ref="form"
            lazy-validation
        >
          <v-text-field
              v-model="firstname"
              :rules="nameRules"
              label="First Name"
              required
          />
          <v-text-field
              v-model="lastname"
              :rules="nameRules"
              label="Last Name"
              required
          />

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

          <v-text-field
              v-model="password2"
              :rules="passwordRules"
              label="Confirm password"
              required
          />

          <v-row style="justify-content: center; align-self: center">
            <v-btn
                elevation="4"
                outlined
                raised
                @click="onSubmitStage1"
            >
              Submit
            </v-btn>
          </v-row>
        </v-form>
      </v-card>
      <v-card
          v-if="isStage2"
          elevation="5"
          outlined
          shaped>
        <v-card-title>Dummy FlightSchedule</v-card-title>
        <v-card-subtitle>Enter One-time password</v-card-subtitle>
        <v-card-text>We've sent a ont-time password to your email address at {{ encryptEmail }}</v-card-text>
        <v-form
            style="padding: 30px"
            ref="form"
            lazy-validation
        >
          <v-row style="justify-content: center; align-self: center">
            <v-btn
                elevation="4"
                outlined
                raised
                @click="onSubmitStage2"
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
import userProfileHttp from "@/api/userProfileHttp";

export default {
  name: "SignUpComponent",
  data() {
    return {
      isStage1: true,
      isStage2: false,
      valid: false,
      firstname: '',
      lastname: '',
      email: '',
      password: '',
      password2: '',
      nameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 10 || 'Name must be less than 10 characters',
      ],
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
    onSubmitStage1() {
      userProfileHttp.signupSubmit().then(res => {
        console.log(res);
      })
          .catch(err => {
            console.error(err);
          });
    },
    onSubmitStage2() {

    },

  },
  computed: {
    encryptEmail() {
      let tempEmail = ''
      let hasPassedAbbreviate = false;
      for (let i = 0; i < this.email.length; i++) {
        if (this.email[i] === '@') {
          hasPassedAbbreviate = true;
        }
        if (i === 0 || hasPassedAbbreviate) {
          tempEmail += this.email[i];
        } else {
          tempEmail += '*';
        }
      }
      return tempEmail;
    }
  }
}
</script>

<style scoped>

</style>