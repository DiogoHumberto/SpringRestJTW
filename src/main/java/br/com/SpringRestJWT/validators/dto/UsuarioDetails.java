package br.com.SpringRestJWT.validators.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UsuarioDetails extends User {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1286114108408824266L;
	private Boolean isAdmin;
    private Boolean isMaster;

    public UsuarioDetails(String email, String senha, Boolean isAdmin, Boolean isMaster, Collection<? extends GrantedAuthority> permissoes) {
        super(email, senha, permissoes);
        this.isAdmin = isAdmin;
        this.isMaster = isMaster;
    }

    public UsuarioDetails(String email, String senha, Boolean isAtivo, Boolean isAdmin, Boolean isMaster, Boolean contaNaoExpirada,
                          Boolean credenciaisNaoExpiradas, Boolean contaNaoBloqueada, Collection<? extends GrantedAuthority> permissoes) {
        super(email, senha, isAtivo, contaNaoExpirada, credenciaisNaoExpiradas, contaNaoBloqueada, permissoes);
        this.isAdmin = isAdmin;
        this.isMaster = isMaster;
    }

}
