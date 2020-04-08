<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <reading-event></reading-event>
    

            

  </div>


</template>

<script>
import ReadingEvent from '../components/ReadingEvent';

export default {
  name: 'home',
  components: {
    ReadingEvent
  }
}
</script>

<style scoped>
body {
  margin: 0px;
  padding: 400px 100px;
  background: linear-gradient(to bottom, rgba(102, 255, 102, 0.7), rgba(0, 128, 0, 0.25));
  background-size: cover;
  background-position: center;
  font-family: sans-serif;
  height: 100%;
}
</style>