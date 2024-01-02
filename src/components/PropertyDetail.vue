<template>
    <div class="property-details-section">
      <div class="property-details"   >
    <!-- <property-container v-bind:property="property" v-for="property in this.$store.state.properties" v-bind:key="property.propertyId" v-for="property in $store.state.properties" v-bind:key="property.propertyId"/> -->
        <h2 class="header">Address: {{ property.address }}</h2>
        <h3>Property ID: {{ property.propertyId }}</h3>
        <div>Manager ID: {{ property.managerId }} </div>
        <div>Number of Rooms: {{ property.numberOfRooms }}</div>
        <div>Property is listed as: {{ displayAvailability() }}</div> 
        <div>Current Rent: ${{ property.rent }}</div>
      </div>
    </div>
  </template>
  
  <script>
  
  export default {
    
    props: ['property'],
    methods: {
          setOccupied(value) {
              this.$store.commit('SET_AVAILABILITY_STATUS', { property: this.property, value: value });
          },
          displayAvailability() {
            if (this.property.available) {
              return "available."
            } else {
              return "unavailable/occupied."
            }
          }
    },
    created(){
      if (this.$store.state.user.authorities[0].name == 'ROLE_ADMIN') {
        this.$store.commit('SET_SHOW_MANAGER_POV', true);
      }
    }
  }
  
  </script>
  
  <style>
  </style>