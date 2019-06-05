<template>
  <div>
    <div v-if="isLoading" class="article-preview">Loading projects...</div>
    <div v-else>
      <div v-if="articles.length === 0" class="article-preview">
        No articles are here... yet.
      </div>
      <RwvArticlePreview
        v-for="(article, index) in orderedProjects"
        :article="article"
        :key="article.name + index"
      />
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import RwvArticlePreview from "./VArticlePreview";
import { FETCH_ARTICLES } from "../store/actions.type";
import { orderBy } from "lodash";

export default {
  name: "RwvArticleList",
  components: {
    RwvArticlePreview
  },
  props: {
    type: {
      type: String,
      required: false,
      default: "all"
    },
    author: {
      type: String,
      required: false
    },
    tag: {
      type: String,
      required: false
    },
    favorited: {
      type: String,
      required: false
    },
    itemsPerPage: {
      type: Number,
      required: false,
      default: 10
    }
  },
  data() {
    return {
      currentPage: 1
    };
  },
  computed: {
    orderedProjects: function() {
      return orderBy(
        this.articles,
        project => {
          let points = 0;
          for (const a of project.achievements) {
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
    pages() {
      if (this.isLoading || this.articlesCount <= this.itemsPerPage) {
        return [];
      }
      return [
        ...Array(Math.ceil(this.articlesCount / this.itemsPerPage)).keys()
      ].map(e => e + 1);
    },
    ...mapGetters(["articlesCount", "isLoading", "articles"])
  },
  watch: {
    currentPage(newValue) {
      this.listConfig.filters.offset = (newValue - 1) * this.itemsPerPage;
      this.fetchArticles();
    },
    type() {
      this.fetchArticles();
    },
    author() {
      this.fetchArticles();
    },
    tag() {
      this.fetchArticles();
    },
    favorited() {
      this.fetchArticles();
    }
  },
  mounted() {
    this.fetchArticles();
  },
  methods: {
    fetchArticles() {
      console.log(this.listConfig);
      this.$store.dispatch(FETCH_ARTICLES, this.listConfig);
    }
  }
};
</script>
