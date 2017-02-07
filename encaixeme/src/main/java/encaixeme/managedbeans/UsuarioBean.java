package encaixeme.managedbeans;

import java.io.Serializable;
import java.text.ParseException;

import javax.enterprise.inject.Model;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.swing.text.MaskFormatter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import encaixeme.modelo.EnumPermissao;
import encaixeme.persistencia.dto.UsuarioDTO;
import encaixeme.seguranca.UsuarioLogado;

@Model
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Log LOG = LogFactory.getLog(UsuarioBean.class);

	@Inject	private ExternalContext externalContext;

	public UsuarioDTO getUsuario() {

		UsernamePasswordAuthenticationToken user = (UsernamePasswordAuthenticationToken) this.externalContext.getUserPrincipal();

		if ( user != null && user.getPrincipal() != null ) {

			return ((UsuarioLogado) user.getPrincipal()).getUsuario();
		}

		return null;
	}

	public String loginUsuarioAssistido() {
		try {

			return formataLoginUsuario(getUsuario().getLogin(), "###.###.###-##");

		} catch (ParseException exception) {

			LOG.error("Não foi possível formatar o CPF do usuário " + getUsuario().getEmail() + " !");
		}
		return null;
	}
	
    private String formataLoginUsuario(String login, String mascara) throws ParseException {
        MaskFormatter formatador = new MaskFormatter(mascara);
        formatador.setValueContainsLiteralCharacters(false);
        return formatador.valueToString(login);
    }
	
	public boolean isAdmin() {

		if ( getUsuario().getPermissao().equals(EnumPermissao.ADMINISTRADOR) ) {

			return true;
		}

		return false;
	}

	public boolean isEmpresa() {

		if ( getUsuario().getPermissao().equals(EnumPermissao.EMPRESA) ) {

			return true;
		}

		return false;
	}

	public boolean isAssistido() {

		if ( getUsuario().getPermissao().equals(EnumPermissao.ASSISTIDO) ) {

			return true;
		}

		return false;
	}

	public boolean isSecretaria() {
		if ( getUsuario().getPermissao().equals(EnumPermissao.SECRETARIA) ) {

			return true;
		}

		return false;
	}
}