import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';

class App extends Component {
	
	constructor(props){
		super(props);
		this.state = {
				users:[]
		};
	}
	
	componentDidMount(){
		axios.get('/users')
			.then(response => {
				this.setState({users: response.data});
				console.log(this.state.users);
			});
	}
		
  render() {
    return (    		
    		 <div>
    	        <div class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
    	            <Link class="navbar-brand" to="/">Web react spring</a>
    	            
    	            <div class="collapse navbar-collapse" id="navbarSupportedContent">
    	                
    	                <div class="navbar-nav ml-auto">
   	                
    	                    <div class="nav-item">    	                    
    	                    	<Link class="nav-link" to="/login">Log in</a>
    	                    </div>
    	                    
    	                    <div class="nav-item">    	                        
    	                        <Link to="/logout" /*method="post"*/>LogOut</Link> 
    	                    </div>
    	                    
    	                </div>
    	                
    	            </div>
    	        </div>

    	        <div class="container">
    	            <div layout:fragment="content"></div>
    	        </div>
    		/*
    	<div class="container">
    		<button class="btn btn-default"><Link to="/login">Iniciar Sesión</Link></button>
    		<button class="btn btn-default"><Link to="/create">Crear Usuario</Link></button>    		
    	</div>  
    	*/
    );
  }
}

export default App;