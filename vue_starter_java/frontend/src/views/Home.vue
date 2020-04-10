<template>
  <div class="home">
    <div class="container">
        <div class="columns">
          <div class="column">
            <h1>Title</h1>
          </div>
        </div>
    </div>

    <div class="container">
        <div class="columns">
          <div class="column">
             <family></family>
          </div>
          
          <div class="column">
            <p>Leaderboard</p>
          </div>

          <div class="column">
            <p>Friends</p>
          </div>
        </div>    
     </div>

    <div class="container">
        <div class="columns">
          <div class="column">
            <books></books>
          </div>
        </div>
    </div>

    <div class="container">
        <div class="columns">
          <div class="column">
            <reading-event></reading-event>
          </div>
        </div>
    </div>           
  
    <div class="container">
        <div class="columns">
          <div class="column">
          </div>
        </div>
     </div>
</div>
</template>

<script>
import Family from '../components/Family';
import ReadingEvent from '../components/ReadingEvent';
import Books from '../components/Books';


export default {
  name: 'home',
  components: {
    Family,
    ReadingEvent,
    Books,

  }
}
</script>

<style scoped>
/* body {
  margin: 0px;
  padding: 400px 100px;
  background: linear-gradient(to bottom, rgba(102, 255, 102, 0.7), rgba(0, 128, 0, 0.25));
  background-size: cover;
  background-position: center;
  font-family: sans-serif;
  height: 100%;
} */
</style>