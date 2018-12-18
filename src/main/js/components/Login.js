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
	
	onSubmit = (e) => {
		e.preventDefault();
		const {name, address, city, phone, email, password} = this.state;
				
		console.log(e);
		console.log(this.state);
		
		axios.post('/users/', { name, email })
			.then(result => console.log(result));
	}
	
	componentDidMount(){
		axios.get('/users/' + this.props.match.params.id)
			.then(response => {
				console.log(response);
				this.setState({user: response.data});
				console.log(this.state.user);
			});
	}
	
	onChange = (e) => {
		const state = this.state.user;
		state[e.target.name] = e.target.value;
		this.setState({user:state});		
	}
	
		
  render() {
    return (    		
    		const {name, address, city, phone, email, password} = this.state;
    		
    	<form class="form-signin"/* th:action="@{/login}" method="post"*/>
            
            <h1 class="h3 mb-3 font-weight-normal">Sign in</h1>
           
            <div th:if="${param.error}">
                Invalid email and password.
            </div>
            
            <div th:if="${param.logout}">
                You have been logged out.
            </div>
                
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" name="email" id="inputEmail" class="form-control" placeholder="Email" required/>
            		"
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Password" required/>
            		
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
        
        <form class="form-signin" th:action="@{/signup}" method="get">
            <button class="btn btn-md btn-success btn-block" type="Submit">Signup Here</button>
        </form>
    );
  }
}

export default Login;

