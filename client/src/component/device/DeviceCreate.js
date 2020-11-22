import React, {useEffect} from "react";
import {NavLink, useHistory} from "react-router-dom";
import {ErrorMessage, Field, Form, Formik} from "formik";
import * as Yup from "yup";
import * as DeviceApi from "../api/DeviceApi";
import Layout from "../layout/Layout";

function DeviceCreate(props) {
    let history = useHistory();
    useEffect(() => {


    }, []);

    const savePeripheral = (device) => {
        DeviceApi.saveDevice(device).then((response) => {
            history.push("/device/list/" + props.match.params.id);
        });
    };

    return(
        <Layout title={"Device Create"}>
            <NavLink to={"/device/list"} className={"btn btn-success"}>List</NavLink>

            <section>
                <Formik
                    initialValues={{
                        vendor: '',
                        gateway : {id: props.match.params.id}
                    }}
                    validationSchema={Yup.object({
                        vendor: Yup.string()
                            .required("Please complete this field"),
                    })}
                    onSubmit={(values, {setSubmitting, setFieldError}) => {
                        setTimeout(() => {
                            savePeripheral(values);
                            setSubmitting(false);
                        }, 400);
                    }}
                >
                    <Form>
                        <div>
                            <div>
                                <label className="" htmlFor="name">Vendor</label>
                                <div className="input-group">
                                    <Field
                                        name="vendor"
                                        type="text"
                                        id="name"
                                        className="form-control" placeholder="Enter vendor"
                                    />
                                    <ErrorMessage className={"alert alert-danger"} name="vendor" component="div"/>
                                </div>
                            </div>
                            <div>
                                <label className="" htmlFor="status">Status</label>
                                <div className="input-group">
                                    <Field as="select" name="status">
                                        <option value="ONLINE">ONLINE</option>
                                        <option value="OFFLINE">OFFLINE</option>
                                    </Field>
                                </div>
                            </div>
                        </div>


                        <div className="text-center">
                            <button type="submit" className="btn btn-success">
                                Save
                            </button>
                        </div>

                    </Form>
                </Formik>
            </section>
        </Layout>
    );
}

export default DeviceCreate;