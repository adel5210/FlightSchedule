<template>
  <div>
    <div style="display: flex;">
      <div>
        <v-navigation-drawer permanent
                             height="100vh">
          <v-list-item>
            <v-list-item-content>
              <v-list-item-title>Home</v-list-item-title>
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
        </v-navigation-drawer>
      </div>
      <div style="width: 100%">
        <v-container>
          <v-autocomplete
              dense
              fille
              solo
          />
        </v-container>
      </div>
    </div>

  </div>
</template>

<script>
export default {
  name: "DashboardComponent",
  data() {
    return {
      navLinks: [
        {title: 'Dashboard', icon: 'mdi-view-dashboard'},
        {title: 'Photos', icon: 'mdi-image'},
        {title: 'About', icon: 'mdi-help-box'}
      ],
    }
  }
}
</script>

<style scoped>

</style>