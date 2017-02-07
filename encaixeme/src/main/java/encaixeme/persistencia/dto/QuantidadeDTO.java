package encaixeme.persistencia.dto;

/**
 * @author marcos
 *
 */

public class QuantidadeDTO {

	private Long disponivel;
	private Long concluida;

	public Long getDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Long disponivel) {
		this.disponivel = disponivel;
	}

	public Long getConcluida() {
		return concluida;
	}

	public void setConcluida(Long concluida) {
		this.concluida = concluida;
	}
}