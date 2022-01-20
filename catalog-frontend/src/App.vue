<template>
    <v-app>
        <v-main class="background">
            <v-container>
                <v-overlay :value="loading">
                    <v-row>
                        Получение данных
                    </v-row>
                    <v-row>
                        <v-progress-circular
                                indeterminate
                                size="128"
                        ></v-progress-circular>
                    </v-row>
                </v-overlay>
                <v-card>
                    <v-toolbar dark color="primary" flat>
                        <v-toolbar-title>Каталог кураторов клиентов</v-toolbar-title>
                        <v-spacer />
                        <v-btn outlined @click="saveOnServer">сохранить</v-btn>
                    </v-toolbar>
                </v-card>
                <v-card outlined class="mt-3">
                    <catalog-table :catalog="catalogs" @edit="onEdit" />
                </v-card>
                <v-dialog v-model="dialog" max-width="750">
                    <catalog-edit :value="editedItem" @save="onSave" @close="close" />
                </v-dialog>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
import CatalogTable from "@/components/CatalogTable";
import CatalogEdit from "./components/CatalogEdit";
import {Catalog} from "./entity/Catalog";

export default {
    name: 'App',
    components: {CatalogEdit, CatalogTable},
    async mounted() {
        this.loading = true;
        try {
            const resp = await fetch('http://localhost:8081/catalog');
            this.catalog = await resp.json();
        } catch (e) {
            alert('Произошла ошибка при получениее данных!')
        } finally {
            this.loading = false;
        }
    },
    data: () => ({
        dialog: false,
        loading: false,
        catalog: [
            {
                arm: "internal_arm_1", boss: "internal_boss_1",
                curators: [
                    {name: "curator_1"},
                    {name: "curator_2"}
                ]
            },
            {
                arm: "internal_arm_2", boss: "internal_boss_2", curators: [
                    {name: "curator_3"}, {name: "curator_4"}
                ]
            }
        ],
        editedItem: new Catalog({}),
        editedIndex: -1
    }),
    methods: {
        close() {
            this.dialog = false;
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, new Catalog({}));
                this.editedIndex = -1;
            });
        },
        onEdit(item) {
            this.editedIndex = this.catalogs.findIndex(e => e === item);
            this.editedItem = Object.assign(new Catalog({}), this.catalogs[this.editedIndex]);
            this.dialog = true;
        },
        onSave(item) {
            this.catalog[this.editedIndex] = item;
            this.close();
        },
        saveOnServer() {

        }
    },
    computed: {
        catalogs() {
            return this.catalog.map(result => new Catalog(result));
        }
    }
}
</script>
