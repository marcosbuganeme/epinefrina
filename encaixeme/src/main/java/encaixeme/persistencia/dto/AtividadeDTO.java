package encaixeme.persistencia.dto;

import encaixeme.persistencia.dto.arquitetura.AbstractDTO;

/**
 * @author Marcos Olavo S. Buganeme
 */

public class AtividadeDTO extends AbstractDTO {

	private static final long serialVersionUID = 1L;

	private String titulo;
	private String categoria;
	private String especialidade;
	private String razaoSocial;

	private Long idAgenda;
	private Long idEmpresa;
	private Long quantidadeAtiva;
	private Long quantidadeConcluida;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Long getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getQuantidadeAtiva() {
		return quantidadeAtiva;
	}

	public void setQuantidadeAtiva(Long quantidadeAtiva) {
		this.quantidadeAtiva = quantidadeAtiva;
	}

	public Long getQuantidadeConcluida() {
		return quantidadeConcluida;
	}

	public void setQuantidadeConcluida(Long quantidadeConcluida) {
		this.quantidadeConcluida = quantidadeConcluida;
	}
}