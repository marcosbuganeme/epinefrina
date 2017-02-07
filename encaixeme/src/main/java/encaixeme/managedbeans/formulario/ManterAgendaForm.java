package encaixeme.managedbeans.formulario;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DualListModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

import encaixeme.modelo.Agenda;
import encaixeme.modelo.Atividade;
import encaixeme.modelo.Categoria;
import encaixeme.modelo.Especialidade;
import encaixeme.modelo.Secretaria;

public class ManterAgendaForm implements Serializable {

	private static final long serialVersionUID = 1L;

	private Agenda agenda;

	private boolean habilitaValor;
	private boolean desabilitaPicklistSecretaria;
	private boolean desabilitaComboboxEspecialidade;

	private DualListModel<Secretaria> modelSecretarias;

	private ScheduleModel scheduleModel = new DefaultScheduleModel();
	private ScheduleEvent scheduleEvent = new DefaultScheduleEvent();

	private List<Atividade> atividades;
	private List<Categoria> categorias;
	private List<Secretaria> secretarias;
	private List<Especialidade> especialidades;

	@NotNull
	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public boolean isHabilitaValor() {
		return habilitaValor;
	}

	public void setHabilitaValor(boolean habilitaValor) {
		this.habilitaValor = habilitaValor;
	}

	public boolean isDesabilitaPicklistSecretaria() {
		return desabilitaPicklistSecretaria;
	}

	public void setDesabilitaPicklistSecretaria(boolean desabilitaPicklistSecretaria) {
		this.desabilitaPicklistSecretaria = desabilitaPicklistSecretaria;
	}

	public boolean isDesabilitaComboboxEspecialidade() {
		return desabilitaComboboxEspecialidade;
	}

	public void setDesabilitaComboboxEspecialidade(boolean desabilitaComboboxEspecialidade) {
		this.desabilitaComboboxEspecialidade = desabilitaComboboxEspecialidade;
	}

	public DualListModel<Secretaria> getModelSecretarias() {
		return modelSecretarias;
	}

	public void setModelSecretarias(DualListModel<Secretaria> modelSecretarias) {
		this.modelSecretarias = modelSecretarias;
	}

	public ScheduleModel getScheduleModel() {
		return scheduleModel;
	}

	public void setScheduleModel(ScheduleModel scheduleModel) {
		this.scheduleModel = scheduleModel;
	}

	public ScheduleEvent getScheduleEvent() {
		return scheduleEvent;
	}

	public void setScheduleEvent(ScheduleEvent scheduleEvent) {
		this.scheduleEvent = scheduleEvent;
	}

	public List<Atividade> getAtividades() {
		return atividades;
	}

	public void setAtividades(List<Atividade> atividades) {
		this.atividades = atividades;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public List<Secretaria> getSecretarias() {
		return secretarias;
	}

	public void setSecretarias(List<Secretaria> secretarias) {
		this.secretarias = secretarias;
	}

	public List<Especialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidade> especialidades) {
		this.especialidades = especialidades;
	}
}