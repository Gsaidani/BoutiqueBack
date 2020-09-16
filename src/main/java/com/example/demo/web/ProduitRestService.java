package com.example.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.ProduitVentes;
import com.example.demo.service.ProduitService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProduitRestService {
	@Autowired
	public ProduitService produitService;
	int currentPage=0;
	int currentSize=1000;
	
	@RequestMapping(value="/produit", method=RequestMethod.GET)
	public Page<ProduitVentes> getAllProducts(
			@RequestParam(name="idUser", defaultValue="1") int idUser,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="100") int size	
			){
		return produitService.getAllProduitsVentes(idUser,page, size);
	}
	
	
	
	
	
	@RequestMapping(value="/produit/delete/{id}", method=RequestMethod.DELETE)
	public Page<ProduitVentes> deleteProduct(
			@PathVariable Long id,
			@RequestParam(name="idUser", defaultValue="0") int idUser){
		produitService.deleteProduit(id);
	 return produitService.getAllProduitsVentes(idUser,this.currentPage,this.currentSize);
	}
	

@RequestMapping(value="/produit/modify/{id}", method=RequestMethod.PUT)
public Page<ProduitVentes> modifyProduct(@PathVariable Long id,@RequestBody ProduitVentes produit ){	
	produitService.modifyProduit(id, produit);
	return  produitService.getAllProduitsVentes(produit.getUser().getId(),this.currentPage,this.currentSize);
}

 
 
 @RequestMapping(value="/produit/save", method=RequestMethod.POST)
	public Page<ProduitVentes> addNewProduct(@RequestBody ProduitVentes produit){	 
	 produitService.saveNewProduit(produit);
		 return  produitService.getAllProduitsVentes(produit.getUser().getId(),this.currentPage,this.currentSize);
		
	}
 
 @RequestMapping(value="/produit/byUser", method=RequestMethod.GET)
	public Integer getAllProductByUser(
			@RequestParam(name="idUser", defaultValue="0") int idUser
			
			){
		return produitService.getAllProduitsByUser(idUser);
	}

}
