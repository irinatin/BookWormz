<template>
  <div class="home">
    <h1>Home</h1>
    <p>You must be authenticated to see this</p>
    <books></books>
    
  </div>
</template>

<script>
import ReadingEvent from '../components/ReadingEvent';
import Books from '../components/Books';
export default {
  name: 'home',
  components: {
    ReadingEvent,
    Books
  }
}
</script>
