<template>
  <div>
      <h2>Reading Activity</h2>
    <div>
      <div v-for="user in readingActivity" v-bind:key="user.userName">{{user.userName}}</div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {

    readingActivity: {
        userName: '',
        readingTime: 0
    },
    users: []
      
    };
  },

  methods: {
  },

    created() {
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
    }
  
};
</script>