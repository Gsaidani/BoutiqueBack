package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.UserConnexion;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserConnexionService userConnexionService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserConnexion user=userConnexionService.findUserByIdentifiant(username);
		if(user==null){
			throw new UsernameNotFoundException(username); 
		}
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getTypeUser()));		
		
		return new User(user.getIdentifiant(),user.getPassword(),authorities);
	}

}
