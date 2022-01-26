<template>
    <v-snackbar
            app
            top
            right
            timeout=3000
            :value="value"
            transition="fab-transition"
    >
        <template v-slot:action="{ attrs }">
            <v-btn icon v-bind="attrs" @click="$emit('close')">
                <v-icon color="red">
                    mdi-close
                </v-icon>
            </v-btn>
        </template>
        <div class="body-2 font-weight-bold">
            <div class="d-flex flex-row">
                <v-icon :color="colored" class="mr-3">
                    {{icon}}
                </v-icon>
                <span class="body-1 d-flex flex-column">
                    {{title}}
                    <span class="body-2">
                        {{value.text}}
                    </span>
                </span>
            </div>
        </div>
    </v-snackbar>
</template>

<script>
    import {ERROR, WARNING} from "../modules/Notification";

    export default {
        name: "Message",
        props: {
            value: {
                type: Object,
                required: true
            }
        },
        data: () => ({
            message: null
        }),
        computed: {
            icon() {
                switch (this.value.type) {
                    case WARNING: return `mdi-alert`;
                    case ERROR: return `mdi-alert-octagon`;
                    default: return `mdi-checkbox-marked-circle-outline`;
                }
            },
            colored() {
                switch (this.value.type) {
                    case WARNING: return `orange`;
                    case ERROR: return `red`;
                    default: return `green`;
                }
            },
            title() {
                switch (this.value.type) {
                    case WARNING: return `Предупреждение`;
                    case ERROR: return `Ошибка`;
                    default: return `Информация`;
                }
            }
        }
    }
</script>

<style scoped>

</style>