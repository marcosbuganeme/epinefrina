package encaixeme.managedbeans.formulario;

import java.util.ArrayList;
import java.util.List;

import encaixeme.managedbeans.formulario.arquitetura.Formulario;
import encaixeme.modelo.Agenda;
import encaixeme.persistencia.dto.AgendaDTO;

/**
 * @author Marcos Olavo S. Buganeme
 */

public class SideBarForm extends Formulario<Agenda> {

	private static final long serialVersionUID = 1L;

	private AgendaDTO agendaSelecionada;

	private List<AgendaDTO> sugestoes = new ArrayList<>();

	public AgendaDTO getAgendaSelecionada() {
		return agendaSelecionada;
	}

	public void setAgendaSelecionada(AgendaDTO agendaSelecionada) {
		this.agendaSelecionada = agendaSelecionada;
	}

	public List<AgendaDTO> getSugestoes() {
		return sugestoes;
	}

	public void setSugestoes(List<AgendaDTO> sugestoes) {
		this.sugestoes = sugestoes;
	}
}