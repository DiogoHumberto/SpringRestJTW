package br.com.SpringRestJWT.domain.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import br.com.SpringRestJWT.domain.enums.entities.EnumStatusEvento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "evento", uniqueConstraints = @UniqueConstraint(columnNames = {"nome", "dt_evento"}))
public class Evento {
	
	@Id
	@Column(name = "id_evento")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@ManyToOne
    @JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@Column(name = "dt_evento")
	private Date dtEvento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "status_evento")
	private EnumStatusEvento statusEvento;
	
	@Column(name = "qt_pessoas")
	private Integer qtPessoas;
	
	 @ManyToMany(fetch = FetchType.EAGER)
	    @JoinTable(
	            name = "convidados_convite_evento",
	            joinColumns = @JoinColumn(name = "id_evento"),
	            inverseJoinColumns = @JoinColumn(name = "id_convite"))
	private List<Convite> convites;
	
	@CreatedDate
	@Column(name = "criado_em")
	private Date criadoEm;

	@LastModifiedDate
	@Column(name = "modificado_em")
	private Date modificadoEm;
	

}
