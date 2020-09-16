package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity(name = "user")
public class UserConnexion implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column (name ="nom")
	private String nom;
	
	@Column (name ="prenom")
	private String prenom;
	
	@Column (name ="identifiant", unique=true)
	private String identifiant;
	
	@Column (name ="password")
	private String password;
	
	@Temporal(TemporalType.DATE)
	@Column (name ="date_inscription")
	private Date dateInscription;
	
	@Column (name ="user_type")
	private String typeUser;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
	private Collection<ProduitVentes> produits;
	
	
	public UserConnexion() {
		
	}	

	

	public UserConnexion(String nom, String prenom, String identifiant, String password, Date dateInscription,
			String typeUser) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.identifiant = identifiant;
		this.password = password;
		this.dateInscription = dateInscription;
		this.typeUser = typeUser;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
@JsonIgnore
	public String getPassword() {
		return password;
	}
@JsonSetter
	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateInscription() {
		return dateInscription;
	}

	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}

	public String getTypeUser() {
		return typeUser;
	}

	public void setTypeUser(String typeUser) {
		this.typeUser = typeUser;
	}

	public Collection<ProduitVentes> getProduits() {
		return produits;
	}

	public void setProduits(Collection<ProduitVentes> produits) {
		this.produits = produits;
	}

	

}
