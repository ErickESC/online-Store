package mx.uam.tsbd.onlineStore.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsbd.onlineStore.datos.CarritoRepository;
import mx.uam.tsbd.onlineStore.datos.LibroRepository;
import mx.uam.tsbd.onlineStore.negocio.model.Carrito;
import mx.uam.tsbd.onlineStore.negocio.model.Libro;

@Service
@Slf4j
public class CarritoService {
	
	@Autowired
	private CarritoRepository carritoRepository;
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private LibroRepository libroRepository;
	
	/**
	 * 
	 * @param nuevoCarrito
	 * @return El carrito recien creado, null de lo contrario
	 */
	public Carrito create(Carrito nuevoCarrito) {
		log.info("Creando carrito con id "+nuevoCarrito.getIdCarrito());
		return carritoRepository.save(nuevoCarrito);

	}
	
	/**
	 * 
	 * @return Lista de los Carritos
	 */
	public Iterable <Carrito> retriveAll(){
		log.info("Regresando arreglo con Carritos");
		return carritoRepository.findAll();
	}
	
	/**
	 * 
	 * @param Id
	 * @return Carrito al que le pertenece el id
	 */
	public Carrito retrive(Integer Id){
		log.info("Llamado a regresar al Carrito con id "+Id);
		
		Optional <Carrito> carritoOpt = carritoRepository.findById(Id);
		
		if(carritoOpt.isPresent()) {
			return carritoOpt.get();
		}else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param carritoActualizado
	 * @return Carrito actualizado, null en caso de no haberse encontrado
	 */
	public Carrito update(Carrito carritoActualizado){ 
		
		log.info("Actualizando al Carrito con id "+carritoActualizado.getIdCarrito());
		
		Optional<Carrito> carritoOpt = carritoRepository.findById(carritoActualizado.getIdCarrito());
		
		if(carritoOpt.isPresent()) {
			
			return carritoRepository.save(carritoActualizado);
			
		}else {
			log.info("El carrito no existe");
			return null;
		}
	}
	
	/**
	 * 
	 * @param Id
	 * @return verdadero si se borro bien o falso en caso contrario
	 */
	public boolean delete(Integer Id){
		
		log.info("Borrando carrito con matricula "+Id);
		try {
			carritoRepository.deleteById(Id);
			return true;
		}catch(Exception e){
			log.info("Algo salio mal");
			e.getMessage();
			return false;
		}	
	}
	
	/**
	 * 
	 * @param Id
	 * @return true si existe, false en caso contrario
	 */
	public boolean exist(Integer Id){
		log.info("Revisando existencia del Carrito con matricula "+Id);
		return carritoRepository.existsById(Id);
	}
	
	/**
	 * 
	 * @param carritoId
	 * @param libroId
	 * @return
	 */
	public boolean addLibroToCarrito(Integer carritoId, Integer libroId) {
		
		Libro libro = libroService.Retrive(libroId);
		
		Optional <Carrito> carritoOpt = carritoRepository.findById(carritoId);
		
		if(!carritoOpt.isPresent() || libro == null) {
			
			return false;
		}

		Carrito carrito = carritoOpt.get();
		carrito.addLibro(libro);
		
		libro.addCarrito(carrito);
		
		// Persistir el cambio
		libroRepository.save(libro);
		carritoRepository.save(carrito);
		
		return true;
	}
	
	/**
	 * 
	 * @param carritoId
	 * @param libroId
	 * @return
	 */
	public boolean quitLibroFromCarrito(Integer carritoId, Integer libroId) {
		
		Libro libro = libroService.Retrive(libroId);
		
		Optional <Carrito> carritoOpt = carritoRepository.findById(carritoId);
		
		if(!carritoOpt.isPresent() || libro == null) {
			
			return false;
		}

		Carrito carrito = carritoOpt.get();
		carrito.quitLibro(libro);
		
		libro.quitCarrito(carrito);
		
		// Persistir el cambio
		libroRepository.save(libro);
		carritoRepository.save(carrito);
		
		return true;
	}
}
