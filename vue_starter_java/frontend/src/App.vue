<template>
<body>
  <div id="app">
    
    <header>
       
    <div id="nav">
      
      <ul class="nav_links">
      <router-link to="/">Home </router-link>
      <router-link to="/login">Login </router-link>
      <router-link to="/register">Register</router-link>
      </ul>

    </div>
    <router-view/>
    
    </header>
  </div>
</body>
</template>

<script>
import Login from "./views/Login";
export default {
  
}
</script>

<style>
.nav_links {
  box-sizing: border-box;
  margin:0px;
  padding: 0;
  background-color:#85848a;
}
.router_links{
  font-family: "Montserrat", sans-serif;
  font-weight: 500;
  font-size: 16px;
  color: #edf0f1;
  text-decoration: none;
}

</style>
