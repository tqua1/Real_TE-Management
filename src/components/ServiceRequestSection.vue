<template>
  <div class="action-board">
    <h2 class="title">{{title}}</h2>
    <div class="service-requests">
        <div class="service-request" v-for="serviceRequest in serviceRequests" v-bind:key="serviceRequest.serviceRequestId" >
          <router-link class="link" v-bind:to="{name: 'serviceRequestDetails', params:{serviceRequestId: serviceRequest.serviceRequestId}}">
          <div class="header">
            <h3>
              Service Request ID :{{ serviceRequest.serviceRequestId }}
            </h3>
          </div>
          <div>
            <h4>Details: {{ serviceRequest.requestDetails }}</h4>
          </div>
        </router-link>
        </div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['title', 'serviceRequests'],
  created(){
    if (this.$store.state.user.authorities[0].name == 'ROLE_ADMIN') {
      this.$store.commit('SET_SHOW_MANAGER_POV', true);
    }
  }
}
</script>

<style scoped>
h2{
  display: flex;
  justify-content: center;
}
.action-board {
  background-color: #6f828e;
  border-radius: 10px;
  padding: 0 20px 20px 20px;
}
.service-request {
  background: #fff;
  border-radius: 0.25rem;
  padding: 10px;
  border: 1px;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
  margin-bottom: 10px;
  cursor: pointer;
}
.title{
  color: white;
}
h3, h4{
  color: slategray;
}

</style>