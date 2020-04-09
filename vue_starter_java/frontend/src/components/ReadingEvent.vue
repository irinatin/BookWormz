<template>
  <div>
    <div class="header">
      <h2>Book Search</h2>
    </div>
    <div class="form">
      <div class="form-input">
        <span class="label">Enter Book ISBN:</span> <input type="text" v-model="book.isbn" placeholder="Enter ISBN Here">
      </div>
      <button :disabled="!isValidForm" v-on:click="searchBooks(10, 13)">Search</button>
    </div>
    <br>
    <div>
      <span v-if="showBook"><img v-bind:src="book.thumbnail"/>   isbn:  {{book.isbn}}  title:  {{book.title}} author: {{book.author}}</span>
    </div>
    <div>
      <p v-if="showBook">To add this book to your personal library, click below:</p>
      <button v-if= "showBook" v-on:click= "addBookToLibrary">Add Book To Library</button>
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
      showBook: false,
      book: {
        isbn: '',
        title: '',
        author: '',
        thumbnail: '',
      }
    }
  },
  methods: {
    
    searchBooks(minlength, maxlength) {
      
      
      let mnlen = minlength;
      let mxlen = maxlength;

      if(this.book.isbn.length<mnlen || this.book.isbn.length> mxlen)
      { 
      alert("Please input the ISBN with digits only between " +mnlen+ " and " +mxlen+ " characters (Ex: 0141439556)");
      return false;
      }
      else
      { 
      axios.get('https://openlibrary.org/api/books?format=json&jscmd=data&bibkeys=ISBN:' + this.book.isbn)

      .then(response => {
        
        console.log(response.data);
      
        //this.book.title = response.data['ISBN:1847246923'].title; THIS WORKS!!!
        let tempIsbn = this.book.isbn;
        
        this.book.title = response.data['ISBN:'+ tempIsbn].title;
        this.book.author = response.data['ISBN:' + tempIsbn].authors[0].name;
        this.book.thumbnail = response.data['ISBN:' + tempIsbn].cover.small;
        this.showBook = true;
      })
      .catch(error => {
        console.log(error + ' there was an error')
      })
    }
    },
  
    
  addBookToLibrary(){
      axios.post(`${process.env.VUE_APP_REMOTE_API}/api/addBook`, this.book, {
      headers:{"Authorization" :  'Bearer ' + localStorage.getItem('Authorization')}
      })
    .then(response => {
      console.log(response)
    })
    .catch(error => {
        console.log(error + ' there was an error')
      })
  }
  }, 

  computed: {
    isValidForm() {
      return this.book.isbn != '';
    },
  }
  

};
</script>

<style>

</style>
