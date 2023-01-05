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
        <v-list-item-group
            v-model="selectedSide">
          <v-list-item
              v-for="(navLink, i) in navLinks"
              :key="i"
              :input-value="navLink"
              @click="selectionPath(navLink)"
              link>
            <v-list-item-icon>
              <v-icon>{{ navLink.icon }}</v-icon>
            </v-list-item-icon>
            <v-list-item-content>
              <v-list-item-title>{{ navLink.title }}</v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </v-list-item-group>
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
        {title: 'Track you flight', icon: 'mdi-airplane-marker', path: "track-flights"},
        {title: 'Search flights', icon: 'mdi-airplane', path: "search-flights"},
        {title: 'History', icon: 'mdi-airplane-clock', path: "history"},
        {title: 'Airline Routes', icon: 'mdi-routes', path: "airline-routes"},
        {title: 'Airports', icon: 'mdi-airport', path: "airports"},
        {title: 'Airlines', icon: 'mdi-airplane-check', path: "airlines"},
        {title: 'Cities', icon: 'mdi-city', path: "cities"},
        {title: 'Countries', icon: 'mdi-image-filter-hdr', path: "countries"},
      ],
      logoutLoading: false,
      selectedSide: null
    }
  },
  methods: {
    onLogout() {
      this.logoutLoading = true;
      this.$emit('handleLogout', true);
      this.logoutLoading = false;
    },
    selectionPath(nav) {
      this.$emit('sidePathSelection', nav.path);
    }
  },
  emits: [
    'handleLogout',
    'sidePathSelection'
  ],

}
</script>

<style scoped>

</style>