<template>
  <div>
    <div>
      <h2 class="label blue">Reading Activity</h2>
      </div>
      <div>
      <button v-if="showUsersButton">Show Members</button>

      <select id="users" v-model="requestUsername">
        <option v-for="user in users" v-bind:key="user.id" :value="user.username">{{user.firstName}}</option>
      </select>

      <button v-on:click="showResults()">Update</button>
        <table class="table is-fullwidth">
        
        <tbody>
          
      <tr>
        <td>Completed Books:</td>
        <td> {{readingActivity.completedBooks}}</td>
      </tr>
      <tr>
        <td>Total Reading Time (mins):</td>
        <td> {{readingActivity.totalReadingTime}}</td>
        </tr>
        <tr>
        <td>Progress Towards Available Prizes:</td>
        <td v-bind:key="name"
          v-for="(value, name) in readingActivity.progressTowardsPrize"
        >{{name}}: {{value}}% There!</td>
        </tr>
        <tr>
        <td>Current Books:</td>
        <td v-bind:key="book" v-for="book in readingActivity.currentBooks">"{{book}}"</td>
        </tr>
        
       
        
        </tbody>
        
      </table>
      </div>
      
    </div>
  
</template>

<script>
import axios from "axios";
import { eventBus } from "../main.js";

export default {
  data() {
    return {
      requestUsername: "",
      showResultsBtn: false,
      showUsersButton: false,
      readingActivity: {
        userName: "",
        completedBooks: 0,
        totalReadingTime: 0,
        progressTowardsPrize: 0,
        currentBooks: ""
      },
      currentUser: {
        userId: "",
        userName: "",
        password: "",
        confirmPassword: "",
        passwordMatching: "",
        role: ""
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
        this.users = response.data;
      })
      .catch(error => {
        console.log(error + " there was an error");
      });
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getCurrentUser`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.requestUsername = response.data.username;
        axios
          .get(
            `${process.env.VUE_APP_REMOTE_API}/api/getReadingActivity/${this.requestUsername}`,
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("Authorization")
              }
            }
          )
          .then(response => {
            this.showResultsBtn = true;
            this.readingActivity = response.data;
          })
          .catch(error => {
            console.log(error + " there was an error");
          });
      })
      .catch(error => {
        console.log(error + " there was an error");
      });

    eventBus.$on("refreshReadingEvent", () => {
      axios
        .get(
          `${process.env.VUE_APP_REMOTE_API}/api/getReadingActivity/${this.requestUsername}`,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("Authorization")
            }
          }
        )
        .then(response => {
          this.showResultsBtn = true;
          this.readingActivity = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    });
    eventBus.$on("refreshFamily", () => {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getUser`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.users = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    });
  },

  methods: {
    showResults() {
      axios
        .get(
          `${process.env.VUE_APP_REMOTE_API}/api/getReadingActivity/${this.requestUsername}`,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("Authorization")
            }
          }
        )
        .then(response => {
          this.showResultsBtn = true;
          this.readingActivity = response.data;
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

<style scoped>
.blue {
  background: #0099ff
}
</style>