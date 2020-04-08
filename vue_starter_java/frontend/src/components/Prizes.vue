<template>
<div>
    <div class="title">
        <h2>PRIZES!</h2>
    </div>

        <div class="active_prizes">
        <!-- Display all current prizes -->
        </div>

            <div class="create_new_prize">
                <form class="new_prize_form">
                    <label for="prize_name">Prize Name</label><br>
                    <input type="text" id="prize_name" name="prize_name" placeholder="Enter Prize Name"><br>
                    <label for="description">Description</label><br>
                    <input type="text" id="description" name="description" placeholder="Enter Prize Description"><br>
                    <label for="milestone">Milestone (minutes reading)</label><br>
                    <input type="text" id="milestone" name="milestone" placeholder="Enter Milestone"><br>
                    <label for="user_group">User Group (Parent/Child)</label><br>
                    <input type="text" id="user_group" name="user_group" placeholder="Enter User Group"><br>
                    <label for="max_prizes">Prize Cap</label><br>
                    <input type="text" id="max_prizes" name="max_prizes" placeholder="Enter Prize Cap"><br>
                    <label for="start_date">Start Date</label><br>
                    <input type="text" id="start_date" name="start_date" placeholder="Enter Prize Start Date"><br>
                    <label for="end_date">End Date</label><br>
                    <input type="text" id="end_date" name="end_date" placeholder="Enter Prize End Date"><br>
            </div>

                <div class="edit_active_prize">

                </div>
</div>
</template>



<script>

import axios from 'axios'

export default{
    props:{

    },
    
    data() {
        return{

        }
    }
},

methods:{

},

computed: {

},
</script>



<style>

</style>
