package mx.uam.tsbd.onlineStore.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.tsbd.onlineStore.negocio.model.Carrito;

public interface CarritoRepository extends CrudRepository<Carrito,Integer>{

}
