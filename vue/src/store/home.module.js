import { ArticlesService, UsersService } from "@/common/api.service";
import { FETCH_ARTICLES, FETCH_USERS, FETCH_USER } from "./actions.type";
import { FETCH_START, FETCH_END } from "./mutations.type";

const state = {
  tags: [],
  articles: [],
  isLoading: true,
  articlesCount: 0
};

const getters = {
  articlesCount(state) {
    return state.articlesCount;
  },
  articles(state) {
    return state.articles;
  },
  isLoading(state) {
    return state.isLoading;
  }
};

const actions = {
  [FETCH_USERS]({ commit }) {
    commit(FETCH_START);
    return UsersService.query()
      .then(({ data }) => {
        commit(FETCH_END, { articles: data });
      })
      .catch(error => {
        throw new Error(error);
      });
  },
  [FETCH_USER]({ commit }, params) {
    commit(FETCH_START);
    return UsersService.get(params)
      .then(({ data }) => {
        commit(FETCH_END, { articles: data });
      })
      .catch(error => {
        throw new Error(error);
      });
  },
  [FETCH_ARTICLES]({ commit }, params) {
    commit(FETCH_START);
    return ArticlesService.query(params.type, params.filters)
      .then(({ data }) => {
        commit(FETCH_END, { articles: data });
      })
      .catch(error => {
        throw new Error(error);
      });
  }
};

/* eslint no-param-reassign: ["error", { "props": false }] */
const mutations = {
  [FETCH_START](state) {
    state.isLoading = true;
  },
  [FETCH_END](state, { articles }) {
    console.log(articles);
    state.articles = articles;
    state.articlesCount = articles.length;
    state.isLoading = false;
  }
};

export default {
  state,
  getters,
  actions,
  mutations
};
