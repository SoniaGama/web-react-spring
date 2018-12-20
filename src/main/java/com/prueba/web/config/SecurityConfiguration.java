package com.prueba.web.config;

//CONFIGURACION DE SEGURIDAD
import com.prueba.web.services.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	CustomizeAuthentication customizeAuthentication;
	
	//DEFINE lOS USER DETAILS
	@Bean
	public UserDetailsService mongoUserDetails() {
	    return new UserSecurity();
	}
	
	//UserSecurity userSecurity;
	
	@Override //CODIFICAR CONTRASEÑAS
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//ESPECIFICA QUE QUEREMOS USAR NUESTRA CONFIGURACION
	    UserDetailsService userDetailsService = mongoUserDetails();
	    auth
	        .userDetailsService(userDetailsService)
	        .passwordEncoder(bCryptPasswordEncoder);

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests()//RESTRINGIR ACESO A url's
	            .antMatchers("/").permitAll()
	            .antMatchers("/login").permitAll()
	            .antMatchers("/signup").permitAll()
	            .antMatchers("/dashboard/**").hasAuthority("ADMIN").anyRequest()
	            .authenticated()
	            .and()
	        .csrf().disable().formLogin().successHandler(customizeAuthentication)
	            //CSRF USUARIO Y CONTRASEÑA PREDEFINIDOS
	        	//INICIO DE SESION BASADO EN FORMMULARIOS
	            .loginPage("/login").failureUrl("/login?error=true")
	            .usernameParameter("email")
	            .passwordParameter("password")
	            .and()
	        .logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/")
	            .and()
	        .exceptionHandling();
	}
	
	/*
	// excluye los recursos static
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	        .ignoring()
	        .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	*/
	
}