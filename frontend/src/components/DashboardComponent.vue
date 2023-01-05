<template>
  <div>
    <DatabindComponent @mainFlights="(m) => {
      this.mainTableLoading=false;
      this.mainTableData=m;
    }"/>
    <div style="display: flex;">
      <div>
        <SidebarComponent
            @handleLogout="(m) => this.onLogout()"
            @sidePathSelection="(m) => this.changeContainedViewPath(m)"/>
      </div>
      <div style="width: 100%">
        <router-view name="containedView"/>
        <v-container>
          <v-row >
            <v-card style="width: 100%">
              <v-card-title>
                <v-text-field
                    v-model="mainTableSearch"
                    append-icon="mdi-magnify"
                    label="Search"
                    single-line
                    hide-details
                />
              </v-card-title>
              <v-data-table
                  style="width: 100%"
                  :headers="mainTableHeader"
                  :items="mainTableData"
                  :items-per-page="10"
                  class="elevation-1"
                  :loading="mainTableLoading"
                  loading-text="Loading... Please wait"
                  :search="mainTableSearch"
              />
            </v-card>
          </v-row>
        </v-container>
      </div>
    </div>

  </div>
</template>

<script>
import SidebarComponent from "@/components/SidebarComponent";
import DatabindComponent from "@/components/DatabindComponent";

export default {
  name: "DashboardComponent",
  components: {DatabindComponent, SidebarComponent},
  data() {
    return {
      mainTableHeader: [
        {text: 'Flight date', value: 'flight_date'},
        {text: 'Flight status', value: 'flight_status'},
        {text: 'Flight number', value: 'flight.number'},
        {text: 'Departure', value: 'departure.airport'},
        {text: 'Departure schedule', value: 'departure.scheduled'},
        {text: 'Arrival', value: 'arrival.airport'},
        {text: 'Arrival schedule', value: 'arrival.scheduled'},
        {text: 'Has Landed?', value: 'live.is_ground'}
      ],
      mainTableData: [],
      mainTableSearch: '',
      mainTableLoading: true,
    }
  },
  methods: {
    onLogout() {
      let data = {
        username: this.currentUser
      };
      this.$store.dispatch('logout', data)
          .then(() => {
            this.$router.replace('login');
          }, err => {
            console.error(err);
          })
    },
    changeContainedViewPath(path){
      console.log(path);
      this.$router.push('/dashboard'+path);
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.user;
    }
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.replace('login')
    }
  }
}
</script>

<style scoped>

</style>