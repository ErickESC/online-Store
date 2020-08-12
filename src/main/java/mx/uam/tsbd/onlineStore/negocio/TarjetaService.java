package mx.uam.tsbd.onlineStore.negocio;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import mx.uam.tsbd.onlineStore.datos.TarjetaRepository;
import mx.uam.tsbd.onlineStore.negocio.model.Tarjeta;

@Service
@Slf4j
public class TarjetaService {
	
	@Autowired
	private TarjetaRepository tarjetaRepository;
	
	/**
	 * 
	 * @param nuevoTarjeta
	 * @return El Usuario recien creado, null de lo contrario
	 */
	public Tarjeta create(Tarjeta nuevoTarjeta) {
		return tarjetaRepository.save(nuevoTarjeta);
	}
	
	/**
	 * 
	 * @return Lista de las tarjetas
	 */
	public Iterable <Tarjeta> retriveAll(){
		log.info("Regresando arreglo con Usuario");
		return tarjetaRepository.findAll();
	}
	
	/**
	 * 
	 * @param Id
	 * @return Tarjeta al que le pertenece el id
	 */
	public Tarjeta retrive(Integer Id){
		log.info("Llamado a regresar al Tarjeta con id "+Id);
		
		Optional <Tarjeta> tarjetaOpt = tarjetaRepository.findById(Id);
		
		if(tarjetaOpt.isPresent()) {
			return tarjetaOpt.get();
		}else {
			return null;
		}
	}
	
	/**
	 * 
	 * @param UsuarioActualizado
	 * @return Usuario actualizado, null en caso de no haberse encontrado
	 */
	public Tarjeta update(Tarjeta tarjetaActualizado){ 
		
		log.info("Actualizando al Carrito con id "+tarjetaActualizado.getIdTarjeta());
		
		Optional<Tarjeta> carritoOpt = tarjetaRepository.findById(tarjetaActualizado.getIdTarjeta());
		
		if(carritoOpt.isPresent()) {
			
			return tarjetaRepository.save(tarjetaActualizado);
			
		}else {
			log.info("La Tarjeta no existe");
			return null;
		}
	}
	
	/**
	 * 
	 * @param Id
	 * @return verdadero si se borro bien o falso en caso contrario
	 */
	public boolean delete(Integer Id){
		
		log.info("Borrando Tarjeta con id "+Id);
		try {
			tarjetaRepository.deleteById(Id);
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
		log.info("Revisando existencia de la Tarjeta con id "+Id);
		return tarjetaRepository.existsById(Id);
	}

}
