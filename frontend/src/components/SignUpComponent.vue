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
              v-model="username"
              :rules="nameRules"
              label="Username"
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
              type="password"
              required
          />

          <v-text-field
              v-model="password2"
              :rules="passwordRules"
              label="Confirm password"
              type="password"
              required
          />
          <v-row  style="justify-content: center; align-self: center; padding-top: 5px; padding-bottom: 5px" >
            <v-btn
                small
                text
                @click="onSignIn"
            >Already have an account? Sign in</v-btn>
          </v-row>
          <v-row style="justify-content: center; align-self: center">
            <v-btn
                elevation="4"
                outlined
                raised
                :loading="stage1SubmitLoading"
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
        <v-card-text>We've sent a one-time password to your email address at {{ encryptEmail }}</v-card-text>
        <v-form
            style="padding: 30px"
            ref="form"
            lazy-validation
        >
          <v-row>
            <v-text-field
                style="padding-left: 5px; padding-right: 5px;text-align: center; width: 64px;"
                required
                @keydown="limitOtp"
                v-model="otp"/>
            <v-btn
                :loading="resendOtpLoading"
                outlined
                raised
                small
                right
                @click="onResendOtp"
            >Re-send OTP</v-btn>
          </v-row>
          <v-row style="justify-content: center; align-self: center">
            <v-btn
                elevation="4"
                outlined
                raised
                :loading="stage2SubmitLoading"
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
import UserProfileDto from "@/model/userProfileDto";

export default {
  name: "SignUpComponent",
  data() {
    return {
      isStage1: true,
      isStage2: false,
      valid: false,
      firstname: '',
      lastname: '',
      username: '',
      email: '',
      password: '',
      password2: '',
      otp: '',
      resendOtpLoading: false,
      stage1SubmitLoading: false,
      stage2SubmitLoading: false,
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
        v => v === this.password || 'Mismatch password'
      ],
      numberRules: [],
      errorMsg:'',
      isErrorMsg: false
    }
  },
  methods: {
    onSubmitStage1() {
      if(!this.$refs.form.validate()){
        return;
      }
      this.isErrorMsg = false;
      this.stage1SubmitLoading = true;
      let submitData = new UserProfileDto();
      submitData.firstName = this.firstname;
      submitData.lastName = this.lastname;
      submitData.email = this.email;
      submitData.username = this.username;
      submitData.password = this.password;

      userProfileHttp.signup(submitData).then(res => {
        console.log(res);
        this.isStage1 = false;
        this.isStage2 = true;
      })
      .catch(err => {
        console.log(err);
        this.errorMsg = err.response.data.message;
        this.isErrorMsg = true;
      }).finally(() => {
        this.stage1SubmitLoading = false;
      });
    },
    onSubmitStage2() {
      if(!this.$refs.form.validate()){
        return;
      }
      this.isErrorMsg = false;
      this.stage2SubmitLoading = true;
      let submitData = new UserProfileDto();
      submitData.username = this.username;
      submitData.otp = this.otp;

      userProfileHttp.validateOtp(submitData).then(res => {
        console.log(res);
        this.$router.push('login');
      })
      .catch(err => {
        console.error(err);
        this.errorMsg = err.response.data.message;
        this.isErrorMsg = true;
      }).finally(() => {
        this.stage2SubmitLoading = false;
      });
    },
    onResendOtp(){
      this.isErrorMsg = false;
      this.resendOtpLoading = true;
      let submitData = new UserProfileDto();
      submitData.username = this.username;
      userProfileHttp.resendOtp(submitData).then(res => {
        console.log(res);
      })
      .catch(err => {
        console.error(err);
        this.errorMsg = err.response.data.message;
        this.isErrorMsg = true;
      }).finally(()=>{
        this.resendOtpLoading = false;
      });
    },
    limitOtp() {
      this.otp = /^\d+$/.test(this.otp) ? this.otp : '';
      this.otp = this.otp.length > 5 ? this.otp.substring(0, 5) : this.otp;
    },
    onSignIn(){
      this.$router.replace('login');
    }
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
    },
    loggedIn(){
      return this.$store.state.status.loggedIn;
    }
  },
  created() {
    if(this.loggedIn){
      this.$router.replace('dashboard');
    }
  }
}
</script>

<style scoped>

</style>