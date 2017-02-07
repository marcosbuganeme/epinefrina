package encaixeme.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import encaixeme.modelo.Assistido;
import encaixeme.modelo.PlanoSaude;
import encaixeme.modelo.Usuario;
import encaixeme.servico.AssistidoServico;
import encaixeme.servico.exception.NegocioException;
import encaixeme.utilitarios.geral.UtilMensagem;
import encaixeme.utilitarios.jsf.MessageHelper;

@Named
@ViewScoped
public class AssistidoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Assistido assistido;
	
	@Inject	private MessageHelper helper;
	@Inject	private AssistidoServico servico;

	@PostConstruct
	private void inicializar() {
		assistido = new Assistido();
		assistido.setUsuario(new Usuario());
		assistido.setPlanoSaude(new PlanoSaude());
	}

	public List<PlanoSaude> todosPlanosDeSaude() {
		return servico.todosPlanosDeSaude();
	}
	
	public String salvar() {
		try {

			verificaSenhas();

			verificaContrato();

			String mensagemDeSucessoDaOperacao = servico.salvar(assistido);

			helper.addFlash(mensagemDeSucessoDaOperacao, null);

			inicializar();

			return "/pages/public/login?faces-redirect=true";

		} catch (NegocioException negocioException) {
	
			helper.error(negocioException.getMessage(), null);
		}
//		} catch (SendFailedException emailException) {
//	
//			helper.error("Falha ao enviar e-mail para " + assistido.getUsuario().getEmail(), null);
//		}
		return null;
	}

	private void verificaSenhas() {
		if ( !StringUtils.equals(assistido.getUsuario().getSenha(), assistido.getUsuario().getContraSenha()) ) {
			throw new NegocioException(UtilMensagem.SENHAS_NAO_COINCIDEM);
		}
	}

	private void verificaContrato() {
		if ( !assistido.isContrato() ) {
			throw new NegocioException(UtilMensagem.TERMO_NAO_ACEITO);
		}
	}

	public Assistido getAssistido() {
		return assistido;
	}

	public void setAssistido(Assistido assistido) {
		this.assistido = assistido;
	}
}