import React from "react";
import {connect} from "react-redux";
//////////////////////////////////////////
import {addPortFolioEntry} from '../actions/portFolioActions'
import {removePortFolioEntry} from '../actions/portFolioActions'
import {fetchPortFolios} from '../actions/portFolioActions'
import {fetchCurrencyData} from '../actions/portFolioActions'
import {AddNewEntry} from "./AddNewEntry";
import {PortFolioContainer} from "./PortFolioContainer";

class App extends React.Component {
    componentDidMount() {
        this.props.fetchPortFolios();
        this.props.fetchCurrencyData(10);
    }

    render() {
        var content;


        if (this.props.isLoading) {
            content = <h2>Loading...</h2>;
        } else if (this.props.error) {
            content = <h2>error</h2>;
        } else {
            content = (
                <div id="appcontainer" className="container">
                    <header>
                        <h1>Main title</h1>
                    </header>

                    {this.props.portfolios.map((portfolio, i) => <PortFolioContainer key={i} name={portfolio} currencies={this.props.currencies}/>)}

                    <AddNewEntry addEntry={(name) => this.props.addPortFolioEntry(name)}/>


                </div>
            );
        }

        return content;
    }
}

const mapStateToProps = (state) => {
    return {
        portfolios: state.portfolios.entries,
        currencies: state.portfolios.currencies,
        isLoading: state.portfolios.isLoading,
        error: state.portfolios.error
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        addPortFolioEntry: (name) => {
            dispatch(addPortFolioEntry(name));

        },
        removePortFolioEntry: (name) => {
            dispatch(removePortFolioEntry(name));

        },
        fetchPortFolios: () => {
            dispatch(fetchPortFolios());

        },
        fetchCurrencyData: (limit) => {
            dispatch(fetchCurrencyData(limit));

        }
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(App);
