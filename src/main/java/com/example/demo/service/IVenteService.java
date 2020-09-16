package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.entities.ProduitVentes;
import com.example.demo.entities.Ventes;

public interface IVenteService  {
	
	public Page<Ventes> getAllVentes(int idUser,int page, int size);
	public Integer getAllVentesByProduit(int idArticle);
	
	

}
