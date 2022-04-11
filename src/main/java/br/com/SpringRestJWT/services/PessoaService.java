package br.com.SpringRestJWT.services;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.SpringRestJWT.Controller.dtos.PessoaDto;
import br.com.SpringRestJWT.domain.entities.Pessoa;
import br.com.SpringRestJWT.domain.mappers.PessoaMapper;
import br.com.SpringRestJWT.repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public PessoaDto salvarPessoa(PessoaDto reqDto) {
		
		Pessoa pessoaExist = pessoaRepository.findByEmail(reqDto.getEmail());		
		if (pessoaExist == null) {			
			return PessoaMapper.toDto(pessoaRepository.save(PessoaMapper.toEntity(reqDto)));
		}
		
		return PessoaMapper.toDto(pessoaExist);		
	}
	
	public PessoaDto buscarPessoaEmail(@Email String email) {
		
		return PessoaMapper.toDto(pessoaRepository.findByEmail(email));
	}
	
	public Page<PessoaDto> listarPessoas(Pageable page){
		
		Page<Pessoa> pessoas = pessoaRepository.findAll(page);
		
		return pessoas.map(PessoaMapper::toDto);		
	
	}

}
