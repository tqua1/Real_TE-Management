<template>
  <section>

    <div class="available-properties ">
      <h4 class="">Available Properties</h4>
     <property-container class="text-center justify-content-center" v-for="property in availableProperties" v-bind:key="property.propertyId" v-bind:property="property"></property-container> 
  </div>
  </section>
</template>

<script>
import PropertyService from '../services/PropertyService';
import PropertyContainer from './PropertyContainer.vue';

export default {
  components: { PropertyContainer },
  computed: {
    availableProperties() {
        return this.$store.state.myProperties.filter((property)=> {
            return property.available == true;
        });
    }
},
  created() {
    PropertyService.getAllMyProperties().then(response => {
      this.$store.commit('SET_MY_PROPERTIES', response.data);
    }).catch(error => {
      if (error.response && error.response.status === 404) {
        this.$store.commit('SET_NOTIFICATION', `Error getting properties.`);
      }
    });
    if (this.$store.state.user.authorities[0].name == 'ROLE_ADMIN') {
      this.$store.commit('SET_SHOW_MANAGER_POV', true);
    }
  }
}
</script>

<style scoped>
.action-boards{
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  justify-items: center;
  gap: 20px;
}

</style>