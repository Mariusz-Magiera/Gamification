<template>
  <div class="article-page">
    <div class="banner">
      <div class="container">
        <h1>{{ article.name }}</h1>
        <RwvArticleMeta :article="article" :actions="true"></RwvArticleMeta>
      </div>
    </div>
    <div class="container page">
      <div class="row article-content">
        <div class="col-xs-12">
          <h3>Participants</h3>
          <ul>
            <li v-for="(user, index) of article.users" :key="tag + index">
              {{ user.name }}
              <!-- <RwvTag -->
              <!-- :name="tag" -->
              <!-- className="tag-default tag-pill tag-outline" -->
              <!-- &gt;</RwvTag> -->
            </li>
          </ul>
          <!-- <div v-html="parseMarkdown(article.body)"></div> -->
          <h3>Available tasks</h3>
          <ul>
            <li v-for="(tag, index) of article.tasks" :key="tag + index">
              {{ tag.description }}
              <!-- <RwvTag -->
              <!-- :name="tag" -->
              <!-- className="tag-default tag-pill tag-outline" -->
              <!-- &gt;</RwvTag> -->
            </li>
          </ul>
          <h3>Achievements</h3>
          <ul>
            <li
              v-for="(achievement, index) of article.achievements"
              :key="tag + index"
            >
              {{ achievement.description }}
              <!-- <RwvTag -->
              <!-- :name="tag" -->
              <!-- className="tag-default tag-pill tag-outline" -->
              <!-- &gt;</RwvTag> -->
            </li>
          </ul>
        </div>
      </div>
      <hr />
      <!-- <div class="article-actions"> -->
      <!--
        <RwvArticleMeta :article="article" :actions="true"></RwvArticleMeta>
      -->
      <!-- </div> -->
      <!-- <div class="row"> -->
      <!-- <div class="col-xs-12 col-md-8 offset-md-2"> -->
      <!-- <RwvCommentEditor -->
      <!-- v-if="isAuthenticated" -->
      <!-- :slug="slug" -->
      <!-- :userImage="currentUser.image" -->
      <!-- &gt; -->
      <!-- </RwvCommentEditor> -->
      <!-- <p v-else> -->
      <!-- <router-link :to="{ name: 'login' }">Sign in</router-link> -->
      <!-- or -->
      <!-- <router-link :to="{ name: 'register' }">sign up</router-link> -->
      <!-- to add comments on this article. -->
      <!-- </p> -->
      <!-- &lt;!&ndash; <RwvComment &ndash;&gt; -->
      <!-- &lt;!&ndash; v-for="(comment, index) in comments" &ndash;&gt; -->
      <!-- &lt;!&ndash; :slug="slug" &ndash;&gt; -->
      <!-- &lt;!&ndash; :comment="comment" &ndash;&gt; -->
      <!-- &lt;!&ndash; :key="index" &ndash;&gt; -->
      <!-- &lt;!&ndash; &gt; &ndash;&gt; -->
      <!-- &lt;!&ndash; </RwvComment> &ndash;&gt; -->
      <!-- </div> -->
      <!-- </div> -->
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import marked from "marked";
import store from "@/store";
import RwvArticleMeta from "@/components/ArticleMeta";
import RwvComment from "@/components/Comment";
import RwvCommentEditor from "@/components/CommentEditor";
import RwvTag from "@/components/VTag";
import {
  FETCH_ARTICLE,
  FETCH_ARTICLE_USERS,
  FETCH_COMMENTS
} from "@/store/actions.type";

export default {
  name: "rwv-article",
  props: {
    slug: {
      type: String,
      required: true
    }
  },
  components: {
    RwvArticleMeta,
    RwvComment,
    RwvCommentEditor,
    RwvTag
  },
  beforeRouteEnter(to, from, next) {
    Promise.all([
      store.dispatch(FETCH_ARTICLE, to.params.slug),
      store.dispatch(FETCH_ARTICLE_USERS, to.params.slug),
      () => FETCH_COMMENTS
      // store.dispatch(FETCH_COMMENTS, to.params.slug)
    ]).then(() => {
      next();
    });
  },
  computed: {
    ...mapGetters(["article", "currentUser", "comments", "isAuthenticated"])
  },
  methods: {
    parseMarkdown(content) {
      return marked(content);
    }
  }
};
</script>
