<template>
  <div>
    <NotAvailable/>
  </div>
</template>

<script>
import NotAvailable from "@/components/NotAvailable";
export default {
  name: "AirlineRoutesComponent",
  components: {NotAvailable},
  mounted() {
    console.log("MOUNTED AIRLINE ROUTES")
  }
}
</script>

<style scoped>

</style>