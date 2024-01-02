<template>
  <h1>Logout</h1>
</template>

<script>
export default {
  created() {
    this.$store.commit("LOGOUT");
    this.$router.push("/login");
    this.$store.commit('SET_MANAGER_ID', 0);
    this.$store.commit('SET_TENANT_ID', 0);
    this.$store.commit('SET_SHOW_MANAGER_POV', false);
  }
}
</script>
