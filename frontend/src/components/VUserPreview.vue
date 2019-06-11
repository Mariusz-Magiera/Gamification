<template>
  <div class="article-preview">
    <router-link :to="articleLink" class="preview-link">
      <h1 v-text="user.name" />
      <p v-text="sumPoints + ' points'" />
      <p v-if="user.permission.type == '1'" v-text="'Member'" />
      <p v-if="user.permission.type == '2'" v-text="'Moderator'" />
      <p v-if="user.permission.type == '3'" v-text="'Administrator'" />
      <span>Read more...</span>
      <!-- <TagList :tags="article.tasks" /> -->
    </router-link>
  </div>
</template>

<script>
export default {
  name: "RwvArticlePreview",
  props: {
    user: { type: Object, required: true }
  },
  computed: {
    sumPoints() {
      let points = 0;
      for (const a of this.user.achievements) {
        points += a.points;
      }
      return points;
    },
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
