package encaixe.online.codigo.modelo.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento extends EncaixeOnline {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(length = 14, nullable = false, updatable = false, unique = true)
	private String cnpj;

	@NotEmpty
	@Column(length = 100, name = "razao_social", nullable = false, updatable = false)
	private String razaoSocial;

	@NotEmpty
	@Column(length = 11, nullable = false)
	private String telefone;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_estabelecimento_vinculado_ao_usuario"), name = "id_usuario", nullable = false, unique = true)
	private Usuario usuario;

	@PrePersist
	private void gatilhoDeVerificacao() {
		usuario.setLogin(cnpj);
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}