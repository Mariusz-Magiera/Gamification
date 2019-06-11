import Vue from "vue";
import axios from "axios";
import VueAxios from "vue-axios";
import JwtService from "@/common/jwt.service";
import { API_URL } from "@/common/config";

const ApiService = {
  init() {
    Vue.use(VueAxios, axios);
    Vue.axios.defaults.baseURL = API_URL;
  },

  setHeader() {
    Vue.axios.defaults.headers.common[
      "Authorization"
    ] = `Token ${JwtService.getToken()}`;
  },

  query(resource, params) {
    return Vue.axios.get(resource, params).catch(error => {
      throw new Error(`[RWV] ApiService ${error}`);
    });
  },

  get(resource, slug = "") {
    return Vue.axios.get(`${resource}/${slug}`).catch(error => {
      throw new Error(`[RWV] ApiService ${error}`);
    });
  },

  post(resource, params) {
    return Vue.axios.post(`${resource}`, params);
  }
};

export default ApiService;

export const UsersService = {
  query() {
    return ApiService.query("users/all");
  },
  get(id) {
    return ApiService.get("users/id", id);
  }
};

export const ArticlesService = {
  query(type, params) {
    return ApiService.query("projects" + (type === "feed" ? "/feed" : ""), {
      params: params
    });
  },
  get(id) {
    return ApiService.get("projects/id", id);
  }
};

export const ArticlesUserService = {
  get(id) {
    return ApiService.get(`projects/id/${id}/users`);
  }
};
