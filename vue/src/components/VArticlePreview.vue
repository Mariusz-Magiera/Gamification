<template>
  <div class="article-preview">
    <router-link :to="articleLink" class="preview-link">
      <h1 v-text="article.name" />
      <p>{{ sumPoints }} points</p>
      <p v-text="article.description" />
      <span>Read more...</span>
      <!-- <TagList :tags="article.tasks" /> -->
    </router-link>
  </div>
</template>

<script>
export default {
  name: "RwvArticlePreview",
  props: {
    article: { type: Object, required: true }
  },
  computed: {
    sumPoints() {
      let points = 0;
      for (const a of this.article.achievements) {
        points += a.points;
      }
      return points;
    },
    articleLink() {
      return {
        name: "article",
        params: {
          slug: this.article.id
        }
      };
    }
  }
};
</script>
