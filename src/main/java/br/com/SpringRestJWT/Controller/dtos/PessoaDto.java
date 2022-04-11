package br.com.SpringRestJWT.Controller.dtos;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PessoaDto {
	
	@NotBlank
	private String nome;	
	
	private String sobrenome;
	
	@NotNull
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dtNascimento;
	
	@NotBlank
	@Email(message = "inválido")
	private String email;
	
	private Date criadoEm;
	
	private Date dtModificacao;
	
	//private EnumGenero genero;

}
