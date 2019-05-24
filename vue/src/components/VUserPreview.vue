<template>
  <div class="article-preview">
    <RwvArticleMeta isPreview :article="user" />
    <router-link :to="articleLink" class="preview-link">
      <h1 v-text="user.name" />
      <p v-if="user.permission.type == '1'" v-text="'Member'"/>
      <p v-if="user.permission.type == '2'" v-text="'Moderator'"/>
      <p v-if="user.permission.type == '3'" v-text="'Administrator'"/>
      <span>Read more...</span>
      <!-- <TagList :tags="article.tasks" /> -->
    </router-link>
  </div>
</template>

<script>
import RwvArticleMeta from "./ArticleMeta";
import TagList from "./TagList";

export default {
  name: "RwvArticlePreview",
  components: {
    RwvArticleMeta,
    TagList
  },
  props: {
    user: { type: Object, required: true }
  },
  computed: {
    articleLink() {
      return {
        name: "user",
        params: {
          slug: this.user.id
        }
      };
    }
  }
};
</script>
