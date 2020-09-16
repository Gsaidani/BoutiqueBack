package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
@Entity
public class ProduitVentes implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String designation;
	private double prixUnitaire;
	@JsonIgnore
	@OneToMany(mappedBy = "article", fetch = FetchType.LAZY)
	private Collection<Ventes> vente;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @ManyToOne(fetch = FetchType.LAZY)	
   @JoinColumn(name = "user_id",nullable=true)
	 private UserConnexion user;
	
	
	
	public ProduitVentes(String designation, double prixUnitaire, UserConnexion user) {
		super();
		this.designation = designation;
		this.prixUnitaire = prixUnitaire;
		this.user = user;
	}
	public ProduitVentes() {
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public double getPrixUnitaire() {
		return prixUnitaire;
	}
	public void setPrixUnitaire(double prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	public Collection<Ventes> getVente() {
		return vente;
	}
	public void setVente(Collection<Ventes> vente) {
		 
		this.vente = vente;
	}

	public UserConnexion getUser() {
		return user;
	}

	public void setUser(UserConnexion user) {
		this.user = user;
	}

	

	
	

	

	

}
