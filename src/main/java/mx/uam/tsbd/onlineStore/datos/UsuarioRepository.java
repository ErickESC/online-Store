package mx.uam.tsbd.onlineStore.datos;

import org.springframework.data.repository.CrudRepository;

import mx.uam.tsbd.onlineStore.negocio.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario,Integer>{

}
