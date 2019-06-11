<template>
  <div class="home-page">
    <div class="banner" style="background-color: darkred">
      <div class="container">
        <h1 class="logo-font">Grywalizacja</h1>
        <p>TK Games projects</p>
      </div>
    </div>
    <div class="container page">
      <div class="row">
        <div class="col-md-9">
          <div class="feed-toggle">
            <ul class="nav nav-pills outline-active">
              <li class="nav-item">
                <router-link
                  :to="{ name: 'home' }"
                  exact
                  class="nav-link"
                  active-class="active"
                >
                  All Projects
                </router-link>
              </li>
              <li class="nav-item">
                <router-link
                  :to="{ name: 'home-my-feed' }"
                  exact
                  class="nav-link"
                >
                  Users
                </router-link>
              </li>
            </ul>
          </div>
          <router-view></router-view>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "home",
  computed: {
    ...mapGetters(["isAuthenticated"])
  }
};
</script>
