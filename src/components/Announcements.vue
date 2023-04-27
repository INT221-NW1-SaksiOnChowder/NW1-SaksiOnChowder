<script setup>
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <div class="w-full h-full">
      <h1 class="text-3xl font-bold flex justify-center">SIT Announcement System (SAS)</h1>
      <p class="mx-10 font-semibold">Date/Time in Timezone:</p>
      <table class="table-auto">
  <thead>
    <tr>
      <th class="text-pink-300">Song</th>
      <th class="text-xl">Artist</th>
      <th>Year</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>The Sliding Mr. Bones (Next Stop, Pottersville)</td>
      <td>Malcolm Lockyer</td>
      <td>1961</td>
    </tr>
    <tr>
      <td>Witchy Woman</td>
      <td >The Eagles</td>
      <td>1972</td>
    </tr>
    <tr>
      <td>Shining Star</td>
      <td>Earth, Wind, and Fire</td>
      <td>1975</td>
    </tr>
  </tbody>
</table>

  </div>
    
</template>

<style scoped>

</style>