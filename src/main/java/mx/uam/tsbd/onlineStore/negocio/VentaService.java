package mx.uam.tsbd.onlineStore.negocio;

import org.springframework.stereotype.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import mx.uam.tsbd.onlineStore.datos.VentaRepository;
import mx.uam.tsbd.onlineStore.negocio.model.Carrito;
import mx.uam.tsbd.onlineStore.negocio.model.Venta;

@Service
public class VentaService {
	double total=0;
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private CarritoService carritoService;
	
	public Venta create(Venta venta)
	{
		
		
		return ventaRepository.save(venta);
	}
	
	
	public boolean carritoaddVenta(Integer idC,Integer idV)
	{
		Carrito carrito=carritoService.retrive(idC);
		
		Optional <Venta> ventaop = ventaRepository.findById(idV);
		
		if(!ventaop.isPresent() || carrito==null)
		  {
		
			  return false;
		  }
		Venta venta =ventaop.get();
		venta.addCarrito(carrito);
		  
		  
		  ventaRepository.save(venta);
		
		return true;
		
	}


	public Venta retrive(Integer ventaId) {
       Optional <Venta> ventafind =ventaRepository.findById(ventaId);
		
		if(ventafind.isPresent())
		{
			return ventafind.get();
		}else
			return null;
		
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
