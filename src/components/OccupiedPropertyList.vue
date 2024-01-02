<template>
  <section>
    
    <div class="occupied-properties text-center"> 
      <h4 class="">Occupied Properties</h4>
      <property-container id="container" class="text-center " v-for="property in occupiedProperties" v-bind:property="property" v-bind:key="property.propertyId" v-bind:enable-add="true" />
    </div>
  </section>
</template>

<script>
import PropertyContainer from './PropertyContainer.vue'
import PropertyService from '../services/PropertyService';

export default {
  components: { PropertyContainer },
  computed: {
    occupiedProperties(){
        return this.$store.state.myProperties.filter((property)=>{
            return property.available === false;
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
  }
}
</script>

<style scoped>

.action-boards{
  display: grid;
  grid-template-columns: repeat(2, 1fr 100%);
  justify-items: center;
  gap: 20px;
}
</style>