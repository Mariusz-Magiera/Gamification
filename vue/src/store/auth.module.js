import ApiService from "@/common/api.service";
import JwtService from "@/common/jwt.service";
import {
  LOGIN,
  LOGOUT,
  REGISTER,
  CHECK_AUTH,
  UPDATE_USER
} from "./actions.type";
import { SET_AUTH, PURGE_AUTH, SET_ERROR } from "./mutations.type";

const state = {
  errors: null,
  user: {},
  isAuthenticated: !!JwtService.getToken()
};

const getters = {
  currentUser(state) {
    return state.user;
  },
  isAuthenticated(state) {
    return state.isAuthenticated;
  }
};

const actions = {
  [LOGIN](context, credentials) {
    return new Promise(resolve => {
      ApiService.post("authorize", credentials)
        .then(({ data }) => {
          console.log(credentials);
          if (data === false) {
            console.log("err");
            context.commit(SET_ERROR, ["Incorrect credentials"]);
          } else {
            console.log("succ");
            context.commit(SET_AUTH, credentials);
          }
          resolve(data);
        })
        .catch(() => {
          context.commit(SET_ERROR, ["Unknown error"]);
        });
    });
  },
  [LOGOUT](context) {
    context.commit(PURGE_AUTH);
  },
  [REGISTER](context, credentials) {
    return new Promise((resolve, reject) => {
      ApiService.post("users/add", {
        name: credentials.username,
        password: credentials.password,
        permission: { type: 1 }
      })
        .then(({ data }) => {
          context.commit(SET_AUTH, credentials);
          resolve(data);
        })
        .catch(({ response }) => {
          context.commit(SET_ERROR, ["Unknown error"]);
          reject(response);
        });
    });
  },
  [CHECK_AUTH](context) {
    console.log(window.localStorage);
    const user = JwtService.getToken();
    if (user) {
      ApiService.setHeader();
      // ApiService.get("user")
      //   .then(({ data }) => {
      context.commit(SET_AUTH, {name: user, password: window.localStorage.getItem('pass')});
      //   })
      //   .catch(({ response }) => {
      //     context.commit(SET_ERROR, response.data.errors);
      //   });
    } else {
      context.commit(PURGE_AUTH);
    }
  }
};

const mutations = {
  [SET_ERROR](state, error) {
    state.errors = error;
  },
  [SET_AUTH](state, credentials) {
    console.log(credentials)
    state.isAuthenticated = true;
    state.user = credentials.name;
    state.errors = {};
    // console.log(`Saving ${state.user.username}`)
    // console.log(state.user);
    JwtService.saveToken(state.user);
    window.localStorage.setItem("pass", credentials.password);
  },
  [PURGE_AUTH](state) {
    state.isAuthenticated = false;
    state.user = {};
    state.errors = {};
    JwtService.destroyToken();
  }
};

export default {
  state,
  actions,
  mutations,
  getters
};
