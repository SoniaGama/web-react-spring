import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';

class Signup extends Component {
	
	constructor(){
		super();
		this.state = {
				name:"",
				address:"",
				city:"",
				phone:"",
				email:"",
				password:""
		};
	}
	
	//envia los datos a mongo db
	onSubmit = (e) => {
		e.preventDefault();
		const {name, address, city, phone, email, password} = this.state;
				
		console.log(e);
		console.log(this.state);
		
		axios.post('./users', { name, address, city, phone, email, password })
			//.then(result => this.props.history.push("/"));
			.then(result => console.log(this.props.history));
	}

	
	//detecta cambio de estado en el formulario
	onChange = (e) => {
		const state = this.state;
		state[e.target.name] = e.target.value;
		this.setState({state});	
	}

	render(){
		const {name, address, city, phone, email, password} = this.state;
		
		return(
			<div class="container">
				<div class="panel panel-default">
				
					<div class="panel-heading">
						<h3 class="panel-title">Sign Up</h3>
					</div>
					
					<div class="panel-body">
	            		<form class="form-signin" onSubmit={this.onSubmit}>	            		
	            			                    
	            			<div class="form-group">										
	            				<label for="name">Name:</label>
	            				<input type="text" class="form-control" name="name" value={name} id="name" onChange={this.onChange} placeholder="Name" required/>
	            			</div>
						
	            			<div class="form-group">
	            				<label for="address">Address:</label>
	            				<input type="text" class="form-control" name="address" value={address} id="address" onChange={this.onChange} placeholder="Address" required/>
							</div>
					
							<div class="form-group">
								<label for="city">City:</label>
								<input type="text" class="form-control" name="city" value={city} id="city" onChange={this.onChange} placeholder="City" required/>
							</div>
					
							<div class="form-group">
								<label for="phone">Phone:</label>
								<input type="text" class="form-control" name="phone" value={phone} id="phone" onChange={this.onChange} placeholder="Phone" required/>
							</div>
					
							<div class="form-group">
								<label for="email">Email:</label>
								<input type="text" class="form-control" name="email" value={email} id="email" onChange={this.onChange} placeholder="Email" required/>
							</div>
							
							<div class="form-group">
								<label for="password">Email:</label>
								<input type="password" class="form-control" name="password" value={password} id="password" onChange={this.onChange} placeholder="Password" required/>
							</div>
						
							<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
				
	                    </form>
							
						<form class="form-signin" /* th:action="@{/login}" method="get"*/>
			                <Link to="/login">
			                	<button class="btn btn-md btn-success btn-block">Sign In</button>
			                </Link>
			            </form>
	                </div>
	            </div>
	         </div>
	    );
	}
	
	
}

export default Signup;