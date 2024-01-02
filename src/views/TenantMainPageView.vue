<template>
  <header>Welcome</header>
  
<div id="tenant-service-boards">
  <div class="back-home-btn"> <router-link v-bind:to="{name: 'home'}">Back Home</router-link> </div>
  <router-link class="btn btn-submit" v-bind:to="{name: 'serviceRequest'}">Service Request</router-link>
  <router-link class="btn btn-submit" v-bind:to="{name: 'rentTransaction'}">Rent Transactions</router-link>
<tenant-home-sections  v-bind:tenantHomeSections="tenantHomeSection" />
 
</div>
<div >

</div>
</template>

<script>
import TenantHomeSections from '../components/TenantHomeSections.vue';
export default {
components: {
    TenantHomeSections
}
}
</script>

<style>
.btn-submit {
  color: rgba(25, 27, 29, 0.87);
  background-color: #b7cddb;
  border-color: #43677c;
  text-decoration: none;
  padding: 6px 12px;
  cursor: pointer;
}
</style>