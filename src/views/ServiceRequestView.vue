<template>
    <div>
      <div class="back-to-tenant-home">
        <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{name:'home'}"> 
          <i class="bi bi-chevron-left"></i>
          Back to Home Page</router-link>
      </div>
      <div class="header">
        <h1>Service Requests</h1>
        <div class="add-new-service-request">
        <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-show="!this.$store.state.showManagerPOV" v-bind:to="{name:'addServiceRequest'}">
          <i class="bi bi-plus-lg"></i>Add New Request</router-link>
      </div>
    </div>
    <div class="action-boards">
        <service-request-section title="Open"  v-bind:serviceRequests="open"/>
        <service-request-section title="In Progress"  v-bind:serviceRequests="inProgress"/>
        <service-request-section title="Complete" v-bind:serviceRequests="complete"/>
    </div>
  </div>
</template>

<script>
import ServiceRequestSection from '../components/ServiceRequestSection.vue'
import serviceRequestService from '../services/ServiceRequestService';

export default {
  components: {
    ServiceRequestSection
  },
  data() {
      return {
          serviceRequestList: { title: '', serviceRequests: [] }
      };
      
  },
  computed: {
    open() {
      return this.$store.state.serviceRequests.filter(serviceRequest => serviceRequest.status === 'STATUS_OPEN');
    },
    inProgress() {
      return this.$store.state.serviceRequests.filter(serviceRequest => serviceRequest.status === 'STATUS_IN_PROGRESS');
    },
    complete() {
      return this.$store.state.serviceRequests.filter(serviceRequest => serviceRequest.status === 'STATUS_COMPLETE');
    }
  },
  methods: {
    refresh(){
      serviceRequestService
          .getAllMyServiceRequests()
          .then(response => {
            this.$store.commit('SET_SERVICE_REQUEST', response.data);
          })
          .catch(error => {
            if (error.response && error.response.status === 404){
              this.$store.commit('SET_NOTIFICATION', `Error getting service request. This service request may have been deleted or you have entered an invalid service request ID.`);
              this.$router.push({ name: 'serviceRequest' });
            }
          });
    }
    
  },
  created(){
    serviceRequestService
      .getAllMyServiceRequests()
      .then(response => {
        this.$store.commit('SET_SERVICE_REQUEST', response.data);
      })
      .catch(error => {
        if (error.response && error.response.status === 404){
          this.$store.commit('SET_NOTIFICATION', `Error getting service request. This service request may have been deleted or you have entered an invalid service request ID.`);
          this.$router.push({ name: 'serviceRequest' });
        }
      });
    if (this.$store.state.user.authorities[0].name == 'ROLE_ADMIN') {
      this.$store.commit('SET_SHOW_MANAGER_POV', true);
    }
  }
}
</script>

<style>

.action-boards{
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 20px;
}
.header{
  display: flex;
  align-items: center;
}
.header h1 {
  flex-grow: 1;
}
service-request-actions {
  display: flex;
  
}
</style>