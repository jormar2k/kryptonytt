import React from "react";
import {Button, ControlLabel, Form, FormControl, FormGroup} from 'react-bootstrap';


export class AddNewEntry extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            value: ''
        }
    }

    getValidationState() {
        const length = this.state.value.length;
        if (length === 0) return 'error';
        else return 'success';
    }

    handleChange(e) {
        this.setState({value: e.target.value});
    }

    btnClicked() {
        this.props.addEntry(this.state.value);
        this.setState({value: ''})
    }
    render() {
        return (
            <Form inline>
                <FormGroup controlId="entryInput" validationState={this.getValidationState()}>
                    <FormControl type="text" value={this.state.value} onChange={this.handleChange.bind(this)}
                                 placeholder="Add portfolio"
                                 componentClass="input"/>
                    <FormControl.Feedback/>
                </FormGroup>
                <Button id="addportfoliobtn" bsStyle="primary" disabled={this.state.value.length < 1}
                        onClick={this.btnClicked.bind(this)}>Add new
                </Button>

            </Form>
        );
    }


}

