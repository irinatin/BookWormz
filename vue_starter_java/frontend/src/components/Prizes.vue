<template>
  <div class="create_new_prize">
      <h1 class="h3 mb-3 font-weight-normal">Create New Prize</h1>
      <div class="alert alert-danger" role="alert" v-if="formErrors">
          There were problems creating this prize.
      </div>

    <form class="new_prize_form" @submit.prevent="createprize">
      <label for="prize_name">Prize Name</label>
      <br />
      <input
        type="text"
        id="prize_name"
        name="prize_name"
        placeholder="Enter Prize Name"
        v-model="prizeinfo.prizeName"
        required
        autofocus
      />
      <br />

      <label for="description">Description</label>
      <br />
      <input
        type="text"
        id="description"
        name="description"
        placeholder="Enter Prize Description"
        v-model="prizeinfo.prizeDescription"
        required
        autofocus
      />
      <br />

      <label for="milestone">Milestone (minutes reading)</label>
      <br />
      <input
        type="text"
        id="milestone"
        name="milestone"
        placeholder="Enter Milestone"
        v-model="prizeinfo.prizeMilestone"
        required
        autofocus
      />
      <br />

      <label for="user_group">User Group (Parent/Child)</label>
      <br />
      <input
        type="text"
        id="user_group"
        name="user_group"
        placeholder="Enter User Group"
        v-model="prizeinfo.prizeUserGroup"
        required
        autofocus
      />
      <br />

      <label for="max_prizes">Prize Cap</label>
      <br />
      <input
        type="text"
        id="max_prizes"
        name="max_prizes"
        placeholder="Enter Prize Cap"
        v-model="prizeinfo.maxPrizes"
        required
        autofocus
      />
      <br />

      <label for="start_date">Start Date</label>
      <br />
      <input
        type="text"
        id="start_date"
        name="start_date"
        placeholder="Enter Prize Start Date"
        v-model="prizeinfo.prizeStartDate"
        required
        autofocus
      />
      <br />

      <label for="end_date">End Date</label>
      <br />
      <input
        type="text"
        id="end_date"
        name="end_date"
        placeholder="Enter Prize End Date"
        v-model="prizeinfo.prizeEndDate"
        required
        autofocus
      />
      <br />

      <button class="add_prize_button" type="submit">Add Prize</button>
      <br />
    </form>
  </div>
</template>



<script>



export default{
    name: 'createprize',
    data() {
        return{
            prizeinfo:{
                prizeName: '',
                prizeDescription: '',
                prizeMilestone: '',
                prizeUserGroup: '',
                maxPrizes: '',
                prizeStartDate: '',
                prizeEndDate: ''
            },
            formErrors: false,
        };
    },
    methods: {
        addNewPrize() {
            fetch(`${process.env.VUE_APP_REMOTE_API}/Prizes`, {
                method: 'POST',
                headers:{
                    Accept: 'application/json',
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(this.prizeinfo),
            })
            .then((response) => {
                if (response.ok) {
                    this.$router.push({ path: '/prizes', query: { createprize: 'success' } });
                    
                } else {
                    this.formErrors = true;
                }
            })
            .then((err) => console.error(err));
        },
    },
};
</script>



<style>
</style>
