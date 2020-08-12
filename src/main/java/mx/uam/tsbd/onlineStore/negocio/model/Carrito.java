package mx.uam.tsbd.onlineStore.negocio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
public class Carrito {

	@Id
	@GeneratedValue
 	private Integer IdCarrito;
 
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
