<template>
  <div>
    <div>
      <h2 class="label">Reading Activity</h2>
      
      <button v-if= "showUsersButton">Show Members</button>

    <select id="users" v-model= "requestUsername">
      <option v-for="user in users" v-bind:key="user.id" :value="user.username">{{user.username}}</option>
    </select>

    <button v-if= "isValidForm" v-on:click="showResults()" >See Results</button>


    <div v-if= "showResultsBtn">
      <h4>Completed Books: {{readingActivity.completedBooks}}</h4>
      <h4>Total Reading Time (mins): {{readingActivity.totalReadingTime}}</h4>
      <h4>Progress Towards Prize:</h4> 
      <p v-bind:key="name" v-for="(value, name) in readingActivity.progressTowardsPrize">{{name}}:{{value}}</p>
      <h4>Current Books:</h4>
      <p v-bind:key="book" v-for="book in readingActivity.currentBooks">"{{book}}"</p>

    
    </div>

      </div>
    </div>
  
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {

      requestUsername: '',
      showResultsBtn: false,
      showUsersButton: false,
      readingActivity: {
        userName: "",
        completedBooks: 0,
        totalReadingTime: 0,
        progressTowardsPrize: 0,
        currentBooks: "",
      },
      users: []
    };
  },

  created() {
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

  methods: {
    getReadingActivity() {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getReadingActivity`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.readingActivity = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },
    selectUser() {
      
    },

    showResults() {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getReadingActivity/${this.requestUsername}`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {

          this.showResultsBtn = true;
          this.readingActivity = response.data;
          console.log(this.readingActivity);
        })
        .catch(error => {
          console.log(error + " there was an error");
        });

  }
  }, 
  computed: {
    isValidForm() {
      return this.requestUsername != "";
    }
  }
};
</script>