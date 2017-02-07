package encaixeme.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import encaixeme.modelo.arquitetura.EntidadeArquitetura;

@Entity
@Table(name = "categoria")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Categoria extends EntidadeArquitetura<Long> {

	private static final long serialVersionUID = 1L;

	private String nome;

	@NotEmpty
	@Column(length = 50, nullable = false, updatable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}