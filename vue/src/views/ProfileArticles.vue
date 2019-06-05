<template>
  <div class="profile-page"></div>
</template>

<script>
import RwvArticleList from "@/components/ArticleList";

export default {
  name: "RwvProfileArticles",
  props: ["profile"],
  components: {
    RwvArticleList
  },
  computed: {
    author() {
      return this.$route.params.username;
    }
  }
};
</script>
