package com.prueba.web.repositories;

//import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.Repository;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.prueba.web.models.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

/*public interface UserRepository extends Repository<User, Long>{*/
@RepositoryRestResource(exported = false)
public interface UserRepository extends MongoRepository<UserModel, String>{
	//@Override
	
	void delete(UserModel deleted);
	UserModel findByName(String name);
	UserModel save(UserModel user); 
	
	UserModel findByEmail(String email);
	//BUSCA AL USUARIO POR MAIL
}

//consulta para  obtener datos por email