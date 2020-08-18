package mx.uam.tsbd.onlineStore.servicio;

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
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsbd.onlineStore.negocio.CarritoService;
import mx.uam.tsbd.onlineStore.negocio.model.Carrito;

/**
 * Controlador para el API rest
 * 
 *
 */
@RestController
@Slf4j
public class CarritoController {
	
	
	@Autowired
	private CarritoService carritoService;
	
	/**
	 * 
	 * @param nuevoCarrito
	 * @return status creado y Especialidad creado, status bad request en caso contrario
	 */
	@ApiOperation(
			value = "Crear nuevo Carrito",
			notes = "Permite crear un nuevo Carrito"
			)
	@PostMapping(path = "/carritos", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Carrito nuevoCarrito) {
		
		log.info("Recibí llamada a create con "+ nuevoCarrito);
		
		Carrito carrito = carritoService.create(nuevoCarrito);
		
		if(carrito != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(carrito);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede crear el Carrito");
		}
	}
	
	/**
	 * 
	 * @return status ok y lista de Carritos
	 */
	@ApiOperation(
			value = "Regresa todos los Carritos",
			notes = "Regresa un json con una lista de los Carritos en la BD"
			)
	@GetMapping(path = "/carritos", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		
		Iterable <Carrito> result = carritoService.retriveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
		
	}

	/**
	 * 
	 * @param id
	 * @return status ok y Carrito solicitado, not found en caso contrario
	 */
	@ApiOperation(
			value = "Regresa el Carrito",
			notes = "Regresa un json con los datos del Carrito solicitado"
			)
	@GetMapping(path = "/carritos/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@PathVariable("id") @Valid Integer id) {
		log.info("Buscando al Carrito con clave "+id);
		
		Carrito carrito = carritoService.retrive(id);
		
		if(carrito != null) {
			return ResponseEntity.status(HttpStatus.OK).body(carrito);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro Carrito");
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return status ok y Carrito actualizado, status no content en caso contrario, status conflict en caso de error
	 */
	@ApiOperation(
			value = "Actualiza Carrito",
			notes = "Permite actualizar los datos del Carrito existente en la DB"
			)
	@PutMapping(path = "/carritos/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> update(@RequestBody @Valid Carrito carritoActualizado) {
		
		log.info("Recibí llamada a update con "+ carritoActualizado);
		
		Carrito carrito;
		
		//Revisa que exista en el repositorio de grupos
		if(carritoService.exist(carritoActualizado.getIdCarrito())) {
			try {
				
				carrito = carritoService.update(carritoActualizado);
				
				return ResponseEntity.status(HttpStatus.OK).body(carrito);
				
			}catch(Exception e){
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al actualizar Carrito");
			}
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro el Carrito con id: "+ carritoActualizado.getIdCarrito());
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return status no content, status conflic en caso de que algo haya salido mal, not found en caso de no encontrar el Carrito
	 */
	@ApiOperation(
			value = "Borra Carrito",
			notes = "Permite borrar un Carrito de la BD"
			)
	@DeleteMapping(path = "/entrenadores/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> delete(@PathVariable("id") @Valid Integer id) {
		
		log.info("Recibí llamada a delete con "+ id);
		
		//Revisa que exista en el repositorio de alumnos
		if(carritoService.delete(id)) {
			try {
				
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				
			}catch(Exception e){
				e.fillInStackTrace();
				return ResponseEntity.status(HttpStatus.CONFLICT).build();
			}
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	/**
	 * POST /carritos/add/id del Carrito/libros/id del libro
	 * 
	 * @param nuevoCarrito
	 * @return OK si se agrego con exito o No content en caso contrario
	 *
	@ApiOperation(
			value = "Agregar un libro a un Carrito",
			notes = "Permite Agregar un libro a un carrito"
			)
	@PostMapping(path = "/carritos/add/{carritoId}/libros/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> addLibroToCarrito(@PathVariable("carritoId") Integer idCarrito, @PathVariable("id") Integer libroID) {
		
		log.info("Recibí llamada a addlibro con carrito"+ idCarrito +" y libro: "+libroID);
		
		boolean result = carritoService.addLibroToCarrito(idCarrito, libroID);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	*/
	
	/**
	 * POST /carritos/quit/id del Carrito/libros/id del libro
	 * 
	 * @param nuevoCarrito
	 * @return OK si se elimino con exito o No content en caso contrario
	 *
	@ApiOperation(
			value = "Eliminar un libro de un Carrito",
			notes = "Permite eliminar un libro de un carrito"
			)
	@PostMapping(path = "/carritos/quit/{carritoId}/libros/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> quitLibroFromCarrito(@PathVariable("carritoId") Integer idCarrito, @PathVariable("id") Integer libroID) {
		
		log.info("Recibí llamada a quitlibro con carrito"+ idCarrito +" y libro: "+libroID);
		
		boolean result = carritoService.quitLibroFromCarrito(idCarrito, libroID);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	*/
}
