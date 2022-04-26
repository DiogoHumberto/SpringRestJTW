package br.com.SpringRestJWT.services;

import java.util.Calendar;
import java.util.Date;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.SpringRestJWT.controllers.dtos.FormAuthDto;
import br.com.SpringRestJWT.controllers.dtos.TokenDto;
import br.com.SpringRestJWT.domain.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthService {
	
	@Value("${api.jwt.expiration}")
	private String expiration;
	
	@Value("${api.jwt.expiration}")
	private String secret;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UsuarioSerive usuarioService;
	
	public TokenDto autenticar(FormAuthDto formDto) throws AuthenticationException {
		
		UsernamePasswordAuthenticationToken dadosLogin = converterToUser(formDto);		
		Authentication authentication = authManager.authenticate(dadosLogin);
		
		return gerarToken(authentication);
		
	}
	
	@Cacheable(value="Usuario", key="#token")
	public Usuario getUsuarioAutenticado(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		
		return usuarioService.buscarUsuarioEmail(claims.getSubject());
	}
	
	public boolean isTokenValid(String token) {		
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;		
		} catch (Exception e) {
			return false;
		}
	}
	
	private UsernamePasswordAuthenticationToken converterToUser(FormAuthDto formDto) {
		return new UsernamePasswordAuthenticationToken(formDto.getEmail(), formDto.getSecretPass());
	}
	
	private TokenDto gerarToken(Authentication authentication) {

		Date dtAtual = Calendar.getInstance().getTime();
		
		return TokenDto.builder()
			.type("Bearer")
			.token(Jwts.builder()
					.setIssuer("Api Eventos")
					.setSubject(authentication.getName())
					.setIssuedAt(dtAtual)
					.setExpiration(createExpirationToken(dtAtual))
					.signWith(SignatureAlgorithm.HS256, secret)
					.compact())
			.createIn(dtAtual)
			.expirationIn(createExpirationToken(dtAtual))
			.build(); 
		
	}
	
	private Date createExpirationToken(Date atual) {		
		return new Date(atual.getTime() + Long.parseLong(expiration));
	}
	
}
