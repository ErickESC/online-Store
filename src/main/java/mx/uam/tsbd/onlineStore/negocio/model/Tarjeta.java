package mx.uam.tsbd.onlineStore.negocio.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity

public class Tarjeta {
	
	@Id 
	private Integer IdTarjeta;
}
