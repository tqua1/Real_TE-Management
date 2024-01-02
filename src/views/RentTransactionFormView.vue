<template>
  <header>Rent Payment</header>
  <div class="back-to-rent-transactions">
    <router-link v-bind:to="{name:'rentTransaction'}">Back to Rent Transactions</router-link>
    <p></p>
    <rent-transaction-form v-bind:rentTransaction="rentTransaction" />
  </div>
</template>

<script>
import RentTransactionForm from '../components/RentTransactionForm.vue';
import RentTransactionService from '../services/RentTransactionService';

export default {
components: {
  RentTransactionForm},
  data() {
    return {
      rentTransaction: {
        rentTransactionId: parseInt(this.$route.params.rentTransactionId),
        amount: 0,
        dueDate: this.$route.params.dueDate,
        pastDue: false
      }
    };
  },
  created() {
    let rentTransactionId = parseInt(this.$route.params.rentTransactionId);
    if(rentTransactionId != 0) {
      RentTransactionService.getRentTransactionById(rentTransactionId)
      .then(response => {
        this.rentTransaction = response.data;
      }) 
      .catch(error => {
          if (error.response && error.response.status === 404) {
            this.$store.commit('SET_NOTIFICATION', `Error getting card ${rentTransactionId}. This rent transaction may have been deleted or you have entered an invalid card ID.`);
            this.$router.push({ name: 'rentTransaction' });
          }
        });
    }
    }
}
</script>

<style>

</style>