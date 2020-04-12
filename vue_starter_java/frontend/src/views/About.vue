<template>
  <div class="home">
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
      <employees title="Adam" v-bind:data="employee1"></employees>
    </section>
    <section>
      <employees title="Irina" v-bind:data="employee2"></employees>
    </section>
    <section>
      <employees title="Joe" v-bind:data="employee3"></employees>
    </section>
    <section>
      <employees title="Leigh" v-bind:data="employee4"></employees>
    </section>
    <section>
      <employees title="Ryan" v-bind:data="employee5"></employees>
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
   
  employee1: {
    photo: "./assets/BookWorm.jpg",
    name: 'Adam',
    adult: ['0000000000', '0000000000'],
    kids: ['0000000000', '0000000000']
  },
  employee2: {
    photo: './assets/Irina.jpg',
    name: 'Irina',
    // { id: 3, adult: [isbn:'0000000000', isbn: '0000000000']},
    // { id: 4, kids: [isbn:'0000000000', isbn: '0000000000']}
  },
  employee3: {
    photo: './assets/Joe.jpg',
    name: 'Joe',
    // { id: 3, adult: [isbn:'0000000000', isbn: '0000000000']},
    // { id: 4, kids: [isbn:'0000000000', isbn: '0000000000']}
  },
  employee4: {
    photo: './assets/Leigh.jpg',
    name: 'Leigh',
    // { id: 3, adult: [isbn:'0000000000', isbn: '0000000000']},
    // { id: 4, kids: [isbn:'0000000000', isbn: '0000000000']}
  },
  employee5: {
    photo: './assets/Ryan.jpg',
    name: 'Ryan',
    // { id: 3, adult: [isbn:'0000000000', isbn: '0000000000']},
    // { id: 4, kids: [isbn:'0000000000', isbn: '0000000000']}
  
  },
  
};
  }
}
</script>

<style scoped>

</style>
