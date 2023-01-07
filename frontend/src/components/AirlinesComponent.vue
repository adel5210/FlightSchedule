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
  name: "AirlinesComponent",
  data(){
    return{
      mainTableHeader: [
        {text: 'Airline', value: 'airline_name'},
        {text: 'IATA Code', value: 'iata_code'},
        {text: 'IATA Prefix accounting', value: 'iata_prefix_accounting'},
        {text: 'ICAO Code', value: 'icao_code'},
        {text: 'Callsign', value: 'callsign'},
        {text: 'Type', value: 'type'},
        {text: 'Status', value: 'status'},
        {text: 'Fleet size', value: 'fleet_size'},
        {text: 'Fleet average age', value: 'fleet_average_age'},
        {text: 'Date founded', value: 'date_founded'},
        {text: 'Hub code', value: 'hub_code'},
        {text: 'Country', value: 'country_name'},
        {text: 'Country ISO2', value: 'country_iso2'},
      ],
      mainTableData: [],
      mainTableSearch: '',
      mainTableLoading: true,
    }
  },
  methods:{
    searchAirlines(){
      let param = this.mainTableSearch ? 'search='+this.mainTableSearch : null;
      aviationHttp.fetchAirlines(param).then(resp => {
        console.log(resp);
        this.mainTableData = resp.data.data;
        this.mainTableLoading = false;
      }).catch(err => {
        console.error(err);
      });
    }
  },
  mounted() {
    this.searchAirlines();
  }}
</script>

<style scoped>

</style>