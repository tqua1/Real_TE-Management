<template>
  <div class="property-container" >
    <h5>
      <router-link class="link" v-bind:to="{name: 'propertyDetails', params: { propertyId: property.propertyId }}" >
        <p>#{{ property.propertyId }}:</p> 
        {{ property.address }}  <!-- -->
      </router-link> 
    </h5> 
    <p></p>
    <div class="button-container" > 
       
    <!-- <button v-if="! enableAdd" v-on:click.prevent="addToOccupiedList(property)">Add to Occupied Property List  </button> -->
    </div>

  </div>
</template>

<script>

export default {
  props: 
  {
    property: Object,
    enableAdd: {
        type: Boolean,
        default: false
    }
  },
  created(){
    if (this.$store.state.user.authorities[0].name == 'ROLE_ADMIN') {
      this.$store.commit('SET_SHOW_MANAGER_POV', true);
    }
  }
}
</script>

<style scoped>
 .property-container{
  display: grid;
  /* flex-direction: column; */
  justify-content: space-evenly;
  flex-wrap: wrap;
  background-color: #889eaa;
  border-radius: 30px;
  padding: 10 10px 10px 10px;
  margin-bottom: 10px;
  width: 600px;
} 

.link {
  text-decoration: none;
  color: rgb(246, 238, 238);
  
}
.link:hover {filter: brightness(2);}
</style>