export function addPortFolioEntry(name) {
    return {
        type: "ADD_PORTFOLIO_ENTRY",
        payload: name
    };
}

export function fetchPortFolios() {
    return {
        type: "FETCH_PORTFOLIOS",
        payload: new Promise((resolve, reject) => {
            return fetch(`https://api.coinmarketcap.com/v1/ticker/?convert=EUR&limit=10`)
                .then(response => resolve(['portfolio 1', 'portfolio 2']), error => reject(error))
        })
    };
}

export function fetchCurrencyData(limit) {
    return {
        type: "FETCH_CURRENCIES",
        payload: new Promise((resolve, reject) => {
            return fetch(`https://api.coinmarketcap.com/v1/ticker/?convert=EUR&limit=` + limit )
                .then(response => resolve(response.json()), error => reject(error))
        })
    };
}

export function removePortFolioEntry(name) {
    return {
        type: "REMOVE_PORTFOLIO_ENTRY",
        payload: name
    };
}
// export function fetchPortFolios() {
//     return function (dispatch) {
//         dispatch(requestPortFolios());
//
//         return fetch(`https://api.coinmarketcap.com/v1/ticker/?convert=EUR&limit=10`)
//             .then(
//                 response => response.json(),
//                 error => fetchPortFoliosRejected(error) //Need better error handling
//             )
//             .then(json => dispatch(fetchPortFoliosSuccess(json)))
//
//     }
// }
//
//
// export function requestPortFolios() {
//     return {
//         type: "FETCH_PORTFOLIOS_REQUEST"
//     }
//
// }
//
// export function fetchPortFoliosSuccess(response) {
//     return {
//         type: "FETCH_PORTFOLIOS_FULFILLED",
//         status: 'success',
//         payload: ['dasffwf', 'jhhthrt']
//     }
// }
//
// export function fetchPortFoliosRejected(err) {
//     return {
//         type: "FETCH_PORTFOLIOS_REJECTED",
//         error: err
//     }
// }

