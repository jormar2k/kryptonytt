const portFolioReducer = (state = {
    portFolios: [],
    lastValues: []
}, action) => {
    switch (action.type) {
        case "ADD_PORTFOLIO_ENTRY":
            state = {
                ...state,
                portFolios: [...state.lastValues, action.payload],
                lastValues: [...state.lastValues, action.payload]
            };
            break;

    case "REMOVE_PORTFOLIO_ENTRY":
        state = {
            ...state,
            portFolios: [...state.lastValues, action.payload],
            lastValues: [...state.lastValues, action.payload]
        };
        break;
    }
    return state;
};

export default portFolioReducer;