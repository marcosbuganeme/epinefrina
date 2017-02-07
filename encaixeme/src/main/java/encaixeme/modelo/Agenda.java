package encaixeme.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import encaixeme.modelo.arquitetura.EntidadeArquitetura;

@Entity
@Table(name = "agenda")
public class Agenda extends EntidadeArquitetura<Long> {
	private static final long serialVersionUID = 1L;

	private Date criado;
	private Status status;
	private String titulo;
	private BigDecimal valor;

	private Empresa empresa;
	private Especialidade especialidade;

	private List<Atividade> atividades;
	private List<Permissao> permissoes;

	@PrePersist
	private void gatilhoAgenda() {
		this.criado = new Date();
		this.status = Status.ATIVO;
	}

	@NotEmpty
	@Column(length = 80, nullable = false, updatable = false)
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCriado() {
		return criado;
	}

	public void setCriado(Date criado) {
		this.criado = criado;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_agenda_pertence_empresa"), name = "id_empresa", nullable = false, updatable = false)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@NotNull
	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_agenda_pertence_especialidade"), name = "id_especialidade", nullable = false)
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda")
	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "agenda")
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
}