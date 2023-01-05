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
  name: "CitiesComponent",
  data(){
    return{
      mainTableHeader: [
        {text: 'City name', value: 'city_name'},
        {text: 'IATA Code', value: 'iata_code'},
        {text: 'Country ISO2', value: 'country_iso2'},
        {text: 'Latitude', value: 'latitude'},
        {text: 'Longitude', value: 'longitude'},
        {text: 'Timezone', value: 'timezone'},
        {text: 'GMT', value: 'gmt'},
        {text: 'Geoname ID', value: 'geoname_id'},
      ],
      mainTableData: [],
      mainTableSearch: '',
      mainTableLoading: true,
    }
  },
  methods:{
    searchCities(){
      console.log(this.mainTableSearch)
      let param = 'search='+this.mainTableSearch;
      aviationHttp.fetchCities(param).then(resp => {
        console.log(resp);
        this.mainTableData = resp.data.data;
        this.mainTableLoading = false;
      }).catch(err => {
        console.error(err);
      });
    }
  },
  mounted() {
    this.searchCities();
  }
}
</script>

<style scoped>

</style>