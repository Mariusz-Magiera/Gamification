<template>
  <div class="profile-page">
    <ul>
      <li v-for="(link, index) of profile.links" :key="index">
        <a :href="link.url">{{ link.type }}</a>
      </li>
    </ul>
  </div>
</template>

<script>
import RwvArticleList from "@/components/ArticleList";

export default {
  name: "RwvProfileFavorited",
  props: ["profile"],
  components: {
    RwvArticleList
  },
  computed: {
    favorited() {
      return this.$route.params.username;
    }
  }
};
</script>
