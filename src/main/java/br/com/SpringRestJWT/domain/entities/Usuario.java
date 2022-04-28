package br.com.SpringRestJWT.domain.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
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

import br.com.SpringRestJWT.domain.converters.database.BooleanoAttributeConverter;
import br.com.SpringRestJWT.domain.entities.configuration.AuditBaseEntity;
import br.com.SpringRestJWT.domain.enums.Booleano;
import br.com.SpringRestJWT.domain.enums.entities.EnumGenero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "usuario", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario extends AuditBaseEntity {
	
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
	
    @Convert(converter = BooleanoAttributeConverter.class)
    @Column(name = "status")
    @Builder.Default
    private Booleano status = Booleano.SIM;

    @Convert(converter = BooleanoAttributeConverter.class)
    @Column(name = "is_admin")
    @Builder.Default
    private Booleano isAdmin = Booleano.NAO;

    @Convert(converter = BooleanoAttributeConverter.class)
    @Column(name = "is_master")
    @Builder.Default
    private Booleano isMaster = Booleano.NAO;
    
	@Enumerated(EnumType.STRING)
	@Column(name = "genero")
	private EnumGenero genero;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id_role"))
	private Collection<Role> roles;
	
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = Booleano.getByBoolean(isAdmin);
    }

    public void setIsMaster(Boolean isMaster) {
        this.isMaster = Booleano.getByBoolean(isMaster);
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(dtNascimento, other.dtNascimento) && Objects.equals(email, other.email)
				&& genero == other.genero && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(password, other.password) && Objects.equals(roles, other.roles)
				&& Objects.equals(sobrenome, other.sobrenome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(dtNascimento, email, genero, id, nome, password, roles, sobrenome);
	}
	
}

