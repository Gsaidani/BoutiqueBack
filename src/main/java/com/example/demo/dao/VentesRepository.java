package com.example.demo.dao;

import java.awt.print.Pageable;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.ProduitVentes;
import com.example.demo.entities.Ventes;

public interface VentesRepository extends JpaRepository<Ventes, Long> {
	
	/*
	@Query("select * from Ventes v where v.date_vente BETWEEN :debut AND :fin")
	public Page<Ventes> findVentesByDate(@Param("debut")String debut, @Param("fin")String fin, Pageable pageable);	
	@Query(value="select * from Ventes v where v.id= :x")
	public Page<Ventes> findVentesById(@Param("x") Long id, Pageable pageable);
	@Query(value="select * from Ventes v where v.article LIKE :mc")
	public List<Ventes> findVentesByMC(@Param("mc") String article);
	
	
	@Query(value="select * from Ventes v where v.article like :mc")
	public Page<Ventes> findVentesByMC(@Param("mc") String article, Pageable pageable);
	
	@Query(value="select * from Ventes v where v.id= :x")
	public List<Ventes> findVentesById(@Param("x") Long id);
	
	@Query(value="select v.article AS article,v.prix_article AS prix_unitaire,v.quantite AS quantite,v.prix_article*v.quantite AS total,v.date_vente AS date from Ventes v ORDER BY date DESC")
	
	@Query(value="select * from Ventes")
	public List<Ventes> findVentesByArticle();
	
	@Query("select v from Ventes v where v.date_vente BETWEEN :debut AND :fin")
	public List<Ventes> findVentesByDate(@Param("debut")Date debut, @Param("fin")Date fin);
		
*/
	//System.out.println("now : "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString());
	
	//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	//String enDate = format.parse(endDate);
	@Query(value="SELECT * FROM ventes v INNER JOIN produit_ventes p ON p.id=v.article_id  where p.user_id= :idUser ", nativeQuery = true)
	public Page<Ventes> findAllVentes(@Param("idUser")int idUser,org.springframework.data.domain.Pageable pageable);
	
	@Query(value="SELECT * FROM Ventes v INNER JOIN produit_ventes p ON p.id=v.article_id  where p.user_id= :idUser and v.date_vente BETWEEN :debut AND :fin ORDER BY v.date_vente DESC, v.article_id ASC, v.quantite ASC", nativeQuery = true)
	public Page<Ventes> findVentesByDate(@Param("idUser")int idUser, @Param("debut")Date debut, @Param("fin")Date fin,org.springframework.data.domain.Pageable pageable);
	
	@Query(value="SELECT COUNT(*) FROM ventes v INNER JOIN produit_ventes p ON p.id=v.article_id where p.id= :idArticle", nativeQuery = true)
	public Integer findAllVentesByArticle(@Param("idArticle")int idArticle);
	 
	@Modifying
	@Transactional
	@Query(value="DELETE FROM ventes WHERE ventes.id= :id", nativeQuery = true)
	public void deleteVente(@Param("id")int id);
	

}


