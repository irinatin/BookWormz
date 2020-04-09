<template>
<div>
    <div class="title">
    <h2>Leaderboard</h2>
    </div>
        <div class="rankings">
        <li v-for="user in data"
            v-bind:>
        </div>
</div>
</template>



<script>
import axios from 'axios'

export default{
    
    props: {
        apiURL: String
    },
    data() {
        return{

        }
    }
}

methods: {


}

computed: {

}
</script>



<style>

</style>
