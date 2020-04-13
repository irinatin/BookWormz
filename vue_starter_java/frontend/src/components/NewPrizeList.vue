<template>
  <div>
    <div>
      <h2 class="label">Prizes</h2>
      <div v-if="formErrors">There were problems creating this prize.</div>
      <div v-if="noPrizes">There are no prizes entered! Add a prize!!!</div>
      <button v-if="!showForm" v-on:click="showFormButton()">Create New Prize</button>
      <button v-if="showForm" v-on:click="hideFormButton()">Hide Form</button>
    </div>
    <div v-if="showForm">
        <label for="prize_name">Prize Name</label>
        <input
          type="text"
          id="prize_name"
          name="prize_name"
          placeholder="Enter Prize Name"
          v-model="prizeinfo.prizeName"
          required
          autofocus
        />
        <label for="description">Description</label>
        <input
          type="text"
          id="description"
          name="description"
          placeholder="Enter Prize Description"
          v-model="prizeinfo.prizeDescription"
          required
          autofocus
        />
        <label for="milestone">Milestone (minutes reading)</label>
        <input
          type="text"
          id="milestone"
          name="milestone"
          placeholder="Enter Milestone"
          v-model="prizeinfo.milestone"
          required
          autofocus
        />
        <br />

        <label for="user_group">User Group (Parent/Child)</label>
        <input
          type="text"
          id="user_group"
          name="user_group"
          placeholder="Enter User Group"
          v-model="prizeinfo.userGroup"
          required
          autofocus
        />
        <label for="max_prizes">Prize Cap</label>
        <input
          type="text"
          id="numOfPrizes"
          name="numOfPrizes"
          placeholder="Enter Prize Cap"
          v-model="prizeinfo.numOfPrizes"
          required
          autofocus
        />
        <label for="start_date">Start Date</label>
        <input
          type="text"
          id="start_date"
          name="start_date"
          placeholder="yyyy-mm-dd"
          v-model="prizeinfo.startDate"
          required
          autofocus
        />
        <label for="end_date">End Date</label>
        <input
          type="text"
          id="end_date"
          name="end_date"
          placeholder="yyyy-mm-dd"
          v-model="prizeinfo.endDate"
          required
          autofocus
        />
        <br />
        <button v-on:click="savePrize()" v-if="this.prizeIdNum == 0" class="add_prize_button" type="submit">Add Prize</button>
        <button v-on:click="savePrize()" v-if="this.prizeIdNum != 0" class="add_prize_button" type="submit">Edit Prize</button>
    </div>

    <new-prize v-if="!showForm" v-on:editPrize="editPrize($event) ; showFormButton()" v-on:deletePrize="deletePrize($event) ; hideFormButton()"></new-prize>
  </div>
</template>

<script>
import axios from "axios";
import NewPrize from "@/components/NewPrize";
export default {
  components: {
    NewPrize
  },
  props: {
    apiURL: String
  },

  data() {
    return {
      isParent: false,
      showForm: false,
      prizeIdNum: 0,
      prizeinfo: {
        prizeId: "",
        prizeName: "",
        prizeDescription: "",
        milestone: "",
        userGroup: "",
        numOfPrizes: "",
        startDate: "",
        endDate: ""
      },
      formErrors: false,
      noPrizes: false
    };
  },

  methods: {
    savePrize() {
      this.prizeIdNum === 0 ? this.addNewPrize() : this.updatePrize();
    },
    editPrize(id) {
      this.prizeIdNum = id;
    },
    addNewPrize() {
      axios
        .post(
          `${process.env.VUE_APP_REMOTE_API}/api/addPrize`,
          this.prizeinfo,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("Authorization")
            }
          }
        )
        // eslint-disable-next-line no-unused-vars
        .then(response => {
          this.showForm = false;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },
    updatePrize() {
      axios
        .post(
          `${process.env.VUE_APP_REMOTE_API}/api/editPrize`,
          this.prizeinfo,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("Authorization")
            }
          }
        )
        // eslint-disable-next-line no-unused-vars
        .then(response => {
          this.showForm = false;
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },
    deletePrize(id) {
      this.prizeinfo.prizeId = id;
      axios
        .post(
          `${process.env.VUE_APP_REMOTE_API}/api/deletePrize`,
          this.prizeinfo,
          {
            headers: {
              Authorization: "Bearer " + localStorage.getItem("Authorization")
            }
          }
        )
        // eslint-disable-next-line no-unused-vars
        .then(response => {
        })
        .catch(error => {
          console.log(error + " there was an error");
        });
    },

    showFormButton() {
      this.showForm = true;
      if (this.prizeIdNum != 0) {
        axios
          .get(
            `${process.env.VUE_APP_REMOTE_API}/api/getPrize/${this.prizeIdNum}`,
            {
              headers: {
                Authorization: "Bearer " + localStorage.getItem("Authorization")
              }
            }
          )
          .then(response => {
            this.prizeinfo = response.data;
            if (this.prizeinfo.userGroup == "user") {
              this.prizeinfo.userGroup = "Parent";
            }
          })
          .catch(err => console.error(err));
      }
    },
    hideFormButton() {
      this.showForm = false;
      
    }
  }
};
</script>



<style>
</style>