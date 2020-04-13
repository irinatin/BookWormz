<template>
<div>
    <h2 class="label">All Prizes</h2>
    <table class="table">
        <thead>
            <tr>
                <th>Prize Name</th>
                <th>Reading Minutes</th>
                <th>Milestone</th>
            </tr>
        </thead>
        <tbody>
            <tr class="prizeList" v-for="prize in prizeList" v-bind:key="prize.prizeName">
                <td> {{prize.prizeName}}</td>
                <td> {{prize.readingTime}}</td>
                <td> {{prize.milestone}}</td>
            </tr>
        </tbody>
    </table>
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