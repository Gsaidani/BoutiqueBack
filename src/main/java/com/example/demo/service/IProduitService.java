package com.example.demo.service;

import org.springframework.data.domain.Page;

import com.example.demo.entities.ProduitVentes;

public interface IProduitService {
	public Page<ProduitVentes> getAllProduitsVentes(int idUser,int page, int size);
	public Integer getAllProduitsByUser(int idUser);

}
