<template>
  <div>
    <div class="header">
      <h2>Book Search</h2>
    </div>
    <div class="form">
      <div class="form-input">
        <span class="label">Enter Book ISBN:</span> <input type="text" v-model="book.isbn" placeholder="Enter ISBN Here">
      </div>
      <button :disabled="!isValidForm" v-on:click="searchBooks">Search</button>
    </div>
  </div>
</template>

<script>

import axios from 'axios'

export default {
  props: {
    apiURL: String

  },
  data() {
    return {
      book: {
        isbn: '',
        title: '',
        author: '',
        thumbnail: '',
      }
    }
  },
  methods: {
    
    addBook() {
      axios.get('https://openlibrary.org/api/books?format=json&jscmd=data&bibkeys=ISBN:' + book.isbn)

      .then(response => {
        (this.book = response.data)
      
      })
      .catch(error => {
        console.log(error + ' there was an error')
      })
    },
    
  //   updateReview() {
  //     axios.put(`${this.apiURL}/${this.reviewID}`,this.review)
  //     .then((response) => {
  //       if( response.status === 204 ) {
  //         this.$emit('showReviews');
  //       }
  //     })
  //     .catch(error => {
  //       console.log(error + ' there was an error')
  //     })
  //   }
  // },
  computed: {
    isValidForm() {
      return this.book.isbn != '';
    },
    
  
};
</script>

<style>

</style>
