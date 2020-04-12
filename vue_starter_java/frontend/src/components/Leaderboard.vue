<template>
  <div>
    <h2 class="label">Leaderboard</h2>
    <div>
      <div v-for="user in leaderboard" v-bind:key="user.userName">Username: {{user.userName}}     Total: {{user.totalReading}} min</div>
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
</style>
