import React from "react";
import {connect} from "react-redux";
//////////////////////////////////////////
import {Button} from 'react-bootstrap';
import {Form} from 'react-bootstrap';
import {FormControl} from 'react-bootstrap';
import {FormGroup} from 'react-bootstrap';
//////////////////////////////////////////
import {PortFolio} from '../components/PortFolio';
import {addPortFolioEntry} from '../actions/portFolioActions'
import {removePortFolioEntry} from '../actions/portFolioActions'

class App extends React.Component {

    render() {
        return (
            <div id="appcontainer" className="container">
                <header>
                    <h1>Test</h1>
                </header>

                <PortFolio/>

                <Form inline>
                    <FormGroup controlId="entryInput" validationState={this.getValidationState()}>
                        <FormControl type="string" value={this.state.value}
                                     placeholder="Add portfolio"
                                     componentClass="input"/>

                        <Button id="addportfoliobtn" bsStyle="primary"
                                onClick={() => this.props.addPortFolioEntry("dd")}>Change the Username
                        </Button>
                    </FormGroup>

                </Form>

            </div>
        );
    }

    getValidationState() {
        const length = this.state.value.length;
        if (length > 10) return 'success';
        else if (length > 5) return 'warning';
        else if (length > 0) return 'error';
    }
}


const mapStateToProps = (state) => {
    return {
        portfolios: state.portfolios,
    };
};

const mapDispatchToProps = (dispatch) => {
    return {
        addPortFolioEntry: (name) => {
            dispatch(addPortFolioEntry(name));

        },
        removePortFolioEntry: (name) => {
            dispatch(removePortFolioEntry(name));

        }
    };
};

export default connect(mapStateToProps, mapDispatchToProps)(App);
