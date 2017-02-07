package encaixeme.utilitarios.geral;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import encaixeme.modelo.Semana;
import encaixeme.modelo.Dia;

public class UtilData {

	public static String formata(Date data) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(data);
	}

	public static long diferencaAtual(Date data) {
		final long diferenca = data.getTime() - new Date().getTime();
		return (diferenca / 1000) / 60 / 60 / 24;
	}

	public static Date adicionaMinutos(Date data, int minutosAdicionado) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.MINUTE, minutosAdicionado);
		return calendar.getTime();
	}

	public static Dia obtemTurnoDoDia(Date data) {
		Dia turno = null;
		int hora = obtemHoras(data);
		int minuto = obtemMinutos(data);

		if (hora >= 7 && hora <= 11 && minuto <= 30) {
			turno = Dia.MATUTINO;
		} else 
			if (hora >= 11 && hora <= 13 && minuto <= 30) {
			turno = Dia.ALMOCO;
		} else 
			if (hora >= 14 && hora <= 17 && minuto <= 30) {
			turno = Dia.VESPERTINO;
		} else {
			turno = Dia.NOTURNO;
		}
		return turno;
	}

	private static int obtemMinutos(Date dataInicio) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dataInicio);
		return calendar.get(Calendar.MINUTE);
	}

	private static int obtemHoras(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	public static int recuperaDiaDaSemana(Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static Semana obtemDiaDasemana(Date data) {
		Semana semana = null;
		int numero = recuperaDiaDaSemana(data);

		switch (numero) {
		case 1:
			semana = Semana.DOMINGO;
			break;

		case 2:
			semana = Semana.SEGUNDA;
			break;

		case 3:
			semana = Semana.TERCA;
			break;

		case 4:
			semana = Semana.QUARTA;
			break;

		case 5:
			semana = Semana.QUINTA;
			break;

		case 6:
			semana = Semana.SEXTA;
			break;

		default:
			semana = Semana.SABADO;
			break;
		}
		return semana;
	}

	public static Date configurarData(int dia, int mes, int ano, int hora, int minuto, int segundo) {
		Calendar calendario = Calendar.getInstance();
		calendario.set(Calendar.YEAR, ano);
		calendario.set(Calendar.MONTH, mes - 1);
		calendario.set(Calendar.DAY_OF_MONTH, dia);
		calendario.set(Calendar.HOUR_OF_DAY, hora);
		calendario.set(Calendar.MINUTE, minuto);
		calendario.set(Calendar.SECOND, segundo);
		return calendario.getTime();
	}
}