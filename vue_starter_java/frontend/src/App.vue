<template>
<body class="has-background-light">
  <div id="app">
    
    <header>
    <navbar class="navbar">
      <div class="container">
        <div class="navbar-brand">
        <a class="navbar-item brand-text">BookWormz</a>
          <a role="button" class="navbar-burger" data-target="navbar-menu" onclick="document.querySelector('.navbar-menu').classList.toggle('is-active');" aria-label="menu" aria-expanded="false">
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
            <span aria-hidden="true"></span>
           
          
        </a>
        </div>
      </div>

      <div class="navbar-menu">
        <div class="navbar-start">
          <a class="navbar-item"><router-link to="/">
          Home 
          </router-link></a>
          
          <a class="navbar-item"><router-link to="/login">
          Login 
          </router-link></a>

          <a class="navbar-item" ><router-link to="/register">Register</router-link></a>
          <a class="navbar-item" > <router-link v-on:click.native= "quitter" to="/login" >Logout </router-link></a>
        </div>
      </div> 
    </navbar>
      
      <!-- <ul class="nav_links">
      <router-link to="/">Home </router-link>
      <router-link to="/login">Login </router-link>
      <router-link to="/register">Register</router-link>
      <router-link v-on:click.native= "quitter" to="/login" >Logout </router-link>
      
      </ul>

    </div> -->
    <router-view/>
    
    </header>
  </div>
</body>
</template>

<script>
import 'bulma/css/bulma.css'
import Login from "./views/Login";
import auth from './auth';
export default {
  methods: {
  quitter(){
    auth.logout();
    alert("You have logged out!");
    
  },

}
}


</script>
<style>
.brand-text{
  padding-left:3co0%;
}
.navbar-start > .navbar-item {
  font-weight: 700;
}
</style>

