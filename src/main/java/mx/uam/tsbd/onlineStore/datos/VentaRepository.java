package mx.uam.tsbd.onlineStore.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.tsbd.onlineStore.negocio.model.Venta;


public interface VentaRepository extends CrudRepository<Venta,Integer> {

}
