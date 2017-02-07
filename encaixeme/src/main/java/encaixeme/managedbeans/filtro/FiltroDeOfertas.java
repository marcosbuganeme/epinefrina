package encaixeme.managedbeans.filtro;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import encaixeme.modelo.Categoria;
import encaixeme.modelo.CorpoHumano;
import encaixeme.modelo.Dia;
import encaixeme.modelo.PlanoSaude;
import encaixeme.modelo.Semana;
import encaixeme.persistencia.dto.EspecialidadeDTO;

public class FiltroDeOfertas implements Serializable {

	private static final long serialVersionUID = 1L;

	private Dia periodo;
	private Semana semana;
	private CorpoHumano corpo;
	private Categoria categoria;
	private PlanoSaude planoSaude;
	private EspecialidadeDTO especialidade;

	public Dia getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Dia periodo) {
		this.periodo = periodo;
	}

	public Semana getSemana() {
		return semana;
	}

	public void setSemana(Semana semana) {
		this.semana = semana;
	}

	public CorpoHumano getCorpo() {
		return corpo;
	}

	public void setCorpo(CorpoHumano corpo) {
		this.corpo = corpo;
	}

	@NotNull
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public PlanoSaude getPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(PlanoSaude planoSaude) {
		this.planoSaude = planoSaude;
	}

	@NotNull
	public EspecialidadeDTO getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(EspecialidadeDTO especialidade) {
		this.especialidade = especialidade;
	}
}