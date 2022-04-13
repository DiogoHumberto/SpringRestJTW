package br.com.SpringRestJWT.controllers.dtos;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FormAuthDto {
	
	@Email(message = "inválido!")
	@NotBlank(message = "Campo obrigatorio!")
	private String email;
	
	@NotBlank(message = "Campo obrigatorio!")
	private String secretPass;	

}
