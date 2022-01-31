"use strict";

const url = process.env.VUE_APP_BACKEND_URL
const catalogUrl = '/catalog'
const getCatalog = url + catalogUrl+ '/get'
const updateCatalog = url +catalogUrl + '/save'

export const api = {
    async load() {
        const response = await fetch(getCatalog);

        if (!response.ok) {
           throw Error("Request failed");
        }

        return await response.json();
    },

    async save(catalogs) {
        const response = await fetch(updateCatalog, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(catalogs)
        });

        if (!response.ok) {
            throw Error("Request failed");
        }

        return await response.json();
    }
};