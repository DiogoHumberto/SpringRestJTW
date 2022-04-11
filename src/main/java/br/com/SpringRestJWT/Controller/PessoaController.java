package br.com.SpringRestJWT.Controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

import br.com.SpringRestJWT.Controller.dtos.PessoaDto;
import br.com.SpringRestJWT.services.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService service;

	@PostMapping(value = "/salvar", produces = "application/json")
	@CacheEvict(value = "listarPessoas", allEntries = true)
	public ResponseEntity<PessoaDto> salvarPessoa(@RequestBody @Valid PessoaDto reqDto,
			UriComponentsBuilder uriBuilder) {

		PessoaDto respDto = service.salvarPessoa(reqDto);
		URI uri = uriBuilder.path("/usuario/{email}").buildAndExpand(respDto.getEmail()).toUri();

		return ResponseEntity.created(uri).body(respDto);
	}

	@GetMapping(value = "/{email}", produces = "application/json")
	public ResponseEntity<PessoaDto> buscarPessoaEmail(@PathVariable(value = "email") @Email @Valid String email) {

		return ResponseEntity.ok(service.buscarPessoaEmail(email));

	}
	
	@GetMapping(value = "/listar", produces = "application/json")
	@Cacheable(value = "listarPessoas")
	public ResponseEntity<Page<PessoaDto>> listarPessoas(
			@PageableDefault(sort = "nome", direction = Direction.DESC) Pageable pagPessoaDto) {
		
		return ResponseEntity.ok(service.listarPessoas(pagPessoaDto));
		

	}

}
