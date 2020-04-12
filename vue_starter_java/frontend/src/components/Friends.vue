<template>
  <div>
    <div>
      <h2 class="label">Friends</h2>
    </div>
    <div v-for="user in allFriends" v-bind:key="user.username">{{user.firstName}} {{user.lastName}}</div>
    <div>
      <br />
      <input type="text" v-model="friend.username" placeholder="Username" />
    </div>
    <div>
      <button :disabled="!isValidForm" v-on:click="friendSearch">Friend Search</button>
    </div>
    <span
      v-if="showFriend && !friendError"
    >Name: {{friend.firstName}} {{friend.lastName}}</span>
    <span v-if="friendError">There was a problem locating that user</span>
    <br />
    <span v-if="friendError">Please try again</span>
    <div>
      <button v-if="showFriend && !friendError" v-on:click="addFriend">Add Friend</button>
      <p v-if="success">Friend Added!!</p>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  props: {},
  data() {
    return {
      friendError: false,
      showFriend: false,
      success: false,
      allFriends: {},
      friend: {
        username: "",
        firstName: "",
        lastName: ""
      }
    };
  },

  methods: {
    friendSearch() {
      axios
        .get(
          `${process.env.VUE_APP_REMOTE_API}/api/searchForFriend/${this.friend.username}`,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("Authorization")
            }
          }
        )
        .then(response => {
          if (response.data.username == null) {
            this.friendError = true;
          }
          this.friend.username = response.data.username;
          this.friend.firstName = response.data.firstName;
          this.friend.lastName = response.data.lastName;
          this.showFriend = true;
          axios
            .get(`${process.env.VUE_APP_REMOTE_API}/api/getAllFriends`, {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("Authorization")
              }
            })
            .then(response => {
              this.allFriends = response.data;
            })
            .catch(error => {
              console.log(error + " there was an error");
            });
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },
    addFriend() {
      axios
        .post(`${process.env.VUE_APP_REMOTE_API}/api/addFriend`, this.friend, {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
          }
        })
        // eslint-disable-next-line no-unused-vars
        .then(response => {
          this.friend.username = "";
          this.success = false;
          axios
            .get(`${process.env.VUE_APP_REMOTE_API}/api/getAllFriends`, {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("Authorization")
              }
            })
            .then(response => {
              this.allFriends = response.data;
            })
            .catch(error => {
              console.log(error + " there was an error");
            });
          this.success = true;
          this.showFriend = false;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    }
  },
  created() {
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getAllFriends`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.allFriends = response.data;
      })
      .catch(error => {
        console.log(error + " there was an error");
      });
  },
  computed: {
    isValidForm() {
      return this.friend.username != "";
    }
  }
};
</script>



<style>
</style>
