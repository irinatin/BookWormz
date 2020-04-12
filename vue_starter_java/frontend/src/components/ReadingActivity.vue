<template>
  <div>
      <h2>
          <button v-on:click="selectUser()">Reading Activity</button>
        </h2>
    <div>
      <select  v-if= "showUsers" id="users" v-model="readingActivity.userId">
      <option v-for="user in users" v-bind:key="user.id" :value="user.id">{{user.username}}</option>
    </select>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {

    showUsers: false,

    readingActivity: {
        userName: '',
        readingTime: 0
    },
    users: []
      
    };
  },

  methods: {
  },

    getReadingActivity() {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getReadingActivity`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.readingActivity = response.data;
          console.log("What's happening?");
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },
    selectUser() {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getUser`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          console.log(response);
          this.users = response.data;
          
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },
  
};
</script>