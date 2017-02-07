package encaixeme.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import encaixeme.modelo.arquitetura.EntidadeArquitetura;
import encaixeme.utilitarios.geral.UtilSenha;

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeArquitetura<Long> {

	private static final long serialVersionUID = 1L;

	private String contraSenha;
	private String senhaOriginal;

	private String email;
	private String login;
	private String senha;
	private EnumPermissao permissao;

	@PreUpdate
	@PrePersist
	private void gatilhoUsuario() {
		this.senhaOriginal = this.senha;
		this.senha = UtilSenha.criptografaSenhaSHA256(this.senha);
		this.contraSenha = UtilSenha.criptografaSenhaSHA256(this.contraSenha);
	}

	@NotEmpty
	@Column(length = 100, nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotEmpty
	@Column(length = 50, nullable = false, unique = true)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@NotEmpty
	@Column(length = 64, nullable = false)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Transient
	public String getContraSenha() {
		return contraSenha;
	}

	public void setContraSenha(String contraSenha) {
		this.contraSenha = contraSenha;
	}

	@Transient
	public String getSenhaOriginal() {
		return senhaOriginal;
	}

	public void setSenhaOriginal(String senhaOriginal) {
		this.senhaOriginal = senhaOriginal;
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 20, nullable = false)
	public EnumPermissao getPermissao() {
		return permissao;
	}

	public void setPermissao(EnumPermissao permissao) {
		this.permissao = permissao;
	}
}