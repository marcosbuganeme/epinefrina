package encaixeme.persistencia.dto;

import encaixeme.persistencia.dto.arquitetura.AbstractDTO;

public class ConvenioDTO extends AbstractDTO {
	private static final long serialVersionUID = 1L;
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}