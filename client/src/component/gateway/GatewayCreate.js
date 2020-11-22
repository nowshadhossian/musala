import React, {useEffect} from "react";
import {NavLink} from "react-router-dom";
import {ErrorMessage, Field, Form, Formik} from "formik";
import * as Yup from "yup";
import * as GatewayApi from "../api/GatewayApi";

function GatewayCreate() {

    useEffect(() => {


    }, []);

    const saveGateway = (gateway) => {
        GatewayApi.saveGateway(gateway);
    };

    return(
        <div>
            <h1>Gateway create</h1>
            <NavLink to={"/gateway/list"} className={"btn btn-success"}>List</NavLink>

            <section>
                <Formik
                    initialValues={{
                        fundAmount: 0,
                    }}
                    validationSchema={Yup.object({
                        name: Yup.string()
                            .required("Please complete this field"),
                    })}
                    onSubmit={(values, {setSubmitting, setFieldError}) => {
                        setTimeout(() => {
                            saveGateway(values);
                            setSubmitting(false);
                        }, 400);
                    }}
                >
                    <Form>
                        <div>
                            <div>
                                <label className="" htmlFor="name">Name</label>
                                <div className="input-group">
                                    <Field
                                        name="name"
                                        type="text"
                                        id="name"
                                        className="form-control" placeholder="Enter Name"
                                    />
                                    <ErrorMessage className={"alert alert-danger"} name="name" component="div"/>
                                </div>
                            </div>
                            <div>
                                <label className="" htmlFor="name">IP</label>
                                <div className="input-group">
                                    <Field
                                        name="ip"
                                        type="text"
                                        id="ip"
                                        className="form-control" placeholder="Enter ip"
                                    />
                                </div>
                            </div>
                            <div>
                                <label className="" htmlFor="serialNo">IP</label>
                                <div className="input-group">
                                    <Field
                                        name="serialNo"
                                        type="text"
                                        id="ip"
                                        className="form-control" placeholder="Enter serial no"
                                    />
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
        </div>
    );
}

export default GatewayCreate;