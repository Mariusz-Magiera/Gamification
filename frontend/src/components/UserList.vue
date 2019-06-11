<template>
  <div>
    <div v-if="isLoading" class="article-preview">Loading users...</div>
    <button id="show-modal" @click="showModal = true;">Add user</button>
    <!-- use the modal component, pass in the prop -->
    <div v-if="showModal">
      <!--
        you can use custom content here to overwrite
        default content
      -->
      <div>
        User:<br />
        <input type="text" name="user" v-model="user" /> <br />
        Password:<br />
        <input type="password" name="password" v-model="password" />
        <br /><br />
        Permission:<br />
        <input type="text" name="permission" v-model="permission" />
        <br /><br />
        <input type="submit" value="Submit" @click="addUser" />
      </div>
    </div>
    <div v-else>
      <div v-if="articles.length === 0" class="article-preview">
        No articles are here... yet.
      </div>
      <RwvUserPreview
        v-for="(user, index) in orderedUsers"
        :user="user"
        :key="user.name + index"
      />
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import RwvUserPreview from "./VUserPreview";
import { FETCH_USERS } from "../store/actions.type";
import { orderBy } from "lodash";
import Vue from "vue";

export default {
  name: "RwvArticleList",
  components: {
    RwvUserPreview
  },
  data() {
    return {
      currentPage: 1,
      showModal: false,
      permission: 1,
      user: "",
      password: ""
    };
  },
  computed: {
    orderedUsers: function() {
      return orderBy(
        this.articles,
        user => {
          let points = 0;
          for (const a of user.achievements) {
            points += a.points;
          }
          return points;
        },
        "desc"
      );
    },
    listConfig() {
      const { type } = this;
      const filters = {
        offset: (this.currentPage - 1) * this.itemsPerPage,
        limit: this.itemsPerPage
      };
      if (this.author) {
        filters.author = this.author;
      }
      if (this.tag) {
        filters.tag = this.tag;
      }
      if (this.favorited) {
        filters.favorited = this.favorited;
      }
      return {
        type,
        filters
      };
    },
    ...mapGetters(["articlesCount", "isLoading", "articles"])
  },
  mounted() {
    this.fetchArticles();
  },
  methods: {
    addUser() {
      this.showModal = false;
      const params = {
        permission: {
          type: this.permission
        },
        name: this.user,
        password: this.password,
        links: [],
        achievements: []
      };
      Vue.axios.post("users/add", params);
    },
    fetchArticles() {
      console.log(this.listConfig);
      this.$store.dispatch(FETCH_USERS, this.listConfig);
    }
  }
};
</script>
