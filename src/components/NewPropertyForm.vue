<template>
  <form v-on:submit.prevent="submitForm" class="property-form">
    <div class="field">
        <div class="info-field"><label for="address">Address: </label>
          <input type="text" id="address" name="address" class="form-control" v-model="editProperty.address" placeholder="Ex: 123 Tech Elevator St. Philadelphia, NY 90210">
        </div>
        <div class="info-field"><label for="numberOfRooms">Number of Rooms: </label>
          <input type="text" id="numberOfRooms" name="numberOfRooms" class="form-control" v-model="editProperty.numberOfRooms" placeholder="Ex: 2">
        </div>
        <div class="info-field"><label for="rent">Rent: </label>
          <input type="text" id="rent" name="rent" class="form-control" v-model="editProperty.rent" placeholder="Ex: 2700.00">
        </div>
        <div class="form-check form-switch"> <label for="isAvailable">Is Available: </label>
          <input type="checkbox" class="form-check-input" id="isAvailable" name="isAvailable" v-model="editProperty.available">
        </div>
        <div class="property-form-btn">
            <button class="btn btn-outline-primary" type="submit">Save Property</button>
            <button class="btn btn-outline-secondary" type="button" v-on:click="cancelForm">Cancel</button>
        </div>
    </div>
  </form>
</template>

<script>
import PropertyService from '../services/PropertyService';
import UserService from '../services/UserService';
export default {
  props: {
    property: {
      type: Object, required: true
    }
  },
  data() {
    return {
      editProperty: {
        propertyId: this.$route.params.propertyId,
        managerId: this.property.managerId,
        address: this.property.address,
        numberOfRooms: this.property.numberOfRooms,
        rent: this.property.rent,
        available: false
      }
    }
  },
  methods: {
    getManagerId() {
      UserService.getManagerIdFromUserId().then(response => {
        this.$store.commit('SET_MANAGER_ID', response.data);
        this.editProperty.managerId = this.$store.state.managerId;
      }).catch(error => {
        if (error.response.status === 404) {
          this.$store.commit('SET_NOTIFICATION', `Error: managerId was not found.`)
          this.$router.push({ name: 'HomeView' });
        } else {
          this.$store.commit('SET_NOTIFICATION',
            "Error getting managerId. Response received was '" + error.response.statusText + "'.");
        }
      });
    }, 
    getPropertyDetails() {
      if (this.editProperty.propertyId > 0){
        PropertyService.getPropertyById(this.editProperty.propertyId).then(response => {
          this.editProperty = response.data;
        }).catch(error => {
          if (error.response.status === 404) {
            this.$store.commit('SET_NOTIFICATION', `Error: managerId was not found.`)
            this.$router.push({ name: 'HomeView' });
          } else {
            this.$store.commit('SET_NOTIFICATION',
              "Error getting managerId. Response received was '" + error.response.statusText + "'.");
          }
        });
      }
    },
    submitForm() {
      if (this.editProperty.propertyId == 0) {
        PropertyService.addProperty(this.editProperty).then(response => {
          if (response.status === 201 || response.status === 200) {
                this.$store.commit(
                  'SET_NOTIFICATION',
                  {
                    message: 'A new property was added.',
                    type: 'success'
                  }
                );
                this.$router.push({ name: 'propertyManagerMainPage' });
              }
        }).catch(error => {
              this.handleErrorResponse(error, 'adding');
            });
      } else {
        PropertyService.updateProperty(this.editProperty).then(response => {
      
          if (response.status === 200) {
            this.$store.commit('SET_NOTIFICATION', {
                message: `Property ${this.editProperty.propertyId} was updated.`,
                type: 'success'
              }
            );
            this.$router.push({ name: 'propertyManagerMainPage'});
          }
        }).catch(error => {
          this.handleErrorResponse(error, 'updating');
        });
      }
    },
    cancelForm() {
        this.$router.push({ name: 'propertyManagerMainPage' });
    },
    handleErrorResponse(error, verb) {
        if (error.response) {
          this.$store.commit('SET_NOTIFICATION',
            "Error " + verb + " property. Response received was '" + error.response.statusText + "'.");
        } else if (error.request) {
          this.$store.commit('SET_NOTIFICATION', "Error " + verb + " property. Server could not be reached.");
        } else {
          this.$store.commit('SET_NOTIFICATION', "Error " + verb + " property. Request could not be created.");
        }
    },
    validateForm() {
        let msg = '';
        if (this.editCard.address.length === 0) {
          msg += 'The new property must have an address. ';
        }
        if (this.editCard.numberOfRooms.length === 0) {
          msg += 'The new property must have a number of rooms.';
        }
        if (this.editCard.rent.length === 0) {
          msg += 'The new property must have a rent.';
        }
        if (msg.length > 0) {
          this.$store.commit('SET_NOTIFICATION', msg);
          return false;
        }
        return true;
      },
  },
  created() {
    this.getManagerId();
    this.getPropertyDetails();
    if (this.$store.state.user.authorities[0].name == 'ROLE_ADMIN') {
            this.$store.commit('SET_SHOW_MANAGER_POV', true);
        }
  }
}
</script>

<style scoped>
.field {
  padding: 10px;
  margin-bottom: 10px;
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