"use strict";

export const INFO = 'info';
export const WARNING = 'warning';
export const ERROR = 'error';

export class Notification {

    constructor(type, text, obj) {
        this.type = type;
        this.text = text;
        this.obj = obj;
    }

    static defaultError() {
        return new Notification(ERROR, `Произошла ошибка при получении данных от сервера!`);
    }

    static defaultSuccess() {
        return new Notification(INFO, `Изменения сохранены`);
    }

}