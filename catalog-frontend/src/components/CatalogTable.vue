<template>
    <v-data-table
            :headers="headers"
            :items="catalog"
            disable-pagination
            hide-default-footer
            no-data-text="Нет данных"
    >
        <template v-slot:[`item.curators`]="{ item }">
            <v-icon
                    small
                    class="mr-4"
                    @click.stop="edit(item)">
                mdi-pencil
            </v-icon>
        </template>
    </v-data-table>
</template>

<script>

export default {
    props: ['catalog'],
    data: () => ({
        headers: [
            {
                text: 'АРМ',
                value: 'arm',
                sortable: false
            },
            {
                text: 'АРМ Босс',
                value: 'boss',
                sortable: false
            },
            {
                text: 'Кураторы',
                value: 'curators',
                width: '5%',
                sortable: false
            }
        ]
    }),
    methods: {
        edit(item) {
            this.$emit('edit', item);
        }
    }
}
</script>
