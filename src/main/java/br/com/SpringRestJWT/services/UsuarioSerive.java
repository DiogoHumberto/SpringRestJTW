package br.com.SpringRestJWT.services;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.SpringRestJWT.controllers.dtos.UsuarioDto;
import br.com.SpringRestJWT.domain.entities.Role;
import br.com.SpringRestJWT.domain.entities.Usuario;
import br.com.SpringRestJWT.domain.mappers.UsuarioMapper;
import br.com.SpringRestJWT.exception.BadRequestException;
import br.com.SpringRestJWT.repositories.UsuarioRepository;

@Service
public class UsuarioSerive implements UserDetailsService, ApplicationRunner {
	
	//private static Logger LOGGER = LoggerFactory.getLogger(UsuarioSerive.class);
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		if (!usuarioRepository.findByEmail("diogohumbertoo@gmail.com").isPresent()) {
			usuarioRepository.save(Usuario.builder()
					.nome("diogo")
					.sobrenome("Humberto")
					.email("diogohumbertoo@gmail.com")
					.password(passwordEncoder.encode("123456"))
					.roles(Arrays.asList(new Role("ROLE_MASTER")))
					.build());
		}		
	}
	
	protected Usuario buscarUsuarioEmail(String email) {
		
		Optional<Usuario> user = usuarioRepository.findByEmail(email);
		if (!user.isPresent()) {
			throw new BadRequestException("usuário não encontrado!");
		}
		
		return user.get();
	}
	
	public UsuarioDto salvarUsuario(UsuarioDto reqDto) {

		Optional<Usuario> pessoaExist = usuarioRepository.findByEmail(reqDto.getEmail());
		if (pessoaExist.isPresent()) {
			
			throw new UsernameNotFoundException("Já existe usuario cadastrado com esse email!");			
		}

		return UsuarioMapper.toDto(usuarioRepository.save(UsuarioMapper.toEntity(reqDto)));
	}

	public UsuarioDto buscarEmail(@Email String email) {

		Optional<Usuario> user = usuarioRepository.findByEmail(email);
		if (!user.isPresent()) {
			throw new BadRequestException("usuário não encontrado!");
		}

		return UsuarioMapper.toDto(user.get());

	}

	public Page<UsuarioDto> listarUsuario(Pageable page) {

		Page<Usuario> pessoas = usuarioRepository.findAll(page);

		return pessoas.map(UsuarioMapper::toDto);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> user = usuarioRepository.findByEmail(username);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("usuário ou senha inválido!");
		}
		
		return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(),
				mapRolesToAuthorities(user.get().getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

}
