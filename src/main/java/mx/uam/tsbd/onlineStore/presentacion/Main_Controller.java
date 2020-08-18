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
	
	@GetMapping("/catalogo")
	public String catalogo() {
		
		log.info("se invoco el metodo catalogo()");
		
		return "catalogo";
	}
	
	@GetMapping("/registroAdministradores")
	public String registroAdministradores() {
		
		log.info("se invoco el metodo registroAdministradores()");
		
		return "registroAdministradores";
	}
	
	@GetMapping("/registro")
	public String registro() {
		
		log.info("se invoco el metodo registro()");
		
		return "registro";
	}
	
	@GetMapping("/reportes")
	public String reportes() {
		
		log.info("se invoco el metodo reportes()");
		
		return "reportes";
	}
	
	@GetMapping("/administrador")
	public String administrador() {
		
		log.info("se invoco el metodo administrador()");
		
		return "administrador";
	}
	
	
	@GetMapping("/administradorMain")
	public String administradorMain() {
		
		log.info("se invoco el metodo administradorMain()");
		
		return "administradorMain";
	}
}
