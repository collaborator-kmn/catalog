<template>
    <v-app>
        <v-main class="background">
            <message v-if="message" :value="message" @close="message = null"></message>
            <v-form method="post" action="/logout" />
            <v-container fluid>
                <v-overlay :value="loading">
                    <v-row>
                        Выполнение запроса
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
                    <v-form method="get" action="/logout">
                        <v-btn text type="submit">Выход</v-btn>
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
import CatalogTable from "./components/CatalogTable";
import CatalogEdit from "./components/CatalogEdit";
import Message from "./components/Message";
import {Catalog} from "./entity/Catalog";
import {deepEquals} from "./utils";
import {api} from "./modules";
import {ERROR, Notification} from "./modules/Notification";

export default {
    name: 'App',
    components: {CatalogEdit, CatalogTable, Message},
    async mounted() {
        this.loading = true;
        try {
            const resp = await api.load();
            this.originalData = JSON.parse(JSON.stringify(resp));
            this.catalog = resp;
        } catch (e) {
            this.message = Notification.defaultError();
        } finally {
            this.loading = false;
        }
    },
    data: () => ({
        originalData: [],
        hasChanges: false,
        dialog: false,
        loading: false,
        catalog: [],
        editedItem: new Catalog({}),
        editedIndex: -1,
        message: null
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
            this.close();
        },
        async saveOnServer() {
            this.loading = true;
            try {
                const resp = await api.save(this.catalog);
                this.originalData = JSON.parse(JSON.stringify(resp));
                this.catalog = resp;
                this.hasChanges = true;
                this.message = Notification.defaultSuccess();
            } catch (e) {
                this.message = new Notification(ERROR, 'Произошла ошибка при обновлении кураторов!');
            } finally {
                this.loading = false;
            }
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
