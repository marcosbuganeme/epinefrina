package encaixeme.utilitarios.geral;

import java.security.MessageDigest;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class UtilSenha {

	private static final Logger LOG = Logger.getLogger(UtilSenha.class.getName());

	public static String criptografaSenhaSHA256(String senha) {
		final StringBuilder builder = new StringBuilder();

		try {
			MessageDigest conversor = MessageDigest.getInstance("SHA-256");
			byte[] bytes = conversor.digest(senha.getBytes("UTF-8"));
			for ( byte b : bytes ) {
				builder.append(String.format("%02X", 0xFF & b));
			}
		} catch ( Exception exception ) {
			LOG.log(Level.SEVERE, exception.getMessage());
		}

		return builder.toString().toLowerCase();
	}
}