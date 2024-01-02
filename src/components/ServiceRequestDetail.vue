<template>
<router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{ name: 'serviceRequest'}">
  <i class="bi bi-chevron-left"></i>Back to Service Requests</router-link>
<div class="card">

  <h1>Service Request Details</h1>
  <p class="info">Tenant ID:  {{ serviceRequest.serviceRequestId }} </p>
  <p class="info">Status: {{ serviceRequest.status }}</p>
  <p>Details: {{ serviceRequest.requestDetails  }}</p>
  <p>
        <select v-model="editServiceRequest.status" v-on:change="updateServiceRequest" v-show="this.$store.state.showManagerPOV">
            <option value="inProgress">In Progress</option>
            <option value="complete">Complete</option>
        </select>
      <button class="btn btn-outline-danger" v-on:click="deleteServiceRequest" v-show="!this.$store.state.showManagerPOV">Delete Service Request</button>
  </p>
</div>                                              
  </template>
  
  <script>
  import serviceRequestService from '../services/ServiceRequestService';
  
  export default {
      props: ['serviceRequest'],
      data(){
        return{
        editServiceRequest: {
              serviceRequestId: this.$route.params.serviceRequestId,
              title: this.serviceRequest.title,
              requestDetails: this.serviceRequest.requestDetails,
              status: this.serviceRequest.status
          }
        }
      },
      methods: {
          deleteServiceRequest(){
          if(confirm("Are you sure you want to delete this service request? This action cannot be undone.")){
              serviceRequestService
              .deleteServiceRequest(this.serviceRequest.serviceRequestId)
              .then(response => {
              if(response.status === 204){
                  this.$store.commit('SET_NOTIFICATION',
                  {
                  message: 'Service request has been deleted',
                  type: 'success'
                  });
                  this.$router.push({ name: 'serviceRequest'});
              }
              }).catch(error => {
              if(error.response){
                  this.$store.commit('SET_NOTIFICATION',
                  "Error deleting service request. API Server could not be reached.");
              }else {
                  this.$store.commit('SET_NOTIFICATION',
                  "Error deleting service request. Request could not be executed.");
              }
              });
          }
          },
          getServiceRequestById(){
            serviceRequestService.getServiceRequestById(this.$route.params.serviceRequestId)
            .then(response => {
                this.editServiceRequest = response.data;
            })
            .catch(error => {
            if(error.response){
                this.$store.commit('SET_NOTIFICATION',
                "Error deleting service request. API Server could not be reached.");
            }else {
                this.$store.commit('SET_NOTIFICATION',
                "Error deleting service request. Request could not be executed.");
            }
        });
          },
          updateServiceRequest(){
            let status = this.editServiceRequest.status;
            if(status === 'inProgress'){
                this.editServiceRequest.status = 'STATUS_IN_PROGRESS';
            } else if (status === 'complete'){
                this.editServiceRequest.status = 'STATUS_COMPLETE';
            }
            serviceRequestService
            .updateServiceRequest(this.editServiceRequest)
            .then(response => {
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
            if(response.status === 200){
                this.$store.commit('SET_NOTIFICATION',
                {
                    message: 'Service request updated',
                    type: 'success'
                });
            }
            }).catch(error => {
            if(error.response){
                this.$store.commit('SET_NOTIFICATION',
                "Error deleting service request. API Server could not be reached.");
            }else {
                this.$store.commit('SET_NOTIFICATION',
                "Error deleting service request. Request could not be executed.");
            }
          });
          this.$router.push({name: 'serviceRequest'})
      },

    } ,
    created(){
      this.getServiceRequestById();
      if (this.$store.state.user.authorities[0].name == 'ROLE_ADMIN') {
        this.$store.commit('SET_SHOW_MANAGER_POV', true);
      }
    }
  
  }
  </script>
  
  <style scoped>
.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  max-width: 500px;
  margin: auto;
  text-align: center;
  font-family: arial;
  padding: 25px;
}

.info {
  color: grey;
  font-size: 22px;
}

.card button:hover {
  opacity: 0.7;
}
  </style>