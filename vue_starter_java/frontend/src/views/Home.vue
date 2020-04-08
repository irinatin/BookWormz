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
