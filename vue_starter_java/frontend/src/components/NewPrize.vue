<template>
  <div>
    <div v-if="noPrizes">No prizes entered. Please enter a prize to begin</div>
    <div v-for="prize in prizes" :key="prize.id">
      <a href="#" v-if="isParent" v-on:click="editPrize(parseInt(prize.prizeId))"><i class="fas fa-edit"></i></a>
      <br />
      <a class="delete" href="#" v-if="isParent" v-on:click="deletePrize(parseInt(prize.prizeId))"></a>
      <br />
      {{prize.prizeName}}
      {{prize.prizeDescription}}
      {{prize.milestone}}
      {{prize.numOfPrizes}}
      {{prize.startDate}}
      {{prize.endDate}}
    </div>
  </div>
</template>

<script>
import axios from "axios";
import '@fortawesome/fontawesome-free/css/all.css'
import '@fortawesome/fontawesome-free/js/all.js'

export default {
  props: {},

  data() {
    return {
      isParent: false,
      noPrizes: true,
      prizes: []
    };
  },

  methods: {
    editPrize(id) {
      this.$emit("editPrize", id);
    },
    deletePrize(id){
      this.$emit("deletePrize", id);
      const index = this.prizes.map(prize => prize.prizeId).indexOf(id);
      this.prizes.splice(index,1);
    }
  },
  created() {
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getPrizes`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.prizes = response.data;
        if (response.data != null) {
          this.noPrizes = false;
        }
      })
      .catch(error => {
        console.log(error + " there was an error");
      });
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getCurrentUser`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.currentUser = response.data;
        if (this.currentUser.role === "user") {
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
</style>