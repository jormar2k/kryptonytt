import React from "react";
//////////////////////////////////////////
import {PortFolio} from '../components/PortFolio';
import {Button} from "react-bootstrap";

export class PortFolioContainer extends React.Component {
    constructor(props) {
        super(props);
		this.state = {symbolValue: ''};
    }

    render() {
        var availableCurrencies = this.props.currencies.map((currency) => currency.symbol);
        return (
            <div id="portfoliocontainer">
				<h3>{this.props.name}</h3>
                <PortFolio/>
                <Button bsStyle="primary" bsSize="sm"> Add row</Button>
            </div>

        );
	
	}
	
	getSymbols(currencies) {
		var symbols = [];
		currencies.forEach(function(currency) {
			symbols.push(currency.symbol);
		});
	}
}

