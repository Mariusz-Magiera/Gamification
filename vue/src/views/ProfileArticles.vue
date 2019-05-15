<template>
  <div class="profile-page">
    <!-- {{profile}} -->
    <!-- <ul> -->
    <!-- <li v-for="(link, index) of profile.links" :key="index"> -->
    <!-- <a :href=link.url>{{ link.name }}</a> -->
    <!-- &lt;!&ndash; <RwvTag &ndash;&gt; -->
    <!-- &lt;!&ndash; :name="tag" &ndash;&gt; -->
    <!--
      &lt;!&ndash; className="tag-default tag-pill tag-outline" &ndash;&gt;
    -->
    <!-- &lt;!&ndash; &gt;</RwvTag> &ndash;&gt; -->
    <!-- </li> -->
    <!-- </ul> -->
  </div>
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
