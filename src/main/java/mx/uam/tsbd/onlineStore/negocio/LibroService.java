package mx.uam.tsbd.onlineStore.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.uam.tsbd.onlineStore.datos.LibroRepository;
import mx.uam.tsbd.onlineStore.negocio.model.Libro;
import mx.uam.tsbd.onlineStore.negocio.model.Venta;


@Service
//@Slf4j
public class LibroService {
	
	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private VentaService ventaService;
	
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
	
	public boolean addVentaToLibro(Integer libroId, Integer ventaId) {
		
		Venta venta = ventaService.retrive(ventaId);
		
		Optional <Libro> usuarioOpt = libroRepository.findById(libroId);
		
		if(!usuarioOpt.isPresent() || venta == null) {
			
			return false;
		}

		Libro libro = usuarioOpt.get();
		libro.addVenta(venta);

		// Persistir el cambio
		libroRepository.save(libro);
		
		return true;
	}
	
	/**
	 * RELLENAR BASE DE DATOS
	 * 
	 */
	
	public List<Libro> rellena(){
		
		List <Libro> libros = new ArrayList<>();
		
		Libro libro1 = new Libro();
		libro1.setAutor("George RR Martin");
		libro1.setCantidad(23);
		libro1.setDescripcion("El 15 de septiembre de 1946 un virus extraterrestre cayó sobre Manhattan. Los sobrevivientes quedaron convertidos en metahumanos: jokers o ases.");
		libro1.setEdicion(1);
		libro1.setEditorial("Oceano");
		libro1.setIdLibro(220);
		libro1.setImagen("https://cdn.gandhi.com.mx/media/catalog/product/cache/1/image/370x/9df78eab33525d08d6e5fb8d27136e95/i/m/image_12414.jpg");
		libro1.setNoPaginas(604);
		libro1.setPrecio(176.0);
		libro1.setTitulo("Wild Cards I");
		
		libros.add(libro1);
		
		Libro libro2 = new Libro();
		libro2.setAutor("JRR Tolkien");
		libro2.setCantidad(30);
		libro2.setDescripcion("La historia del mundo antiguo");
		libro2.setEdicion(1);
		libro2.setEditorial("Booket");
		libro2.setIdLibro(330);
		libro2.setImagen("https://cdn.gandhi.com.mx/media/catalog/product/cache/1/image/370x/9df78eab33525d08d6e5fb8d27136e95/i/m/image_1165_1_60716.jpg");
		libro2.setNoPaginas(430);
		libro2.setPrecio(210.0);
		libro2.setTitulo("Simarilion");
		
		libros.add(libro2);	
		
		Libro libro3 = new Libro();
		libro3.setAutor("Haruki Murakami");
		libro3.setCantidad(30);
		libro3.setDescripcion("En marzo de 1983, el joven protagonista de esta novela siente la necesidad de viajar a Sapporo para volver a alojarse en el Hotel Delfín, donde años atrás pasó una semana con una misteriosa mujer.");
		libro3.setEdicion(1);
		libro3.setEditorial("Tusquets Editores");
		libro3.setIdLibro(110);
		libro3.setImagen("https://cdn.gandhi.com.mx/media/catalog/product/cache/1/image/370x/9df78eab33525d08d6e5fb8d27136e95/i/m/image_1165_1_61080.jpg");
		libro3.setNoPaginas(464);
		libro3.setPrecio(150.0);
		libro3.setTitulo("Baila, Baila, Baila");
		
		libros.add(libro3);	
		
		for(int i=0; i<libros.size(); i++) {
			libroRepository.save(libros.get(i));
		}
		
		return libros;
	}
}
