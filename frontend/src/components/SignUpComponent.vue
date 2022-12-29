<template>
  <div>
    <v-container style="width: 500px; padding-top: 200px;">
      <v-form
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
              color="primary"
              class="mr-4"
              @click="onSubmit"
          >
            Submit
          </v-btn>
        </v-row>
      </v-form>
    </v-container>
  </div>
</template>

<script>
export default {
  name: "SignUpComponent",
  data() {
    return {
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
    onSubmit() {

    }
  }
}
</script>

<style scoped>

</style>