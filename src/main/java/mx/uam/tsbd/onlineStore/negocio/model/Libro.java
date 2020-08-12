package mx.uam.tsbd.onlineStore.negocio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Libro {
	
	@Id
	@GeneratedValue
	private Integer IdLibro;
	
	@NotBlank
	private String imagen;
	
	@NotBlank
	private String Autor;
	
	@NotBlank
	private String Titulo;
	
	private Integer Edicion;
	
	private String Editorial;
	
	@NotNull
	private Integer NoPaginas;
	
	@NotNull
	private Double Precio;
	
	private String Descripcion;
	
	private Integer Cantidad;
	
	/**
	 * Para reporte de interesados en el libro
	 */
	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "Carritos")
	private List <Carrito> carritos = new ArrayList <> ();
	
	public boolean addCarrito(Carrito carrito) {
		return carritos.add(carrito);
	}
	
	public boolean quitCarrito(Carrito carrito) {
		return carritos.remove(carrito);
	} 
	
	/**
	 * Para reporte de ventas totales del libro
	 */
	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "Ventas")
	private List <Venta> ventas = new ArrayList <> ();
	
	public boolean addVenta(Venta venta) {
		return ventas.add(venta);
	}
	
	public boolean quitVenta(Venta venta) {
		return ventas.remove(venta);
	}
}
