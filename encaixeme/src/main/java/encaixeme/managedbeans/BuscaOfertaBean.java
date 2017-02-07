package encaixeme.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import encaixeme.managedbeans.filtro.FiltroDeOfertas;
import encaixeme.managedbeans.formulario.BuscaOfertaForm;
import encaixeme.modelo.CorpoHumano;
import encaixeme.persistencia.CategoriaDAO;
import encaixeme.persistencia.PlanoSaudeDAO;
import encaixeme.persistencia.dto.EspecialidadeDTO;
import encaixeme.servico.BuscaOfertaServico;
import encaixeme.servico.exception.ResultadoNaoEncontradoException;
import encaixeme.utilitarios.geral.UtilData;
import encaixeme.utilitarios.jsf.MessageHelper;

@Named
@ConversationScoped
public class BuscaOfertaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject	private BuscaOfertaForm form;
	@Inject	private MessageHelper helper;
	@Inject	private BuscaOfertaServico servico;

	/*
	 * A manipulação somente acontece a nível de consulta <br>
	 * */
	@Inject	private CategoriaDAO categoriaDAO;
	@Inject	private PlanoSaudeDAO planoSaudeDAO;

	@Inject private FacesContext context;
	@Inject	private Conversation conversation;
	@Inject private ExternalContext externalContext;

	public void iniciar() {
		if ( !context.isPostback() ) {
			
			form.setPlanos(planoSaudeDAO.todos());
			form.setCategorias(categoriaDAO.todos());

			form.setDesabilitaComboboxCorpo(true);
			form.setDesabilitaComboboxEspecialidade(true);

			if ( conversation.isTransient() ) {
	
				form.setFiltro(new FiltroDeOfertas());
				conversation.begin();
			}

		}
	}

	public void validaUrl() {
		if ( form.getFiltro() == null ) {
			try {

				externalContext.redirect("pesquisa.xhtml");

			} catch (IOException exception) {

				helper.info("O sistema não encontrou public/pesquisa.xhtml", null);
			}
		}
	}

	private void finalizaEscopoConversation() {
		if ( !conversation.isTransient() ) {
			conversation.end();
		}
	}

	public List<String> preparaBreadCrumb() {
		return servico.itensDaPesquisa(form.getFiltro());
	}

	public String abreCadastroAssistido() {

		finalizaEscopoConversation();

		return "cadastro/assistido?faces-redirect=true";
	}

	public String abreLogin() {

		finalizaEscopoConversation();

		return "login?faces-redirect=true";
	}
	
	public String redirecionar() {

		try {

			form.setAgendas(servico.agendasAtivas(form.getFiltro()));

		} catch( ResultadoNaoEncontradoException exception ) {

			helper.warn(exception.getMessage(), null);

			return null;
		}

		return "resultados?faces-redirect=true";
	}

	public void buscaEncaixeDeOfertas() {
		try {

			form.setAgendas(servico.agendasAtivas(form.getFiltro()));

		} catch( ResultadoNaoEncontradoException exception ) {

			helper.warn(exception.getMessage(), null);
		}
	}

	public boolean isCategoriaExame() {
		return form.getFiltro().getCategoria().getId() == 1L;
	}
	
	public long dataResultado(Date dataResultado) {
		return UtilData.diferencaAtual(dataResultado);
	}

	public void preparaExamesRelacionados() {

		if ( form.getFiltro().getCategoria() != null ) {

			List<EspecialidadeDTO> examesRelacionados = categoriaDAO.especialidadesRelacionadas(form.getFiltro().getCategoria().getId());

			if ( !examesRelacionados.isEmpty() ) {

				form.setEspecialidades(examesRelacionados);
				form.setDesabilitaComboboxEspecialidade(false);

			} else {

				form.setDesabilitaComboboxCorpo(true);
				form.setDesabilitaComboboxEspecialidade(true);
			}
		}
	}

	public void preparaPartesDoCorpoRelacionadas() {

		if ( form.getFiltro().getCategoria().getId() == 1L ) {

			List<CorpoHumano> partesDoCorpo = categoriaDAO.corposRelacionadosAoExame(form.getFiltro().getEspecialidade().getId());

			if ( !partesDoCorpo.isEmpty() ) {

				form.setCorpos(partesDoCorpo);
				form.setDesabilitaComboboxCorpo(true);

			} else {

				form.setDesabilitaComboboxCorpo(false);
			}
		}
	}

	public BuscaOfertaForm getForm() {
		return form;
	}
}