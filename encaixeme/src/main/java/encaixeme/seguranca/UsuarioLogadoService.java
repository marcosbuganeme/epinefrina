package encaixeme.seguranca;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import encaixeme.persistencia.UsuarioDAO;
import encaixeme.persistencia.dto.UsuarioDTO;
import encaixeme.utilitarios.cdi.CDIServiceLocator;

public class UsuarioLogadoService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		UsuarioDTO usuario = CDIServiceLocator.getBean(UsuarioDAO.class).porLogin(login);

		if (usuario == null) {
			throw new UsernameNotFoundException("Nenhum usuário foi encontrado !!");
		}

		return new UsuarioLogado(usuario, getPermissao(usuario));
	}

	private List<? extends GrantedAuthority> getPermissao(UsuarioDTO usuario) {
		List<SimpleGrantedAuthority> permissoes = new ArrayList<SimpleGrantedAuthority>();
		SimpleGrantedAuthority regra = new SimpleGrantedAuthority(usuario.getPermissao().name());
		permissoes.add(regra);
		return permissoes;
	}
}