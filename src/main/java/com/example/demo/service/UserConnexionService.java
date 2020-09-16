package com.example.demo.service;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.UserConnexionRepository;
import com.example.demo.dao.VentesRepository;
import com.example.demo.entities.UserConnexion;
import com.example.demo.entities.Ventes;



	@Service
	@Transactional
	public class UserConnexionService implements IUserService {
		@Autowired
		private BCryptPasswordEncoder bCryptPasswordEncoder;

		@Autowired
		private UserConnexionRepository connexionRepository;		
		

		public UserConnexion modifyVente(int id, UserConnexion user) {
			user.setId(id);
			return connexionRepository.save(user);
			
		}

		public UserConnexion saveNewUser(UserConnexion user) {
			String hashPW =bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(hashPW);
			return connexionRepository.save(user);
			
		}

		public void deleteUser(int id) {
			
			connexionRepository.deleteUser(id);
			
		}

		@Override
		public UserConnexion getUser(String identifiant, String password) {
			UserConnexion user = connexionRepository.findUser(identifiant, password);
			
			if(user==null){
				throw new RuntimeException("user introuvable !");
			}
			return user;
		}

		@Override
		public List<UserConnexion> getUserByMC(String mot) {
			List<UserConnexion> user = connexionRepository.findUserByMC(mot);
	
			if(user==null){
				throw new RuntimeException("user introuvable !");
			}
			return user;
		}

		@Override
		public UserConnexion findUserByIdentifiant(String identifiant) {
			UserConnexion user = connexionRepository.findUserByIdentifiant(identifiant);
			return user;
		}

		
		
		
		
		

}
