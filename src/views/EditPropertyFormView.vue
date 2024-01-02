<template>
  <router-link class="btn btn-submit" v-bind:to="{ name: 'propertyManagerMainPage' }">Property Manager Home</router-link>
<header>Edit This Property </header>
<div class="property-form">
<new-property-form v-bind:property="property"/>
</div>
</template>

<script>
import NewPropertyForm from '../components/NewPropertyForm.vue';
import PropertyService from '../services/PropertyService';

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
created() {
  let propertyId = parseInt(this.$route.params.propertyId)
  if (propertyId != 0) {
      PropertyService.getPropertyById(this.propertyId).then(response => {
          this.property = response.data;
      }).catch(error => {
          if (error.response && error.response.status === 404) {
              this.$store.commit('SET_NOTIFICATION', `Error getting property ${propertyId}. This property may have been deleted or you have entered an invalid property ID.`)
              this.$router.push({ name: 'propertyManagerMainPage' })
          }
      })
  }
}

}
</script>

<style>

</style>