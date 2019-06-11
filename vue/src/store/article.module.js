import { ArticlesService, ArticlesUserService } from "@/common/api.service";
import { FETCH_ARTICLE, FETCH_ARTICLE_USERS } from "./actions.type";
import { SET_ARTICLE, SET_ARTICLE_USERS } from "./mutations.type";

const initialState = {
  article: {
    author: {},
    title: "",
    description: "",
    body: "",
    tasks: []
  },
  comments: []
};

export const state = { ...initialState };

export const actions = {
  async [FETCH_ARTICLE_USERS](context, articleSlug) {
    // avoid extronuous network call if article exists
    const { data } = await ArticlesUserService.get(articleSlug);
    context.commit(SET_ARTICLE_USERS, data);
    return data;
  },
  async [FETCH_ARTICLE](context, articleSlug, prevArticle) {
    // avoid extronuous network call if article exists
    if (prevArticle !== undefined) {
      return context.commit(SET_ARTICLE, prevArticle);
    }
    const { data } = await ArticlesService.get(articleSlug);
    context.commit(SET_ARTICLE, data);
    return data;
  }
};

/* eslint no-param-reassign: ["error", { "props": false }] */
export const mutations = {
  [SET_ARTICLE_USERS](state, users) {
    console.log(users);
    state.users = users;
  },
  [SET_ARTICLE](state, article) {
    state.article = article;
  }
};

const getters = {
  article(state) {
    return state.article;
  },
  users(state) {
    return state.users;
  }
};

export default {
  state,
  actions,
  mutations,
  getters
};
