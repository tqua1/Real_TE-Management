<template>
  <form v-on:submit.prevent="submitForm" class="applicationForm">
    <div class="field">
        <div class="info-field">
            <label for="fullName">Full Name:</label>
            <input type="text" class="form-control" id="fullName" name="fullName" v-model="editApplication.fullName" placeholder="Ex: Harry Potter">
        </div>
        <div class="info-field">
            <label for="propertyId">Property ID:</label>
            <input type="number" class="form-control" id="propertyId" name="propertyId" v-model="editApplication.propertyId" placeholder="Ex: 4001">
        </div>
        <div class="info-field">
            <label for="email">Email:</label>
            <input type="email" id="email" class="form-control" name="email" v-model="editApplication.email" placeholder="name@email.com">
        </div>
        <div class="form-check form-switch">
          <label for="hasRoomates">Roomates?</label>
          <input type="checkbox" class="form-check-input" id="hasRoomates" v-model="editApplication.hasRoomates">
        </div>
        <div class="info-field">
            <label for="roomateNames">Names of Roomates: </label>
            <input type="text" class="form-control" id="roomateNames" name="roomateNames" v-model="editApplication.roomateNames">
        </div>
        <div class="applicationButton">
            <button type="submit" class="btn btn-outline-primary">Submit</button>
            <button class="btn btn-outline-secondary" type="button" v-on:click.prevent="cancelForm">Cancel</button>
        </div>
    </div>

  </form>
</template>

<script>
import applicationService from '../services/ApplicationService';

export default {
    props: {
        application:{
            type: Object,
            required: true
        },
    },
    data() {
        return {
            editApplication: {
              id: this.application.applicationId,
              propertyId: this.application.propertyId,
              fullName: this.application.fullName,
              email: this.application.email,
              hasRoomates: this.application.hasRoomates,
              roomateNames: this.application.roomateNames,
              status: this.application.status 
            }
        };
    },
    methods: {
      submitForm(){
        if(this.editApplication.id === 0){
          applicationService
            .addApplication(this.editApplication)
            .then(response => {
              if(response.status === 201 || response.status === 200){
                this.$store.commit(
                  'SET_NOTIFICATION',
                  {
                    message: 'A new application was submitted',
                    type: 'success'
                  }
                );
                this.$router.push({ name: 'home' });
              }
            })
            .catch(error => {
              this.handleErrorResponse(error, 'adding');
            });
        }

      },
      cancelForm() {
        this.$router.push({ name: 'home' });
      },
      handleErrorResponse(error, verb){
            if(error.response){
                this.$store.commit('SET_NOTIFICATION', "Error " + verb + " service request. Response received was '" + error.response.statusText + "'.");
            } else if(error.request){
                this.$store.commit('SET_NOTIFICATION', "Error " + verb + " service request. Server could not be reached.");
            } else {
                this.$store.commit('SET_NOTIFICATION', "Error " + verb + " service request. Request could not be created.");
            }
        }
    }
}
</script>

<style scoped>
.applicationForm {
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