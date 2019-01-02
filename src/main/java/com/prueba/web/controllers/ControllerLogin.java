package com.prueba.web.controllers;
//CONTROLADOR DE VISTAS

import com.prueba.web.models.UserModel;
import com.prueba.web.repositories.UserRepository;
import com.prueba.web.services.UserSecurity;

import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
 
@RestController
public class ControllerLogin {
	
	@Autowired
	private UserSecurity userSecurity;
	
	@Autowired
	UserRepository userRepository;
	
	//INICIO DE SESIÓN----Devuelve el modelo y la vista
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("login");
	    return modelAndView;
	}
	
	//DATOS DE INICIO DE SESIÓN POR USUARIO 
	@RequestMapping(method = RequestMethod.GET , path = "/dashboard", value = "/users/{id}")
	public ModelAndView dashboard() {
	    ModelAndView modelAndView = new ModelAndView();
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserModel user = userSecurity.findUserByEmail(auth.getName());
	    modelAndView.addObject("currentUser", user);
	    modelAndView.addObject("Welcome " + user.getName());
	    modelAndView.setViewName("dashboard");
	    return modelAndView;
	
	}
	/*
	//Vista usuario
	@RequestMapping(method = RequestMethod.GET, path = "/signup")
	public ModelAndView signup() {
	    ModelAndView modelAndView = new ModelAndView();
	    UserModel user = new UserModel();
	    modelAndView.addObject("user", user);
	    modelAndView.setViewName("signup");
	    return modelAndView;
	}
	*/
	//CREAR NUEVO USUARIO
	@RequestMapping(method = RequestMethod.POST,  value="/users", path = "/signup")
	public ModelAndView createNewUser(@Valid UserModel user, BindingResult bindingResult) {
	    ModelAndView modelAndView = new ModelAndView();
	    UserModel userExists = userSecurity.findUserByEmail(user.getEmail());
	    if (userExists != null) {
	        bindingResult
	                .rejectValue("email", "error.user",
	                        "User registered");
	    }
	    if (bindingResult.hasErrors()) {
	        modelAndView.setViewName("signup");
	    } else {
	    	userSecurity.saveUser(user);
	        modelAndView.addObject("successMessage", "User registered");
	        modelAndView.addObject("user", new UserModel());
	        modelAndView.setViewName("login");

	    }
	    return modelAndView;
	}
	
	

	//VISTA DEL HOME
	@RequestMapping (method = RequestMethod.GET, path = {"/","/home"})
	public ModelAndView home() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("home");
	    return modelAndView;
	}
	
	//editar
	@RequestMapping(method=RequestMethod.PUT, value= "/users/{id}", path="/edit")
	public UserModel update(@PathVariable String id, UserModel user) {
		Optional<UserModel> optUser = userRepository.findById(id);
		UserModel userEdit = optUser.get();
		if(user.getName() != null)
			userEdit.setName(user.getName());
		if(user.getAddress() != null)
			userEdit.setAddress(user.getAddress());
		if(user.getCity() != null)
			userEdit.setCity(user.getCity());
		if(user.getPhone() != null)
			userEdit.setPhone(user.getPhone());
		if(user.getEmail() != null)
			userEdit.setEmail(user.getEmail());
		
		userRepository.save(userEdit);
		return userEdit;	//regresa a la vista ./show
	}
	

}
