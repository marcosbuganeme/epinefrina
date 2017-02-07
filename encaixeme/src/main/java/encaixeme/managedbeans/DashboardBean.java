package encaixeme.managedbeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import encaixeme.managedbeans.filtro.FiltroDeAgendamentos;
import encaixeme.managedbeans.formulario.DashboardForm;
import encaixeme.modelo.Semana;
import encaixeme.modelo.Dia;
import encaixeme.persistencia.dto.AgendamentoDTO;
import encaixeme.persistencia.dto.AtividadeDTO;
import encaixeme.servico.DashboardServico;
import encaixeme.utilitarios.geral.UtilData;
import encaixeme.utilitarios.jsf.MessageHelper;

@Named
@ViewScoped
public class DashboardBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject	private DashboardForm form;
	@Inject	private MessageHelper helper;
	@Inject	private UsuarioBean usuarioBean;
	@Inject	private DashboardServico servico;

	@PostConstruct
	private void iniciarDados() {
		reiniciar();
	}
	
	private void reiniciar() {

		form.setFiltro(new FiltroDeAgendamentos());
	}

	public List<Semana> diasDaSemana() {

		return servico.semanas();
	}

	public List<Dia> periodosDoDia() {

		return servico.periodos();
	}

	public long diasParaResultadoConcluir(Date dataResultado) {

		return UtilData.diferencaAtual(dataResultado);
	}

	public void todosAgendamentos() {
		try {

			String login = usuarioBean.getUsuario().getLogin();
			form.getFiltro().setLogin(login);

			List<AgendamentoDTO> agendamentosAtivos = servico.todosAgendamentos(form.getFiltro());
			form.setAgendamentos(agendamentosAtivos);

		} catch (Exception exception) {

			helper.error(exception.getMessage(), null);
		}
	}

	public void montarDadosEmpresa() {
		try {

			List<AtividadeDTO> atividades = servico.agendasDaEmpresa(usuarioBean.getUsuario());
			form.setAgendasDaEmpresa(atividades);

		} catch (Exception exception) {

			helper.error(exception.getMessage(), null);
		}
	}

	public void montarDadosSupervisor() {
		try {

			Long idUsuarioLogado = usuarioBean.getUsuario().getId();
			form.setAgendasDaSecretaria(servico.agendasDaSecretaria(idUsuarioLogado));

		} catch (Exception exception) {

			helper.error(exception.getMessage(), null);
		}
	}

	public DashboardForm getForm() {
		return form;
	}
}