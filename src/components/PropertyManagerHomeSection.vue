<template>
  <div class="properties-board">
    <div class="available-properties-board" v-for="property in $store.state.properties" v-bind:key="property.propertyId" v-on:click="viewpropertytDetails(propertyId)">
    <h2>Available Property</h2>
    
    </div>
    <div class="properties-board" v-for="property in $store.state.properties" v-bind:key="property.propertyId" v-on:click="viewpropertytDetails(propertyId)">
    <h2>Occupied Properties</h2>
    
    
  </div>
  </div>
</template>

<script>
export default {
props: ['propertyId']
}
</script>

<style>
.properties-board {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 20px;
}
</style>