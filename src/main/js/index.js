import React from 'react';
import ReactDOM from 'react-dom';
import {BrowserRouter as Router, Route} from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import App from './App';
import './App.css';
import Edit from './components/Edit';
import Signup from './components/Signup';
import Dashboard from './components/Dashboard';
import Home from './components/Home';
import Login from './components/Login';

ReactDOM.render(		
		<Router>
			<div>
				<Route exact path='/' component={App} />
				<Route path='/edit/:id' component={Edit} />
				<Route path='/signup' component={Signup} />
				<Route path='/home/:id' component={Home} />
				<Route path = '/login' component={Login} />
				<Route path = '/dashboard' component={Dashboard} />
			</div>
		</Router>,
		document.getElementById('root')		
		//<App />, document.getElementById('root')
		);
