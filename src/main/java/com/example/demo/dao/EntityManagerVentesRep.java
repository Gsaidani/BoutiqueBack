package com.example.demo.dao;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entities.ProduitVentes;
import com.example.demo.entities.Ventes;

@org.springframework.stereotype.Repository
@Transactional
public class EntityManagerVentesRep implements Repository<Ventes,Long> {
	@PersistenceContext
	EntityManager em;
	
	String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	
	/*	
	public List<Ventes> findVentesByDate(Date debut,Date fin){
		List<Ventes> ventes = em
			    .createNamedQuery("Ventes.byDate",Ventes.class)
			    .setParameter("debut",debut,TemporalType.DATE)
			    .setParameter("fin",fin,TemporalType.DATE)
			    .getResultList();
		return ventes;
	}

	*/
	public List<Ventes> findVentesByDate(Date debut,Date fin){
		List<Ventes> ventes = em
			    .createQuery("select v from Ventes v where v.date_vente <= :fin and v.date_vente >= :debut")
			    .setParameter("debut",debut,TemporalType.DATE)
			    .setParameter("fin",fin,TemporalType.DATE)
			    .getResultList();
		return ventes;
	}
	
	public void saveVentes(Ventes v){
		em.persist(v);
			    
	}
	
	public ProduitVentes saveProduitsVentes(ProduitVentes v){
		em.persist(v);
		return v;
			    
	}
	
	
}
 