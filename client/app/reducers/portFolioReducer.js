const portFolioReducer = (state = {
    currencies: [],
    entries: [],
    lastValues: [],
    isLoading: true,
    error: false
}, action) => {
    switch (action.type) {
        case "ADD_PORTFOLIO_ENTRY":
            state = {
                ...state,
                entries: [...state.entries, action.payload],
                lastValues: [...state.entries, action.payload]
            };
            break;

        case "FETCH_PORTFOLIOS_REQUEST":
            state = {
                ...state,
                isLoading: true
            };
            break;
        case "FETCH_PORTFOLIOS_REJECTED":
            state = {
                ...state,
                isLoading: false,
                error: true
            };
            break;
        case "FETCH_PORTFOLIOS_FULFILLED":
            console.log(action.payload);
            state = {
                ...state,
                entries: action.payload,
                isLoading: false
            };
            break;
        case "FETCH_CURRENCIES_FULFILLED":
            console.log(action.payload);
            state = {
                ...state,
                currencies: action.payload,
                isLoading: false
            };
            break;
        case "FETCH_CURRENCIES_REJECTED":
            console.log(action.payload);
            state = {
                ...state,
                isLoading: false,
                error: true
            };
            break;

        case "REMOVE_PORTFOLIO_ENTRY":
            state = {
                ...state,
                entries: [...state.lastValues, action.payload],
                lastValues: [...state.lastValues, action.payload]
            };
            break;
}
    return state;
};

export default portFolioReducer;