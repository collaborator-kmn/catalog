<template>
    <v-card>
        <v-toolbar flat color="primary" dark>
            <v-toolbar-title>Редактирование записи</v-toolbar-title>
            <v-spacer/>
            <v-btn text icon @click.stop="close">
                <v-icon>mdi-close</v-icon>
            </v-btn>
        </v-toolbar>
        <v-form ref="form" v-model="valid" lazy-validation>
            <v-card-text>
                <v-row dense>
                    <v-col grow>
                        <v-text-field placeholder="Куратор" dense hide-details outlined v-model="curator" required :rules="[v => !!v ||'Обязательное поле']"></v-text-field>
                    </v-col>
                    <v-col cols="2">
                        <v-btn @click="onClick">добавить</v-btn>
                    </v-col>
                    <v-col cols="12"/>
                </v-row>
                <v-divider />
                <v-data-table
                        :headers="headers"
                        :items="items"
                        disable-pagination
                        hide-default-footer
                >
                    <template v-slot:[`item.actions`]="{ item }">
                        <v-icon
                                small
                                @click.stop="remove(item)"
                        >
                            mdi-delete
                        </v-icon>
                    </template>
                </v-data-table>
            </v-card-text>
        </v-form>
        <v-divider />
        <v-card-actions>
            <v-btn @click="save">сохранить</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
    export default {
        name: "CatalogEdit",
        props: {
            value: {
                type: Object,
                required: true
            }
        },
        data: () => ({
            valid: true,
            item: {},
            curator: '',
            headers: [
                {text: "Куратор", sortable: false, value: "name"},
                {text: "Действия", sortable: false,  value: "actions", width: '5%'},
            ]
        }),
        methods: {
            onClick() {
                if (this.$refs.form.validate()) {
                    this.item.curators.push({name: this.curator});
                }
            },
            save() {
                this.$emit('save', this.item);
                this.close();
            },
            close() {
                this.$refs.form.resetValidation();
                this.$emit('close');
            },
            remove(item) {
                this.item.curators.splice(item, 1);
            }
        },
        computed: {
            items() {
                return this.item.curators;
            }
        },
        watch: {
            value: {
                immediate: true,
                handler(val) {
                    console.log(val)
                    this.item = Object.assign({}, val);
                }
            }
        }
    }
</script>

<style scoped>

</style>