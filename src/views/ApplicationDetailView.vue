<template>
  <div>
    <application-detail v-bind:application="application"/>
  </div>
</template>

<script>
import ApplicationDetail from '../components/ApplicationDetail.vue';
import applicationService from '../services/ApplicationService';

export default {
    components: {
        ApplicationDetail
    },
    data(){
        return {
            application: {

            },
        }
    },
    created(){
        applicationService
        .getApplicationById(this.$route.params.applicationId)
        .then(response => {
            this.application = response.data;
        });
        if (this.$store.state.user.authorities[0].name == 'ROLE_ADMIN') {
        this.$store.commit('SET_SHOW_MANAGER_POV', true);
        }
    }
}
</script>

<style>

</style>