import React,{Component} from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';

class Edit extends Component {
	
	constructor(props){
		super(props);
		this.state = {
				user: {}
		};
	}
	
	componentDidMount(){
		axios.get('/users/' +  this.props.match.params.id)
			.then(response => {
				this.setState({user: response.data});
				console.log(this.state.user);
			});
	}
	
	onChange = (e) => {
		const state = this.state.user;
		state[e.target.name] = e.target.value;
		this.setState({user:state});		
	}
	
	onSubmit = (e) => {
		e.preventDefault();
		
		const {name, address, city, postalCode, phone} = this.state.user;
		
		axios.put('/users/' + this.props.match.params.id, {name, address, city, postalCode, phone})
			.then(result => {
				this.props.history.push('/show/'+this.props.match.params.id)
			});
	}
	
	render(){
		return(
				<div class="container">
				
					<div class="panel-heading">
						<h3 class="panel-title">
							Edit User
						</h3>
					</div>
					
					<div class="panel-body">
						//<h4><Link to={`/show/${this.state.user.id}`}>User List</Link></h4>
						
						<form onSubmit={this.onSubmit}>
						
							<div class="form-group">
								<label for="name">Name:</label>
								<input type="text" class="form-control" name="name" value={this.state.user.name} onChange={this.onChange} placeholder="Name"/>
							</div>
								
							<div class="form-group">
								<label for="address">Address:</label>
								<input type="text" class="form-control" name="address" value={this.state.user.address} onChange={this.onChange} placeholder="Address"/>
							</div>
						
							<div class="form-group">
								<label for="city">City:</label>
								<input type="text" class="form-control" name="city" value={this.state.user.city} onChange={this.onChange} placeholder="City"/>
							</div>
								
							<div class="form-group">
								<label for="phone">Phone:</label>
								<input type="text" class="form-control" name="phone" value={this.state.user.phone} onChange={this.onChange} placeholder="Phone"/>
							</div>
						
							<div class="form-group">
								<label for="email">Email:</label>
								<input type="text" class="form-control" name="email" value={this.state.user.email} onChange={this.onChange} placeholder="Email"/>
							</div>
								
							<button type="submit" class="btn btn-default">Update User</button>
							<button><Link to='/'>Salir</Link></button>
						</form>
						
					</div>
					
				</div>				
				);		
	}
	
}

export default Edit;