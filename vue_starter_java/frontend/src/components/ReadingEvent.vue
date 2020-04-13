<template>
  <div>
    
      <h2>Reading Event</h2>

  <span class="label">Select Book:</span>
    <select id="books" v-on:click="showFamUsersBtn()" v-model="readingEvent.bookId">
      <option v-for="book in books" v-bind:key="book.id" :value="book.id">{{book.title}}</option>
    </select>
    <span v-if= "showUsersButton" class="label">Select Family Member:</span>
    <select  v-if= "showUsersButton" id="users" v-model="readingEvent.userId">
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
        <input type="text" placeholder="YYYY-MM-dd" v-model="readingEvent.readingDate"/>
      </div>
    </div>

    <span class="label">Select Reading Format:</span>
    <select id="format" v-model="readingEvent.format">
      <option v-for="format in formats" v-bind:key="format.id">{{format.format}}</option>
    </select> 
    <br>
    <label class ="label" for="checkbox"> Book Completed</label>
    <input type="checkbox" id="checkbox" v-model="readingEvent.completed">
    <br>
    

    <div>
          <button v-on:click="addReadingEvent">Submit</button>
    </div>    
    </div>


  
</template>


<script>
import axios from "axios"

export default {
  components: {
  },

  props: {
    apiURL: String
  },
  data() {
    return {
      showUsersButton: false,
      showUsers: false,
      showForm: false,
      readingEvent: {
          userId: '',
          bookId: '',
          readingTime: 0,
          readingDate: '',
          format: '',
          completed: false
      },
      currentUser: {
        userId: '',
        userName: '',
        password: '',
        confirmPassword: '',
        passwordMatching: '',
        role: ''
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
          format: "Read-Aloud (Listener)"
        },
        {
          id: 6,
          format: "Other"
        }
      ]
    };
  },

  created(){
    axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getCurrentUser`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          console.log(response.data);
          this.currentUser = response.data;
          if (this.currentUser.role === "user"){
          this.showUsersButton = true;
      };
        })
        .catch(error => {
          console.log(error + " there was an error");
        });

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

  methods: {
    showFamUsersBtn(){
      if (this.currentUser.role === "user"){
      this.showUsersButton = true;
      }
    },

    showFamUsers(){
      console.log(this.currentUser);
      if (this.currentUser.role === "user"){
        this.showUsers = true;
      }
    },
    showFormButton(){
      this.showForm = true;
    },
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
      if (this.readingEvent.userId === ''){
        this.readingEvent.userId = this.currentUser.id;
      }

      if (document.querySelector("#checkbox").checked){
        this.readingEvent.completed = true;
      }
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
          this.showForm = false;
          alert("Your reading event has been saved, BookWorm!");
          this.readingEvent.userId = '';
          this.readingEvent.bookId = '';
          this.readingEvent.readingTime = 0;
          this.readingEvent.readingDate = '';
          this.readingEvent.format = '';
          this.readingEvent.completed = false;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },
     getCurrentUser() {
      axios
        .get(`${process.env.VUE_APP_REMOTE_API}/api/getCurrentUser`, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        .then(response => {
          console.log(response.data);
          this.currentUser = response.data;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },

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
