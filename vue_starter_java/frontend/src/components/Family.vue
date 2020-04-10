<template>
  <body>
      <h1> Family </h1>
        <div v-for="user in family" :key="user.firstName"></div>   
        <div>{{family[0].firstName}}</div> 

  </body>    
</template>



<script>
import axios from 'axios'

export default {
  props: {
    apiURL: String

  },
  data() {
    return {
      family:[],
      }
  },

  methods: {

  },
  created(){
    axios.get(`${process.env.VUE_APP_REMOTE_API}/api/getFamilyList`,{
        headers:{"Authorization" :  'Bearer ' + localStorage.getItem('Authorization')}})
    .then(response => {
        this.family = response.data;
        console.log(this.family);
    })
    .catch(error => {
        console.log(error + ' there was an error')
    })
    }
};
</script>



<style>

</style>
