package com.example.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.expression.ParseException;

import com.example.demo.dao.EntityManagerVentesRep;
import com.example.demo.dao.ProduitsVenteRepository;
import com.example.demo.dao.VentesRepository;
import com.example.demo.entities.ProduitVentes;
import com.example.demo.entities.Ventes;

@Service("venteService")
@Transactional
public class VenteService implements IVenteService {

	
	private VentesRepository ventesRepository;
	
	
	@Autowired(required = true)
	@Qualifier("venteService")
	public void setVentesRepository(VentesRepository ventesRepository) {
		this.ventesRepository = ventesRepository;
	}

	@Override
	public Page<Ventes> getAllVentes(int idUser,int page, int size) {
		Page<Ventes> listVentes=ventesRepository.findAllVentes(idUser,PageRequest.of(page, size) );
		if(listVentes==null){
			throw new RuntimeException("ventes introuvables !");
		}
		return listVentes;
	}
	
	@Override
	public Integer getAllVentesByProduit(int idArticle) {
		return ventesRepository.findAllVentesByArticle(idArticle);
		
	
	}



	
	
	public Ventes modifyVente(int id, Ventes vente ){
		vente.setId(id);
		return ventesRepository.save(vente);
	}
	
	 public Page<Ventes> getVentesByDate(
			 int idUser,
				 String debut, 
				 String fin,
				 int page,
				 int size				
				){
		 
		 Date date1=Date.valueOf(debut);
		 Date date2=Date.valueOf(fin);
		 System.out.println("date1 : "+date1);
			return ventesRepository.findVentesByDate(idUser,date1, date2, PageRequest.of(page, size));
		}

	 public Ventes saveNewVente(Ventes vente, String dateVente){
		 
		 Date date=Date.valueOf(dateVente);
		   
		    System.out.println("test : "+new SimpleDateFormat("yyyy-MM-dd").format(date));
		    System.out.println("test2 : "+Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(date)));
		 
		 //vente.setDateVente(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(date)));
		 vente.setDateVente(date);
		 System.out.println("vente : "+vente);
		 System.out.println("dateVente : "+date);
		return ventesRepository.save(vente);
	}
	 
	 public void deleteVente(int id){
		 
		ventesRepository.deleteVente(id);
	}



	

	
}
