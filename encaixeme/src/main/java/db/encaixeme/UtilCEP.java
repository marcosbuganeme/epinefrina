package db.encaixeme;

/**
 * Gera automaticamente um CEP
 * 
 * @author Marcos Olavo S. Buganeme
 */

public final class UtilCEP {

	/**
	 * Método responsável por gerar um cep
	 *
	 * @author marcosbuganeme
	 * @return <i>um cep completo de 8 dígitos</i>.
	 */
	public static String executar() {
		final StringBuilder builder = new StringBuilder();
		for ( int i = 1; i < 9; i++ ) {
			builder.append(new Integer((int) (Math.random() * 10)).toString());
		}
		return builder.toString();
	}

	public static String removeCaracteresCEP(String cep) {
		return cep.replace(".", " ")
				  .replace("-", " ")
				  .replaceAll(" ", "")
				  .trim();
	}
}