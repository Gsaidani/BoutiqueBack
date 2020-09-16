package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entities.UserConnexion;
import com.example.demo.entities.Ventes;

public interface IUserService {
	 
	 public UserConnexion getUser(String identifiant, String password);
	 public List<UserConnexion> getUserByMC(String mot);
	 public UserConnexion findUserByIdentifiant(String identifiant);

}
