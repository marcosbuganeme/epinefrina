package encaixeme.servico.exception;

public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NegocioException() {
	}

	public NegocioException(final String mensagem) {
		super(mensagem);
	}

	public NegocioException(final Throwable causa) {
		super(causa);
	}

	public NegocioException(final String mensagem, final Throwable causa) {
		super(mensagem, causa);
	}
}