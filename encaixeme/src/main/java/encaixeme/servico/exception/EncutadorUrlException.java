package encaixeme.servico.exception;

/**
 * @author marcos
 *
 */

public class EncutadorUrlException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public EncutadorUrlException() {
	}

	public EncutadorUrlException(String mensagem) {
		super(mensagem);
	}

	public EncutadorUrlException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

	public EncutadorUrlException(Throwable causa) {
		super(causa);
	}
}