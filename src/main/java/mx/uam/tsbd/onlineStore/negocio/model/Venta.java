package mx.uam.tsbd.onlineStore.negocio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode
public class Venta {
	
	@Id 
	private Integer IdVenta;
	
	@NotNull
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "Carrito")
	private Usuario Cliente;
	
	@NotBlank
	private String Fecha;
	
	@NotNull
	private Double IVA;
	
	@NotNull
	private Double Subtotal;
	
	@NotNull
	private Double Total;
	
	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "libros")
	private List <Libro> libros = new ArrayList <> ();
	
	public boolean addLibro(Libro libro) {
		return libros.add(libro);
	}
	
	public boolean quitLibro(Libro libro) {
		return libros.remove(libro);
	}
}
