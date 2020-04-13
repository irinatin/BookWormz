<template>
  <div class="home">
    <section class="hero is-primary">
    <div class="container">
        <div class="columns">
          <div class="column hero-body" >
            <h1 class= "title">{{family[0].familyName}} Family Dashboard</h1>
          </div>
        </div>
    </div>
    </section>

    <section>
    <div class="container">
        <div class="columns tile is-ancestor has-text-centered">
          
          <div class="column is-parent box-margin purple ">
            <div class="tile is-child box has-background-primary">
               <family></family>
            </div>
          </div>
          
          <div class="column is-parent box-margin">
            <div class="tile is-child box">
                <leaderboard></leaderboard>
             </div>
          </div>

           <div class="column is-parent box-margin">
            <div class="tile is-child box">
                <reading-activity></reading-activity>
                <prizes-per-user></prizes-per-user>
            </div>
          </div>

          <div class="column is-parent box-margin">
            <div class="tile is-child box">
              <friends>friends</friends>
             </div>
          </div>

        </div>    
     </div>
</section>
 
    <div class="container box has-text-centered">
        <div class="columns card-content">
          <div class="column tile is-child box">
            <books></books>
          </div>

          <div class="column is-child box tile ">
            <reading-event></reading-event>
          </div>
        </div>
    </div>           
  
    <div class="container box">
        <div class="columns card-content">
          <div class="column content">
            <prizes></prizes>
          </div>
        </div>
     </div>

     <div class="container box">
       <div class="columns card-content">
         <div class="column content">
           <prize-list></prize-list>
         </div>
       </div>
     </div>
</div>
</template>

<script>
import axios from "axios";
import Family from '../components/Family';
import ReadingEvent from '../components/ReadingEvent';
import Books from '../components/Books';
import Prizes from '../components/Prizes';
import PrizeList from '../components/PrizeList';
import Leaderboard from '../components/Leaderboard';
import ReadingActivity from '../components/ReadingActivity';
import Friends from '../components/Friends';
import PrizesPerUser from '../components/PrizesPerUser';


export default {
  name: 'home',
  components: {
    Family,
    ReadingEvent,
    Books,
    Prizes,
    PrizeList,
    Leaderboard,
    ReadingActivity,
    Friends,
    PrizesPerUser
  },
  data() {
    return {    
      family: {},
    }
  },
   created() {
    axios
      .get(`${process.env.VUE_APP_REMOTE_API}/api/getFamilyList`, {
        headers: {
          Authorization: "Bearer " + localStorage.getItem("Authorization")
        }
      })
      .then(response => {
        this.family = response.data;
      })
      .catch(error => {
        console.log(error + " there was an error");
      });
     
      
  },
  
}
</script>

<style scoped>
  .box-margin {
    margin-top: 3%;
    margin-bottom: 2%;
  }
  .purple {
    background-color: #b366ff;
  }
/* body {
  margin: 0px;
  padding: 400px 100px;
  background: linear-gradient(to bottom, rgba(102, 255, 102, 0.7), rgba(0, 128, 0, 0.25));
  background-size: cover;
  background-position: center;
  font-family: sans-serif;
  height: 100%;
} */
</style>