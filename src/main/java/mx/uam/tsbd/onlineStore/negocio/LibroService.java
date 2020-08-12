package mx.uam.tsbd.onlineStore.negocio;

import java.util.Optional;



//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.tsbd.onlineStore.datos.LibroRepository;
import mx.uam.tsbd.onlineStore.negocio.model.Libro;


@Service
//@Slf4j
public class LibroService {
	
	@Autowired
	private LibroRepository libroRepository;
	
	//Dar alta un libro
	public Libro Create (Libro nuevoLibro)
	{
		nuevoLibro= libroRepository.save(nuevoLibro);
		return nuevoLibro;	
	}
	
	//Recuperar todos los libros
	public Iterable <Libro> RetrieveAll()
	{
		return libroRepository.findAll();
	}
	
    //Recupera un libro por id
	public Libro Retrive(Integer idLibro)
	{
		Optional <Libro> librofind = libroRepository.findById(idLibro);
		
		if(librofind.isPresent())
		{
			return librofind.get();
		}else
			return null;
	}
	
	//Actualizacion de libro
	public Libro UpdateLibro(Integer idLibro, Libro actualizaLibro)
	{
		Optional <Libro> librofind= libroRepository.findById(idLibro);
		if(librofind.isPresent())
		{
			Libro libro =new Libro();
			
			libro.setAutor(actualizaLibro.getAutor());
			libro.setTitulo(actualizaLibro.getTitulo());
			libro.setEditorial(actualizaLibro.getEditorial());
			libro.setEdicion(actualizaLibro.getEdicion());
			libro.setNoPaginas(actualizaLibro.getNoPaginas());
			libro.setPrecio(actualizaLibro.getPrecio());
			libro.setDescripcion(actualizaLibro.getDescripcion());
			
			Libro libroActualizado=libroRepository.save(actualizaLibro);
			
			return libroActualizado;
		}else
			return null;
	}
	
	//Borrar Libro
	public boolean Delate(Integer idLibro)
	{
		Optional <Libro> libro = libroRepository.findById(idLibro);
		
		if(libro.isPresent())
		{
			libroRepository.deleteById(idLibro);
			return true;
		}else 
			return false;
	}
	
	public Double precio(Integer idlibro)
	{
		Libro libro =new Libro();
		
		Double preciolibro=libro.getPrecio();
		
		return preciolibro;
	}

}
