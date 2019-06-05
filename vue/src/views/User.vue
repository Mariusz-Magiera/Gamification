<template>
  <div class="article-page">
    <div class="banner">
      <div class="container">
        <h1>{{ articles.name }}</h1>
        <h2 v-if="articles.permission.type == '1'" v-text="'Member'"/>
        <h2 v-if="articles.permission.type == '2'" v-text="'Moderator'"/>
        <h2 v-if="articles.permission.type == '3'" v-text="'Administrator'"/>
        <RwvArticleMeta :article="article" :actions="true"></RwvArticleMeta>
      </div>
    </div>
    <div class="container page">
      <h3>
        Total points: <b style="color: green">{{ sumPoints() }}</b>
      </h3>
      <div class="row article-content">
        <div class="col-xs-12">
          <h3>Links</h3>
          <ul>
            <li v-for="(link, index) of articles.links" :key="index">
              <a :href="link.url">{{ link.type }}</a>
            </li>
          </ul>
          <h3>Achievements</h3>
          <button id="show-modal" @click="showModal = true">Add achievement</button>
          <!-- use the modal component, pass in the prop -->
          <div v-if="showModal">
            <!--
              you can use custom content here to overwrite
              default content
            -->
            <div>
              Description:<br>
              <input type="text" name="description" value="Mickey" v-model="new_achievement_description">
              <br>
              Points:<br>
              <input type="number" name="points" value="Mouse" v-model="new_achievement_points">
              <br><br>
              <input type="submit" value="Submit" @click="addAchievement">
            </div>
          </div>
          <ul>
            <li
              v-for="(achievement, index) of articles.achievements"
              :key="achievement + index"
            >
              {{ achievement.description }} ({{ achievement.points }} points)
            </li>
          </ul>
        </div>
      </div>
      <hr/>

    </div>
  </div>
</template>

<script>
  import { mapGetters } from 'vuex'
  import marked from 'marked'
  import store from '@/store'
  import { FETCH_ARTICLE_USERS, FETCH_COMMENTS, FETCH_USER } from '@/store/actions.type'
  import Vue from 'vue'

  export default {
    name: 'rwv-article',
    data () {
      return {
        showModal: false,
        new_achievement_description: '',
        new_achievement_points: 0
      }
    },
    props: {
      slug: {
        type: String,
        required: true
      }
    },
    beforeRouteEnter (to, from, next) {
      Promise.all([
        store.dispatch(FETCH_USER, to.params.slug)
        // store.dispatch(FETCH_COMMENTS, to.params.slug)
      ]).then(() => {
        next()
      })
    },
    computed: {
      ...mapGetters(['articles', 'currentUser', 'comments', 'isAuthenticated'])
    },
    methods: {
      addAchievement (data) {
        this.showModal = false
        console.log(window.localStorage)
        const user = window.localStorage.getItem('id_token')
        const params = {
          'name': user,
          'password': window.localStorage.getItem('pass')
        }
        Vue.axios.post(`users/id/${this.articles.id}/addAchievement?points=${this.new_achievement_points}&desc=${this.new_achievement_description}`, params).then(
          response => {
            console.log(response)
            if(response.data){
              store.dispatch(FETCH_USER, this.articles.id)
            } else {
              alert(`User "${user}" doesn't have moderator or admin access`)
            }
          }
        )
        // alert(this.new_achievement_description + this.new_achievement_points)
      },
      sumPoints () {
        let points = 0
        for (const a of this.articles.achievements) {
          points += a.points
        }
        return points
      }
    }
  }
</script>
