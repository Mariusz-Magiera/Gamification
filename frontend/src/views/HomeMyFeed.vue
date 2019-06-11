<template>
  <div class="home-my-feed"><UserList type="feed" /></div>
</template>

<script>
import UserList from "@/components/UserList";

export default {
  name: "rwv-home-my-feed",
  components: {
    UserList
  }
};
</script>
