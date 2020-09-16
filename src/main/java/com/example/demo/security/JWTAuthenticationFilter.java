package com.example.demo.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.entities.UserConnexion;
import com.example.demo.service.UserConnexionService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	private AuthenticationManager authenticationManager=null;
	private UserConnexionService userConnexionService=null;
	private String userAccessUsername = "";
	private String userAccessPassword="";
	UserConnexion userToIdentify = null;
	
public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super();
		this.authenticationManager = authenticationManager;
	}

@Override
public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
		throws AuthenticationException {
	UserConnexion user = null;
	try {
		user=new ObjectMapper().readValue(request.getInputStream(),UserConnexion.class);
		this.userAccessUsername = user.getIdentifiant();
		this.userAccessPassword = user.getPassword();
		
		
	}catch (Exception e) {
		// TODO Auto-generated catch block
		throw new RuntimeException(e);
	}
	//this.getUserConnected();
	return authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(user.getIdentifiant(), user.getPassword()));
}

@Override
	protected void successfulAuthentication(
			HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	
		User springUser=(User) authResult.getPrincipal();
		String jwt=Jwts.builder()
				.setSubject(springUser.getUsername())
				//.setSubject(springUser.getUsername() + " " + this.userToIdentify.getId()+ " " +this.userToIdentify.getNom()+ " " +this.userToIdentify.getPrenom())
				.setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS256, SecurityConstants.SECRET)
				.claim("roles", springUser.getAuthorities())
				.compact();
		/*
		ObjectMapper objectMapper = new ObjectMapper();
		   JsonNode jsonJwt = objectMapper.readTree(jwt);
		   ((ObjectNode)jsonJwt).put("userId", this.userToIdentify.getId());
		   ((ObjectNode)jsonJwt).put("nom", this.userToIdentify.getNom());
		   ((ObjectNode)jsonJwt).put("prenom",this.userToIdentify.getPrenom());*/
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX+jwt);
				
		//super.successfulAuthentication(request, response, chain, authResult);
	}
/*
private void getUserConnected(){
	if(this.userAccessUsername!=null && this.userAccessPassword!=null){
		try {
			this.userToIdentify = this.userConnexionService.getUser(this.userAccessUsername,this.userAccessPassword);
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	System.out.println("userToIdentify : "+this.userToIdentify);
}*/

private void getUserConnected(){
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	UserConnexion customUser = (UserConnexion)authentication.getPrincipal();
	int userId = customUser.getId();
	
	System.out.println("userToIdentify : "+userId);
}

}
