"use strict";

const url = `http://localhost:8081/catalog`;

export const api = {
    async load() {
        const response = await fetch(url);

        if (!response.ok) {
           throw Error("Request failed");
        }

        return await response.json();
    },

    async save(catalogs) {
        const response = await fetch(url, {
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