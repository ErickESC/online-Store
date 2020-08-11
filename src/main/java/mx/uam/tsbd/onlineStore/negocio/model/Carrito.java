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
public class Carrito {

 @Id
 private Integer IdCarrito;
 
 @NotBlank
 private Integer IdLibro;
 
 @NotNull
 private Double Total;
}
