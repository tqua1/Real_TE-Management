<template>
    <form class="rentTransactionForm" v-on:submit.prevent="submitPayment">
      <div class="form-section">
        <div class="info-field">Paymemt Due: {{ rentTransaction.amount }}</div>
        <div class="info-field">
        <label for="rentPayment">Amount you would like to pay:  </label>  
          <input id="rentPayment" type="number" name="paymentInput" ref="paymentInput" v-model="editRentTransaction.amount"></div>
        
        <div class="action-btn">  
          <button  class="btn btn-outline-primary" type="submit">Complete Payment</button>&nbsp;&nbsp;
           <button class="btn btn-outline-secondary" type="button" v-on:click="cancelPayment">Cancel</button>
  </div>
      </div>
    </form>
    
  </template>

  <script>
import RentTransactionService from '../services/RentTransactionService'

export default {
  // Props are read-only, can use just this to call different values 
  // since not editing any parameters in the pay rent form from tenant side..
    props: {
        rentTransaction: {
            type: Object,
            required: true
        },
        enableAdd: {
          type: Boolean,
          default: false
        }
    },
    data() {
      return {
        editRentTransaction: {
          rentTransactionId: this.rentTransaction.rentTransactionId,
          amount: this.rentTransaction.amount,
          dueDate: this.rentTransaction.dueDate,
          pastDue: this.rentTransaction.pastDue
        }
      }
    },
    methods: {
      submitPayment() {
      
      if (!this.validateTransaction()) {
          return;
      } 
      // if(this.editRentTransaction.pastDue === true) {
        RentTransactionService.updateRentTransaction(this.editRentTransaction)
        .then(response => {
          if(response.status === 200) {
              this.$store.commit(
                'SET_NOTIFICATION',
                {
                  message: `Payment for ${this.rentTransactionId} was successful.`,
                  type: 'success'
                }
              );
              alert('Thank you for your payment! ');
              this.$router.push({ name: 'rentTransaction' });
            } else {
              alert('error')
            }
        })
      },  
    
// pastDuePaid(rentTransaction) {
//       this.$store.commit(SET_IS_PAID, this.rentTransaction)
//     },
        
        cancelPayment() {
      this.$router.push({ name: 'rentTransaction'});
    // },
    // handleErrorResponse(error, verb) {
    //   if (error.response) {
    //     this.$store.commit('SET_NOTIFICATION',
    //       "Error " + verb + " rent transaction. Response received was '" + error.response.statusText + "'.");
    //   } else if (error.request) {
    //     this.$store.commit('SET_NOTIFICATION', "Error " + verb + " rent transaction. Server could not be reached.");
    //   } else {
    //     this.$store.commit('SET_NOTIFICATION', "Error " + verb + " rent transaction. Request could not be created.");
    //   }
    },
    validateTransaction() {
      
      if(this.rentTransaction.amount == this.$refs.paymentInput.value)
      {
        return true;
      } else
        {
          alert('Please enter valid amount')}
      return false

  }
  
}
}

     </script>
<style>
.rentTransactionForm{
  background-color: #6f828e;
  border-style: ridge;
  border-radius: 0.25rem;
  padding: 10px;
  
}
.info-field{
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer
  
}

.form-section{
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
  border-style: ridge;
  
  
}


</style>