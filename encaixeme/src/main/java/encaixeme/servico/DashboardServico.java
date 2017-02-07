package encaixeme.servico;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import encaixeme.managedbeans.filtro.FiltroDeAgendamentos;
import encaixeme.modelo.Semana;
import encaixeme.modelo.Dia;
import encaixeme.persistencia.AgendaDAO;
import encaixeme.persistencia.AgendamentoDAO;
import encaixeme.persistencia.dto.AgendamentoDTO;
import encaixeme.persistencia.dto.AtividadeDTO;
import encaixeme.persistencia.dto.QuantidadeDTO;
import encaixeme.persistencia.dto.UsuarioDTO;
import encaixeme.servico.exception.ResultadoNaoEncontradoException;
import encaixeme.utilitarios.geral.UtilMensagem;

public class DashboardServico implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject	private AgendaDAO dao;
	@Inject	private AgendamentoDAO agendamentoDAO;

	public List<Dia> periodos() {

		return Arrays.asList(Dia.values());
	}

	public List<Semana> semanas() {

		return Arrays.asList(Semana.values());
	}

	public List<AgendamentoDTO> todosAgendamentos(FiltroDeAgendamentos filtro) throws ResultadoNaoEncontradoException {

		List<AgendamentoDTO> agendamentos = agendamentoDAO.todosAgendamentos(filtro);

		if ( agendamentos.isEmpty() ) {

			throw new ResultadoNaoEncontradoException(UtilMensagem.NENHUM_AGENDAMENTO_MARCADO);
		}

		return agendamentos;
	}

	public List<AtividadeDTO> agendasDaSecretaria(Long idUsuario) throws ResultadoNaoEncontradoException {

		List<AtividadeDTO> agendas = dao.agendasDaSecretaria(idUsuario);

		if ( agendas.isEmpty() ) {

			throw new ResultadoNaoEncontradoException(UtilMensagem.NENHUMA_AGENDA_CADASTRADA);
		}

		return agendas;
	}

	public List<AtividadeDTO> agendasDaEmpresa(UsuarioDTO usuario) throws ResultadoNaoEncontradoException {

		List<AtividadeDTO> atividades = dao.agendasDaEmpresa(usuario.getId());

		if ( atividades.isEmpty() ) {

			throw new ResultadoNaoEncontradoException(UtilMensagem.NENHUMA_AGENDA_CADASTRADA);

		} else {
			for (AtividadeDTO atividade : atividades) {

				QuantidadeDTO ativa = dao.contadorAtivas(atividade.getId());
				QuantidadeDTO concluida = dao.contadorConcluidas(atividade.getId());

				if ( ativa == null ) {

					atividade.setQuantidadeAtiva(0L);

				} else {

					atividade.setQuantidadeAtiva(ativa.getDisponivel());
				}

				if ( concluida == null ) {

					atividade.setQuantidadeConcluida(0L);

				} else {

					atividade.setQuantidadeConcluida(concluida.getConcluida());
				}
			}
		}

		return atividades;
	}
}