export function addPortFolioEntry(name) {
    return {
        type: "ADD_PORTFOLIO_ENTRY",
        payload: name
    };
}

export function removePortFolioEntry(name) {
    return {
        type: "REMOVE_PORTFOLIO_ENTRY",
        payload: name
    };
}
