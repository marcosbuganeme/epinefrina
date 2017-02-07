package encaixeme.servico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import encaixeme.modelo.Agenda;
import encaixeme.modelo.Empresa;
import encaixeme.modelo.Secretaria;
import encaixeme.persistencia.AgendaDAO;
import encaixeme.persistencia.ConvenioDAO;
import encaixeme.persistencia.UsuarioDAO;
import encaixeme.persistencia.dto.ConvenioDTO;
import encaixeme.persistencia.transacional.Transacao;
import encaixeme.servico.exception.NegocioException;
import encaixeme.utilitarios.geral.UtilMensagem;

public class ManterAgendaServico implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject	private AgendaDAO dao;
	@Inject	private UsuarioDAO usuarioDAO;
	@Inject private ConvenioDAO convenioDAO;
	

	@Transacao
	public String salvar(Agenda agenda) throws NegocioException {

		regrasDeNegocio(agenda);
		dao.salvar(agenda);

		return UtilMensagem.AGENDA_SALVAR;
	}

	private void regrasDeNegocio(Agenda agenda) {

		List<ConvenioDTO> convenios = convenioDAO.porAgenda(agenda.getEmpresa().getId());

		if ( convenios.isEmpty() ) {

			throw new NegocioException(UtilMensagem.AGENDA_SEM_CONVENIO);
		}
	}

	public Empresa buscarEmpresaPorUsuario(Long idUsuario) {

		return usuarioDAO.empresaPorUsuario(idUsuario);
	}

	public List<Secretaria> secretariasAtivas() {

		List<Secretaria> secretarias = usuarioDAO.todasSecretariasAtivas();

		if ( secretarias == null || secretarias.isEmpty() ) {

			return new ArrayList<>(0);
		}

		return secretarias;
	}
}