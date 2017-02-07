package encaixeme.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import encaixeme.modelo.arquitetura.EntidadeArquitetura;

@Entity
@Table(name = "secretaria")
public class Secretaria extends EntidadeArquitetura<Long> {
	private static final long serialVersionUID = 1L;
	private String nome;
	private Usuario usuario;
	private Status status;
	private List<Permissao> permissoes;

	@PrePersist
	private void gatilhoSecretaria() {

		this.status = Status.ATIVO;
		this.usuario.setLogin(this.usuario.getEmail());
		this.usuario.setPermissao(EnumPermissao.SECRETARIA);
	}

	@Column(length = 100)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 10, nullable = false)
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST })
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_secretaria_possui_usuario"), name = "id_usuario", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@OneToMany(mappedBy = "secretaria")
	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}
}