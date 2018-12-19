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
	
	//envia el formulario
	onSubmit = (e) => {
		e.preventDefault();
		const {email, password} = this.state;
		console.log(this.state);
		axios.post('/users/', { email, password })
			.then(result => console.log(result));
	}
	
	//trae los datos de la base de satos
	componentDidMount(){
		axios.get('/users/' + this.props.match.params.id)
			.then(response => {
				console.log(response);
				this.setState({user: response.data});
				console.log(this.state.user);
			});
	}
	
	
	//cambios de estado
	onChange = (e) => {
		const state = this.state;
		state[e.target.name] = e.target.value;
		this.setState({state});		
	}
	
		
  render() {
    return (    		
    	const {email, password} = this.state;
    		
    	<h1 class="h3 mb-3 font-weight-normal">Sign in</h1>    		
    	<form class="form-signin"/* th:action="@{/login}" method="post"*/>
            
           
            /*            
            if("${param.error}"){
            	<div>
                	Invalid
                </div>
            }
           
            if("${param.logout}"){
            	<div>
                	You have been logged out.
                </div>
            }             
            */
            
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email" required/>
            		"
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required/>
            		
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
        
        <form class="form-signin"/* th:action="@{/signup}" method="get"*/>
            <Link to="/signup"><button class="btn btn-md btn-success btn-block" type="Submit">Signup Here</button></Link>
        </form>
    );
  }
}

export default Login;

