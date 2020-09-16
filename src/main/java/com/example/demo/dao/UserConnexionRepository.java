package com.example.demo.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.UserConnexion;
import com.example.demo.entities.Ventes;


public interface UserConnexionRepository extends JpaRepository<UserConnexion, Long> {

	
	
	@Query(value="SELECT * FROM user", nativeQuery = true)
	public UserConnexion findUser(@Param("username")String username, @Param("password")String password);
	
	@Query(value="SELECT * FROM user u where u.identifiant LIKE :login", nativeQuery = true)
	public UserConnexion findUserByIdentifiant(@Param("login")String login);
	
	@Query(value="SELECT * FROM user u where u.nom LIKE %:mot% OR u.prenom LIKE %:mot%", nativeQuery = true)
	public List<UserConnexion> findUserByMC(@Param("mot")String mot);

	@Modifying
	@Transactional
	@Query(value="DELETE FROM user WHERE user.id= :id", nativeQuery = true)
	public void deleteUser(@Param("id")int id);
	

}
