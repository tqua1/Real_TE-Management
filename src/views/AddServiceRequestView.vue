<!-- similar to AddCardView -->

<template>
  <div>
    <h1>New Service Request</h1>
    <service-request-form v-bind:serviceRequest="serviceRequest"/>
  </div>
</template>

<script>
import ServiceRequestForm from '../components/ServiceRequestForm.vue';
import serviceRequestService from '../services/ServiceRequestService';

export default {
  components: { 
    ServiceRequestForm
  },
  data() {
    return {
      serviceRequest: {
        id:0,
        requestDetails: '',
        status: 'STATUS_OPEN'
      }
    }
  },

}
</script>

