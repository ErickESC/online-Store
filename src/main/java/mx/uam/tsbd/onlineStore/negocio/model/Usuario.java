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
public class Usuario {
	
	@Id 
	private Integer IdUsuario;
	
	@NotBlank
	private String TipoUsuario;
	
	@NotBlank
	private String Nombre;
	
	@NotBlank
	private String ApellidoPaterno;
	
	@NotBlank
	private String ApellidoMaterno;
	
	@NotBlank
	private String Correo;
	
	private String Telefono;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "Carrito")
	private Carrito carrito;
	
	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "Info_Tarjeta")
	private List <Informacion_Tarjeta> tarjetas = new ArrayList <> ();
	
	public boolean addTarjeta(Informacion_Tarjeta tarjeta) {
		return tarjetas.add(tarjeta);
	}
	
	public boolean quitTarjeta(Informacion_Tarjeta tarjeta) {
		return tarjetas.remove(tarjeta);
	}

	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	@JoinColumn(name = "Compras")
	private List <Venta> compras = new ArrayList <> ();
	
	public boolean addCompra(Venta compra) {
		return compras.add(compra);
	}
	
	public boolean quitCompra(Venta compra) {
		return compras.remove(compra);
	}
}
