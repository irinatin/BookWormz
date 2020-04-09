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
    <div>
      <span><img v-bind:src="book.thumbnail"/> isbn: {{book.isbn}} title:{{book.title}} author:{{book.author}}</span>
    </div>
    <!-- <div>
      <button v-on:click="addBookToLibrary">Add To Library</button>
    </div> -->
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
    
    searchBooks() {
      //axios.get('https://api2.isbndb.com/book/' + this.book.isbn)
      axios.get('https://openlibrary.org/api/books?format=json&jscmd=data&bibkeys=ISBN:' + this.book.isbn)

      .then(response => {
        
        console.log(response.data);
        //this.book.title = response.data.ISBN.title;
        //this.book.title = response.data['ISBN:1847246923'].title; THIS WORKS!!!
        let tempIsbn = this.book.isbn;
        
        this.book.title = response.data['ISBN:'+ tempIsbn].title;
        this.book.author = response.data['ISBN:' + tempIsbn].authors[0].name;
        this.book.thumbnail = response.data['ISBN:' + tempIsbn].cover.small;


        //this.book.author = response.data.authors.name;
        //this.book.thumbnail = response.data.cover.small;
      
      })
      .catch(error => {
        console.log(error + ' there was an error')
      })
    }
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
    
  
}
  
};
</script>

<style>

</style>
