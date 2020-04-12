<template>
<div>
    <div class="label">All Prizes</div>
    <div class="prizeList" v-for="prize in prizeList" v-bind:key="prize.prizeName">
        Name: {{prize.prizeName}}
        Reading Time: {{prize.readingTime}}
        Milestone: {{prize.milestone}}
    </div>
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
            prizeList: {}
        };
    },

    methods: {

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
        console.log(this.prizeList);
    })
    .catch(error => {
        console.log(error + " there was an error");
    });
    }
}

</script>