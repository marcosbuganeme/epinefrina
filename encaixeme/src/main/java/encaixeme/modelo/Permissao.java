package encaixeme.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import encaixeme.modelo.arquitetura.EntidadeArquitetura;
import encaixeme.utilitarios.geral.UtilGeraHash;

@Entity
@Table(name = "permissao")
public class Permissao extends EntidadeArquitetura<Long> {
	private static final long serialVersionUID = 1L;
	private String hash;
	private Agenda agenda;
	private Status status;
	private Secretaria secretaria;

	public Permissao() {
	}

	public Permissao(Agenda agenda, Secretaria secretaria) {
		this.agenda = agenda;
		this.secretaria = secretaria;
	}

	@PrePersist
	private void iniciaAcesso() {
		this.status = Status.ATIVO;
		this.hash = UtilGeraHash.geraSenhaAleatoria(50);
	}

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_permissao_vinculada_agenda"), name = "id_agenda", nullable = false)
	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	@Column(length = 50, nullable = false)
	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 15, nullable = false)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_permissao_concedida_secretaria"), name = "id_secretaria", nullable = false)
	public Secretaria getSecretaria() {
		return secretaria;
	}

	public void setSecretaria(Secretaria secretaria) {
		this.secretaria = secretaria;
	}
}