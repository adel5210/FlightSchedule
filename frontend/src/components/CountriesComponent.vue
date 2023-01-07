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
  name: "CountriesComponent",
  data(){
    return{
      mainTableHeader: [
        {text: 'Country name', value: 'country_name'},
        {text: 'Country ISO2', value: 'country_iso2'},
        {text: 'Country ISO3', value: 'country_iso3'},
        {text: 'Country ISO Numeric', value: 'country_iso_numeric'},
        {text: 'Population', value: 'population'},
        {text: 'Capital', value: 'capital'},
        {text: 'Continent', value: 'continent'},
        {text: 'Currency name', value: 'currency_name'},
        {text: 'Currency code', value: 'currency_code'},
        {text: 'Fips Code', value: 'fips_code'},
        {text: 'Phone prefix', value: 'phone_prefix'},
      ],
      mainTableData: [],
      mainTableSearch: '',
      mainTableLoading: true,
    }
  },
  methods:{
    searchCountry(){
      console.log(this.mainTableSearch)
      let param = this.mainTableSearch ? 'search='+this.mainTableSearch : null;
      aviationHttp.fetchCountries(param).then(resp => {
        console.log(resp);
        this.mainTableData = resp.data.data;
        this.mainTableLoading = false;
      }).catch(err => {
        console.error(err);
      });
    }
  },
  mounted() {
    this.searchCountry();
  }
}
</script>

<style scoped>

</style>