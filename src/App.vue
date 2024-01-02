<template>
  <main>
    <header class="app-header">
      <img class="logo mt-5 pt-5" src="../src/assets/stickHouseLogo&NameCropped.png">
      <!-- <h1>Real-TE Management</h1>  -->
    </header>
    <div id="capstone-app">
      <nav  class="navbar navbar-expand-lg bg-body-tertiary fixed-top ">
        <div class="container-fluid d-flex justify-content-evenly">
          <ul class="nav nav-underline text-bg-light p-3 grid gap-0 column-gap-3">
          <!-- <router-link v-bind:to="{name: 'home' }">Home</router-link>&nbsp;|&nbsp;
          <router-link v-bind:to="{name: 'aboutUs'}">About Us</router-link>&nbsp;|&nbsp;
          <router-link v-bind:to="{name: 'availableProperties'}">See Rentals</router-link>&nbsp;|&nbsp;
          <router-link v-bind:to="{name: 'qualifications'}">Apply Here</router-link>&nbsp;|&nbsp;
          <router-link v-bind:to="{name: 'contactUs'}">Contact Us</router-link>&nbsp;|&nbsp; -->
          <!-- <router-link v-bind:to="{ name: 'logout' }" v-if="this.$store.state.token != ''">Logout</router-link> -->
          <li class="nav-item p-2 g-col-6">
            <a class="bi bi-house p-2 g-col-6 link-dark link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover" 
            href="/" target="_self"> Home</a>
          </li>
          <li class="nav-item p-2 g-col-6">
            <a class="bi bi-info-circle p-2 g-col-6 link-dark  link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover" 
            href="/about-us" target="_self"> About Us</a>
          </li>
          <li class="nav-item p-2 g-col-6">
            <a class="bi bi-search p-2 g-col-6 link-dark link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover" 
            href="/available-properties" target="_self"> Search Rentals</a>
          </li>
          <li class="nav-item p-2 g-col-6">
            <a class="bi bi-clipboard-plus p-2 g-col-6 link-dark link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover" 
            href="/qualifications" target="_self"> Apply Here</a>
          </li>
          <li class="nav-item p-2 g-col-6">
            <a class="bi bi-person-rolodex p-2 g-col-6 link-dark link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover" 
            href="/contact-us" target="_self"> Contact Us</a>
          </li>
          <li class="nav-item p-2 g-col-6">
            <a  class="bi bi-box-arrow-right p-2 g-col-6 link-dark link-offset-2 link-offset-3-hover link-underline link-underline-opacity-0 link-underline-opacity-75-hover" 
            href="/logout" target="_self" v-if="this.$store.state.token != ''" > Logout</a>
          </li>
          
        </ul>
        </div>
      </nav>
      
      <router-view />
    </div>
    </main>
  
</template>


<style>
@import 'bootstrap/dist/css/bootstrap.min.css';
@import url("https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.2/font/bootstrap-icons.min.css");
.logo {
      /* border-radius: 50%;
      max-width: 100%;
      height: 25vh;
      
      padding-right: 10px; */
      display: block;
      margin-left: auto;
      margin-right: auto;
      width: 50%;
    }

main{
  height: 100vh;
  margin: 25px;
  align-content: center;
}

button{
  margin: 10px;
}
.nav-link{
justify-content: center;
}
.link{
  text-decoration: none;
}

/* body {
        margin: 0;
    }

    /* Add CSS Grid here  background colors: Header - #f69a00;  Nav - #f2ead2;  
    main - #f9f5f0;  aside - #f2ead2;  footer - #331312;
    font colors header, footer - #ffffff;  nav, main, aside - #74889a;

    .container{
        display: grid;

        grid-template-areas: 
            "header header header" 
            "nav content side"
            "footer footer footer";

        grid-template-columns: 200px 1fr 200px;
        grid-template-rows: 125px 1fr 85px;
        gap: 10px;

        height: 100vh;


    }
    
    @media (max-width: 768px) {
        .container{
            grid-template-areas: 
                "header"
                "nav"
                "content"
                "side"
                "footer";
            grid-template-columns: 1fr;
            grid-template-rows:
                auto *//*Header 
                75px *//*Nav
                1fr *//* content */
                /*75px  Sidebar 
                auto; *//* Footer */
            
        /* }
        header, footer, nav, main, aside {
            padding: 5px 0;
        }
        nav, aside {
        margin: 0;
        }

    }

    

    .logo {
      border-radius: 50%;
      height: 10rem;
      padding: 10px;
    }

    header {
    grid-area: header;
    background-color: #6f828e;
    display: flex;
    }

    nav {
        grid-area: nav;        
        background-color: #f2ead2;
    }

    main {
        grid-area: content;
        background-color: #f9f5f0;
    }

    aside {
        grid-area: side;        
        background-color: #f2ead2;
    }

    footer {
        grid-area: footer;
        background-color: #41565a;
    }

    header, footer {
        color: #ffffff;
    }
    nav, main, aside {
        color: #74889a;
    }
    header, footer, nav, main, aside {
        font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        font-size: 24px;
        text-transform: uppercase;
    }

    div.content {
        text-align: center;
        padding-top: 20px;
    }

.btn {
  display: inline-block;
  font-weight: 400;
  color: #212529;
  text-align: center;
  vertical-align: middle;
  border: 1px solid transparent;
  padding: .375rem .75rem;
  margin-right: 10px;
  margin-bottom: 5px;
  font-size: 1rem;
  line-height: 1.5;
  border-radius: .25rem;
  cursor: pointer;
}

.btn-submit {
  color: rgba(255,255,255,0.7);
  background-color: #0062cc;
  border-color: #005cbf;
  text-decoration: none;
  padding: 6px 12px;
  cursor: pointer;
}

.btn-submit:hover {
  color: rgba(255,255,255,1.0);
  border-color: #fff;
}
fieldset {
  border: none;
}

.btn-cancel {
  color: rgba(255,255,255,0.7);
  background-color: #dc3545;
  border-color: #dc3545;
}

.btn-cancel:hover {
  color: rgba(255,255,255,1.0);
  border-color: rgba(255,255,255,1.0);
} */
</style>
