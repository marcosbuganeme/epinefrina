package encaixe.online.codigo.modelo.jpa;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "usuario")
public class Usuario extends EncaixeOnline {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(length = 14, nullable = false, unique = true, updatable = false)
	private String login;

	@NotEmpty
	@Column(length = 50, nullable = false, unique = true)
	private String email;

	@NotEmpty
	@Column(length = 64, nullable = false)
	private String senha;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false, updatable = false)
	private Date criado;

	@NotEmpty
	@Transient
	private String contraSenha;

	@PreUpdate
	@PrePersist
	public void gatilhoDeVerificacao() {
		if (isNovoRegistro()) {
			criado = new Date();
		}
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getCriacao() {
		return criado;
	}

	public void setCriacao(Date criado) {
		this.criado = criado;
	}

	public String getContraSenha() {
		return contraSenha;
	}

	public void setContraSenha(String contraSenha) {
		this.contraSenha = contraSenha;
	}
}