<template>
  <div class="profile-page">
    <ul>
      <li v-for="(achievement, index) of profile.achievements" :key="index">
        {{ achievement.description }}
        <!-- <RwvTag -->
        <!-- :name="tag" -->
        <!-- className="tag-default tag-pill tag-outline" -->
        <!-- &gt;</RwvTag> -->
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
