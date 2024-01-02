<template>
    <div class="action-board">
      <h2 id="title">{{title}}</h2>
      <div class="rent-transactions">
         <div class="rent-transaction" v-for="rentTransaction in rentTransactions" v-bind:key="rentTransaction.rentTransactionId">
         <div class="transaction-section-details">
            <div class="info-field">Rent Payment Id: {{rentTransaction.transactionId}}</div> 
        
         <div class="info-field"> Amount Due: {{ rentTransaction.amount }} </div>
         
         <div class="info-field">Payment Due Date: {{ rentTransaction.dueDate }}</div>
        
        </div>
        <button class="btn btn-outline-secondary">
          <router-link class="back-btn" v-bind:to="{name:'RentTransactionForm', params:{rentTransactionId: rentTransaction.transactionId, dueDate: rentTransaction.dueDate} }">
          Pay Rent
          </router-link> 
        </button>
   
         </div>
        
        </div>

    </div>
  </template>
  
  <script>
  export default {
  props: ['title', 'rentTransactions'],

  }
</script>

<style scoped>
.action-board {
  background-color: #6f828e;
  border-radius: 10px;
  padding: 0 20px 20px 20px;
}
.action-board h2 {
color: #2c363b
}

.info-field{
  background: #fff;
  border-radius: 0.10rem;
  padding: 15px;
  border: 10px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
}
.rent-transactions {
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
}

.rent-transaction {
  border-style: ridge;
  border-radius: 0.25rem;
  padding: 10px;
}
.pay-btn{
  color: rgba(25, 27, 29, 0.87);
  background-color: #b7cddb;
  border-color: #43677c;
  text-decoration: none;
  padding: 6px 12px;
  cursor: pointer;
  border-radius: 0.20 rem;
}

.rent-transaction:last-child {
  margin-bottom: 0px;
}

.rent-transaction h3 {
  margin-top: 0px;
  font-size: 0.875rem;
}

.rent-transaction .header {
  display: flex;
  justify-content: space-between;
}

#title{
  color: white;
}

h2{
  display: flex;
  justify-content: center;
}

</style>