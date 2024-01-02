<template>
  <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{ name: 'home' }" >
    <i class="bi bi-chevron-left"></i>
    Back To Home
  </router-link>
  <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{ name: 'propertyManagerMainPage' }"> <i class="bi bi-chevron-left"></i> My Properties</router-link>
  <header>Search Properties</header>
  <div class="search-properties-form">
    <property-search-form v-bind:properties="properties"/>
  </div>
</template>

<script>
import PropertySearchForm from '../components/PropertySearchForm.vue';

export default {
  components: { PropertySearchForm },
  data() {
    return {
       properties : []
    }
  },
}
</script>

<style>

</style>
