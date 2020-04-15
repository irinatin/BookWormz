<template>
<div>
<h2 class="yellow label">Your Prizes</h2>
    <ul>
        <li v-for="prize in prizes" v-bind:key="prize">{{prize}}</li>
    </ul>
 </div>   
</template>

<script>
import axios from "axios";
import { eventBus } from "../main.js";

export default {
  data() {
    return {
      prizes: []
    };
  },

  created() {
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getPrizesPerUser`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.prizes = response.data;
      })
      .catch(error => {
        console.log(error + " there was an error");
      });

    eventBus.$on("refreshReadingEvent", () => {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getPrizesPerUser`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.prizes = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
  });
  eventBus.$on("refreshCreatePrize", () => {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getPrizesPerUser`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          this.prizes = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
  });
  }
};
</script>
<style>
.yellow {
  background: #ffff00;
  }
</style>
