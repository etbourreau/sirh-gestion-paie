package dev.paie.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder)
			.usersByUsernameQuery("SELECT nomUtilisateur, motDePasse, actif FROM utilisateur WHERE nomUtilisateur=?")
			.authoritiesByUsernameQuery("SELECT nomUtilisateur, role FROM utilisateur WHERE nomUtilisateur=?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest()
			.authenticated()
		.and()
			.exceptionHandling().accessDeniedPage("/mvc/employes") //page de redirection
		.and()
			.formLogin()
			.loginPage("/mvc/connexion")
			.permitAll();
	}
	
	@Override
	public void configure(WebSecurity web){
		web.ignoring()
			.antMatchers("/res/**");
	}
	
}
