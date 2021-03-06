package mx.uam.tsbd.onlineStore.servicio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.swagger.annotations.ApiOperation;
import mx.uam.tsbd.onlineStore.negocio.LibroService;
import mx.uam.tsbd.onlineStore.negocio.model.Libro;

@RestController
public class LibroController {
	
	@Autowired
	  private LibroService libroService;

	@ApiOperation(
			value = "Crear nuevo Libro",
			notes = "Permite crear un nuevo Libro"
			)
		@PostMapping(path="/libros", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity <?> Create(@RequestBody @Valid Libro nuevoLibro)
				
		{		
				Libro libro= libroService.Create(nuevoLibro);
				
				if(libro!=null)
				{
					return ResponseEntity.status(HttpStatus.CREATED).body(libro);
				}else 
				{
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se puede dar de alta el libro");
				}
				
		
	}	
		
	@ApiOperation(
			value = "Cargar un Archivo PDF",
			notes = "Permite Cargar un Archivo PDF"
			)
		//cargar el archivo pdf 
		@PostMapping("/libros")
		public String uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes)
				throws IOException {

			if (file == null || file.isEmpty()) {
				attributes.addFlashAttribute("message", "Por favor seleccione un archivo");
				return "redirect:status";
			}

			StringBuilder builder = new StringBuilder();
			builder.append(System.getProperty("user.home"));
			builder.append(File.separator);
			builder.append("spring_upload_example");
			builder.append(File.separator);
			builder.append(file.getOriginalFilename());

			byte[] fileBytes = file.getBytes();
			Path path = Paths.get(builder.toString());
			Files.write(path, fileBytes);
			
			attributes.addFlashAttribute("message", "Archivo cargado correctamente ["+builder.toString()+"]");

			return "redirect:/status";
		}
		
		
	@ApiOperation(
			value = "Regresa Todos los libros",
			notes = "Permite regresar todos los Libros"
			)
	    @GetMapping(path = "/libros", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity <?> retrieveAll() {
		
			libroService.rellena();
		
			Iterable <Libro> libros = libroService.RetrieveAll();
			
			return ResponseEntity.status(HttpStatus.OK).body(libros); 
		}
		
	@ApiOperation(
			value = "Regresa Libro",
			notes = "Permite Regresar el Libro solicitado"
			)
		@GetMapping(path = "/libros/{IdLibro}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity <?> retrieve(@PathVariable("IdLibro") Integer IdLibro) {
			
			
			Libro libro = libroService.Retrive(IdLibro);
			
			if(libro != null) {
				return ResponseEntity.status(HttpStatus.OK).body(libro);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro con id: "+IdLibro +" no existe");
			}
			
			
		}
		
	@ApiOperation(
			value = "Actualiza Libro",
			notes = "Permite Actualizar el Libro solicitado"
			)
		@PutMapping(path = "libros/{IdLibro}", produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity <?> update(@PathVariable("IdLibro") Integer IdLibro,@RequestBody Libro actualizaLibro)
		{
	 		Libro libro= libroService.UpdateLibro(IdLibro, actualizaLibro);
	 		if(libro!=null)
	 		{
	 	      return ResponseEntity.status(HttpStatus.OK).body(libro);
	 		}
	 		else
	 		{
	 			return  ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	 		}
		}
		
	@ApiOperation(
			value = "Elimina Libro",
			notes = "Permite Eliminar el Libro solicitado"
			)
		@DeleteMapping(path = "/libros/{IdLibro}")
		public ResponseEntity<?> delete(@PathVariable("IdLibro") @Valid Integer IdLibro) {
			
				
			if(libroService.Delate(IdLibro)) {
				return ResponseEntity.status(HttpStatus.OK).body("Libro con ID "+IdLibro+" eliminado correctamente");
			} 
			else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Libro con ID "+IdLibro+" no encontrado. No se ha podido eliminar");
			}
		}
}
