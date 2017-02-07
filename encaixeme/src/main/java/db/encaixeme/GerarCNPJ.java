package db.encaixeme;

/**
 * Gera um CNPJ
 * @author Marcos Olavo S. Buganeme
 */

public class GerarCNPJ {

	/**
	 * Método responsável por gerar um cpf completo
	 *
	 * @author marcosbuganeme
	 * @return <i>um cpf completo de 11 dígitos</i>.
	 */
	public static String executar() {
		final StringBuilder builder = new StringBuilder();
		for ( int contador = 1; contador < 9; contador++ ) {
			builder.append(new Integer((int) (Math.random() * 10)).toString());
		}
		builder.append("0001");
		for ( int contador = 1; contador < 3; contador++ ) {
			builder.append(new Integer((int) (Math.random() * 10)).toString());
		}
		return builder.toString();
	}
}