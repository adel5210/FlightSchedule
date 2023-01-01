<template>
  <div>
    <DatabindComponent @mainFlights="(m) => this.mainTableData=m"
    />
    <div style="display: flex;">
      <div>
        <SidebarComponent
            @handleLogout="(m) => this.onLogout()"/>
      </div>
      <div style="width: 100%">
        <v-container>
          <v-row>
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
    }
  },
  methods:{
    onLogout(){
      let data = {
        username : this.currentUser
      };
      this.$store.dispatch('logout', data)
      .then(() => {
        this.$router.push('login');
      }, err => {
        console.error(err);
      })
    }
  },
  computed:{
    currentUser(){
      console.log(this.$store.state.user.username);
      return this.$store.state.user.username;
    }
  },
  mounted() {
    if(!this.currentUser){
      this.$router.push('login')
    }
  }
}
</script>

<style scoped>

</style>