import React from "react";
import { Table } from 'react-bootstrap';

export const PortFolio = (props) => {
    return (
        <div>
            <h4>props.name</h4>
            <Table responsive>

                <thead>
                <tr>
                    <th>#</th>
                    <th>Th 1</th>
                    <th>Th 2</th>
                    <th>Th 3</th>
                    <th>Th 4</th>
                    <th>Th 5</th>
                    <th>Th 6</th>
                    <th>Th 7</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>Cell 1</td>
                    <td>Cell 2</td>
                    <td>Cell 3</td>
                    <td>Cell 4</td>
                    <td>Cell 5</td>
                    <td>Cell 6</td>
                    <td>Cell 7</td>
                </tr>
                </tbody>
            </Table>
        </div>

    );
}
