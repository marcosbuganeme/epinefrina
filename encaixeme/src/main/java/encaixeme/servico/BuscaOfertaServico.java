package encaixeme.servico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import encaixeme.managedbeans.filtro.FiltroDeOfertas;
import encaixeme.persistencia.AgendaDAO;
import encaixeme.persistencia.dto.AgendaDTO;
import encaixeme.servico.exception.ResultadoNaoEncontradoException;

public class BuscaOfertaServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject	private AgendaDAO agendaDAO;

	public List<String> itensDaPesquisa(FiltroDeOfertas filtro) {

		List<String> itens = new ArrayList<>(0);

		itens.add(filtro.getCategoria().getNome());
		itens.add(filtro.getEspecialidade().getNome());

		if ( filtro.getCategoria().getId() == 1L ) {

			if ( filtro.getCorpo() != null ) {
				itens.add(filtro.getCorpo().getNome());
			}
		}

		if ( filtro.getPlanoSaude() != null ) {
			itens.add(filtro.getPlanoSaude().getNome());
		}

		return itens;
	}

	public List<AgendaDTO> agendasAtivas(FiltroDeOfertas filtro) throws ResultadoNaoEncontradoException {
		List<AgendaDTO> desejadas = agendaDAO.ofertasDesejadas(filtro);

		List<AgendaDTO> indesejadas = new ArrayList<>();

		if ( desejadas.isEmpty() && indesejadas.isEmpty() ) {

			throw new ResultadoNaoEncontradoException("O sistema dectou uma ausência temporária de ofertas. Tente mais tarde novamente !!");
		}

		return desejadas;
	}
}