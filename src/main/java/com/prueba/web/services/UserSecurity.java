package com.prueba.web.services;
//SERVICIO DE DETALLES DE USUARIO

import com.prueba.web.models.Role;
import com.prueba.web.models.UserModel;
import com.prueba.web.repositories.RoleRepository;
import com.prueba.web.repositories.UserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserSecurity implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserModel findUserByEmail(String email) {
		return userRepository.findByEmail(email);
		//OBTENER USUARIO POR EMAIL
	}
	
	
	//CREAR USUARIO NUEVO 
	//CIFRAR CONTRASEÑA 
	//ESTABLECE ROL
	public void saveUser(UserModel user) {
	    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    user.setEnabled(true);
	    Role userRole = roleRepository.findByRole("ADMIN");
	    user.setRoles(new HashSet<>(Arrays.asList(userRole)));
	    userRepository.save(user);
	}
	
	
	//INICIO DE SESIÓN
	//COMPARA EL EMAIL DE USUARIO CON EL DE LA COLECCION DE MONGODB
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	    UserModel user = userRepository.findByEmail(email);
	    if(user != null) {
	        List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
	        return buildUserForAuthentication(user, authorities);
	    } else {
	        throw new UsernameNotFoundException("username not found");
	    }
	}
	
	//CONVERTIR ROLES COMO COLECCION GRANTEDAUTHORITY
	private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
	    Set<GrantedAuthority> roles = new HashSet<>();
	    userRoles.forEach((role) -> {
	        roles.add(new SimpleGrantedAuthority(role.getRole()));
	    });

	    List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
	    return grantedAuthorities;
	}
	
	
	//CONECTAR USUARIO MONGODB CON USUARIO SPRING SECURTY
	private UserDetails buildUserForAuthentication(UserModel user, List<GrantedAuthority> authorities) {
	    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
	}
	
}




/*

@Component
public class UserSecurity implements UserDetailsService {
	
	private final UserRepository repository;
	
	@Autowired
	public UserSecurity(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		UserModel user = this.repository.findByName(name);
		return (UserDetails) new User(user.getName(), user.getPassword(),
				AuthorityUtils.createAuthorityList(user.getRoles()));
	}
	
}

*/

/*
@Component
public class UserSecurity implements UserDetailsService {
	//encontrar y autenticar usuarios
	@Autowired
	private UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		 
		User user = repository.findByName(name);

	    if(user == null) {
	      throw new UsernameNotFoundException("User not found");
	    }

	    return (UserDetails) new User();
	   
	}

}
*/
