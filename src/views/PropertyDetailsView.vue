<template>
 <h1>Property Details</h1>
 <div class="back-to-management-home">
  <nav  class="navbar navbar-expand-md bg-body-tertiary ">
    <div class="container-fluid">
      <a class="bi bi-chevron-left" href="/property-manager-main-view" target="_self">Back</a>
    </div>
  </nav>
   <!--<router-link  v-bind:to="{name:'propertyManagerMainPage'}"> to Property Manager Home</router-link>  
  class="btn btn-submit"  -->
 </div>
  <div class="property-details">
<property-detail  v-bind:property="property"/>
<router-link class="btn btn-submit" v-bind:to="{ name: 'editProperty' }">
  <i class="bi bi-pen"></i>
  Edit Property</router-link>
  </div>
  
</template>

<script>
import PropertyDetail from '../components/PropertyDetail.vue';
import PropertyService from '../services/PropertyService';

export default {
  components: {PropertyDetail},
  data() {
    return {
      property: {
        propertyId: '',
        managerId: '',
        address: '',
        numberOfRooms: '',
        rent: '',
        isAvailable: ''
      }
    };
  },
  created() {
    PropertyService.getPropertyById(this.$route.params.propertyId).then(response => {
      this.property = response.data;
    });
  }
}
</script>

<style>

</style>