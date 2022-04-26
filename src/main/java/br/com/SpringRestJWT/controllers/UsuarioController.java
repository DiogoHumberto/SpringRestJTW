package br.com.SpringRestJWT.controllers;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.SpringRestJWT.controllers.dtos.UsuarioDto;
import br.com.SpringRestJWT.services.UsuarioSerive;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private static Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private UsuarioSerive service;

	@PostMapping(value = "/salvar", produces = "application/json")
	//@CacheEvict(cacheNames = "listarUsuario" , key="#root.method.name")
	public ResponseEntity<UsuarioDto> salvarUsuario(@RequestBody @Valid UsuarioDto reqDto,
			UriComponentsBuilder uriBuilder) {

		UsuarioDto respDto = service.salvarUsuario(reqDto);
		URI uri = uriBuilder.path("/usuario/{email}").buildAndExpand(respDto.getEmail()).toUri();

		return ResponseEntity.created(uri).body(respDto);
	}

	@GetMapping(value = "/{email}", produces = "application/json")
	//@Cacheable(value = "buscaUsuarioEmail", key="#email")
	public ResponseEntity<UsuarioDto> buscarPessoaEmail(@PathVariable(value = "email") @Valid @NotEmpty @Email String email) {

		return ResponseEntity.ok(service.buscarEmail(email));

	}
	
	@GetMapping(value = "/listar", produces = "application/json")
	//@Cacheable(value = "listarUsuario", key="#pagDto")
	public ResponseEntity<Page<UsuarioDto>> listarPessoas(
			@PageableDefault(sort = "nome", direction = Direction.DESC) Pageable pagDto) {
		
		LOGGER.info("listarUsuario {}", pagDto);
		
		return ResponseEntity.ok(service.listarUsuario(pagDto));		

	}

}
