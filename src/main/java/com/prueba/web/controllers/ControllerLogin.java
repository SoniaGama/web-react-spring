package com.prueba.web.controllers;

import com.prueba.web.models.UserModel;
import com.prueba.web.services.UserSecurity;
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
	
	//INICIO DE SESIÓN	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("login");
	    return modelAndView;
	}
	
	//REGISTRO
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public ModelAndView signup() {
	    ModelAndView modelAndView = new ModelAndView();
	    UserModel user = new UserModel();
	    modelAndView.addObject("user", user);
	    modelAndView.setViewName("signup");//regresa al login
	    return modelAndView;
	}
	
	/*
	@RequestMapping(method=RequestMethod.POST, value="/users")
	public UserModel save (UserModel user) {
		userRepository.save(user);
		return user;
	}*/
	
	
	//VISUALIZACIÓN NUEVO USUARIO
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
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
	        modelAndView.addObject("successMessage", "User has been registered");
	        modelAndView.addObject("user", new UserModel());
	        modelAndView.setViewName("login");

	    }
	    return modelAndView;
	}
	
	
	//DATOS DE INICIO DE SESIÓN
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
	    ModelAndView modelAndView = new ModelAndView();
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    UserModel user = userSecurity.findUserByEmail(auth.getName());
	    modelAndView.addObject("currentUser", user);
	   /* 
	    modelAndView.addObject("fullName", "Welcome " + user.getName());	    
	    modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
	    */
	    modelAndView.setViewName("dashboard");
	    return modelAndView;
	}
	
	//VISTA DEL HOME
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public ModelAndView home() {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("home");
	    return modelAndView;
	}
	

}
