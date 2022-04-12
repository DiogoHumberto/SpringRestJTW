package br.com.SpringRestJWT.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	//CONFIGURACOES DE AUTENTICACAO
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	
//	}
	
	//CONFIGURACOES DE AUTORIZACAO
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/auth").permitAll()
        .antMatchers(HttpMethod.POST, "/pessoa/salvar").permitAll()
        .antMatchers(HttpMethod.GET, "/pessoa/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		
	
		//.antMatchers(HttpMethod.POST, "/pessoa").permitAll();
		//.antMatchers(HttpMethodsuper.configure(http);
	}
	
	//CONFIGURACOES DE RECURSOS ESTATICOS(js, CSS, images)
//	public void configure(WebSecurity web) throws Exception {
//		
//	}

}
