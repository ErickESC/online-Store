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
public class Venta {
	
	@Id 
	private Integer IdVenta;
	
	@NotNull
	private Integer IdCliente;
	
	@NotNull
	private Integer IdLibros;
	
	@NotBlank
	private String Fecha;
	
	@NotNull
	private Double IVA;
	
	@NotNull
	private Double Subtotal;
	
	@NotNull
	private Double Total;

}
