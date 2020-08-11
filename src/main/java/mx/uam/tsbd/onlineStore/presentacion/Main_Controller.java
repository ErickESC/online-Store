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

}
