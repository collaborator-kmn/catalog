function hasOwnProperty(object, prop) {
    return Object.prototype.hasOwnProperty.call(object, prop);
}

export function deepEquals(left, right) {
    if (left === right) {
        return true;
    }

    else if ((!!left && typeof left == 'object') && (!!right && typeof right == 'object')) {
        if (Object.keys(left).length !== Object.keys(right).length) {
            return false;
        }

        for(const prop of Object.keys(left).values()) {
            if (hasOwnProperty(right, prop)) {
                if (!deepEquals(left[prop], right[prop])) {
                    return false;
                }
            }
        }

        return true;

    } else if ((!!left && Array.isArray(left)) && (!!right && Array.isArray(right))) {
        if (left.length !== right.length) {
            return false;
        }

        for(const prop of left.values()) {
            if (right[prop]) {
                if (!deepEquals(left[prop], right[prop])) {
                    return false;
                }
            }
        }

        return true;
    }

    else {
        return  false;
    }
}