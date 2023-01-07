<template>
  <div>
    <v-container>
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
    </v-container>
  </div>
</template>

<script>
import aviationHttp from "@/api/aviationHttp";

export default {
  name: "AirlineRoutesComponent",
  data(){
    return{
      mainTableHeader: [
        {text: 'Flight number', value: 'flight.number'},
        {text: 'Departure', value: 'departure.airport'},
        {text: 'Departure terminal', value: 'departure.terminal'},
        {text: 'Arrival', value: 'arrival.airport'},
        {text: 'Arrival Terminal', value: 'arrival.terminal'},
        {text: 'Airline', value: 'airline.name'}
      ],
      mainTableData: [],
      mainTableSearch: '',
      mainTableLoading: true,
    }
  },
  methods:{
    searchRoutes(){
      let param = 'search='+this.mainTableSearch;
      aviationHttp.fetchRoutes(param).then(resp => {
        console.log(resp);
        this.mainTableData = resp.data.data;
        this.mainTableLoading = false;
      }).catch(err => {
        console.error(err);
      });
    }
  },
  mounted() {
    this.searchRoutes();
  }
}
</script>

<style scoped>

</style>