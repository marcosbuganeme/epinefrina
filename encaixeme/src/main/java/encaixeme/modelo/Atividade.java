package encaixeme.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import encaixeme.modelo.arquitetura.EntidadeArquitetura;
import encaixeme.utilitarios.geral.UtilData;

@Entity
@Table(name = "atividade")
public class Atividade extends EntidadeArquitetura<Long> {

	private static final long serialVersionUID = 1L;

	private Date comeca;
	private Dia periodo;
	private Date termina;
	private Status status;
	private Semana semana;
	private Date resultado;
	private String observacao;

	private Agenda agenda;

	@PreUpdate
	@PrePersist
	private void gatilhoAtividade() {
		if ( isNovo() ) {
			this.status = Status.ATIVO;
		}

		this.semana = UtilData.obtemDiaDasemana(this.comeca);
		this.periodo = UtilData.obtemTurnoDoDia(this.comeca);
		this.termina = UtilData.adicionaMinutos(this.comeca, 45);
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "comeca", nullable = false, updatable = false)
	public Date getComeca() {
		return comeca;
	}

	public void setComeca(Date comeca) {
		this.comeca = comeca;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "termina", nullable = false, updatable = false)
	public Date getTermina() {
		return termina;
	}

	public void setTermina(Date termina) {
		this.termina = termina;
	}

	@NotEmpty
	@Column(length = 100)
	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	public Date getResultado() {
		return resultado;
	}

	public void setResultado(Date resultado) {
		this.resultado = resultado;
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
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false, updatable = false)
	public Semana getSemana() {
		return semana;
	}

	public void setSemana(Semana semana) {
		this.semana = semana;
	}

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false, updatable = false)
	public Dia getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Dia periodo) {
		this.periodo = periodo;
	}

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_atividade_vinculada_agenda"), name = "id_agenda", nullable = false)
	public Agenda getAgenda() {
		return this.agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
}