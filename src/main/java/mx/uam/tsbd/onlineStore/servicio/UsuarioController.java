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
import mx.uam.tsbd.onlineStore.negocio.UsuarioService;
import mx.uam.tsbd.onlineStore.negocio.model.Usuario;

/**
 * Controlador para el API rest
 * 
 *
 */
@RestController
@Slf4j
public class UsuarioController {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	/**
	 * 
	 * @param nuevoUsuario
	 * @return status creado y Especialidad creado, status bad request en caso contrario
	 */
	@ApiOperation(
			value = "Crear nuevo Usuario",
			notes = "Permite crear un nuevo Usuario"
			)
	@PostMapping(path = "/usuarios", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Usuario nuevoUsuario) {
		
		log.info("Recibí llamada a create con "+ nuevoUsuario);
		
		Usuario carrito = usuarioService.create(nuevoUsuario);
		
		if(carrito != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(carrito);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se puede crear el Usuario");
		}
	}
	
	/**
	 * 
	 * @return status ok y lista de Usuarios
	 */
	@ApiOperation(
			value = "Regresa todos los Usuarios",
			notes = "Regresa un json con una lista de los Usuarios en la BD"
			)
	@GetMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		
		Iterable <Usuario> result = usuarioService.retriveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
		
	}

	/**
	 * 
	 * @param id
	 * @return status ok y Usuario solicitado, not found en caso contrario
	 */
	@ApiOperation(
			value = "Regresa el Usuario",
			notes = "Regresa un json con los datos del Usuario solicitado"
			)
	@GetMapping(path = "/usuarios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@PathVariable("id") @Valid Integer id) {
		log.info("Buscando al Usuario con clave "+id);
		
		Usuario usuario = usuarioService.retrive(id);
		
		if(usuario != null) {
			return ResponseEntity.status(HttpStatus.OK).body(usuario);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro Usuario");
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return status ok y Usuario actualizado, status no content en caso contrario, status conflict en caso de error
	 */
	@ApiOperation(
			value = "Actualiza Usuario",
			notes = "Permite actualizar los datos del Usuario existente en la DB"
			)
	@PutMapping(path = "/usuarios/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> update(@RequestBody @Valid Usuario usuarioActualizado) {
		
		log.info("Recibí llamada a update con "+ usuarioActualizado);
		
		Usuario usuario;
		
		//Revisa que exista en el repositorio de grupos
		if(usuarioService.exist(usuarioActualizado.getIdUsuario())) {
			try {
				
				usuario = usuarioService.update(usuarioActualizado);
				
				return ResponseEntity.status(HttpStatus.OK).body(usuario);
				
			}catch(Exception e){
				return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al actualizar Usuario");
			}
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro el Usuario con id: "+ usuarioActualizado.getIdUsuario());
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return status no content, status conflic en caso de que algo haya salido mal, not found en caso de no encontrar el Carrito
	 */
	@ApiOperation(
			value = "Borra Usuario",
			notes = "Permite borrar un Usuario de la BD"
			)
	@DeleteMapping(path = "/usuarios/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> delete(@PathVariable("id") @Valid Integer id) {
		
		log.info("Recibí llamada a delete con "+ id);
		
		//Revisa que exista en el repositorio de alumnos
		if(usuarioService.delete(id)) {
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
	 * POST /usuarios/add/id del Usuario/ventas/id de la venta
	 * 
	 * @param nuevoUsuario
	 * @return OK si se agrego con exito o No content en caso contrario
	 */
	@ApiOperation(
			value = "Agregar una venta a un usuario",
			notes = "Permite Agregar una venta a un usuario"
			)
	@PostMapping(path = "/usuarios/add/{usuarioId}/ventas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> addVentaToUsuario(@PathVariable("usuarioId") Integer idUsuario, @PathVariable("id") Integer ventaID) {
		
		log.info("Recibí llamada a addVenta con Usuario"+ idUsuario +" y tarjeta: "+ventaID);
		
		boolean result = usuarioService.addVentaToUsuario(idUsuario, ventaID);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	/**
	 * POST /usuarios/quit/id del Usuario/ventas/id de la venta
	 * 
	 * @param nuevoUsuario
	 * @return OK si se elimino con exito o No content en caso contrario
	 */
	@ApiOperation(
			value = "Eliminar una venta a un usuario",
			notes = "Permite eliminar una venta de un usuario"
			)
	@PostMapping(path = "/usuarios/quit/{usuarioId}/tarjetas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> quitVentaFromUsuario(@PathVariable("usuarioId") Integer idUsuario, @PathVariable("id") Integer ventaID) {
		
		log.info("Recibí llamada a quitVenta con Usuario"+ idUsuario +" y tarjeta: "+ventaID);
		
		boolean result = usuarioService.quitVentaFromUsuario(idUsuario, ventaID);
		
		if(result) {
			return ResponseEntity.status(HttpStatus.OK).build();
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	/**
	 * 
	 * @param id
	 * @return status ok y Usuario solicitado, not found en caso contrario
	 */
	@ApiOperation(
			value = "Regresa OK si usuario y contranseña coinciden",
			notes = "Regresa OK si xoincide, Not found en caso contrario"
			)
	@GetMapping(path = "/usuarios/{id}/{psswrd}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveSingIn(@PathVariable("id") @Valid Integer id, @PathVariable("psswrd") @Valid String psswrd) {
		log.info("Buscando al Usuario con clave "+id+" y contraseña: "+psswrd);
		
		Boolean respuesta = usuarioService.retriveSingIn(id, psswrd);
		
		if(respuesta) {
			return ResponseEntity.status(HttpStatus.OK).body("Exito");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro Usuario o no coinciden");
		}
	}
}
