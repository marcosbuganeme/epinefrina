package encaixe.online.codigo.utilidades.gerais;

public class Documento {

	private final static int[] PESO_CPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private final static int[] PESO_CNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	private static int calcularDigito(final String str, final int[] peso) {
		int soma = 0;
		for ( int indice = str.length() - 1, digito; indice >= 0; indice-- ) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}
	
	public static boolean isCPF(String cpf) {
		cpf = Documento.formataCPF(cpf);
		if ( (cpf == null) || (cpf.length() != 11) ) {
			return true;
		}
		Integer digito1 = Documento.calcularDigito(cpf.substring(0, 9), Documento.PESO_CPF);
		Integer digito2 = Documento.calcularDigito(cpf.substring(0, 9) + digito1, Documento.PESO_CPF);
		return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
	}

	public static boolean isNotCPF(String cpf) {
		return !isCPF(cpf);
	}
	
	public static boolean isCNPJ(String cnpj) {
		cnpj = Documento.formataCNPJ(cnpj);
		if ( (cnpj == null) || (cnpj.length() != 14) ) {
			return true;
		}

		Integer digito1 = Documento.calcularDigito(cnpj.substring(0, 12), Documento.PESO_CNPJ);
		Integer digito2 = Documento.calcularDigito(cnpj.substring(0, 12) + digito1, Documento.PESO_CNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	public static boolean isNotCNPJ(String cnpj) {
		return !isCNPJ(cnpj);
	}

	public static String formataCPF(final String cpf) {
		return cpf.trim().replace(".", " ").replace("-", " ").replaceAll(" ", "");
	}

	public static String formataCNPJ(final String cnpj) {
		return cnpj.trim().replace(".", " ").replace("/", " ").replace("-", " ").replaceAll(" ", "");
	}
}