package br.com.SpringRestJWT.domain.mappers;

import br.com.SpringRestJWT.controllers.dtos.UsuarioDto;
import br.com.SpringRestJWT.domain.entities.Usuario;

public class UsuarioMapper {
	
	public static Usuario toEntity(UsuarioDto dto) {
		
		return Usuario.builder()
				.nome(dto.getNome())
				.sobrenome(dto.getSobrenome())
				.dtNascimento(dto.getDtNascimento())
				.email(dto.getEmail())
				.build();
				
	}
	
	public static UsuarioDto toDto(Usuario entity) {
		
		return UsuarioDto.builder()
				.nome(entity.getNome())
				.sobrenome(entity.getSobrenome())
				.dtNascimento(entity.getDtNascimento())
				.email(entity.getEmail())
				.criadoEm(entity.getCreatedAt())
				.dtModificacao(entity.getUpdatedAt())
				.build();
				
	}

}
