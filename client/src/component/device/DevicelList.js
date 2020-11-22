import React, {useEffect, useState} from "react";
import {NavLink, Link} from "react-router-dom";
import {Button, Table} from "react-bootstrap";
import * as DeviceApi from "../api/DeviceApi";
import moment from "moment";
import Layout from "../layout/Layout";


function DeviceList(props) {
    const [devices, setDevices] = useState([]);

    useEffect(() => {
        DeviceApi.findByGateway(props.match.params.id).then((response) => {
            setDevices(response);
        });

    }, []);

    return(
        <Layout title={"Peripheral List"}>
            <Link to={"/device/create/" + props.match.params.id} className={"btn btn-success"}>Create</Link>
            <section>
                <Table>
                    <thead>
                    <td>sl.</td>
                    <td>Vendor</td>
                    <td>Status</td>
                    <td>Created</td>
                    <td>Action</td>
                    </thead>
                    <tbody>
                    {devices && devices.map((item, index) =>
                        <tr>
                            <td>{index + 1}</td>
                            <td>{item.vendor}</td>
                            <td>{item.status}</td>
                            <td>{moment(item.created).format("YYYY-MM-DD h:mm")}</td>
                            <td><Button>Delete</Button></td>
                        </tr>
                    )}
                    </tbody>
                </Table>
            </section>
        </Layout>
    );
}

export default DeviceList;