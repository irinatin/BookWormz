/* eslint-disable no-unused-vars */
<template>
  <div>
    <div class="header">
      <h2 class="label has-background-primary">Book Search</h2>
    </div>
    <img class="book" src="../views/assets/Book.gif"/>
    <div class="form">
      <div class="form-input">
        <span>Enter Book ISBN:</span><br>
        <input type="text" v-model="book.isbn" placeholder="Enter ISBN Here" />
      </div>
      <button :disabled="!isValidForm" v-on:click="searchBooks(10, 13)">
        Search
      </button>
    </div>

    <br />

    <div>
      <span v-if="showBook"
        ><img v-bind:src="book.thumbnail"/> Title: {{ book.title }} 
        <p>Author: {{ book.author }}</p> </span>
    </div>
  <br>
    <div>
      <p v-if="showBook">
        To add this book to your personal library, click below:
      </p>
      <button v-if="showBook" v-on:click="addBookToLibrary">
        Add Book To Library
      </button>
      <p v-if="success">Book Added Successfully!</p>
    </div>

    <div class="form" v-if="manualBook">
      <div class="form-input">
        <p>Your book could not be found. Please add it below:</p>
        <span class="label">Title:</span>
        <input
          type="text"
          v-model="book.title"
          placeholder="Enter Title Here"
        />
        <span class="label">Author:</span>
        <input
          type="text"
          v-model="book.author"
          placeholder="Enter Author Here"
        />
      </div>
      <button v-on:click="addBookToLibraryManually">Add Book</button
      ><button v-on:click="clearSearch">Cancel</button>
      <p v-if="success2">Book Added Successfully!</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {eventBus} from "../main.js";

export default {
  props: {
    apiURL: String
  },
  data() {
    return {
      showBook: false,
      manualBook: false,
      success: false,
      success2: false,
      book: {
        isbn: "",
        title: "",
        author: "",
        thumbnail: ""
      }
    };
  },
  methods: {
    searchBooks(minlength, maxlength) {
      this.success = false;
      let mnlen = minlength;
      let mxlen = maxlength;

      if (this.book.isbn.length < mnlen || this.book.isbn.length > mxlen) {
        alert(
          "Please input the ISBN with digits only between " +
            mnlen +
            " and " +
            mxlen +
            " characters (Ex: 0141439556)"
        );
        return false;
      } else {
        axios
          .get(
            "https://openlibrary.org/api/books?format=json&jscmd=data&bibkeys=ISBN:" +
              this.book.isbn
          )

          .then(response => {

            //this.book.title = response.data['ISBN:1847246923'].title; THIS WORKS!!!
            let tempIsbn = this.book.isbn;

            this.book.title = response.data["ISBN:" + tempIsbn].title;
            this.book.author =
              response.data["ISBN:" + tempIsbn].authors[0].name;
            this.book.thumbnail = response.data["ISBN:" + tempIsbn].cover.small;
            this.showBook = true;
            // this.book.isbn = ' ';
          })
          .catch(error => {
            console.log(error + " there was an error");
            this.manualBook = true;
            this.showBook = false;
          });
      }
    },

    addBookToLibraryManually() {
      axios
        .post(`${process.env.VUE_APP_REMOTE_API}/api/addBook`, this.book, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        // eslint-disable-next-line no-unused-vars
        .then(response => {
          this.success = true;
          this.manualBook = false;
          this.book.isbn = "";
          eventBus.$emit('refreshBooks');
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },

    addBookToLibrary() {
      axios
        .post(`${process.env.VUE_APP_REMOTE_API}/api/addBook`, this.book, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        // eslint-disable-next-line no-unused-vars
        .then(response => {
          this.success = true;
          this.showBook = false;
          this.book.isbn = "";
          eventBus.$emit('refreshBooks');
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },

    clearSearch() {
      this.book.isbn = "";
      this.book.title = "";
      this.book.author = "";
      this.book.thumbnail = "";
      this.showBook = false;
      this.manualBook = false;
      this.success = false;
      this.success2 = false;
    }
  },
  computed: {
    isValidForm() {
      return this.book.isbn != "";
    }
  }
};
</script>

<style scoped>
.book {
  width: 25%;
  height: auto;
  display: inline;
  float: left;
  padding-top: 3%;
  padding-left: 10%;
}
.form-input {
  padding-top: 3%;
}
</style>
