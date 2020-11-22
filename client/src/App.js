import './App.css';
import {Route, Switch} from "react-router-dom";
import GatewayList from "./component/gateway/GatewayList";
import React from "react";
import GatewayCreate from "./component/gateway/GatewayCreate";

function App() {
  return (
          <Switch>
              <Route exact path="/" component={GatewayList} />
              <Route path={"/gateway/list"} component={GatewayList} />
              <Route path={"/create"} component={GatewayCreate} />
          </Switch>
  );
}

export default App;
