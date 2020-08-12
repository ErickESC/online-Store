package mx.uam.tsbd.onlineStore.negocio;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsbd.onlineStore.datos.UsuarioRepository;
import mx.uam.tsbd.onlineStore.negocio.model.Carrito;
import mx.uam.tsbd.onlineStore.negocio.model.Libro;
import mx.uam.tsbd.onlineStore.negocio.model.Tarjeta;
import mx.uam.tsbd.onlineStore.negocio.model.Usuario;
import mx.uam.tsbd.onlineStore.negocio.model.Venta;

@Service
@Slf4j
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TarjetaService tarjetaService;
	
	@Autowired
	private VentaService ventaService;
	

	
	/**
	 * 
	 * @param nuevoUsuario
	 * @return El Usuario recien creado, null de lo contrario
	 */
	public Usuario create(Usuario nuevoUsuario) {
		log.info("Creando Usuario con id "+nuevoUsuario.getIdUsuario());
		//Crea su carrito de compras
		Carrito carrito = new Carrito();
		nuevoUsuario.setCarrito(carrito);
		return usuarioRepository.save(nuevoUsuario);

	}
	
	/**
	 * 
	 * @return Lista de los Carritos
	 */
	public Iterable <Usuario> retriveAll(){
		log.info("Regresando arreglo con Usuario");
		return usuarioRepository.findAll();
	}
	
	/**
	 * 
	 * @param Id
	 * @return Usuario al que le pertenece el id
	 */
	public Usuario retrive(Integer Id){
		log.info("Llamado a regresar al Usuario con id "+Id);
		
		Optional <Usuario> usuarioOpt = usuarioRepository.findById(Id);
		
		if(usuarioOpt.isPresent()) {
			return usuarioOpt.get();
		}else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param UsuarioActualizado
	 * @return Usuario actualizado, null en caso de no haberse encontrado
	 */
	public Usuario update(Usuario usuarioActualizado){ 
		
		log.info("Actualizando al Carrito con id "+usuarioActualizado.getIdUsuario());
		
		Optional<Usuario> carritoOpt = usuarioRepository.findById(usuarioActualizado.getIdUsuario());
		
		if(carritoOpt.isPresent()) {
			
			return usuarioRepository.save(usuarioActualizado);
			
		}else {
			log.info("El Usuario no existe");
			return null;
		}
	}
	
	/**
	 * 
	 * @param Id
	 * @return verdadero si se borro bien o falso en caso contrario
	 */
	public boolean delete(Integer Id){
		
		log.info("Borrando Usuario con matricula "+Id);
		try {
			usuarioRepository.deleteById(Id);
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
		log.info("Revisando existencia del Usuario con matricula "+Id);
		return usuarioRepository.existsById(Id);
	}
	
	/**
	 * 
	 * @param UsuarioId
	 * @param tarjetaId
	 * @return
	 */
	public boolean addTarjetaToUsuario(Integer usuarioId, Integer tarjetaId) {
		
		Tarjeta tarjeta = tarjetaService.retrive(tarjetaId);
		
		Optional <Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
		
		if(!usuarioOpt.isPresent() || tarjeta == null) {
			
			return false;
		}

		Usuario usuario = usuarioOpt.get();
		usuario.addTarjeta(tarjeta);
		
		
		
		// Persistir el cambio
		usuarioRepository.save(usuario);
		
		return true;
	}
	
	/**
	 * 
	 * @param usuarioId
	 * @param tarjetaId
	 * @return
	 */
	public boolean quitTarjetaFromUsuario(Integer usuarioId, Integer tarjetaId) {
		
		Tarjeta tarjeta = tarjetaService.retrive(tarjetaId);
		
		Optional <Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
		
		if(!usuarioOpt.isPresent() || tarjeta == null) {
			
			return false;
		}

		Usuario usuario = usuarioOpt.get();
		usuario.quitTarjeta(tarjeta);
		
		
		
		// Persistir el cambio
		usuarioRepository.save(usuario);
		
		return true;
	}
	
	/**
	 * 
	 * @param UsuarioId
	 * @param ventaId
	 * @return
	 */
	public boolean addVentaToUsuario(Integer usuarioId, Integer ventaId) {
		
		Venta venta = ventaService.retrive(ventaId);
		
		Optional <Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
		
		if(!usuarioOpt.isPresent() || venta == null) {
			
			return false;
		}

		Usuario usuario = usuarioOpt.get();
		usuario.addCompra(venta);
		
		
		
		// Persistir el cambio
		usuarioRepository.save(usuario);
		
		return true;
	}
	
	/**
	 * 
	 * @param usuarioId
	 * @param ventaId
	 * @return
	 */
	public boolean quitVentaFromUsuario(Integer usuarioId, Integer ventaId) {
		
		Venta venta = ventaService.retrive(ventaId);
		
		Optional <Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
		
		if(!usuarioOpt.isPresent() || venta == null) {
			
			return false;
		}

		Usuario usuario = usuarioOpt.get();
		usuario.quitCompra(venta);
		
		// Persistir el cambio
		usuarioRepository.save(usuario);
		
		return true;
	}
}
