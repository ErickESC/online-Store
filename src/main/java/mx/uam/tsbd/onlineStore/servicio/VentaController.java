package mx.uam.tsbd.onlineStore.servicio;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import mx.uam.tsbd.onlineStore.negocio.VentaService;
import mx.uam.tsbd.onlineStore.negocio.model.Venta;

/**
 * Controlador para el API rest
 * 
 *
 */
@RestController
@Slf4j
public class VentaController {
	
	@Autowired
	private VentaService ventaService;
	
	/**
	 * 
	 * @param nuevaVenta
	 * @return status creado y venta creado, status bad request en caso contrario
	 */
	@ApiOperation(
			value = "Crear nueva venta",
			notes = "Permite crear una nueva Venta"
			)
	@PostMapping(path = "/ventas/{idUsuario}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> create(@RequestBody @Valid Venta nuevaVenta, @PathVariable("idUsuario") @Valid Integer idUsuario) {
		
		log.info("Recibí llamada a create con "+ nuevaVenta);
		
		Venta venta = ventaService.create(nuevaVenta, idUsuario);
		
		if(venta != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(venta);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo realizar la venta");
		}
	}
	
	/**
	 * 
	 * @return status ok y lista de Ventas
	 */
	@ApiOperation(
			value = "Regresa todas las Ventas",
			notes = "Regresa un json con una lista de las Ventas en la BD"
			)
	@GetMapping(path = "/ventas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieveAll() {
		
		Iterable <Venta> result = ventaService.retriveAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
		
	}
	
	/**
	 * @param limite inferior y limite superior
	 * @return status ok y lista de Ventas en un lapso de tiempo
	 * @throws ParseException 
	 */
	@ApiOperation(
			value = "Regresa todas las Ventas realizadas en un lapso de tiempo",
			notes = "Regresa un json con una lista de las Ventas en la BD"
			)
	@GetMapping(path = "/search/ventas/{inferior}/{superior}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrievePart(@PathVariable("inferior") @Valid String inferior, @PathVariable("superior") @Valid String superior) throws ParseException {
		
		Iterable <Venta> result = ventaService.retrivePart(inferior, superior);
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
		
	}

	/**
	 * 
	 * @param id
	 * @return status ok y Usuario solicitado, not found en caso contrario
	 */
	@ApiOperation(
			value = "Regresa Venta",
			notes = "Regresa un json con los datos de la Venta solicitada"
			)
	@GetMapping(path = "/ventas/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <?> retrieve(@PathVariable("id") @Valid Integer id) {
		log.info("Buscando al Usuario con clave "+id);
		
		Venta venta = ventaService.retrive(id);
		
		if(venta != null) {
			return ResponseEntity.status(HttpStatus.OK).body(venta);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro Usuario");
		}
	}
}
