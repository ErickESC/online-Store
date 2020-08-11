package mx.uam.tsbd.onlineStore.servicio;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class Manejador_Excepciones {
	
	@ExceptionHandler(MultipartException.class)
	public String handleMultipart(MultipartException e, RedirectAttributes attributes) {
		attributes.addFlashAttribute("message", e.getCause().getMessage());
		
		return "redirect:/status";
	}

}
