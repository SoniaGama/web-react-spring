import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';

class Login extends Component {
	
	constructor(props){
		super(props);
		this.state = {
				users:[]
		};
	}
	
	//envia los datos del formulario a la base de datos
	onSubmit = (e) => {
		e.preventDefault();
		const {email, password} = this.state;
		axios.post('/users/', { email, password })
			.then(result => onSuccess(result))
				.catch(error => console.log(error));
	}
	
	onChange = (e) => {
		const state = this.state;
		state[e.target.name] = e.target.value;
		this.setState(state);
	}

  render() {
    return (    		
    	const {email, password} = this.state;
    		
    	<h1 class="h3 mb-3 font-weight-normal">Sign in</h1>    		
    	<form class="form-signin" onSubmit={this.onSubmit}/>

            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email" value={email} onChange={this.onChange} required/>
            		"
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" name="password" id="inputPassword" class="form-control" value={password} onChange={this.onChange} placeholder="Password" required/>
            		
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
        
        <form class="form-signin">
            <Link to="/signup"><button class="btn btn-md btn-success btn-block" type="Submit">Signup Here</button></Link>
        </form>
    );
  }
}

export default Login;

