package encaixeme.persistencia.dto;

import encaixeme.modelo.EnumPermissao;
import encaixeme.persistencia.dto.arquitetura.AbstractDTO;

public class UsuarioDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String login;
	private String email;
	private String senha;
	private EnumPermissao permissao;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EnumPermissao getPermissao() {
		return permissao;
	}

	public void setPermissao(EnumPermissao permissao) {
		this.permissao = permissao;
	}
}