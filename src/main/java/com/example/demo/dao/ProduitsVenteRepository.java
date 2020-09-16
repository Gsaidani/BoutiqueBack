package com.example.demo.dao;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.ProduitVentes;

public interface ProduitsVenteRepository extends JpaRepository<ProduitVentes, Long>{
	
	@Query(value="SELECT * FROM produit_ventes p where p.user_id= :idUser", nativeQuery = true)
	public Page<ProduitVentes> findAllProduits(@Param("idUser")int idUser,org.springframework.data.domain.Pageable pageable);

	
	@Query(value="SELECT COUNT(*) FROM produit_ventes p INNER JOIN user u ON u.id=p.user_id where u.id= :idUser", nativeQuery = true)
	public Integer findAllProduitsByUser(@Param("idUser")int idUser);

}
