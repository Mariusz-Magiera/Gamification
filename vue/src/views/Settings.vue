<template>
  <div class="settings-page">
    <div class="container page">
      <div class="row">
        <div class="col-md-6 offset-md-3 col-xs-12">
          <h1 class="text-xs-center">Your Settings</h1>
          <hr />
          <button @click="logout" class="btn btn-outline-danger">
            Log out.
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { LOGOUT } from "@/store/actions.type";

export default {
  name: "RwvSettings",
  computed: {
    ...mapGetters(["currentUser"])
  },
  methods: {
    logout() {
      this.$store.dispatch(LOGOUT).then(() => {
        this.$router.push({ name: "home" });
      });
    }
  }
};
</script>
