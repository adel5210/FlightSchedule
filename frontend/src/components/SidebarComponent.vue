<template>
  <div>
    <v-navigation-drawer permanent
                         height="100vh">
      <v-list-item>
        <v-list-item-content>
          <v-list-item-title>Dummy FlightSchedule</v-list-item-title>
          <v-list-item-subtitle>Created by Adel Mahmoud Sadek</v-list-item-subtitle>
        </v-list-item-content>
      </v-list-item>
      <v-divider/>
      <v-list
          dense
          nav>
        <v-list-item
            v-for="navLink in navLinks"
            :key="navLink.title"
            link>
          <v-list-item-icon>
            <v-icon>{{ navLink.icon }}</v-icon>
          </v-list-item-icon>
          <v-list-item-content>
            <v-list-item-title>{{ navLink.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
      <template v-slot:append>
        <div class="pa-2">
          <v-btn block
                 :loading="logoutLoading"
                 @click="onLogout">
            Logout
          </v-btn>
        </div>
      </template>
    </v-navigation-drawer>
  </div>
</template>

<script>
export default {
  name: "SidebarComponent",
  data() {
    return {
      navLinks: [
        {title: 'Track you flight', icon: 'mdi-airplane-marker'},
        {title: 'Search flights', icon: 'mdi-airplane'},
        {title: 'History', icon: 'mdi-airplane-clock'},
        {title: 'Airline Routes', icon: 'mdi-routes'},
        {title: 'Airports', icon: 'mdi-airport'},
        {title: 'Airlines', icon: 'mdi-airplane-check'},
        {title: 'Cities', icon: 'mdi-city'},
        {title: 'Countries', icon: 'mdi-image-filter-hdr'},
      ],
      logoutLoading: false
    }
  },
  methods: {
    onLogout() {
      this.logoutLoading = true;
      this.$emit('handleLogout', true);
      this.logoutLoading = false;
    }
  },
  emits: [
    'handleLogout'
  ]
}
</script>

<style scoped>

</style>