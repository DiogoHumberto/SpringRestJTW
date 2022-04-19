package br.com.SpringRestJWT.controllers.dtos;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.SpringRestJWT.domain.entities.Convite;
import br.com.SpringRestJWT.domain.enums.entities.EnumStatusEvento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventoDto {
	
	private Long id;
	
	@NotBlank
	private String nome;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dtEvento;
	
	@NotNull
	private Integer qtPessoas;	
	
	private EnumStatusEvento statusEvento;
	
	private List<Convite> convites;
	
	private Date criadoEm;

}
