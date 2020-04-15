<template>
  <div>
    <div>
      <h2 class="label salmon">Prizes</h2>
      <div v-if="formErrors">There were problems creating this prize.</div>
      <div v-if="noPrizes">There are no prizes entered! Add a prize!!!</div>
      <button v-if="!showForm && isParent" v-on:click="showFormButton()">Create New Prize</button>
      <button v-if="showForm" v-on:click="hideFormButton()">Hide Form</button>
    </div>
    <div v-if="showForm">
      <span><label for="prize_name">Prize Name</label>
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
      /></span>
      <br />

      <label for="user_group">Parent/Child Prize</label>
      <select id="users" v-model="prizeinfo.userGroup">
        <option :value="parent">Parent</option>
        <option :value="child">Child</option>
      </select>

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
      <label for="startDate">Start Date</label>
      <input
        type="text"
        id="startDate"
        name="startDate"
        placeholder="yyyy-mm-dd"
        v-model="prizeinfo.startDate"
        required
        autofocus
      />
      <label for="endDate">End Date</label>
      <input
        type="text"
        id="endDate"
        name="endDate"
        placeholder="yyyy-mm-dd"
        v-model="prizeinfo.endDate"
        required
        autofocus
      />
      <br />
      <button
        v-on:click="savePrize()"
        v-if="this.prizeIdNum == 0"
        class="add_prize_button"
        type="submit"
      >Add Prize</button>
      <button
        v-on:click="savePrize()"
        v-if="this.prizeIdNum != 0"
        class="add_prize_button"
        type="submit"
      >Edit Prize</button>
    </div>

    <new-prize
      v-if="!showForm"
      v-on:editPrize="editPrize($event) ; showFormButton()"
      v-on:deletePrize="deletePrize($event) ; hideFormButton()"
    ></new-prize>
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
      parent: "user",
      child: "child",
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
      currentUser: {
        userId: "",
        userName: "",
        password: "",
        confirmPassword: "",
        passwordMatching: "",
        role: ""
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
        .then(response => {})
        .catch(error => {
          console.log(error + " there was an error");
        });
    },

    showFormButton() {
      this.prizeinfo.prizeId = "";
      this.prizeinfo.prizeName = "";
      this.prizeinfo.prizeDescription = "";
      this.prizeinfo.milestone = "";
      this.prizeinfo.userGroup = "";
      this.prizeinfo.numOfPrizes = "";
      this.prizeinfo.startDate = "";
      this.prizeinfo.endDate = "";
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
              this.isParent = true;
            }
          })
          .catch(err => console.error(err));
      }
    },
    hideFormButton() {
      this.prizeIdNum = 0;
      this.showForm = false;
    }
  },
  created() {
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getCurrentUser`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.currentUser = response.data;
        if(this.currentUser.role == "user"){
          this.isParent = true;
        }
      })
      .catch(error => {
        console.log(error + " there was an error");
      });
  }
};
</script>



<style>
.salmon {
  background-color: #ff6666;
}
</style>