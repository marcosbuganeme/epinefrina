package encaixeme.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import encaixeme.modelo.arquitetura.EntidadeArquitetura;

@Entity
@Table(name = "corpo_humano")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class CorpoHumano extends EntidadeArquitetura<Long> {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String complemento;

	private List<RequisitoCorporal> requisitos;

	@Column(length = 75, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(length = 100)
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@OneToMany(mappedBy = "corpoHumano")
	public List<RequisitoCorporal> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<RequisitoCorporal> requisitos) {
		this.requisitos = requisitos;
	}
}