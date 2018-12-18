import React, { Component } from 'react';
import {Link} from 'react-router-dom';
import axios from 'axios';

class Home extends Coponent {
	
	render(){
		return(
				<div layout:fragment="content">
	            	<h1>Welcome!</h1>
	            </div>
				);
	}
	
}

export default Home;

