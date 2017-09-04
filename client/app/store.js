import {createStore, combineReducers, applyMiddleware} from "redux";
import {createLogger} from "redux-logger";
import thunk from "redux-thunk";
import promise from "redux-promise-middleware";


import portfolios from "./reducers/portFolioReducer";
import test from "./reducers/testReducer";

export default createStore(
    combineReducers({portfolios, test}), {}, applyMiddleware(thunk, promise())
);