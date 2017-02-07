package encaixeme.modelo;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import encaixeme.modelo.arquitetura.EntidadeArquitetura;

@Entity
@Table(name = "categoria_relacionada")
public class CategoriaRelacionada extends EntidadeArquitetura<Long> {
	private static final long serialVersionUID = 1L;

	private Empresa empresa;
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_empresa_envolvida"), nullable = false)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_categoria_relacionada"), nullable = false)
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}