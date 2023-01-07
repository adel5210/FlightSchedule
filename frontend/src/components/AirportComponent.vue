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
  name: "AirportComponent",
  data(){
    return{
      mainTableHeader: [
        {text: 'Airport', value: 'airport_name'},
        {text: 'IATA Code', value: 'iata_code'},
        {text: 'ICAO Code', value: 'icao_code'},
        {text: 'Latitude', value: 'latitude'},
        {text: 'Longitude', value: 'longitude'},
        {text: 'Geoname ID', value: 'geoname_id'},
        {text: 'GMT', value: 'gmt'},
        {text: 'Phone #', value: 'phone_number'},
        {text: 'Country', value: 'country_name'},
        {text: 'Country ISO2', value: 'country_iso2'},
        {text: 'City IATA', value: 'city_iata_code'}
      ],
      mainTableData: [],
      mainTableSearch: '',
      mainTableLoading: true,
    }
  },
  methods:{
    searchAirports(){
      let param = this.mainTableSearch ? 'search='+this.mainTableSearch : null;
      aviationHttp.fetchAirports(param).then(resp => {
        console.log(resp);
        this.mainTableData = resp.data.data;
        this.mainTableLoading = false;
      }).catch(err => {
        console.error(err);
      });
    }
  },
  mounted() {
    this.searchAirports();
  }
}
</script>

<style scoped>

</style>