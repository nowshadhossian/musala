import './App.css';
import {Route, Switch} from "react-router-dom";
import GatewayList from "./component/gateway/GatewayList";
import React from "react";
import GatewayCreate from "./component/gateway/GatewayCreate";
import DeviceList from "./component/device/DevicelList";
import DeviceCreate from "./component/device/DeviceCreate";


function App() {
  return (
          <Switch>
              <Route exact path="/" component={GatewayList} />
              <Route path={"/gateway/list"} component={GatewayList} />
              <Route path={"/gateway/create"} component={GatewayCreate} />
              <Route path={"/device/list/:id"} component={DeviceList} />
              <Route path={"/device/create/:id"} component={DeviceCreate} />
          </Switch>
  );
}

export default App;
