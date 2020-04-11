<template>
  <div>
    <div class="header">
      <h2>Reading Event</h2>
    </div>

    <select v-on:click="getBooks" id="books" v-model="readingEvent.bookId">
      <option v-for="book in books" v-bind:key="book.id" :value="book.id">{{book.title}}</option>
    </select>

    <select v-on:click="selectUser" id="users" v-model="readingEvent.userId">
      <option v-for="user in users" v-bind:key="user.id" :value="user.id">{{user.username}}</option>
    </select>

     <div class="form">
      <div class="form-input">
        <span class="label">Enter Reading Time:</span>
        <input type="number" placeholder="In Minutes" v-model="readingEvent.readingTime"/>
        
      </div>
    </div>

    <div class="form">
      <div class="form-input">
        <span class="label">Enter Reading Date:</span>
        <input type="text" placeholder="YYYY/MM/dd" v-model="readingEvent.readingDate"/>
      </div>
    </div>

    <select id="format" v-model="readingEvent.format">
      <option v-for="format in formats" v-bind:key="format.id">{{format.format}}</option>
    </select> 

    <div>
          <button v-on:click="addReadingEvent">Submit</button>
    </div>    


    <!-- <datepicker v-model="readingEvent.readingDate"></datepicker> -->


  </div>
</template>


<script>
import axios from "axios"
import Datepicker from 'vuejs-datepicker';

export default {
  components: {
    Datepicker
  },

  props: {
    apiURL: String
  },
  data() {
    return {
      readingEvent: {
          userId: '',
          bookId: '',
          readingTime: 0,
          readingDate: '',
          format: ''
      },
      books: [],
      users: [],
      formats: [
        {
          id: 1,
          format: "Paper"
        },
        {
          id: 2,
          format: "Digital/Kindle"
        },
        {
          id: 3,
          format: "Audiobook"
        },
        {
          id: 4,
          format: "Read-Aloud (Reader)"
        },
        {
          id: 5,
          format: "Read-Aloud (Listener"
        },
        {
          id: 6,
          format: "Other"
        }
      ]
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
      console.log(this.readingEvent);
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
    },

    selectDate() {
      return this.date;
    },



  }
};
</script>


<style>
</style>
