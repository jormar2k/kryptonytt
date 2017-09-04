import React from 'react';
import ReactDOM from 'react-dom';
import App from './containers/App';
import {Provider} from "react-redux";
import store from "./store";
// require('../assets/scss/app.scss');


{/*<Provider store={store}>*/}
{/*<Router history={history}>*/}
{/*<Route path="/" component={App}>*/}
{/*<Route path="foo" component={Foo}/>*/}
{/*<Route path="bar" component={Bar}/>*/}
{/*</Route>*/}
{/*</Router>*/}
{/*</Provider>*/}

ReactDOM.render(
        <Provider store={store}>
            <App />
        </Provider>,
    document.getElementById('root'));