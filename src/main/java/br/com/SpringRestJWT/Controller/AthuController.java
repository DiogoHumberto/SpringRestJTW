package br.com.SpringRestJWT.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.SpringRestJWT.Controller.dtos.FormAuthDto;
import br.com.SpringRestJWT.Controller.dtos.TokenDto;

@RestController
@RequestMapping("/auth")
public class AthuController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping
	public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid FormAuthDto formDto){
		
		return ResponseEntity.ok(new TokenDto());
	}

}
