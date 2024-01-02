<template>
  <div class="tenant-action-boards">
    <div class="tenant-service-request-board" v-for="serviceRequest in serviceRequests" v-bind:key="serviceRequest.id" v-on:click="viewServiceRequestDetails(serviceRequest)">
    <h2>Service Requests</h2>
    
    </div>
   <div class="tenant-rent-transaction-board" v-for="rentTransaction in rentTransactions" v-bind:key="rentTransaction.id" v-on:click="viewRentTransaction(rentTransaction)">
    <h2>Rent Transactions</h2>
    
  </div>
  </div>
</template>

<script>

export default {
  props: ['serviceRequest', 'rentTransaction'],
 
}
</script>

<style>
.tenant-action-boards{
  display: flex;
  margin: 0 20px;
  flex-wrap: wrap;
  column-gap: 20px;
  row-gap: 25px;
  justify-content: flex-start;
  align-items: center; 
}
</style>