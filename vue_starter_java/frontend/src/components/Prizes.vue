<template>
  <div class="create_new_prize">
    <div
      class="alert alert-danger"
      role="alert"
      v-if="formErrors"
    >There were problems creating this prize.
    </div>
    <button v-if="!showForm" v-on:click="showFormButton();">Create New Prize</button>
    <button v-if="showForm" v-on:click="hideFormButton();">Hide Form</button>
    <div v-if= "showForm">
    <form class="new_prize_form" @submit.prevent="addNewPrize">
      <label for="prize_name">Prize Name</label>
      <br />
      <input
        type="text"
        id="prize_name"
        name="prize_name"
        placeholder="Enter Prize Name"
        v-model= "prizeinfo.prizeName"
        required
        autofocus
      />
      <br>

      <label for="description">Description</label>
      <br />
      <input
        type="text"
        id="description"
        name="description"
        placeholder="Enter Prize Description"
        v-model= "prizeinfo.prizeDescription"
        required
        autofocus
      />
      <br>

      <label for="milestone">Milestone (minutes reading)</label>
      <br />
      <input
        type="text"
        id="milestone"
        name="milestone"
        placeholder="Enter Milestone"
        v-model= "prizeinfo.prizeMilestone"
        required
        autofocus
      />
      <br>

      <label for="user_group">User Group (Parent/Child)</label>
      <br />
      <input
        type="text"
        id="user_group"
        name="user_group"
        placeholder="Enter User Group"
        v-model= "prizeinfo.userGroup"
        required
        autofocus
      />
      <br>

      <label for="max_prizes">Prize Cap</label>
      <br />
      <input
        type="text"
        id="max_prizes"
        name="max_prizes"
        placeholder="Enter Prize Cap"
        v-model= "prizeinfo.maxPrizes"
        required
        autofocus
      />
      <br>

      <label for="start_date">Start Date</label>
      <br />
      <input
        type="text"
        id="start_date"
        name="start_date"
        placeholder="yyyy-mm-dd"
        v-model= "prizeinfo.startDate"
        required
        autofocus
      />
      <br>

      <label for="end_date">End Date</label>
      <br />
      <input
        type="text"
        id="end_date"
        name="end_date"
        placeholder="yyyy-mm-dd"
        v-model= "prizeinfo.endDate"
        required
        autofocus
      />
      <br>

      <button class="add_prize_button" type="submit">Add Prize</button>
      <br>
    </form>
  </div>
  

  <div class="prizeList" v-for="prize in prizeList" v-bind:key="prize.prizeName">{{prize.prizeName}}</div>

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
      showForm: false,
      prizeinfo: {
        prizeName: "",
        prizeDescription: "",
        prizeMilestone: "",
        userGroup: "",
        maxPrizes: "",
        startDate: "",
        endDate: ""
      },
      formErrors: false,
      
      prizeList: {}
    };
  },

  methods: {
    addNewPrize(){
      axios.post(`${process.env.VUE_APP_REMOTE_API}/api/addPrize`, this.prizeinfo, {
      headers:{"Authorization" :  'Bearer ' + localStorage.getItem('Authorization')}
      })
    .then(response => {
      console.log(response)
    })
    .catch(error => {
        console.log(error + ' there was an error')
      })
  },
  showFormButton(){
      this.showForm = true;
  },
  hideFormButton(){
      this.showForm = false;
  }
  },


created() {
    axios
    .get(`${process.env.VUE_APP_REMOTE_API}/api/getPrizeList`, {
        headers: {
            Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
        
    })
    .then(response => {
        this.prizeList = response.data;
        console.log(this.prizeList)
        console.log("this work?");
    })
    .catch(error => {
        console.log(error + " there was an error");
    });
}
};
</script>



<style>
</style>
