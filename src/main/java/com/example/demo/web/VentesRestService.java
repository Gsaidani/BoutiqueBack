package com.example.demo.web;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;
import com.example.demo.dao.EntityManagerVentesRep;
import com.example.demo.dao.ProduitsVenteRepository;
import com.example.demo.dao.VentesRepository;
import com.example.demo.entities.ProduitVentes;
import com.example.demo.entities.Ventes;
import com.example.demo.service.VenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class VentesRestService {
	String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	long time = System.currentTimeMillis();
	java.sql.Date time1 = new java.sql.Date(time);
	String currentDateDebut="";
	String currentDateFin="";
	int currentPage=0;
	int currentSize=1000;
	int idActualUser =0;
	@Autowired
	public VenteService venteService;
	
	/*
	@RequestMapping(value="/vente/{id:.+}", method=RequestMethod.GET)
	public Optional<Ventes> getVenteById(@PathVariable Long id){
		return ventesRepository.findById(id);
	}
	
	
	
	@RequestMapping(value="/venteById", method=RequestMethod.GET)
	public Page<Ventes> getVentesByMC(
			@RequestParam(name="id", defaultValue="4") Long id, 
			@RequestParam(name="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(name="pageSize", defaultValue="5") int pageSize
			
			){
		
		return ventesRepository.findVentesById(id,new PageRequest(pageNumber, pageSize));
	}
	
	
	@RequestMapping(value="/venteById", method=RequestMethod.GET)
	public Page<Ventes> findVentesById(
			@RequestParam(name="id", defaultValue="") Long id
			
			){
		
		return (Page<Ventes>) ventesRepository.findVentesById(id);
	}
	*/
	
	/*
	@RequestMapping(value="/venteByMC", method=RequestMethod.GET)
	public List<Ventes> getVentesByMC(
			@RequestParam(name="article", defaultValue="") String article
			
			){
		
		return (List<Ventes>) ventesRepository.findVentesByMC("%"+article+"%", null);
	}
	
	
	 @RequestMapping(value="/venteByMC", method=RequestMethod.GET)
	public Page<Ventes> getVentesByMC(
			@RequestParam(name="article", defaultValue="") String article, 
			@RequestParam(name="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(name="pageSize",defaultValue="3") int pageSize
			
			){
		
		return ventesRepository.findVentesByMC("%"+article+"%",new PageRequest(pageNumber, pageSize));
	}
	 
	 @RequestMapping(value="/venteByDate", method=RequestMethod.GET)
	 
	public Page<Ventes> getVentesByDate(
			@RequestParam(name="debut", defaultValue="") String debut, 
			@RequestParam(name="fin", defaultValue="") String fin,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="5") int size
			
			){
		DateFormat df = new SimpleDateFormat("dd/MM/yyy");
		try {
			Date d1 =df.parse(debut);
			Date d2 =df.parse(fin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(debut=="" || fin=="" ){
			this.getAllVentes();
			System.out.println(this.getAllVentes());
		
		}
		
		return ventesRepository.findVentesByDate(debut, fin, new PageRequest(page, size));
	}
	
	
	
	@RequestMapping(value="/venteByArticle", method=RequestMethod.GET)
	public Page<Object> getVentesByArticle(
			@RequestParam(name="pageNumber", defaultValue="0") int pageNumber,
			@RequestParam(name="pageSize", defaultValue="5") int pageSize			
			){
		
		return ventesRepository.findVentesByArticle(new PageRequest(pageNumber, pageSize));
	}
	@RequestMapping(value="/venteByArticle", method=RequestMethod.GET)
	public List<Ventes> getVentesByArticle(){
		
		return ventesRepository.findVentesByArticle();
	}
	
	
	
	*/
	@RequestMapping(value="/vente", method=RequestMethod.GET)
	public Page<Ventes> getAllVentes(
			@RequestParam(name="idUser", defaultValue="1") int idUser,
			@RequestParam(name="page", defaultValue="0") int page,
			@RequestParam(name="size", defaultValue="100") int size	
			){
		return venteService.getAllVentes(idUser,page, size);
	}
	
	@RequestMapping(value="/vente/byArticle", method=RequestMethod.GET)
	public Integer getAllVentesBYProduct(
			@RequestParam(name="idArticle", defaultValue="0") int idArticle
			
			){
		return venteService.getAllVentesByProduit(idArticle);
	}
	
	
	/*
	
	
	
	
	@RequestMapping(value="/venteByDate", method=RequestMethod.GET)	 
	public List<Ventes> getVentesByDate(
			@RequestParam(name="debut", defaultValue="") String debut, 
			@RequestParam(name="fin", defaultValue="") String fin
			
			){
		Date d1=null;
		Date d2=null;
		if(debut==null){
			debut=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		}
		if(fin=="" || fin== null ){
			fin=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		}
		
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
			try {
				d1 =df.parse(debut);
				d2 =df.parse(fin);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		
		return entityManagerVentesRep.findVentesByDate(d1, d2);
	}
	
	*/
	
	 @RequestMapping(value="/vente/delete/{id}", method=RequestMethod.DELETE)
		public Page<Ventes> deleteVente(@PathVariable int id){
		 venteService.deleteVente(id);
		 return venteService.getVentesByDate(idActualUser,this.currentDateDebut,this.currentDateFin,this.currentPage,this.currentSize);
		}

	@RequestMapping(value="/vente/modify/{id}", method=RequestMethod.PUT)
	public Page<Ventes> modifyVente(@PathVariable int id,@RequestBody Ventes vente ){
		
		venteService.modifyVente(id, vente);
		return venteService.getVentesByDate(vente.getArticle().getUser().getId(),this.currentDateDebut,this.currentDateFin,this.currentPage,this.currentSize);
	}
	
	 @RequestMapping(value="/vente/byDate", method=RequestMethod.GET)
	 public Page<Ventes> getVentesByDate(
			 @RequestParam(name="idUser", defaultValue="0") int idUser, 
				@RequestParam(name="debut", defaultValue="") String debut, 
				@RequestParam(name="fin", defaultValue="") String fin,
				@RequestParam(name="page", defaultValue="0") int page,
				@RequestParam(name="size", defaultValue="100") int size
				
				){
		 this.currentDateDebut=debut;
		 this.currentDateFin=fin;
		 this.currentPage=page;
		 this.currentSize=size;
		 this.idActualUser=idUser;
			
			System.out.println("debut : "+debut);
			return venteService.getVentesByDate(idUser,debut, fin, page, size);
		}
	 
	 @RequestMapping(value="/vente/save", method=RequestMethod.POST)
		public Page<Ventes> addNewVente( 
				@RequestParam(name="dateVente", defaultValue="") String dateVente, 
				@RequestBody Ventes vente){
		 System.out.println("venteUserId : "+vente.getArticle().getUser().getId());
		 
			venteService.saveNewVente(vente,dateVente);
			 return venteService.getVentesByDate(vente.getArticle().getUser().getId(),dateVente,dateVente,this.currentPage,this.currentSize);
			
		}
}
