package com.prueba.web.models;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//import com.fasterxml.jackson.annotation.JsonIgnore;

//import java.util.Collection;
//import java.util.List;
import java.util.Set;

import lombok.Data;

@Data
@Document(collection = "users")
public class UserModel /*implements UserDetails*/ {
	
	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();
	@Id
	private String id;
	@Indexed(unique=true, direction=IndexDirection.DESCENDING)
	private String name;
	private String address;	
	private String city;
	private String phone;
	private String email;
	private boolean enabled;
	//private @JsonIgnore String password;
	private String password;
	@DBRef
	private Set<Role> roles;
	
	/*
	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}
	
	protected UserModel() {}
	
	public UserModel(String name, String address, String city, String phone, String email ,String password, Set<Role> roles) {		
		
		this.name = name;
		this.address = address;
		this.city = city;
		this.phone = phone;
		this.email = email;	
		this.setPassword(password);
		this.roles = roles;
	}
	*/
	
/*
	public UserModel(String name2, String password2, List<GrantedAuthority> createAuthorityList) {
		// TODO Auto-generated constructor stub
	}
	
*/

	/* static Object withDefaultPasswordEncoder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	*/
	
	/*
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address=address;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city=city;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone=phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	*/
	
}