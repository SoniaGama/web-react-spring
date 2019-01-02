package com.prueba.web.repositories;

//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.Repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.prueba.web.models.UserModel;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
//import org.springframework.data.repository.CrudRepository;

/*
@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<UserModel, String>{*/
@RepositoryRestResource(exported = false)
public interface UserRepository extends MongoRepository<UserModel, String>{
	//@Override
	
	void delete(UserModel deleted);
	
	UserModel findByName(String name);
	UserModel save(UserModel user); 
	
	UserModel findByEmail(String email);//BUSCA AL USUARIO POR MAIL
	Optional<UserModel> findById(String id);//objeto contenedor que puede o no contener un valor no nulo.
	
	
}
