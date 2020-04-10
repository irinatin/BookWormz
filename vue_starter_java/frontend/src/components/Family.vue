<template>
  <div>
    <div></div>
    <form class="form-register" @submit.prevent="registerChild">
      <div>{{family[0].familyName}}
        
        <a v-if="!addChildForm" href="#" v-on:click="showForm">Add Child</a>
        <a v-if="addChildForm" href="#" v-on:click="hideForm">Hide Form</a>
      </div>

      <span v-if="addChildForm">
        <label for="username">Username</label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Child Username"
          v-model="child.username"
          required
          autofocus
        />
      </span>

      <span v-if="addChildForm">
        <label for="password">Password</label>
        <input
          type="text"
          id="password"
          class="form-control"
          placeholder="Child Password"
          v-model="child.password"
          required
          autofocus
        />
      </span>

      <span v-if="addChildForm">
        <label for="firstName">First Name</label>
        <input
          type="text"
          id="firstName"
          class="form-control"
          placeholder="First Name"
          v-model="child.firstName"
          required
          autofocus
        />
      </span>

      <span v-if="addChildForm">
        <label for="lastName">Last Name</label>
        <input
          type="text"
          id="lastName"
          class="form-control"
          placeholder="Last Name"
          v-model="child.lastName"
          required
          autofocus
        />
      </span>

      <br />
      <button v-if="addChildForm" class="create-account-button" type="submit">Submit Child Info</button>
      <br />
    </form>
    <div v-if="!addChildForm">
      <div v-for="user in family" v-bind:key="user.firstName">{{user.firstName}} {{user.lastName}}</div>
    </div>
  </div>
</template>



<script>
import axios from "axios";

export default {
  props: {
    apiURL: String
  },
  data() {
    return {
      
      child: {
        username: "",
        password: "",
        firstName: "",
        lastName: ""
      },
      family: {},

      addChildForm: false,
    };
  },

  methods: {
    showForm() {
      this.addChildForm = true;
    },
    hideForm() {
      this.addChildForm = false;
    },
    registerChild() {
      axios
        .post(`${process.env.VUE_APP_REMOTE_API}/api/addChild`, this.child, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          console.log(response);
        })
        .catch(err => {
          this.registrationErrors = true;
          console.log(err);
        });
    }
  },
  created() {
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getFamilyList`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.family = response.data;
        console.log(this.family);
      })
      .catch(error => {
        console.log(error + " there was an error");
      });
  }
};
</script>



<style>
</style>
