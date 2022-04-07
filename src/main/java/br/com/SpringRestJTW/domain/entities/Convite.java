package br.com.SpringRestJTW.domain.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import br.com.SpringRestJTW.domain.enums.entities.EnumPresenca;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "convite", uniqueConstraints = @UniqueConstraint(columnNames = {"id_pessoa", "id_evento"}))
@EntityListeners(AuditingEntityListener.class)
public class Convite {
	
	@Id
	@Column(name = "id_convite")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "id_pessoa")
	private Pessoa pessoa;
	
	@ManyToOne
    @JoinColumn(name = "id_evento")
	private Evento evento;	
	
	@Enumerated(EnumType.STRING)
	private EnumPresenca statusPresenca;	
	
	@CreatedDate
	@Column(name = "dt_convite")
	private Date dtConvite;

	@LastModifiedDate
	@Column(name = "modificado_em")
	private Date modificadoEm;

}
