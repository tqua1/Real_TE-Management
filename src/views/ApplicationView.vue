<template>
  <div>
    <div class="back-to-home">
        <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-bind:to="{name: 'home'}">
            <i class="bi bi-chevron-left"></i>Back to Home</router-link>
    </div>
    <div class="header">
        <h1>Applications</h1>
        <router-link class="btn btn-outline-secondary text-bg-light p-3 position-relative" v-show="!this.$store.state.showManagerPOV" v-bind:to="{ name: 'applicationForm'}"> 
            <i class="bi bi-plus-lg"></i>
            Create A New Application</router-link>
    </div>
    <div class="action-boards">
        <application-section title="Pending" v-bind:applications="pending"/>
        <application-section title="Approved" v-bind:applications="approved"/>
        <application-section title="Rejected" v-bind:applications="rejected"/>
    </div>
  </div>
</template>

<script>
import ApplicationSection from '../components/ApplicationSection.vue';
import applicationService from '../services/ApplicationService';

export default {
    components: {
        ApplicationSection
    },
    data(){
        return {
            applicationList: { title: '', applications: [] }
        };
    },
    computed: {
        pending(){
            return this.$store.state.applications.filter(application => application.status === 'STATUS_PENDING');
        },
        approved(){
            return this.$store.state.applications.filter(application => application.status === 'STATUS_APPROVED');
        },
        rejected(){
            return this.$store.state.applications.filter(application => application.status === 'STATUS_REJECTED');
        }

    },
    created(){
        if(this.$store.state.user.authorities[0].name === 'ROLE_ADMIN'){
            applicationService
            .getAllApplications()
            .then(response => {
                this.$store.commit('SET_APPLICATION', response.data);
            })
            .catch(error => {
                if(error.response && error.response.status === 404){
                    this.$store.commit('SET_NOTIFICATION', `Error getting applications.`)
                    this.$router.push({ name: 'application' });
                }
            });
        } else if (this.$store.state.user.authorities[0].name === 'ROLE_USER'){
            applicationService
            .getMyApplications()
            .then(response => {
                this.$store.commit('SET_APPLICATION', response.data);
            })
            .catch(error => {
                if(error.response && error.response.status === 404){
                    this.$store.commit('SET_NOTIFICATION', `Error getting applications.`)
                    this.$router.push({ name: 'application' });
                }
            });
        }
        if (this.$store.state.user.authorities[0].name == 'ROLE_ADMIN') {
            this.$store.commit('SET_SHOW_MANAGER_POV', true);
        }
    }
}
</script>

<style>

</style>