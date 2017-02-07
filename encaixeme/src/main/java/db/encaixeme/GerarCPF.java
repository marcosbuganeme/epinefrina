package db.encaixeme;

/**
 * Gera automaticamente um cpf válido
 * 
 * @author Marcos Olavo S. Buganeme
 */

public class GerarCPF {

	/**
	 * Método responsável por gerar um cpf completo
	 *
	 * @author marcosbuganeme
	 * @return <i>um cpf completo de 11 dígitos</i>.
	 */
	public static String executar() {
		final StringBuilder builder = new StringBuilder();
		for ( int i = 0; i < 9; i++ ) {
			builder.append(new Integer((int) (Math.random() * 10)).toString());
		}
		return builder.toString() + GerarCPF.calcularDigitosVerifacacao(builder.toString());
	}

	/**
	 * Método responsável por calcular os últimos 2 dígitos do CPF baseado no
	 * número gerado automaticamente pelo método invocador.
	 *
	 * @author marcosbuganeme
	 * @param noveDigitosIniciaisCPF
	 *            - cpf aleatório de 9 dígitos gerados pelo método invocador.
	 * @return <i>os 2 últimos dígitos do CPF</i>.
	 */
	private static String calcularDigitosVerifacacao(final String noveDigitosIniciaisCPF) {
		Integer decimoNumeroCPF, decimoPrimeiroNumeroCPF;
		int soma = 0, peso = 10;

		for ( int i = 0; i < noveDigitosIniciaisCPF.length(); i++ ) {
			soma += Integer.parseInt(noveDigitosIniciaisCPF.substring(i, i + 1)) * peso--;
		}

		if ( soma % 11 == 0 | soma % 11 == 1 ) {
			decimoNumeroCPF = new Integer(0);

		} else {
			decimoNumeroCPF = new Integer(11 - (soma % 11));
		}

		soma = 0;
		peso = 11;

		for ( int i = 0; i < noveDigitosIniciaisCPF.length(); i++ ) {
			soma += Integer.parseInt(noveDigitosIniciaisCPF.substring(i, i + 1)) * peso--;
		}

		soma += decimoNumeroCPF.intValue() * 2;

		if ( soma % 11 == 0 | soma % 11 == 1 ) {
			decimoPrimeiroNumeroCPF = new Integer(0);

		} else {
			decimoPrimeiroNumeroCPF = new Integer(11 - (soma % 11));
		}
		return decimoNumeroCPF.toString() + decimoPrimeiroNumeroCPF.toString();
	}
}
