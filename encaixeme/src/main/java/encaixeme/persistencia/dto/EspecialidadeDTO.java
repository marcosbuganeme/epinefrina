package encaixeme.persistencia.dto;

import encaixeme.persistencia.dto.arquitetura.AbstractDTO;

/**
 * @author marcos
 *
 */

public class EspecialidadeDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}