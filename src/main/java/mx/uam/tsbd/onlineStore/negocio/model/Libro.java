package mx.uam.tsbd.onlineStore.negocio.model;

import javax.persistence.Entity;
import javax.persistence.Id;
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
	private Integer IdLibro;
	
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
	


}
