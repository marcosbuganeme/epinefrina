package encaixe.online.codigo.excecao;

@SuppressWarnings("serial")
public class NegocioException extends RuntimeException {

	public NegocioException() {
	}

	public NegocioException(String mensagem) {
		super(mensagem);
	}

	public NegocioException(Throwable causa) {
		super(causa);
	}

	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
