<template>

    <v-card>
        <v-toolbar color="primary" dark flat>
                <v-icon>mdi-close</v-icon>
            <v-toolbar-title>Кураторы</v-toolbar-title>
            <v-spacer/>
        </v-toolbar>
        <v-container>
            {{value}}
        </v-container>
    </v-card>

</template>

<script>
    export default {
        props: {
            value: Object
        },
    }
</script>
