package br.com.SpringRestJWT.config.security;

import org.springframework.boot.actuate.endpoint.web.WebEndpointHttpMethod;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	//CONFIGURACOES DE AUTENTICACAO
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	
//	}
	
	//CONFIGURACOES DE AUTORIZACAO
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/pessoa/salvar").permitAll()
        .antMatchers(HttpMethod.GET, "/pessoa/**").hasRole("ADMIN")
        .and()
        .csrf().disable()
        .formLogin().disable();
		
		
	
		//.antMatchers(HttpMethod.POST, "/pessoa").permitAll();
		//.antMatchers(HttpMethodsuper.configure(http);
	}
	
	//CONFIGURACOES DE RECURSOS ESTATICOS(js, CSS, images)
//	public void configure(WebSecurity web) throws Exception {
//		
//	}

}
