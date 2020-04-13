<template>
  <div class="has-background-white">
    <section class="hero is-primary">
      <div class="container">
        <div class="columns">
          <div class="column hero-body">
            <h1 class="title">Meet the BookWormz Team</h1>
          </div>
        </div>
      </div>
    </section>
    <section>
      <div class="container">
      <h2 class="has-background-info has-text-black-bis">Adam</h2>
        <div class="columns">
        <div class="column has-text-centered">
                <img class="photo" src="./assets/BookWorm.jpg" alt="employee photo"/>
        </div>
        <div class="column">
            Favorite Book: 
            <br>
            <br>
            Favorite Kids Book: 
            <br>
            <img src="https://covers.openlibrary.org/b/id/24239-S.jpg"/>
            Trollhunters By: Guillermo Del Toro, Daniel Kraus, Sean Murray
        </div>
      
        </div>
      </div>
    </section>
    <section>
      <div class="container">
      <h2 class="has-background-info has-text-black-bis">Irina</h2>
        <div class="columns">
        <div class="column has-text-centered">
                <img class="photo" src="./assets/ki8oxyX7T.jpg" alt="employee photo"/>
        </div>
        <div class="column">
            Favorite Book: 
            <br>
            <img src="https://covers.openlibrary.org/b/id/8869747-S.jpg"/>
            Goodbye, Things: The New Japanese Minimilism By: Fumio Sasaki
            <br>
            <br>
            Favorite Kids Book: 
            <br>
            <img src="https://covers.openlibrary.org/b/id/8507700-S.jpg"/>
            Pippi Longstocking By: Astrid Lindgren
        </div>
      
        </div>
      </div>
    </section>
    <section>
      <<div class="container">
      <h2 class="has-background-info has-text-black-bis">Joe</h2>
        <div class="columns">
        <div class="column has-text-centered">
                <img class="photo" src="./assets/Joe.jpeg" alt="employee photo"/>
        </div>
        <div class="column">
            Favorite Book: 
            <br>
            <img class="thumbnail" src="https://images2.penguinrandomhouse.com/cover/9780767930611"/>
            Breathers: A Zombie's Lament By: SG Browne
            <br>
            <br>
            Favorite Kids Book: 
            <br>
            <img class="thumbnail" src="https://images.randomhouse.com/cover/9780525428893"/>
            High Five by Adam Rubin
        </div>
      
        </div>
      </div>
    </section>
    <section>
      <div class="container">
      <h2 class="has-background-info has-text-black-bis">Leigh</h2>
        <div class="columns">
        <div class="column has-text-centered">
                <img class="photo" src="./assets/Leigh.jpeg" alt="employee photo"/>
        </div>
        <div class="column">
            Favorite Book: 
            <br>
            <img src="https://covers.openlibrary.org/b/id/27764-S.jpg"/>
            One Hundred Years of Solitude By: Gabriel García Márquez
            <br>
            <br>
            Favorite Kids Book: 
            <br>
            <img class="thumbnail" src="https://www.akpress.org/pub/media/catalog/product/cache/1ec012b46cbfe4262fc94f3e95ab2d9c/r/u/ruleistobreak_72.jpg"/>
            <br>
            A Rule is to Break: A Child's Guide to Anarchy By: John Seven & Jana Christy
        </div>
      
        </div>
      </div>
    </section>
    <section>
      <div class="container">
      <h2 class="has-background-info has-text-black-bis">Ryan</h2>
        <div class="columns">
        <div class="column has-text-centered">
                <img class="photo" src="./assets/Ryan.jpg" alt="employee photo"/>
        </div>
        <div class="column">
            Favorite Book: 
            <br>
            Favorite Kids Book: 
            <br>
            <img src="https://covers.openlibrary.org/b/id/24239-S.jpg"/>
            Trollhunters By: by Guillermo Del Toro, Daniel Kraus, Sean Murray
        </div>
      
        </div>
      </div>
    </section>
  </div>
</template>

<script>
import axios from "axios";
import Employees from "../components/Employees"

export default {
  name: 'about',
  components: {
    Employees 
  },
  data() {
    return {
 
};
  }
}
</script>

<style scoped>
.photo {
    width: 30%;
    height: auto;
}
.thumbnail{
  width: 10%;
  height: auto;
}
</style>
