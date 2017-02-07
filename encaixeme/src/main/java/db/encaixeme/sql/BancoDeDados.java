package db.encaixeme.sql;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.encaixeme.GerarCNPJ;
import db.encaixeme.GerarCPF;

/**
 * Gerador de dados ficticios<br> 
 * Utilizado para testes de perfomance
 * @author Marcos Olavo S. Buganeme
 */

public class BancoDeDados {

	private static final String CAMINHO_RELATIVO_ARQUIVO_SQL = "C://Users/marco/Desktop/carregar.sql";

	private static List<Integer> meses = new ArrayList<>();
	private static List<Integer> dias = new ArrayList<>();
	private static List<Integer> horas = new ArrayList<>();
	private static List<Integer> minutos = new ArrayList<>();
	
	private static List<String> loginsEmpresa = new ArrayList<>();
	private static List<String> loginsAssistido = new ArrayList<>();

	private static List<Integer> idsCorpos = new ArrayList<>();
	private static List<Integer> idsPlanosSaude = new ArrayList<>();

	private static List<Integer> idsAgendas = new ArrayList<>();
	private static List<Integer> idsVacancias = new ArrayList<>();
	private static List<Integer> idsEmpresas = new ArrayList<>();
	private static List<Integer> idsAssistidos = new ArrayList<>();
	private static List<Integer> idsExamesMedicos = new ArrayList<>();
	private static List<Integer> idsEspecialidades = new ArrayList<>();
	private static List<Integer> idsMedicosEspecialistas = new ArrayList<>();

	private static Map<Integer, List<Integer>> especialidades = new HashMap<>();

	static {
		for (int index = 1; index <= 28; index++) {
			idsVacancias.add(index);
		}

		for (int index = 1; index <= 766; index++) {
			idsPlanosSaude.add(index);
		}

		for (int index = 1; index <= 140; index++) {
			idsCorpos.add(index);
		}

		for (int index = 1; index <= 33; index++) {
			idsMedicosEspecialistas.add(index);
		}

		for (int index = 34; index <= 57; index++) {
			idsExamesMedicos.add(index);
		}

		for (int index = 1; index <= 57; index++) {
			idsEspecialidades.add(index);
		}
		
		for (int index = 1; index <= 100; index++) {
			loginsAssistido.add(GerarCPF.executar());
		}

		for (int index = 1; index <= 65; index++) {
			loginsEmpresa.add(GerarCNPJ.executar());
		}

		especialidades.put(2, idsExamesMedicos);
		especialidades.put(3, idsMedicosEspecialistas);

		for (int index = 0; index <= 11; index++) {
			meses.add(index);
		}

		for (int index = 1; index <= 30; index++) {
			dias.add(index);
		}

		for (int index = 8; index <= 20; index++) {
			horas.add(index);
		}

		for (int index = 0; index <= 30; index++) {
			if (index == 0 || index == 30) {
				minutos.add(index);
			}
		}

		for (int index = 1; index <= 65; index++) {
			idsEmpresas.add(index);
		}

		for (int index = 1; index <= 150; index++) {
			idsAgendas.add(index);
		}

		for (int index = 2; index <= 101; index++) {
			idsAssistidos.add(index);
		}
	}
	
	/**
	 * Cria um arquivo com extensão .sql<br>
	 * Recebe uma lista de insert's gerados automaticamente
	 * 
	 * @param linhasCodigoSQL - lista de insert's
	 */
	public static void criarArquivo(List<String> linhasCodigoSQL) {
		try ( BufferedWriter fileBuffered = new BufferedWriter(new FileWriter(CAMINHO_RELATIVO_ARQUIVO_SQL)) ) {
			for ( String linha : linhasCodigoSQL ) {
				fileBuffered.write(linha);
				fileBuffered.newLine();
			}
			fileBuffered.flush();
		} catch ( IOException exception ) {
			System.err.println(exception.getMessage());
		}
	}

	/**
	 * Agendas
	 * */
	private static final String AGENDA = "INSERT INTO `agenda` (`id`, `status`, `version`, `titulo`, `sub_titulo`, `criado`, `valor`, `resultado`, `id_empresa`, `id_especialidade`) VALUES ";

	public static String agendas() {
		StringBuilder builder = new StringBuilder().append(AGENDA).append("\n");
		for ( int index = 1; index <= 150; index++ ) {
			misturaInformacoesAgenda();

			String data = manipulaData(meses.get(0), dias.get(0), horas.get(0), minutos.get(0));

			builder
				.append("(").append(index).append(", ")
				.append("'ativo', ")
				.append(0).append(", ")
				.append("'titulo da agenda ").append(index).append("', ")
				.append("'sub titulo da agenda " + index).append("', ")
				.append("'").append(data).append("', ")
				.append(new Integer((int) (Math.random() * 1000))).append(", ")
				.append("now()").append(", ")
				.append(idsEmpresas.get(0)).append(", ")
				.append(idsEspecialidades.get(0)).append(")");

			if (index != 150) {				
				builder.append(", ");
			} else {
				builder.append(";");
			}
			builder.append("\n");
		}
		return builder.toString();
	}

