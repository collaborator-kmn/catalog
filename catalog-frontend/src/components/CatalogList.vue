<template>
    <div>
        <li>
        <CatalogItem
            v-for="(item,i) in catalog" :item="item" :key="i"/>
        </li>
    </div>
</template>

<script>
import CatalogItem from '@/components/CatalogItem'

    export default {
        props: ['catalog'],
        components: {
            CatalogItem
        }
    }
</script>

<style scoped>

</style>


