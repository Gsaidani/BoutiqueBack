package com.example.demo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.UserConnexion;
import com.example.demo.service.UserConnexionService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserConnexionRestService {
	@Autowired
	public UserConnexionService connexionService;
	
	private String login = ""; 
	private String mdp="";
	
	
	@RequestMapping(value="/user/modify/{id}", method=RequestMethod.PUT)
	public UserConnexion modifyVente(@PathVariable int id,@RequestBody UserConnexion user ){
		
		connexionService.modifyVente(id, user);
		return connexionService.getUser(user.getIdentifiant(),user.getPassword());
	}
	
	 
/*
	@RequestMapping(value="/login", method=RequestMethod.GET)
	 public UserConnexion getUserByCompte(
				@RequestParam(name="login", defaultValue="") String login, 
				@RequestParam(name="mdp", defaultValue="") String mdp			
				
				){
		 this.login=login;
		 this.mdp=mdp;
		 System.out.println(login + mdp);
		 
			return connexionService.getUser(login,mdp);
		}*/
	@RequestMapping(value="/login", method=RequestMethod.POST)
	 public UserConnexion getUserByCompte(
			 @RequestBody UserConnexion user
				
				){
		 
			return connexionService.findUserByIdentifiant(user.getIdentifiant());
		}
	
	@RequestMapping(value="/user/byMC", method=RequestMethod.GET)
	 public List<UserConnexion> findUserByMotcle(
				@RequestParam(name="mot", defaultValue="") String mot){
		
			return connexionService.getUserByMC(mot);
		}
	
	
	@RequestMapping(value="/user", method=RequestMethod.GET)
	 public UserConnexion findUserByCredentials(
				@RequestParam(name="username", defaultValue="") String username,
				@RequestParam(name="password", defaultValue="") String password){
		
			return connexionService.getUser(username, password);
		}
	
	@RequestMapping(value="/userAA", method=RequestMethod.GET)
	 public UserConnexion findUserAA(){
		
			return getUserAfterAuth();
		}
	
	
	public UserConnexion getUserAfterAuth(){
		
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			System.out.println("ici!");
			UserConnexion customUser = (UserConnexion)authentication.getPrincipal();
			int userId = customUser.getId();
			
			System.out.println("userToIdentify : "+userId);
			return customUser;
		
	}
	 
	 @RequestMapping(value="/user/save", method=RequestMethod.POST)
		public UserConnexion addNewVente(@RequestBody UserConnexion user){
		 UserConnexion existingUser = connexionService.findUserByIdentifiant(user.getIdentifiant());
		 if(existingUser!=null){
			 throw new RuntimeException(" Ce User existe deja");
		 }
		 
		 connexionService.saveNewUser(user);
			 return connexionService.getUser(user.getIdentifiant(),user.getPassword());
			
		}
	 
	 @RequestMapping(value="/user/delete/{id}", method=RequestMethod.DELETE)
		public void deleteUserById(@PathVariable int id){
		 connexionService.deleteUser(id);		 
		}

}
