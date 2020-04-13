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
            <a href="#" class="edit-prize" v-on:click="editPrize(prizeList.id)">
        Edit
        </a>
            <a href="#" class="delete-prize" v-on:click="deletePrize(prizeList.id)">
    Delete
    </a>
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
        editPrize(id) {
            this.$emit('editPrize', id)
        },

        deletePrize(id) {
            axios
            .delete(`${this.apiURL}/${id}`)
            .then(response => {
                const arrayIndex = this.prizeList.findIndex(response => prizeList.id === id);
                this.prizeList.splice(arrayIndex, 1);
            })
            .catch(error => {
            console.log(error + " there was an error");
            });
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
        console.log(this.prizeList);
    })
    .catch(error => {
        console.log(error + " there was an error");
    });

    //Repopulates form field with prize to edit?
    if(this.prizeId != 0) {
        axios.get(this.apiURL + '/' + this.prizeId).then((result) => {
            this.prize = result.data;
        })
    }
    }
}

</script>