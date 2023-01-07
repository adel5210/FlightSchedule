<template>
  <div>
    <v-container>
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
    </v-container>
  </div>
</template>

<script>
import aviationHttp from "@/api/aviationHttp";

export default {
  name: "SearchFlightComponent",
  data(){
    return{
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
  methods:{
    searchFlights(){
      let param = this.mainTableSearch ? 'search='+this.mainTableSearch : null;
      aviationHttp.fetchFlights(param).then(resp => {
        console.log(resp);
        this.mainTableData = resp.data.data;
        this.mainTableLoading = false;
      }).catch(err => {
        console.error(err);
      });
    }
  },
  mounted() {
    this.searchFlights();
  }
}
</script>

<style scoped>

</style>