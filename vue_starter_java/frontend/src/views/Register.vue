<template>
<body class="has-background-info">
  <div class="columns">
      <div class="column is-half">
        <h1>Welcome to BookWormz: A Family Reading Tracker</h1>
        <p>We help make reading fun for the whole family.</p>
      </div>
      <div class="column">
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        There were problems registering this user.
      </div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <br>
      <button class="create-account-button" type="submit">
        Create Account
      </button>
      <br>
      
      <router-link :to="{ name: 'login' }" class="have-an-account">
        Have an account?
      </router-link>
    </form>
  </div>
  </div>
  </div>
  </body>
</template>

<script>
export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
    };
  },
  methods: {
    register() {
      fetch(`${process.env.VUE_APP_REMOTE_API}/register`, {
        method: 'POST',
        headers: {
          Accept: 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(this.user),
      })
        .then((response) => {
          if (response.ok) {
            console.log('OK');
            this.$router.push({ path: '/userinfo' });
          } else {
            console.log('else fired');
            console.log(response.text());
            this.registrationErrors = true;
          }
        })

        .then((err) => 
        console.error(err));
    },
  },
};
</script>

<style scoped>
h1 {
  font-size: 200%;
}
body {
  /* margin: 0px; */
  padding: 100px 100px;
  padding-bottom: 400px;
  /* background: linear-gradient(to bottom, rgba(102, 255, 102, 0.7), rgba(0, 128, 0, 0.25)); */
  background-size: cover;
  background-position: center;
  font-family: sans-serif;
  height: 100%;
}

.form-register{
  width: 70%;
  height: auto;
  background: rgba(0, 0, 0, 0.55);
  color: #fff;
  /* top: 50%;
  left: 50%; */
  
  /* transform: translate(-50%, -50%); */
  box-sizing: border-box;
  box-shadow: 0px 0px 8px rgba(0,0,0,0.9);
  padding-left: 20%;
  padding-top: 20%;
  padding-right: 15%;
  padding-bottom: 20%;
}
.form-control{
  margin: 10px;
}
.have-an-account{
  margin: 10px;
}
.create-account-button{
  margin: 10px;
  
}
#confirm-password{
  left: 50%;
}
</style>