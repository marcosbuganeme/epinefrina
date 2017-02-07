package encaixeme.servico;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import encaixeme.persistencia.AgendaDAO;
import encaixeme.persistencia.dto.AgendaDTO;

public class SideBarServico implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject	private AgendaDAO dao;

	public List<AgendaDTO> autoCompletar(Long idUsuario, String tituloAgenda) {
		return dao.porTitulo(idUsuario, tituloAgenda);
	}
}