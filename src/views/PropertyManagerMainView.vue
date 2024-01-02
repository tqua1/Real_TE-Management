<template>
  <main>
    <h2 class="mb-5 text-center" >My Properties</h2>
    <div class="d-grid gap-3 d-md-flex justify-content-md-center ">
      <!--  d-flex justify-content-around -->
      <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{ name: 'home' }" >
        <i class="bi bi-chevron-left"></i>
        Back To Home
      </router-link>
      <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{ name: 'propertySearch' }">
        <i class="bi bi-search"></i> 
        Search My Properties 
      </router-link>
      <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{ name: 'addProperty', params: {propertyId: 0 } }">
        <i 
        class="bi bi-plus-lg "
        ></i>
        Add Property
      </router-link>
      
    </div>
    
    <div class="text-center action-boards">
      
      <available-property-list id="container" class="mt-4 text-center action-boards"/>
      
      <occupied-property-list id="container" class="mt-4 text-center action-boards"/>
    </div>
      
  </main>
  <!-- <router-link class="btn btn-primary" v-bind:to="{ name: 'serviceRequest' }">See Service Requests</router-link> -->
  <!-- <router-link class="btn btn-primary" v-bind:to="{name: 'application'}">See Applications</router-link> -->
  
</template>

<script>
import AvailablePropertyList from '../components/AvailablePropertyList.vue';
import OccupiedPropertyList from '../components/OccupiedPropertyList.vue';

export default{
  components: { AvailablePropertyList, OccupiedPropertyList },
}
</script>

<style scoped>
/* #container {
  display: flex;
  justify-content: center;
} */
/* #container{
  display: grid;
  grid-template-columns: repeat(1, 1fr);
  gap: 20px;
} */
.action-boards{
  display: flex;
  justify-content: space-evenly;
  grid-template-columns: repeat(2, 1fr);
  justify-items: center;
  gap: 20px;
}
/*.btn-submit {
  color: rgba(25, 27, 29, 0.87);
  background-color: #b7cddb;
  border-color: #43677c;
  text-decoration: none;
  padding: 6px 12px;
  cursor: pointer;
}
.btn-cancel {
  color: rgba(255,255,255,0.7);
  background-color: #b65d65;
  border-color: #b65d65;
  padding: 6px 12px;
}
 header, footer, nav, main, aside {
        font-family:'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
        font-size: 24px;
        text-transform: uppercase;
    } */
</style>