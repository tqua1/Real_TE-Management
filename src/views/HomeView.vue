<template class="main-page">
  <div class="home">
    <h1 class="home-header">Home</h1>
    <p></p>
  </div>
  <div class="manager-home-btn d-grid gap-3 d-md-flex justify-content-md-center " > 
    <!-- <router-link class="btn btn-primary" v-show="this.$store.state.showManagerPOV" v-bind:to="{ name: 'propertyManagerMainPage' }">My Properties</router-link>
    <router-link class="btn btn-primary" v-show="this.$store.state.showManagerPOV" v-bind:to="{ name: 'application' }">View Applications for My Properties</router-link>
    <router-link class="btn btn-primary" v-show="this.$store.state.showManagerPOV" v-bind:to="{ name: 'serviceRequest' }">See Service Requests</router-link> -->
    <a class="btn btn-outline-secondary text-bg-light p-3 position-relative" href="/property-manager-main-view" target="_self" v-show="this.$store.state.showManagerPOV"><i class="bi bi-houses"></i> My Properties </a> &nbsp;&nbsp;
   
    <a class="btn btn-outline-secondary text-bg-light p-3 position-relative" href="/application-view" target="_self"  v-show="this.$store.state.showManagerPOV">
      <i class="bi bi-clipboard-plus"></i> View Applications for My Properties</a> &nbsp;&nbsp;
    
    <a class="btn btn-outline-secondary text-bg-light p-3 position-relative" href="/service-request-view" target="_self" v-show="this.$store.state.showManagerPOV" >
      <i class="bi bi-search"></i> See Service Requests</a>
  </div>


  <div class="tenant-home-btn d-grid gap-3 d-md-flex justify-content-md-center " > 
    <!-- <router-link  v-show="!this.$store.state.showManagerPOV" class="btn btn-submit" v-bind:to="{name: 'tenantMainPage'}">Tenant Home</router-link> -->
    <!-- <router-link class="btn btn-primary" v-show="!this.$store.state.showManagerPOV" v-bind:to="{name: 'serviceRequest'}">My Service Requests</router-link>
    <router-link class="btn btn-primary" v-show="!this.$store.state.showManagerPOV" v-bind:to="{ name: 'application' }">My Applications</router-link>
    <router-link class="btn btn-primary" v-show="!this.$store.state.showManagerPOV" v-bind:to="{name: 'rentTransaction'}"> My Rent Transactions</router-link> -->

    <a class="btn btn-outline-secondary text-bg-light p-3 position-relative"   href="/service-request-view" target="_self" v-show="!this.$store.state.showManagerPOV"> 
      <i class="bi bi-search"></i> My Service Requests</a>
    <a class="btn btn-outline-secondary text-bg-light p-3 position-relative"  href="/application-view" target="_self" v-show="!this.$store.state.showManagerPOV" >
      <i class="bi bi-clipboard-plus"></i> My Applications</a>
    <a class="btn btn-outline-secondary text-bg-light p-3 position-relative"   href="/rent-transaction-view" target="_self" v-show="!this.$store.state.showManagerPOV">
      <i class="bi bi-currency-dollar"></i> My Rent Transactions</a>
  </div>


  <div id="carouselExample" class="carousel slide">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="../assets/Property Photos/pexels-max-rahubovskiy-7031408.jpg" class="d-block w-100" alt="pexels-max-rahubovskiy-7031408.jpg">Max Rahubovskiy
    </div>
    <div class="carousel-item">
      <img src="../assets/Property Photos/pexels-max-rahubovskiy-7031608.jpg" class="d-block w-100" alt="pexels-max-rahubovskiy-7031608.jpg">
    </div>
    <div class="carousel-item">
      <img src="../assets/Property Photos/pexels-myburgh-roux-3081701.jpg" class="d-block w-100" alt="pexels-myburgh-roux-3081701.jpg">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Previous</span>
  </button>
  <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>
</template>

<script>
import PropertyService from '../services/PropertyService';

export default {
  created() {
    if (this.$store.state.user.authorities[0].name == 'ROLE_ADMIN') {
     
      this.$store.commit('SET_SHOW_MANAGER_POV', true);
     
    }
    PropertyService.getAllMyProperties().then(response => {
      this.$store.commit('SET_MY_PROPERTIES', response.data);
    });
    PropertyService.getProperties().then(response => {
      this.$store.commit('SET_PROPERTIES', response.data);
    });
  } 
};
</script>

<style >
.manager-home-btn{
display: flex;
margin: 10px;
padding: 10px;
justify-content: center;


}

.tenant-home-btn{
display: flex;
margin: 10px;
padding: 10px;
justify-content: center;

}
.btn-btn-outline-primary-btn-lg {
  padding-left: 10px;
  margin-left: 10px;
  justify-content: center;
}
.home-header{
  display: flex;
  justify-content: center;
}



</style>