<template>
    <form v-on:submit.prevent="submitForm" class="serviceRequestForm">
      <div class="info-field">
          <label for="title">Title:</label>
           <input id="title" type="text" class="form-control" v-model="editServiceRequest.title"/>
      </div>
      <div class="info-field">
          <label for="requestDetails">Request Details:</label>  
          <textarea id="requestDetails" class="form-control" v-model="editServiceRequest.requestDetails"></textarea>
      </div>
      <div class="serviceRequestButton">
          <button class="btn btn-outline-primary" type="submit">Submit</button>
          <button class="btn btn-outline-secondary" type="button" v-on:click="cancelForm">Cancel</button>
      </div>
      
    </form>
  </template>
  
  <script>
  import serviceRequestService from '../services/ServiceRequestService';
  
  export default {
      props: {
          serviceRequest: {
              type: Object, 
              required: true
          }
      },
      data() {    
      return {
          editServiceRequest: {
              id: this.serviceRequest.id,
              title: this.serviceRequest.title,
              requestDetails: this.serviceRequest.requestDetails,
              status: this.serviceRequest.status
          }
      };
      },
      methods: {
          submitForm(){
              // if(!this.validateForm()){
              //     return;
              // }
              if(this.editServiceRequest.id === 0){
                  serviceRequestService
                      .addServiceRequest(this.editServiceRequest)
                      .then(response => {
                          if(response.status === 201 || response.status === 200){
                              this.$store.commit(
                                  'SET_NOTIFICATION',
                                  {
                                      message: 'A new service request was added.',
                                      type: 'success'
                                  }
                              );
                              this.$router.push({ name: 'serviceRequest' });
                          }
                      })
                      .catch(error => {
                          this.handleErrorResponse(error, 'adding');
                      });
              } else {
                  serviceRequestService
                      .updateServiceRequest(this.editServiceRequest)
                      .then(response => {
                          if(response.status === 200){
                              this.$store.commit(
                                  'SET_NOTIFICATION',
                                      {
                                          message: `Service request ${this.editServiceRequest.id} was updated.`,
                                          type: 'success'
                                      }
                              );
                              this.$router.push({ name: 'serviceRequest' });
                          }
                      })
                      .catch(error => {
                          this.handleErrorResponse(error, 'updating');
                      });
              }
          },
          cancelForm(){
              this.$router.push({ name: 'serviceRequest', params: { serviceRequestId: this.editServiceRequest.id } });
          },
          handleErrorResponse(error, verb){
              if(error.response){
                  this.$store.commit('SET_NOTIFICATION', "Error " + verb + " service request. Response received was '" + error.response.statusText + "'.");
              } else if(error.request){
                  this.$store.commit('SET_NOTIFICATION', "Error " + verb + " service request. Server could not be reached.");
              } else {
                  this.$store.commit('SET_NOTIFICATION', "Error " + verb + " service request. Request could not be created.");
              }
          },
          // validateForm(){
          //     let msg = '';
          //     if(this.editServiceRequest.title.length === 0){
          //         msg += 'The new service request must have a title.';
          //     }
          //     if(this.editServiceRequest.details.length === 0){
          //         msg += 'The new service request must have a details.';
          //     }
          //     if(msg.length > 0){
          //         this.$store.commit('SET_NOTIFICATION', msg);
          //         return false;
          //     }
          //     return true;
          // }
      }
  }
  </script>
  
  <style>
  
  .serviceRequestForm {
    padding: 10px;
    margin-bottom: 10px; 
  }
  label {
    display: inline-block;
    margin-bottom: 0.5rem;
  }
  .info-field {
    margin-bottom: 10px;
    margin-top: 10px;
    font-family: Arial, Helvetica, sans-serif;
  }
  .form-control {
    display: block;
    width: 80%;
    height: 30px;
    padding: 0.375rem 0.75rem;
    font-size: 1rem;
    font-weight: 400;
    line-height: 1.5;
    color: #495057;
    border: 1px solid #ced4da;
    border-radius: 0.25rem;
  }
  </style>