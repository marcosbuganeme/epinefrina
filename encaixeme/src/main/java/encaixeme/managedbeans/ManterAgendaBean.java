package encaixeme.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DualListModel;
import org.primefaces.model.ScheduleEvent;

import encaixeme.managedbeans.formulario.ManterAgendaForm;
import encaixeme.modelo.Agenda;
import encaixeme.modelo.Atividade;
import encaixeme.modelo.Categoria;
import encaixeme.modelo.Empresa;
import encaixeme.modelo.Especialidade;
import encaixeme.modelo.Permissao;
import encaixeme.modelo.Secretaria;
import encaixeme.persistencia.CategoriaDAO;
import encaixeme.servico.ManterAgendaServico;
import encaixeme.utilitarios.jsf.MessageHelper;

@Named
@ViewScoped
public class ManterAgendaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject	private MessageHelper helper;
	@Inject	private ManterAgendaForm form;
	@Inject private UsuarioBean usuarioBean;
	@Inject private CategoriaDAO categoriaDAO;
	@Inject	private ManterAgendaServico servico;

	@PostConstruct
	private void iniciarDados() {

		form.setCategorias(categoriaDAO.todos());
		form.setSecretarias(servico.secretariasAtivas());

		if ( form.getSecretarias() != null || !form.getSecretarias().isEmpty() ) {

			form.setDesabilitaPicklistSecretaria(false);
			form.setModelSecretarias(new DualListModel<Secretaria>(form.getSecretarias(), new ArrayList<Secretaria>()));

		} else {

			form.setDesabilitaPicklistSecretaria(true);
			form.setModelSecretarias(new DualListModel<Secretaria>(new ArrayList<>(), new ArrayList<>()));
		}

		form.setHabilitaValor(true);
		form.setDesabilitaComboboxEspecialidade(true);

		reiniciar();
	}

    public void adicionaAtividade(ActionEvent actionEvent) {

        if( form.getScheduleEvent().getId() == null ) {

        	form.getScheduleModel().addEvent(form.getScheduleEvent());

        } else {

            form.getScheduleModel().updateEvent(form.getScheduleEvent());
        }

        form.setScheduleEvent(new DefaultScheduleEvent());
    }

    public void selecionaPorAtividade(SelectEvent selectEvent) {

        form.setScheduleEvent((ScheduleEvent) selectEvent.getObject());
    }

    public void selecionaPorData(SelectEvent selectEvent) {

        form.setScheduleEvent(new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject()));
    }

    public void moveAtividade(ScheduleEntryMoveEvent event) {
        helper.info("Objeto movido: " + event.getSource(), null);
    }

    public void redimencionaAtividade(ScheduleEntryResizeEvent event) {

        helper.info("Objeto redimencionado: " + event.getSource() , null);
    }

	public String salvar() {
		try {
			criaAtividadesNaAgenda();
			vinculaEmpresaNaAgenda();
			vinculaSecretariasNaAgenda();

			String resumo = servico.salvar(getForm().getAgenda());
			helper.info(resumo, null);

			reiniciar();

			form.getScheduleModel().getEvents().clear();

			return "index";

		} catch ( Exception exception ) {

			helper.error(exception.getMessage(), null);
		}

		return null;
	}

	private void vinculaSecretariasNaAgenda() {

		List<Secretaria> secretarias = form.getModelSecretarias().getTarget();

		if( !secretarias.isEmpty() ) {

			List<Permissao> permissoes = new ArrayList<>();

			for (Secretaria secretaria : secretarias) {

				Permissao permissao = new Permissao(form.getAgenda(), secretaria);
				permissoes.add(permissao);
			}

			form.getAgenda().setPermissoes(permissoes);
		}
	}
	
	private void criaAtividadesNaAgenda() {

		List<ScheduleEvent> eventos = form.getScheduleModel().getEvents();

		if ( !eventos.isEmpty() ) {
			for (ScheduleEvent evento : eventos){
				Atividade atividade = new Atividade();

				if ( form.getAgenda() != null ) {

					atividade.setAgenda(form.getAgenda());
				}

				if ( evento.getStartDate() != null ) {

					atividade.setComeca(evento.getStartDate());
				}

				if ( evento.getDescription() != null ) {

					atividade.setObservacao(evento.getDescription());
				}

				form.getAgenda().getAtividades().add(atividade);
			}
		}
	}

	private void vinculaEmpresaNaAgenda() {

		Long idUsuario = usuarioBean.getUsuario().getId();
		Empresa empresaAutenticada = servico.buscarEmpresaPorUsuario(idUsuario);
		form.getAgenda().setEmpresa(empresaAutenticada);
	}

	public void preparaExamesRelacionados() {
		Categoria categoria = form.getAgenda().getEspecialidade().getCategoria();

		if ( categoria != null ) {

			List<Especialidade> examesRelacionados = categoriaDAO.especialidadesPorCategoria(categoria.getId());

			if ( categoria.getId() == 1L ) {
				form.setHabilitaValor(false);
			}

			if ( !examesRelacionados.isEmpty() ) {
				
				form.setEspecialidades(examesRelacionados);
				form.setDesabilitaComboboxEspecialidade(false);
			}
		}
	}

	protected void reiniciar() {

		form.setAgenda(new Agenda());
		form.getAgenda().setEmpresa(new Empresa());
		form.getAgenda().setAtividades(new ArrayList<>(0));
		form.getAgenda().setPermissoes(new ArrayList<>(0));
		form.getAgenda().setEspecialidade(new Especialidade());
		form.getAgenda().getEspecialidade().setCategoria(new Categoria());
	}

	public ManterAgendaForm getForm() {
		return form;
	}
}