package br.com.SpringRestJWT.controllers.dtos;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {
	
	@NotBlank
	private String nome;	
	
	private String sobrenome;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dtNascimento;
	
	@NotBlank
	@Email(message = "inválido")
	private String email;
	
	@NotBlank
	private String password;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date criadoEm;
	
	@JsonFormat
    (shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dtModificacao;
	
	//private EnumGenero genero;

}
