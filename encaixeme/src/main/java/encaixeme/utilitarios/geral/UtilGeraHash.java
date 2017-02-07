package encaixeme.utilitarios.geral;

import java.util.Random;

import encaixeme.modelo.Agenda;
import encaixeme.modelo.Secretaria;

/**
 * Utilitario responsável por gerar hashs aleatórios que servem como Pin para a
 * autenticação e validação de um
 * <code>{@link Secretaria} de uma <code>{@link Agenda}</code>
 * 
 * @author Marcos Olavo S. Buganeme
 */

public final class UtilGeraHash {

	private static final Random RANDOM = new Random();
	private static final char[] ALL_CHARS = new char[62];

	static {
		for (int contador = 48, secundario = 0; contador < 123; contador++) {
			if (Character.isLetterOrDigit(contador)) {
				ALL_CHARS[secundario] = (char) contador;
				secundario++;
			}
		}
	}

	public static String geraSenhaAleatoria(final int length) {
		final char[] result = new char[length];
		for (int i = 0; i < length; i++) {
			result[i] = ALL_CHARS[RANDOM.nextInt(ALL_CHARS.length)];
		}
		return new String(result);
	}

	public static String geraSenhaAleatoria() {
		return geraSenhaAleatoria(6);
	}

	public static void main(String[] args) {
		System.out.println(geraSenhaAleatoria(50));
	}
}