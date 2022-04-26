package br.com.SpringRestJWT.domain.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.SpringRestJWT.domain.enums.entities.EnumGenero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@EntityListeners(AuditingEntityListener.class)
public class Usuario implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1129101199514298775L;

	@Id
	@Column(name = "id_usuario")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "sobrenome")
	private String sobrenome;
	
	@Column(name = "dt_nascimento")
	private Date dtNascimento;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genero")
	private EnumGenero genero;
	
	@CreatedDate
	@Column(name = "dt_criado_em")
	private Date dtCriadoEm;

	@LastModifiedDate
	@Column(name = "dt_modificado_em")
	private Date dtModificadoEm;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"))
	private Collection<Role> roles;

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(dtCriadoEm, other.dtCriadoEm) && Objects.equals(dtModificadoEm, other.dtModificadoEm)
				&& Objects.equals(dtNascimento, other.dtNascimento) && Objects.equals(email, other.email)
				&& genero == other.genero && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(password, other.password) && Objects.equals(roles, other.roles)
				&& Objects.equals(sobrenome, other.sobrenome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dtCriadoEm, dtModificadoEm, dtNascimento, email, genero, id, nome, password, roles,
				sobrenome);
	}
	
}

