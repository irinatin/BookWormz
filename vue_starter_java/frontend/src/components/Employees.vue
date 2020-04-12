<template>
    <div class="employees">
        <h2>{{title}}</h2>
        <ul>
            <li>
<<<<<<< HEAD
                <img v-bind:src="data.photo" />
            </li>
            <li v-for="isbn in data.adult" v-bind:isbn="book.isbn" >
=======
                 <img v-bind:src="data.photo" alt="employee photo"/>
>>>>>>> 5d1bc86e46f040c68d7aa39916489a7d907f0236
                
                
            </li>
            <!-- <li v-for="isbn in data.adult" v-bind:isbn="book.isbn" > -->
               
             
            
        </ul>
    </div>
</template>

<script>
import image from '../views/assets/BookWorm.jpg'

export default {
    
    name: 'employees',
    props: {
        title: String,
        data: Array
    },
    data() {
        return {
<<<<<<< HEAD
    photo : this.data.photo,
    book: {
        isbn: "",
        title: "",
        author: "",
        thumbnail: ""
      }  
=======
            photo: "../views/assets/BookWorm.jpg",
            book: {
                isbn: "",
                title: "",
                author: "",
                thumbnail: ""
            }  
>>>>>>> 5d1bc86e46f040c68d7aa39916489a7d907f0236
        }
    },
    methods: {
       searchBooks(isbn) {
        axios
          .get(
            "https://openlibrary.org/api/books?format=json&jscmd=data&bibkeys=ISBN:" +
              this.book.isbn
          )

          .then(response => {
            console.log(response.data);

           
            let tempIsbn = this.book.isbn;

            this.book.title = response.data["ISBN:" + tempIsbn].title;
            this.book.author =
              response.data["ISBN:" + tempIsbn].authors[0].name;
            this.book.thumbnail = response.data["ISBN:" + tempIsbn].cover.small;
            
            
          })
          .catch(error => {
            console.log(error + " there was an error");
          });
    },
       }  
}


</script>

<style>
.shopping-list {
    width:450px;
    background: #fff;
    margin: 50px auto;
    font-family: 'Roboto Condensed', sans-serif;
    border-radius: 10px;
}
h2{
    background:#f2f2f2;
    color:#5c8578;
    padding:10px;
    font-size:24px;
    text-transform: uppercase;
    text-align: center;
    margin: 0px;
    border-top-left-radius: 10px;
    border-top-right-radius: 10px;
}
ul {
    list-style-type: none;
    margin:0px;
    padding:0px;
}
li {
    font-size: 24px;
    border-bottom:1px solid #f2f2f2;
    padding:10px 20px;
    cursor: pointer;
}
li:last-child{
    border:0px;
}
li.completed {
    text-decoration: line-through;
    color:darkgrey;
}
i.far.fa-check-circle { 
    float:right;
    color: darkgray;
}

i.far.fa-check-circle.completed {
    color:green;
}
input[type="checkbox"] {
    font-size:40px;
    vertical-align: middle;
    margin-top:0px;
}
</style>
