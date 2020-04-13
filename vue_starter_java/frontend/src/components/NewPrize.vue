<template>
  <div>
    <div v-for="prize in prizes" :key="prize.id">
      <a href="#" class="edit-review" v-on:click="editPrize(parseInt(prize.prizeId))">
        <i class="far fa-edit"></i> Edit
      </a>
      <br />
      <a href="#" class="delete-review" v-on:click="deletePrize(prize.id)">
        <i class="far fa-trash-alt"></i> Delete
      </a>
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
export default {
  props: {},

  data() {
    return {
      prizes: []
    };
  },

  methods: {
    editPrize(id) {
      this.$emit('editPrize', id)
    },
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
      })
      .catch(error => {
        console.log(error + " there was an error");
      });
  }
};
</script>



<style>
</style>