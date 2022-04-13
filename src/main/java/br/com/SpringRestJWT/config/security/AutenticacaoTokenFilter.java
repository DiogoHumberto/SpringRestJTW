package br.com.SpringRestJWT.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.SpringRestJWT.domain.entities.Usuario;
import br.com.SpringRestJWT.services.AuthService;

public class AutenticacaoTokenFilter extends OncePerRequestFilter {

	private AuthService authService;

	public AutenticacaoTokenFilter(AuthService authService) {
		super();
		this.authService = authService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recuperarToken(request);
		if (StringUtils.hasText(token) && this.authService.isTokenValid(token)) {
			autenticarClient(token);
		}

		filterChain.doFilter(request, response);

	}

	private void autenticarClient(String token) {

		Usuario usuario = this.authService.getUsuarioAutenticado(token);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
				usuario.getRoles());		
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	private String recuperarToken(HttpServletRequest request) {		
		String token = request.getHeader("Authorization");
		if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7, token.length());
	}

}
