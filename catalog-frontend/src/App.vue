<template>
    <v-app>
        <v-main class="background">
            <v-form method="post" action="/logout" />
            <v-container fluid>
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
                <v-app-bar app dark color="primary" flat>
                    <v-toolbar-title>Каталог кураторов клиентов</v-toolbar-title>
                    <v-spacer />

                    <v-btn :disabled="!hasChanges" text class="mr-3" @click="saveOnServer">сохранить</v-btn>
                    <v-form method="post" action="/logout">
                        <v-btn text type="submit">exit</v-btn>
                    </v-form>
                </v-app-bar>
                <v-container>
                    <v-card outlined class="mt-3">
                        <catalog-table :catalog="catalogs" @edit="onEdit" />
                    </v-card>
                    <v-dialog v-model="dialog" max-width="750">
                        <catalog-edit :value="editedItem" @save="onSave" @close="close" />
                    </v-dialog>
                </v-container>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
import CatalogTable from "@/components/CatalogTable";
import CatalogEdit from "./components/CatalogEdit";
import {Catalog} from "./entity/Catalog";
import {deepEquals} from "./utils";

export default {
    name: 'App',
    components: {CatalogEdit, CatalogTable},
    // async mounted() {
    //     this.loading = true;
    //     try {
    //         const resp = await fetch('http://localhost:8081/catalog');
    //         this.catalog = await resp.json();
    //         this.originalData = JSON.parse(JSON.stringify(this.catalog));
    //     } catch (e) {
    //         alert('Произошла ошибка при получении данных!')
    //     } finally {
    //         this.loading = false;
    //     }
    // },
    mounted() {
        this.originalData = JSON.parse(JSON.stringify(this.catalog));
        console.log(deepEquals(this.originalData, this.catalog))
    },
    data: () => ({
        originalData: [],
        hasChanges: false,
        dialog: false,
        loading: false,
        catalog: [
            {
                arm: "internal_arm_1", boss: "internal_boss_1", curators: [
                    {name: "curator_1"}, {name: "curator_2"}
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
        checkChanges() {
            return !deepEquals(this.catalog, this.originalData);
        },
        close() {
            this.dialog = false;
            this.$nextTick(() => {
                this.editedItem = Object.assign({}, new Catalog({}));
                this.editedIndex = -1;
            });
        },
        onEdit(item) {
            this.editedIndex = this.catalogs.findIndex(e => e === item);
            this.editedItem = JSON.parse(JSON.stringify(this.catalogs[this.editedIndex]));
            this.dialog = true;
        },
        onSave(item) {
            this.$set(this.catalog, this.editedIndex, item);
            this.hasChanges = this.checkChanges();
            console.log(this.hasChanges);
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
<style>

</style>
