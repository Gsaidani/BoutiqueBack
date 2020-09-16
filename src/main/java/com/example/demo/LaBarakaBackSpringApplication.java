package com.example.demo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.EntityManagerVentesRep;
import com.example.demo.dao.ProduitsVenteRepository;
import com.example.demo.dao.VentesRepository;
import com.example.demo.entities.ProduitVentes;
import com.example.demo.entities.UserConnexion;
import com.example.demo.entities.Ventes;
import com.example.demo.service.ProduitService;
import com.example.demo.service.UserConnexionService;
import com.example.demo.service.VenteService;

@SpringBootApplication
public class LaBarakaBackSpringApplication implements CommandLineRunner {
	@Autowired
	private VentesRepository ventesRepository;
	@Autowired
	private ProduitsVenteRepository produitsVenteRepository;
	@Autowired
	private EntityManagerVentesRep entityManagerVentesRep;
	@Autowired
	private UserConnexionService userService;
	@Autowired
	private VenteService venteService;
	@Autowired
	private ProduitService produitService;
	long time = System.currentTimeMillis();
	java.sql.Date time1 = new java.sql.Date(time);
	//System.out.println("now : "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString());
			String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
	//Date d1= null;
			//Date d2 = null;
			EntityManager em = null;
			
	public static void main(String[] args) {
		SpringApplication.run(LaBarakaBackSpringApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder getBCPE(){
		return new BCryptPasswordEncoder();
	}

	@Override
	public void run(String... args) throws Exception {	
		/*
		userService.saveNewUser(new UserConnexion(
				"test",
				"test",
				"test",
				"test",
				null,
				"admin"));
		
		
		 * ProduitVentes pv1= produitsVenteRepository.save(new
		 * ProduitVentes("poulet",5)); ventesRepository.save(new
		 * Ventes(pv1,13,time1)); ProduitVentes pv1=
		 * produitsVenteRepository.save(new ProduitVentes("kebab",5));
		 * ProduitVentes pv2=produitsVenteRepository.save(new
		 * ProduitVentes("panini",6)); ProduitVentes
		 * pv3=produitsVenteRepository.save(new ProduitVentes("steak",4.5));
		 * ventesRepository.save(new Ventes(pv1,33,new Date(2020-05-05)));
		 * ventesRepository.save(new Ventes(pv2,12,new Date(2020-01-02)));
		 * ventesRepository.save(new Ventes(pv2,1,time1));
		 * ventesRepository.save(new Ventes(pv3,28,time1));
		 */
		// String
		// debut=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

		// String
		// fin=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		/*
		produitService.getAllProduitsVentes(0, 5).forEach(action -> {
			System.out.println(action);
		});

		Long debut = (long) 2020 - 05 - 12;
		Long fin = (long) 2020 - 06 - 25;
		java.sql.Date d1 = new java.sql.Date(debut);
		java.sql.Date d2 = new java.sql.Date(fin);
		System.out.println("date 1 : " + d1);
		System.out.println("date 2 : " + d2);
		System.out.println("time : " + time1);

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		 * try { //d1 =df.parse("2020-05-12"); //d2 =df.parse("2020-07-12");
		 * 
		 * } catch (java.text.ParseException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */

		// entityManagerVentesRep.findVentesByDate(d1,
		// d2).forEach(item->System.out.println(item));
		}
	

}
