import React from "react";
import {connect} from "react-redux";
import { Button } from 'react-bootstrap';
import { setName } from "../actions/testActions";

class Main extends React.Component {
    render() {
        return (
            <div>
                <div className="row">
                    <div className="col-xs-12">
                        <h1>The Main Page</h1>
                    </div>
                </div>
                <div className="row">
                    <div className="col-xs-12">
                        <Button
                            bsStyle="primary"
                            onClick={() => this.props.setName("Jon")}>Change the Username
                        </Button>
                    </div>
                </div>
            </div>
        );
    }

}

const mapDispatchToProps = (dispatch) => {
    return {
        setName: (name) => {
            dispatch(setName(name));
        }
    };
};


export default connect(null, mapDispatchToProps)(Main);