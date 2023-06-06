package com.example.demo.service;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.ProduitsVenteRepository;
import com.example.demo.entities.ProduitVentes;
import com.example.demo.entities.Ventes;

@Service
@Transactional
public class ProduitService implements IProduitService {
	/*@Autowired
	private ProduitsVenteRepository produitsVenteRepository;*/
	
	@Override
	public Page<ProduitVentes> getAllProduitsVentes(int idUser,int page, int size) {
		// TODO Auto-generated method stub
		Page<ProduitVentes> listProduits=null;
				// produitsVenteRepository.findAllProduits(idUser,PageRequest.of(page, size));
		if(listProduits==null){
			throw new RuntimeException("Produits introuvables !");
		}
		return listProduits;
	}
	


	
	
	public ProduitVentes modifyProduit(Long id, ProduitVentes produit ){
		produit.setId(id);
		return null;
				//produitsVenteRepository.save(produit);
	}
	
	

	 public ProduitVentes saveNewProduit(ProduitVentes produit){
		 
		return null;
				// produitsVenteRepository.save(produit);
	}
	 
	 public void deleteProduit(Long id){
		 
		// produitsVenteRepository.deleteById(id);
	}

	
	@Override
	public Integer getAllProduitsByUser(int idUser) {
		return null;
				// produitsVenteRepository.findAllProduitsByUser(idUser);
		
	
	}
}
