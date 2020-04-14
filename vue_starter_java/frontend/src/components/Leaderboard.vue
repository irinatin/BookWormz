<template>
  <div>
    <h2 class="label purple">Monthly Leaderboard</h2>
    <div>
      <table class="table">
        <thead>
          <tr>
            <th>BookWorm</th>
            <th>Total Minutes</th>
          </tr>
        </thead>
        <tbody>
          <tr v-bind="leaderboard" v-for="user in leaderboard" v-bind:key="user.userName" >
            <td class="has-text-centered">{{user.firstName}} {{user.lastName}}</td>    
            <td class="has-text-centered">{{user.totalReading}}</td>
          </tr>
        </tbody>
      </table>
    </div>
    
  </div>
</template>



<script>
import axios from "axios";


export default {

  data() {
    return {
      leaderboard: {}
    };
  },

  methods: {
      
  },

  

  created() {
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getLeaderboard`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.leaderboard = response.data;
      })
      .catch(error => {
        console.log(error + " there was an error");
      });
  }
};
</script>



<style>
.purple {
    background-color: #b366ff;
  }
</style>
