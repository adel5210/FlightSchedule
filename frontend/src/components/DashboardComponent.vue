<template>
  <div>
    <div style="display: flex;">
      <div>
        <v-overlay :value="overlay"/>
        <SidebarComponent
            @handleLogout="(m) => this.onLogout()"
            @sidePathSelection="(m) => this.changeContainedViewPath(m)"/>
      </div>
      <div style="width: 100%">
        <component :is="multipleComponent"/>
      </div>
    </div>
  </div>
</template>

<script>
import SidebarComponent from "@/components/SidebarComponent";
import TrackFlightConponent from "@/components/TrackFlightConponent";
import SearchFlightComponent from "@/components/SearchFlightComponent";
import HistoryComponent from "@/components/HistoryComponent";
import AirlineRoutesComponent from "@/components/AirlineRoutesComponent";
import AirportComponent from "@/components/AirportComponent";
import AirlinesComponent from "@/components/AirlinesComponent";
import CitiesComponent from "@/components/CitiesComponent";
import CountriesComponent from "@/components/CountriesComponent";

export default {
  name: "DashboardComponent",
  components: {
    CountriesComponent,
    CitiesComponent,
    AirlinesComponent,
    AirportComponent,
    AirlineRoutesComponent,
    HistoryComponent,
    SearchFlightComponent,
    TrackFlightConponent,
    SidebarComponent
  },
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
      currentSelectedPath: null,
      overlay: true
    }
  },
  methods: {
    onLogout() {
      let data = {
        username: this.currentUser.username
      };
      this.$store.dispatch('logout', data)
          .then(() => {
            this.$router.replace('login');
          }, err => {
            console.error(err);
          })
    },
    changeContainedViewPath(path) {
      console.log(path);
      this.currentSelectedPath = path;
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.user;
    },
    multipleComponent(){
      switch (this.currentSelectedPath){
        case 'track-flights': return TrackFlightConponent;
        case 'search-flights': return SearchFlightComponent;
        case 'history': return HistoryComponent;
        case 'airline-routes': return AirlineRoutesComponent;
        case 'airports': return AirportComponent;
        case 'airlines': return AirlinesComponent;
        case 'cities': return CitiesComponent;
        case 'countries': return CountriesComponent;
      }
      return null;
    }
  },
  mounted() {
    // if (!this.currentUser) {
    //   this.$router.replace('login')
    // }
    this.overlay = false;
  }
}
</script>

<style scoped>

</style>