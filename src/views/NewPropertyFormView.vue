<template>
<div class="d-grid gap-3 d-md-flex justify-content-md-start ">
  <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{ name: 'home' }"><i class="bi bi-chevron-left"></i> Back To Home</router-link>
  <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{ name: 'propertyManagerMainPage' }"><i class="bi bi-chevron-left"></i>  My Properties</router-link>
  </div>
  <h1 class="mt-3">Add New Property </h1>
  <div class="property-form">
    <new-property-form v-bind:property="property"/>
  </div>
</template>

<script>
import NewPropertyForm from '../components/NewPropertyForm.vue';

export default {
  components: { NewPropertyForm},
  data() {
    return {
      property: {
        propertyId: 0,
        managerId: '',
        address: '',
        numberOfRooms: '',
        rent: '',
        available: ''
      }
    }
  },
}
</script>

<style>

</style>