package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
/*@NamedQueries({
@NamedQuery(
        name = "Ventes.byDate",
        query = "select v " +
                "from Ventes v " +
                "where v.date_vente BETWEEN :debut AND :fin"
    )})*/

@Entity (name = "ventes")
public class Ventes implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	 @ManyToOne(fetch = FetchType.LAZY)	
     @JoinColumn(name = "article_id",nullable=true)
	 private ProduitVentes article;
	
	@Column (name ="quantite")
	private int quantite;
	
	@Temporal(TemporalType.DATE)
	@Column (name ="date_vente")
	private Date dateVente;	
	
	public Ventes() {
		//super();
	}

	

	public Ventes(ProduitVentes article, UserConnexion user, int quantite, Date dateVente) {
		super();
		this.article = article;
		this.quantite = quantite;
		this.dateVente = dateVente;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProduitVentes getArticle() {
		return article;
	}

	public void setArticle(ProduitVentes article) {
		this.article = article;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public Date getDateVente() {
		return dateVente;
	}

	public void setDateVente(Date dateVente) {
		this.dateVente = dateVente;
	}


}

