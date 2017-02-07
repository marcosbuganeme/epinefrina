package encaixeme.utilitarios.geral;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import encaixeme.servico.exception.EncutadorUrlException;

/**
 * Utilitario responsável por encurtar URL's utilizando uma api terceirizada
 * 
 * @author marcos
 */

public final class UtilEncurtadorURL {

	private static final String API_ENCURTADOR = "http://migre.me/api.txt?url=";
	
	public static String encurtar(String url) throws EncutadorUrlException, IOException {
		URL linkURL;
		String linha;
		BufferedReader leitor = null;
		HttpURLConnection protocoloHTTP;

		try {
			linkURL = new URL(API_ENCURTADOR + url);

			protocoloHTTP = (HttpURLConnection) linkURL.openConnection();
			protocoloHTTP.setRequestMethod("GET");

			leitor = new BufferedReader(new InputStreamReader(protocoloHTTP.getInputStream()));

			if ((linha = leitor.readLine()) != null) {
				return linha;
			}

		} catch (Exception exception) {
			throw new EncutadorUrlException("A API de encurtamento de URL falhou !!");
		} finally {
			if (leitor != null) {
				try {
					leitor.close();
				} catch (IOException e) {
					throw new IOException("A API de leitura de fluxo de dados não pode finalizar o objeto de leitura");
				}
			}
		}
		return null;
	}

	public static void main(String[] args) throws EncutadorUrlException, IOException {
		String miniUrl = encurtar("http://www.encaixeme.com.br/empresa/1/agenda/2");
		System.out.println(miniUrl);
	}
}