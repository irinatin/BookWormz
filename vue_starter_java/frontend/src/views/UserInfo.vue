<template>
  <div class="home">
     <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        There were problems registering this user.
      </div>

<form class="form-register" @submit.prevent="userregister">
     <label for="firstName">First Name</label>
      <input
        type="text"
        id="firstName"
        class="form-control"
        placeholder="First Name"
        v-model="userinfo.firstName"
        required
        autofocus
      />

      <label for="lastName">Last Name</label>
      <input
        type="text"
        id="lastName"
        class="form-control"
        placeholder="Last Name"
        v-model="userinfo.lastName"
        required
        autofocus
      />

      <label for="familyName">Family Name</label>
      <input
        type="text"
        id="familyName"
        class="form-control"
        placeholder="Family Name"
        v-model="userinfo.familyName"
        required
        autofocus
      />

      <br>
      <button class="create-account-button" type="submit">
        Submit User Info
      </button>
      <br>
</form>

  </div>
</template>

<script>
export default {
  name: 'userinfo',
data() {
    return {
      userinfo: {
        firstName: '',
        lastName: '',
        familyName: ''
      },
      registrationErrors: false,
    };
  },
  methods: {
    userregister() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/userinfo`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.userinfo),
      })
        .then((response) => {
          if (response.ok) {
            this.$router.push('/');
          } else {
            this.registrationErrors = true;
          }
        })

        .then((err) => console.error(err));
    },
  },
};
</script>

<style scoped>

.create-account-button{
  margin: 10px;
  
}


</style>