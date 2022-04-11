package br.com.SpringRestJWT.domain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "pessoa", uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "sobrenome"}))
@EntityListeners(AuditingEntityListener.class)
public class Pessoa {
	
	@Id
	@Column(name = "id_pessoa")
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
	
	@Enumerated(EnumType.STRING)
	@Column(name = "genero")
	private EnumGenero genero;
	
	@CreatedDate
	@Column(name = "criado_em")
	private Date criadoEm;

	@LastModifiedDate
	@Column(name = "modificado_em")
	private Date modificadoEm;
}

