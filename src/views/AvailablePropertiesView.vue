<template>
  <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{ name: 'home' }"> 
    <i class="bi bi-chevron-left"></i> Back To Home</router-link>
  <h2>Search Rentals</h2>
  <div class="search-properties-form">
    <search-rentals-form class="form"/>
  
</div>
  
</template>

<script>
import SearchRentalsForm from '../components/SearchRentalsForm.vue';

export default {
  components: { SearchRentalsForm },
  data() {
  return {
      properties : []
  }
  },
}
</script>

<style scoped>
h2{
  display: flex;
 justify-content: center;
 padding-bottom: 20px;
}
.form{
  display: flex;
  justify-content: center;
}


</style>
