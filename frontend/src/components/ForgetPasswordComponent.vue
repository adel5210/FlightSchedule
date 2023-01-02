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
      >{{ errorMsg }}
      </v-alert>
      <v-card
          v-if="isStage1"
          elevation="5"
          outlined
          shaped>
        <v-card-title>Dummy FlightSchedule</v-card-title>
        <v-card-subtitle>Forget password</v-card-subtitle>
        <v-card-text>
          Provide an email address to perform a password reset
        </v-card-text>
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
        <v-card-subtitle>Forget password</v-card-subtitle>
        <v-card-text>
          Enter one-time-password sent from you email address
        </v-card-text>
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
            >Re-send OTP
            </v-btn>
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
      <v-card
          v-if="isStage3"
          elevation="5"
          outlined
          shaped>
        <v-card-title>Dummy FlightSchedule</v-card-title>
        <v-card-subtitle>Forget password</v-card-subtitle>
        <v-card-text>
          Enter your new password
        </v-card-text>
        <v-form
            style="padding: 30px"
            ref="form"
            lazy-validation
        >
          <v-row>
            <v-text-field
                v-model="password"
                :rules="passwordRules"
                label="New Password"
                type="password"
                required
            />
          </v-row>
          <v-row>
            <v-text-field
                v-model="password2"
                :rules="passwordRules"
                label="Confirm password"
                type="password"
                required
            />
          </v-row>
          <v-row style="justify-content: center; align-self: center">
            <v-btn
                elevation="4"
                outlined
                raised
                :loading="stage3SubmitLoading"
                @click="onSubmitStage3"
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
import UserProfileDto from "@/model/userProfileDto";
import userProfileHttp from "@/api/userProfileHttp";

export default {
  name: "ForgetPasswordComponent",
  data() {
    return {
      isStage1: true,
      isStage2: false,
      isStage3: false,
      errorMsg: '',
      isErrorMsg: false,
      email: '',
      otp: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid',
      ],
      stage1SubmitLoading: false,
      stage2SubmitLoading: false,
      stage3SubmitLoading: false,
      resendOtpLoading: false,
      password: '',
      password2: '',
      passwordRules: [
        v => !!v || 'Password is required',
        v => v.length >= 8 || 'Min 8 characters',
        v => v === this.password || 'Mismatch password'
      ],
    }
  },
  methods: {
    onSubmitStage1() {
      this.$refs.form.validate();
      this.stage1SubmitLoading = true;
      this.isErrorMsg = false;
      let submitData = new UserProfileDto();
      submitData.email = this.email;
      userProfileHttp.resendOtp(submitData).then(res => {
        console.log(res);
        this.isStage1 = false;
        this.isStage2 = true;
        this.$refs.form.resetValidation()
      })
          .catch(err => {
            console.error(err);
            this.errorMsg = err.response.data.message;
            this.isErrorMsg = true;
          }).finally(() => {
        this.stage1SubmitLoading = false;
      });
    },
    onResendOtp() {
      this.resendOtpLoading = true;
      this.isErrorMsg = false;
      let submitData = new UserProfileDto();
      submitData.email = this.email;
      userProfileHttp.resendOtp(submitData).then(res => {
        console.log(res);
        this.$refs.form.resetValidation()
      })
          .catch(err => {
            console.error(err);
            this.errorMsg = err.response.data.message;
            this.isErrorMsg = true;
          }).finally(() => {
        this.resendOtpLoading = false;

      });
    },
    onSubmitStage2() {
      this.$refs.form.validate();
      this.isErrorMsg = false;
      this.stage2SubmitLoading = true;
      let submitData = new UserProfileDto();
      submitData.email = this.email;
      submitData.otp = this.otp;

      userProfileHttp.validateOtp(submitData).then(res => {
        console.log(res);
        this.isStage2 = false;
        this.isStage3 = true;
        this.$refs.form.resetValidation()
      })
          .catch(err => {
            console.error(err);
            this.errorMsg = err.response.data.message;
            this.isErrorMsg = true;
          }).finally(() => {
        this.stage2SubmitLoading = false;
      });
    },
    onSubmitStage3() {
      this.$refs.form.validate();
      this.isErrorMsg = false;
      this.stage3SubmitLoading = true;
      let submitData = new UserProfileDto();
      submitData.email = this.email;
      submitData.newPassword = this.password;

      userProfileHttp.resetPassword(submitData).then(res => {
        console.log(res);
        this.$router.push('login');
      })
          .catch(err => {
            console.error(err);
            this.errorMsg = err.response.data.message;
            this.isErrorMsg = true;
          }).finally(() => {
        this.stage3SubmitLoading = false;
      });
    },
    limitOtp() {
      this.otp = /^\d+$/.test(this.otp) ? this.otp : '';
      this.otp = this.otp.length > 5 ? this.otp.substring(0, 5) : this.otp;
    },
  }
}
</script>

<style scoped>

</style>