<template>
    <ul>
        <li v-for="prize in prizes" v-bind:key="prize">{{prize}}</li>
    </ul>
    
</template>

<script>
import axios from 'axios'


export default {

    data() {
        return {
            prizes: []
        }
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
    }


    
}
</script>