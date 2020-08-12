package mx.uam.tsbd.onlineStore.presentacion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class Main_Controller {
	
	@GetMapping("/")
	public String index() {
		
		log.info("se invoco el metodo index()");
		
		return "index";
	}
	
	@GetMapping("/adopcion")
	public String adopcion() {
		
		log.info("se invoco el metodo adopcion()");
		
		return "adopcion";
	}
	
	@GetMapping("/reclutamiento")
	public String reclutamiento() {
		
		log.info("se invoco el metodo reclutamiento()");
		
		return "reclutamiento";
	}
	
	@GetMapping("/registro")
	public String registro() {
		
		log.info("se invoco el metodo registro()");
		
		return "registro";
	}
	
	@GetMapping("/administrador")
	public String administrador() {
		
		log.info("se invoco el metodo administrador()");
		
		return "administrador";
	}
	
	@GetMapping("/enfermera")
	public String enfermera() {
		
		log.info("se invoco el metodo enfermera()");
		
		return "enfermera";
	}

}
