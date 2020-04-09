<template>
  <div class="home">
     <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        This family does no exist. Do you want to create a new family?
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
      <span v-if="userinfo.newFamily">
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
      </span>

      <span v-if="!userinfo.newFamily">
        <label for="newFamilyName">Search For Family</label>
      <input
        type="text"
        id="newFamilyName"
        class="form-control"
        placeholder="Search For Family"
        v-model="userinfo.familyName"
        required
        autofocus
      />
      </span>

      <a v-if="userinfo.newFamily" href="#" v-on:click="userinfo.newFamily = false">Search For Family 
      </a>

      <a v-if="!userinfo.newFamily" href="#" v-on:click="userinfo.newFamily = true">Make New Family 
      </a>

      <br>
      <button class="create-account-button" type="submit">
        Submit User Info
      </button>
      <br>
</form>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'userinfo',
data() {
    return {
      userinfo: {
        firstName: '',
        lastName: '',
        familyName: '',
        newFamily: true
      },
      registrationErrors: false
    };
  },
  methods: {
    userregister(){
      axios.post(`${process.env.VUE_APP_REMOTE_API}/userinfo`, this.userinfo)
      .then(response => {
        if(response.status === 200){
          this.$router.push({ path: '/login', query: { registration: 'success' } });
          console.log('200');
        }
        else {
          console.log('else fired')
            this.registrationErrors = true;
            console.log(this.registrationErrors);
         }
      })
      .catch(err => {
        this.registrationErrors = true;
        console.log(err);
      })
    }
  },
};
</script>

<style scoped>

.create-account-button{
  margin: 10px;
  
}


</style>