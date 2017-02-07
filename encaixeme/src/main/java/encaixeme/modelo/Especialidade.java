package encaixeme.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;

import encaixeme.modelo.arquitetura.EntidadeArquitetura;

@Entity
@Table(name = "especialidade")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Especialidade extends EntidadeArquitetura<Long> {

	private static final long serialVersionUID = 1L;

	private String nome;
	private Categoria categoria;

	private List<RequisitoCorporal> requisitos;

	@NotEmpty
	@Column(length = 50, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_especialidade_referente_categoria"), name = "id_categoria", nullable = false)
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(final Categoria categoria) {
		this.categoria = categoria;
	}

	@OneToMany(mappedBy = "especialidade")
	public List<RequisitoCorporal> getRequisitos() {
		return requisitos;
	}

	public void setRequisitos(List<RequisitoCorporal> requisitos) {
		this.requisitos = requisitos;
	}
}