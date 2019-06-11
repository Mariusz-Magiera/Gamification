<template>
  <div class="profile-page">
    <ul>
      <li v-for="(achievement, index) of profile.achievements" :key="index">
        {{ achievement.description }}
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
