package encaixeme.persistencia.dto;

import encaixeme.persistencia.dto.arquitetura.AbstractDTO;

/**
 * @author marcos
 *
 */

public class CorpoHumanoDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String complemento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}