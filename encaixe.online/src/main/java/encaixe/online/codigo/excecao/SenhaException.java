package encaixe.online.codigo.excecao;

public class SenhaException extends NegocioException {

	private static final long serialVersionUID = 1L;

	public SenhaException(String mensagem) {
		super(mensagem);
	}

	public SenhaException(Throwable causa) {
		super(causa);
	}

	public SenhaException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}