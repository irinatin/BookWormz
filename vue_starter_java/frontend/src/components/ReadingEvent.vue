<template>
  <div>
    <div class="header">
      <h2>Reading Event</h2>
    </div>

    <select v-on:click="getBooks" id="books">
      <option v-for="book in books" v-bind:key="book.id" value="book.id">{{book.title}}</option>
    </select>

    <select v-on:click="selectUser" id="users">
      <option v-for="user in users" v-bind:key="user.id" value="user.id">{{user.username}}</option>
    </select>

    <div class="form">
      <div class="form-input">
        <span class="label">Enter Reading Time:</span>
        <input type="text" placeholder="In Minutes" />
        <div>
          <button v-on:click="addReadingEvent">Submit</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: {
    apiURL: String
  },
  data() {
    return {
      books: [],
      users: [],
      readingEvent: 0,
      format: []
    };
  },

  methods: {
    getBooks() {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getAllBooks`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          console.log(response);
          this.books = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },

    selectUser() {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getUser`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          console.log(response);
          this.users = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },

    addReadingEvent() {
      axios
        .post(
          `${process.env.VUE_APP_REMOTE_API}/api/addReadingEvent`,
          this.readingEvent,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("Authorization")
            }
          }
        )
        .then(response => {
          console.log(response);
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    }
  },

  computed: {
    isValidForm() {
      return this.readingEvent > 0;
    }
  }
};
</script>


<style>
</style>
