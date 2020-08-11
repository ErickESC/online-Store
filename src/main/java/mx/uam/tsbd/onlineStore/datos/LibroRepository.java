package mx.uam.tsbd.onlineStore.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.tsbd.onlineStore.negocio.model.Libro;

public interface LibroRepository extends CrudRepository<Libro,Integer>{

}
