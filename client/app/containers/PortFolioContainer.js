import React from "react";
//////////////////////////////////////////
import {PortFolio} from '../components/PortFolio';
import {Button} from "react-bootstrap";

export class PortFolioContainer extends React.Component {
    constructor(props) {
        super(props);
    }

    render() {
        var availableCurrencies = this.props.currencies.map((currency) => currency.symbol);
        console.log(this.props.currencies);
        console.log(availableCurrencies);
        return (
            <div id="portfoliocontainer">
                <PortFolio/>
                <Button bsStyle="primary" bsSize="sm"> Add row</Button>
            </div>

        );
    }
}

