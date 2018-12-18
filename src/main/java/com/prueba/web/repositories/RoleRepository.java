package com.prueba.web.repositories;

import com.prueba.web.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {
	//CONSULTA POR DATOS DE ROL
	Role findByRole(String role);
}

