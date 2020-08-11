package mx.uam.tsbd.onlineStore.negocio.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
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
	

}
