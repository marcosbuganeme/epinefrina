package encaixeme.modelo;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import encaixeme.modelo.arquitetura.EntidadeArquitetura;

@Entity
@Table(name = "agendamento")
public class Agendamento extends EntidadeArquitetura<Long> {
	private static final long serialVersionUID = 1L;

	private Atividade atividade;
	private Assistido assistido;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_agendamento_pertence_atividade"), name = "id_atividade", nullable = false)
	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_agendamento_realizado_assistido"), name = "id_assistido", nullable = false)
	public Assistido getAssistido() {
		return assistido;
	}

	public void setAssistido(Assistido assistido) {
		this.assistido = assistido;
	}
}