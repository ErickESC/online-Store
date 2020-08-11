package mx.uam.tsbd.onlineStore.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.tsbd.onlineStore.negocio.model.Factura;

public interface FacturaRepository extends CrudRepository<Factura,Integer>{

}
