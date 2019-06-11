<template>
  <div class="profile-page">
    <ul>
      <li v-for="(link, index) of profile.links" :key="index">
        <a :href="link.url">{{ link.type }}</a>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "RwvProfileFavorited",
  props: ["profile"]
};
</script>
