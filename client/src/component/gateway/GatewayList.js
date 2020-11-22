import React, {useEffect, useState} from "react";
import {NavLink, Link} from "react-router-dom";
import {Button, Table} from "react-bootstrap";
import * as GatewayApi from "../api/GatewayApi";


function GatewayList() {
    const [gateways, setGateways] = useState([]);

    useEffect(() => {
        GatewayApi.findAll().then((response) => {
            setGateways(response.content);
        });

    }, []);

    return(
        <div>
            <h1>Gateway list</h1>
            <Link to={"/gateway/create"} className={"btn btn-success"}>Create</Link>
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
                        {gateways && gateways.map((item, index) =>
                            <tr>
                                <td>{index + 1}</td>
                                <td>{item.serialNo}</td>
                                <td>{item.name}</td>
                                <td>{item.ip}</td>
                                <td><Link to={"/device/list/" + item.id} className={"btn btn-warning"}>Peripheral</Link></td>
                            </tr>
                        )}
                    </tbody>
                </Table>
            </section>
        </div>
    );
}

export default GatewayList;