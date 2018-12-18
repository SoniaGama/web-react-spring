package com.prueba.web.controllers;


import com.prueba.web.models.UserModel;
import com.prueba.web.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
public class UserController{
		
	@Autowired
	UserRepository userRepository;
	
	
	@RequestMapping(method=RequestMethod.GET, value="/users")
	public Iterable<User> user(){
		return userRepository.findAll();
	}
	
	
	@RequestMapping(method=RequestMethod.POST, value="/users")
	public UserModel save (UserModel user) {
		userRepository.save(user);
		return user;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/users/{id}")
	public Optional<UserModel> show(@PathVariable String id){
		return userRepository.findById(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/users/{id}")
	public UserModel update(@PathVariable String id, UserModel user) {
		Optional<UserModel> optUser = userRepository.findById(id);
		UserModel one = optUser.get();
		if(user.getName() != null)
			one.setName(user.getName());
		if(user.getAddress() != null)
			one.setAddress(user.getAddress());
		if(user.getCity() != null)
			one.setCity(user.getCity());
		if(user.getPhone() != null)
			one.setPhone(user.getPhone());
		if(user.getEmail() != null)
			one.setEmail(user.getEmail());
		
		userRepository.save(one);
		return one;	//regresa a la vista ./show
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/users/{id}")
	public String delete(@PathVariable String id) {
		Optional<UserModel> optUser = userRepository.findById(id);
		UserModel user = optUser.get();
		userRepository.delete(user);
		
		return "";
	}
	
	
}