	private static void misturaInformacoesAgenda() {
		Collections.shuffle(idsEspecialidades);
		Collections.shuffle(idsEmpresas);
		Collections.shuffle(meses);
		Collections.shuffle(dias);
		Collections.shuffle(horas);
		Collections.shuffle(minutos);
	}

	private static String manipulaData(int mes, int dia, int hora, int minuto) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendario = Calendar.getInstance();
		calendario.set(2016, mes, dia, hora, minuto, 0);
		return sdf.format(calendario.getTime()).toString();
	}

	/**
	 * Agendamentos
	 * */
	private static final String AGENDAMENTO = "INSERT INTO `agendamento` (`id`, `status`, `version`, `id_agenda`, `id_assistido`) VALUES ";

	public static String agendamentos() {
		StringBuilder builder = new StringBuilder().append(AGENDAMENTO).append("\n");

		for ( int index = 1; index <= 500; index++ ) {
			Collections.shuffle(idsAgendas);
			Collections.shuffle(idsAssistidos);

			builder
				.append("(").append(index).append(", ")
				.append("'ativo', ")
				.append(0).append(", ")
				.append(idsAgendas.get(0)).append(", ")
				.append(idsAssistidos.get(0)).append(")");

			if (index != 500) {
				builder.append(", ");
			} else {
				builder.append(";");
			}

			builder.append("\n");
		}
		return builder.toString();
	}

	private static final String ATIVIDADE = "INSERT INTO `atividade` (`id`, `status`, `version`, `horario_marcado`, `id_vacancia`, `vaga`, `id_agenda`) VALUES ";

	public static String atividades() {
		StringBuilder builder = new StringBuilder().append(ATIVIDADE).append("\n");

		for (int index = 1; index <= 1500; index++) {
			misturaInformacoesAtividades();

			String data = manipulaData(meses.get(0), dias.get(0), horas.get(0), minutos.get(0));

			builder
				.append("(").append(index).append(", ")
				.append("'ativo', ")
				.append(0).append(", ")
				.append("'").append(data).append("', ")
				.append(idsVacancias.get(0)).append(", ")
//				.append("'").append( vagas.get(0) ).append("', ")
				.append(idsAgendas.get(0)).append(")");

			if ( index != 1500 ) {
				builder.append(", ");
			} else {
				builder.append(";");
			}

			builder.append("\n");
		}
		return builder.toString();
	}

	private static void misturaInformacoesAtividades() {
		Collections.shuffle(dias);
//		Collections.shuffle(vagas);
		Collections.shuffle(meses);
		Collections.shuffle(horas);
		Collections.shuffle(minutos);
		Collections.shuffle(idsAgendas);
		Collections.shuffle(idsVacancias);
	}

	private static final String CONVENIO = "INSERT INTO `convenio` (`id`, `status`, `version`, `id_empresa`, `id_plano_saude`) VALUES ";

	public static String convenios() {
		StringBuilder builder = new StringBuilder().append(CONVENIO).append("\n");

		for (int index = 1; index <= 500; index++) {
			Collections.shuffle(idsEmpresas);
			Collections.shuffle(idsAgendas);
			builder
				.append("(").append(index).append(", ")
				.append("'ativo', ")
				.append(0).append(", ")
				.append(idsEmpresas.get(0)).append(", ")
				.append(idsAgendas.get(0)).append(")");

			if (index != 500) {
				builder.append(", ");
			} else {
				builder.append(";");
			}
			builder.append("\n");
		}

		return builder.toString();
	}

	private static final String PLANO_SUPORTADO = "INSERT INTO `planos_suportados` (`id_agenda`, `id_plano_saude`) VALUES ";

	public static String planosSuportados() {
		StringBuilder builder = new StringBuilder().append(PLANO_SUPORTADO).append("\n");

		for (int index = 0; index <= 1800; index++) {
			Collections.shuffle(idsAgendas);
			Collections.shuffle(idsPlanosSaude);
			builder
				.append("(").append(idsAgendas.get(0)).append(", ")
				.append(idsPlanosSaude.get(0)).append(")");

			if ( index != 1800 ) {
				builder.append(", ");
			} else {
				builder.append(";");
			}
			builder.append("\n");
		}

		return builder.toString();
	}

	public static void main(String[] args) {
		List<String> sqls = new ArrayList<>();
//		sqls.add(enderecos());
//		sqls.add(assistidos());
//		sqls.add(usuarios());
//		sqls.add(empresas());
//		sqls.add(supervisores());
//		sqls.add(agendas());
//		sqls.add(agendamentos());
//		sqls.add(atividades());
//		sqls.add(convenios());
//		sqls.add(planosSuportados());
		criarArquivo(sqls);
	}
}