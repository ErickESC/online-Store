package mx.uam.tsbd.onlineStore.negocio;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import mx.uam.tsbd.onlineStore.datos.VentaRepository;
import mx.uam.tsbd.onlineStore.negocio.model.Usuario;
import mx.uam.tsbd.onlineStore.negocio.model.Venta;

@Service
public class VentaService {
	double total=0;
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	
	
	public Venta create(Venta nuevaVenta, Integer idUsuario)
	{
		
		Date fecha = new Date();
		
		nuevaVenta.setFecha(fecha);
		
		Usuario usuario = usuarioService.retrive(idUsuario);
		
		nuevaVenta.setCliente(usuario);
		
		Venta venta = ventaRepository.save(nuevaVenta);
		
		usuarioService.addVentaToUsuario(venta.getCliente().getIdUsuario(), venta.getIdVenta());
		
		for(int i=0; i<venta.getLibros().size(); i++) {
			Integer idLibro = venta.getLibros().get(i).getIdLibro();
			Integer Cantidad = venta.getLibros().get(i).getCantidad();
			venta.getLibros().get(i).setCantidad(Cantidad--);
			
			libroService.addVentaToLibro(idLibro, venta.getIdVenta());
			libroService.UpdateLibro(idLibro, venta.getLibros().get(i));
		}
		
		return venta;
	}
	
	/*
	 * EN CASO DE TENER SESIONES
	public boolean carritoaddVenta(Integer idC,Integer idV)
	{
		Carrito carrito=carritoService.retrive(idC);
		
		Optional <Venta> ventaop = ventaRepository.findById(idV);
		
		if(!ventaop.isPresent() || carrito==null)
		  {
		
			  return false;
		  }
		Venta venta =ventaop.get();
		//venta.addCarrito(carrito);
		  
		  
		  ventaRepository.save(venta);
		
		return true;
		
	}
	*/
	
	public Iterable <Venta> retriveAll(){
		return ventaRepository.findAll();
	}
	
	public Venta retrive(Integer ventaId) {
       Optional <Venta> ventafind =ventaRepository.findById(ventaId);
		
		if(ventafind.isPresent())
		{
			return ventafind.get();
		}else
			return null;
		
	}
	
	public Iterable <Venta> retrivePart(String fechaInferior, String FechaSuperior) throws ParseException{
		
		SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd");
		
		Date aux = objSDF.parse("20-08-1981");
		
		//Para imprimir: objSDF.format(dt_1)
		
		Iterable <Venta> ventas = ventaRepository.findAll();
		
		return ventaRepository.findAll();
	}
	
	public double totalcompraadd(double precio)
	{
		
		
		total=total+precio;
		
	
		return total;
	}
	
	
	public double totalcompraresta(double precio)
	{
		total=total-precio;
		
		return total;
	}

}
