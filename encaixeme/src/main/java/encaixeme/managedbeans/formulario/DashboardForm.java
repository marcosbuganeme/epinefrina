package encaixeme.managedbeans.formulario;

import java.util.List;

import encaixeme.managedbeans.filtro.FiltroDeAgendamentos;
import encaixeme.managedbeans.formulario.arquitetura.Formulario;
import encaixeme.modelo.Atividade;
import encaixeme.persistencia.dto.AgendamentoDTO;
import encaixeme.persistencia.dto.AtividadeDTO;

public class DashboardForm extends Formulario<Atividade> {

	private static final long serialVersionUID = 1L;

	private FiltroDeAgendamentos filtro;

	private int quantidadeAtiva;
	private String secretarias;

	private List<AgendamentoDTO> agendamentos;
	private List<AtividadeDTO> agendasDaEmpresa;
	private List<AtividadeDTO> agendasDaSecretaria;

	public FiltroDeAgendamentos getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroDeAgendamentos filtro) {
		this.filtro = filtro;
	}

	public int getQuantidadeAtiva() {
		return quantidadeAtiva;
	}

	public void setQuantidadeAtiva(int quantidadeAtiva) {
		this.quantidadeAtiva = quantidadeAtiva;
	}

	public String getSecretarias() {
		return secretarias;
	}

	public void setSecretarias(String secretarias) {
		this.secretarias = secretarias;
	}

	public List<AgendamentoDTO> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<AgendamentoDTO> agendamentos) {
		this.agendamentos = agendamentos;
	}

	public List<AtividadeDTO> getAgendasDaEmpresa() {
		return agendasDaEmpresa;
	}

	public void setAgendasDaEmpresa(List<AtividadeDTO> agendasDaEmpresa) {
		this.agendasDaEmpresa = agendasDaEmpresa;
	}

	public List<AtividadeDTO> getAgendasDaSecretaria() {
		return agendasDaSecretaria;
	}

	public void setAgendasDaSecretaria(List<AtividadeDTO> agendasDaSecretaria) {
		this.agendasDaSecretaria = agendasDaSecretaria;
	}
}