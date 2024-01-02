<template>
  <div class="back-to-tenant-home">
    <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{name:'home'}"><i class="bi bi-chevron-left"></i>Back to Home Page</router-link>
  </div>
 
  
  <div class="header">
<h1>Rent Transactions</h1>
 
</div>

  <div class="action-boards">
    <rent-transaction-section title="Payments Completed" v-bind:rentTransactions="payments" />
    <rent-transaction-section title="Payments Due" v-bind:rentTransactions="pastDuePayments" />
    
  </div> 
</template>

<script>
import { computed } from 'vue';
import RentTransactionSection from '../components/RentTransactionSection.vue'
import RentTransactionService from '../services/RentTransactionService';
export default {
  components: { 
    RentTransactionSection,
 },
 data() {
  return {
    rentTransactionBoard: { title: '', rentTransactions: []}
  }
 },
 computed: {
    payments() {
      return this.$store.state.rentTransactions.filter(rentTransaction => rentTransaction.pastDue === false);
    },
    pastDuePayments() {
      return this.$store.state.rentTransactions.filter(rentTransaction => rentTransaction.pastDue === true);
    }
  },
 
  created() {
      // let rentTransactionId = parseInt(this.$route.params.rentTransactionId)
      // if(rentTransactionId != 0) {
      RentTransactionService.getMyRentTransactions()
      .then(response => {
        this.$store.commit('SET_RENT_TRANSACTION',response.data)
      })
      .catch(error => {
          if (error.response) {
            this.$store.commit('SET_NOTIFICATION',
              "Error getting rent transactions list. Response received was '" + error.response.statusText + "'.");
          } else if (error.request) {
            this.$store.commit('SET_NOTIFICATION', "Error getting rent transactions list. Server could not be reached.");
          } else {
            this.$store.commit('SET_NOTIFICATION', "Error getting rent transactions list. Request could not be created.");
          }
        });
    }
  };

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