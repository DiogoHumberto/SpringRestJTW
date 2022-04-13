package br.com.SpringRestJWT.controllers;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.SpringRestJWT.controllers.dtos.FormAuthDto;
import br.com.SpringRestJWT.controllers.dtos.TokenDto;
import br.com.SpringRestJWT.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping(produces = "application/json")
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid FormAuthDto formDto){
		TokenDto responseDto = null;
		
		try {			
			responseDto = authService.autenticar(formDto);			
		} catch (AuthenticationException e) {
			
			ResponseEntity.badRequest();
		}
		
		return ResponseEntity.ok(responseDto);
	}

}
