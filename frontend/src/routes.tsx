import React from 'react';
import { Switch, Route } from 'react-router-dom';

import Home from './pages/Home';
import Clients from './pages/Clients'
import Login from './pages/Login';
import NotFound from './components/NotFound';

const Routes: React.FC = () => {
  return (
      <Switch>
          <Route path="/login" exact component={Login} />
          <Route path="/" exact component={Home} />
          <Route path="/clientes" exact component={Clients} />
          <Route component={NotFound} />
      </Switch>
  );
}

export default Routes;