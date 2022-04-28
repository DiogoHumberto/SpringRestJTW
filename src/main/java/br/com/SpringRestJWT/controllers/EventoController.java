package br.com.SpringRestJWT.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.SpringRestJWT.controllers.dtos.EventoDto;
import br.com.SpringRestJWT.controllers.dtos.FormEventoDto;
import br.com.SpringRestJWT.services.EventoService;

@RestController
@RequestMapping("/evento")
public class EventoController {
	
	@Autowired
	private EventoService eventoService;
	
	@PostMapping
	public ResponseEntity<EventoDto> createEvento(@Valid FormEventoDto reqDto, UriComponentsBuilder uriBuilder, Authentication authentication ){
		
		EventoDto respDto = eventoService.createEvento(reqDto);
		URI uri = uriBuilder.path("/evento/{id}").buildAndExpand(respDto.getId()).toUri();
		
		return ResponseEntity.created(uri).body(respDto);
		
	}

}
