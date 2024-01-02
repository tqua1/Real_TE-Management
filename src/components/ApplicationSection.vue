<template>
  <div class="action-board">
    <h2 class="title">{{ title }}</h2>
    <div class="application" v-for="application in applications" v-bind:key="application.applicationId">
      <div>
          <router-link class="link" v-bind:to="{name: 'applicationDetails', params:{ applicationId: application.applicationId}}">
            <h3>
                Application ID: {{ application.applicationId }}
            </h3>
            <div>
              <h4>Property ID: </h4>
              {{ application.propertyId }}
            </div>
            <div>
              <h5>Applicant Name: </h5>
              {{ application.fullName }}
            </div>
        </router-link>
      </div>  
    </div>
  </div>
</template>

<script>
export default {
props: ['title', 'applications'],

method: {
    
}
}
</script>

<style scoped>

.action-board {
  background-color: #6f828e;
  border-radius: 10px;
  padding: 0 20px 20px 20px;
}
.application {
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
h2{
  display: flex;
  justify-content: center;
}
h3, h4, h5{
  color: slategray;
}

</style>