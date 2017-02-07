package encaixeme.servico.exception;

/**
 * @author marcos
 *
 */

public class ResultadoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ResultadoNaoEncontradoException() {
	}

	public ResultadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}

	public ResultadoNaoEncontradoException(Throwable causa) {
		super(causa);
	}

	public ResultadoNaoEncontradoException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}