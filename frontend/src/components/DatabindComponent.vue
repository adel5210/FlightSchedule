<template><div></div></template>
<script>
import aviationHttp from "@/api/aviationHttp";

export default {
  name: "DatabindComponent",
  data(){
    return{
      cachedFlights: null
    }
  },
  methods: {
    initFlights(){
      aviationHttp.fetchFlights().then(resp => {
        console.log(resp);
        this.$emit('mainFlights', resp.data.data);
      }).catch(err => {
        console.error(err);
      });
    }
  },
  mounted() {
    this.initFlights();
  },
  emits: [
      'mainFlights'
  ]
}
</script>
