package com.example.demo.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      /*
		auth.inMemoryAuthentication()
                .withUser("user").password("user").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("USER","ADMIN");
                */
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.csrf().disable();
    	http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	
    	//http.formLogin();
    	//http.formLogin().loginPage("/login");
    	http.authorizeRequests().antMatchers("/login/**").permitAll();
    	http.authorizeRequests().antMatchers("/produit/**").permitAll();
    	http.authorizeRequests().antMatchers("/vente/**").permitAll();
    	http.authorizeRequests().antMatchers("/user/**").permitAll();
    	 //http.authorizeRequests().antMatchers(HttpMethod.POST, "/user/**").hasAuthority("admin");
        http.authorizeRequests()
        .anyRequest().authenticated();
                //.antMatchers("/ventes").permitAll()
                //.antMatchers("/produits").permitAll()
                //.antMatchers("/program/").access("hasRole(ROLE_USER')")
                //.antMatchers("/admin").access("hasRole(ROLE_ADMIN')")
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
           http.addFilterBefore(new JWTAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);   
    }

}
