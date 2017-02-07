package encaixeme.managedbeans.filtro;

import java.io.Serializable;

import encaixeme.modelo.Dia;
import encaixeme.modelo.Semana;

public class FiltroDeAgendamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private Semana semana;
	private Dia periodo;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Semana getSemana() {
		return semana;
	}

	public void setSemana(Semana semana) {
		this.semana = semana;
	}

	public Dia getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Dia periodo) {
		this.periodo = periodo;
	}
}