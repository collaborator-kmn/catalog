import Vue from 'vue';
import Vuetify from 'vuetify/lib/framework';
import colors from 'vuetify/lib/util/colors';
import '@mdi/font/css/materialdesignicons.css';

Vue.use(Vuetify);

export default new Vuetify({
    theme: {
        themes: {
            light: {
                primary: '#dd0a34',
                background: colors.grey.lighten4,
            }
        }
    },
    icons: {
        iconfont: 'mdi',
    },
});
