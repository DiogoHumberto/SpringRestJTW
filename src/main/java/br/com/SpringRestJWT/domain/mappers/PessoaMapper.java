package br.com.SpringRestJWT.domain.mappers;

import br.com.SpringRestJWT.Controller.dtos.PessoaDto;
import br.com.SpringRestJWT.domain.entities.Pessoa;

public class PessoaMapper {
	
	public static Pessoa toEntity(PessoaDto dto) {
		
		return Pessoa.builder()
				.nome(dto.getNome())
				.sobrenome(dto.getSobrenome())
				.dtNascimento(dto.getDtNascimento())
				.email(dto.getEmail())
				.build();
				
	}
	
	public static PessoaDto toDto(Pessoa entity) {
		
		return PessoaDto.builder()
				.nome(entity.getNome())
				.sobrenome(entity.getSobrenome())
				.dtNascimento(entity.getDtNascimento())
				.email(entity.getEmail())
				.criadoEm(entity.getCriadoEm())
				.dtModificacao(entity.getModificadoEm())
				.build();
				
	}

}
