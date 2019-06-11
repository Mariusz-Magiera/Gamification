<template>
  <div class="article-page">
    <div class="banner">
      <div class="container">
        <h1>{{ article.name }}</h1>
        <RwvArticleMeta :article="article" :actions="true"></RwvArticleMeta>
      </div>
    </div>
    <div class="container page">
      <h3>
        Total points: <b style="color: green">{{ sumPoints() }}</b>
      </h3>
      <div class="row article-content">
        <div class="col-xs-12">
          <h3>Participants</h3>
          <ul>
            <li v-for="(user, index) of users" :key="user + index">
              <router-link :to="'/users/' + user.id" class="preview-link">
                <span style="color: blue">{{ user.name }}</span>
              </router-link>
            </li>
          </ul>
          <h3>Available tasks</h3>
          <ul>
            <li v-for="(tag, index) of article.tasks" :key="tag + index">
              <u>{{ tag.description }}</u> ({{ tag.points }} points).<br />
              Deadline:
              <i style="color: red">{{
                new Date(tag.due_date).toDateString()
              }}</i>
            </li>
          </ul>
          <h3>Achievements</h3>
          <ul>
            <li
              v-for="(achievement, index) of article.achievements"
              :key="achievement + index"
            >
              {{ achievement.description }} ({{ achievement.points }} points)
            </li>
          </ul>
        </div>
      </div>
      <hr />
    </div>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import store from "@/store";
import { FETCH_ARTICLE, FETCH_ARTICLE_USERS } from "@/store/actions.type";

export default {
  name: "rwv-article",
  props: {
    slug: {
      type: String,
      required: true
    }
  },
  beforeRouteEnter(to, from, next) {
    Promise.all([
      store.dispatch(FETCH_ARTICLE_USERS, to.params.slug),
      store.dispatch(FETCH_ARTICLE, to.params.slug)
    ]).then(() => {
      next();
    });
  },
  computed: {
    ...mapGetters([
      "article",
      "users",
      "currentUser",
      "comments",
      "isAuthenticated"
    ])
  },
  methods: {
    sumPoints() {
      let points = 0;
      for (const a of this.article.achievements) {
        points += a.points;
      }
      return points;
    }
  }
};
</script>
