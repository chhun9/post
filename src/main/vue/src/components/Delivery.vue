<template>
  <div class="delivery">
    <input-info/>
    <delivery-status/>
  </div>
</template>

<script>
import InputInfo from "@/main/vue/src/components/InputInfo";
import DeliveryStatus from "@/main/vue/src/components/DeliveryStatus";

export default {
  name: 'Delivery',
  components: {DeliveryStatus, InputInfo}
}
</script>
