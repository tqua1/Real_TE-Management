<template>
  <div >
    <h3 class="qualifications-header">Please read qualifications of becoming a tenant at a Real-TE property before submitting an application.</h3>
   </div>
   <div class="qualifications">
    <p class="q-list">Must be 18 years old.</p>
    <p class="q-list"> Must have good credit/ rental history.</p>
    <p class="q-list">Must have first and last month rent.</p>
    <p class="q-list">Must have no prior evictions.</p>
    <p class="q-list">Must have not owe a balance to a past landlord.</p>
    <p class="q-list">Small cats and dogs only.</p>
     </div>
     <div class="application-link-to-register">
      <router-link class="btn btn-outline-secondary" v-bind:to="{name: 'register'}">Register to Apply</router-link>&nbsp;&nbsp;
      <router-link class="btn btn-outline-secondary" v-bind:to="{name: 'applicationForm'}">Rental Application</router-link>
    </div>


  </template>
  
  <script>
  
  export default {
  
  }
  </script>
  
  <style scoped>
  .btn.btn-light{
    margin-right: 10px;
  }
  .qualifications-header {
    display: flex;
    color: black;
    margin-top: 2%
  }
  .q-list {
    display: flex;
    border-bottom-style: groove;
    margin-bottom: 4%;
    width:40%;
    color: black;

  }
  .qualifications {
    padding: 1%;
    margin-top: 1%;
    margin-right: 50%; 
    

   border-color: rgb(193, 197, 202);
  }
  </style>