import React from "react";
import {NavLink, Link} from "react-router-dom";
import {Button, Table} from "react-bootstrap";


function GatewayList() {
    return(
        <div>
            <h1>Gateway list</h1>
            <Link to={"/create"} className={"btn btn-success"}>Create</Link>
            <section>
                <Table>
                    <thead>
                        <td>sl.</td>
                        <td>Serial No</td>
                        <td>Name</td>
                        <td>IP</td>
                        <td>Action</td>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>22</td>
                            <td>ff</td>
                            <td>192</td>
                            <td><Button>Peripheral</Button></td>
                        </tr>
                    </tbody>
                </Table>
            </section>
        </div>
    );
}

export default GatewayList;