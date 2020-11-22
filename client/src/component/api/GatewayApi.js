import AxiosAgent from "./AxiosAgent";

const API_ROOT = "http://localhost:8595/api";


export const saveGateway = (gateway) => {
    return AxiosAgent.requests.post(API_ROOT + '/gateway', gateway);
};



