<!-- ADD APPLICATION VIEW -->
<template>
  <div class="application-header">
    <header>Real-TE Tenant Application</header>
    <application-form v-bind:application="application"/>
  </div>
</template>

<script>
import ApplicationForm from '../components/ApplicationForm.vue';

export default {
    components: {
        ApplicationForm
    },
    data() {
      return {
        application: {
          applicationId: 0,
          propertyId: 0,
          fullName: '',
          email: '',
          hasRoomates: false,
          roomateNames: '',
          status: 'STATUS_PENDING'
        }
      }
    }
}
</script>

<style>

</style>