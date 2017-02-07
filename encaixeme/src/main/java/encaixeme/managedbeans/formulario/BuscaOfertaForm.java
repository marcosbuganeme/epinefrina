package encaixeme.managedbeans.formulario;

import java.util.Arrays;
import java.util.List;

import encaixeme.managedbeans.filtro.FiltroDeOfertas;
import encaixeme.managedbeans.formulario.arquitetura.Formulario;
import encaixeme.modelo.Atividade;
import encaixeme.modelo.Categoria;
import encaixeme.modelo.CorpoHumano;
import encaixeme.modelo.Dia;
import encaixeme.modelo.PlanoSaude;
import encaixeme.modelo.Semana;
import encaixeme.persistencia.dto.AgendaDTO;
import encaixeme.persistencia.dto.EspecialidadeDTO;

public class BuscaOfertaForm extends Formulario<Atividade> {

	private static final long serialVersionUID = 1L;

	private FiltroDeOfertas filtro;

	private boolean desabilitaComboboxCorpo;
	private boolean desabilitaComboboxEspecialidade;

	private List<Dia> periodos = Arrays.asList(Dia.values());
	private List<Semana> semanas = Arrays.asList(Semana.values());

	private List<String> breadcrumb;
	private List<AgendaDTO> agendas;
	private List<PlanoSaude> planos;
	private List<CorpoHumano> corpos;
	private List<Categoria> categorias;
	private List<EspecialidadeDTO> especialidades;

	public FiltroDeOfertas getFiltro() {
		return filtro;
	}

	public void setFiltro(FiltroDeOfertas filtro) {
		this.filtro = filtro;
	}

	public boolean isDesabilitaComboboxCorpo() {
		return desabilitaComboboxCorpo;
	}

	public void setDesabilitaComboboxCorpo(boolean desabilitaComboboxCorpo) {
		this.desabilitaComboboxCorpo = desabilitaComboboxCorpo;
	}

	public boolean isDesabilitaComboboxEspecialidade() {
		return desabilitaComboboxEspecialidade;
	}

	public void setDesabilitaComboboxEspecialidade(boolean desabilitaComboboxEspecialidade) {
		this.desabilitaComboboxEspecialidade = desabilitaComboboxEspecialidade;
	}

	public List<Dia> getPeriodos() {
		return periodos;
	}

	public List<Semana> getSemanas() {
		return semanas;
	}

	public List<String> getBreadcrumb() {
		return breadcrumb;
	}

	public void setBreadcrumb(List<String> breadcrumb) {
		this.breadcrumb = breadcrumb;
	}

	public List<AgendaDTO> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<AgendaDTO> agendas) {
		this.agendas = agendas;
	}

	public List<PlanoSaude> getPlanos() {
		return planos;
	}

	public void setPlanos(List<PlanoSaude> planos) {
		this.planos = planos;
	}

	public List<CorpoHumano> getCorpos() {
		return corpos;
	}

	public void setCorpos(List<CorpoHumano> corpos) {
		this.corpos = corpos;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<EspecialidadeDTO> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<EspecialidadeDTO> especialidades) {
		this.especialidades = especialidades;
	}
}