package encaixeme.seguranca;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import encaixeme.persistencia.dto.UsuarioDTO;

/**
 * Classe que captura o usuário logado no sistema com as suas permissões vinculadas
 * 
 * @author Marcos Olavo S. Buganeme
 */

public class UsuarioLogado extends User {

	private static final long serialVersionUID = 1L;
	private final UsuarioDTO usuario;

	public UsuarioLogado(UsuarioDTO usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getLogin(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}
}