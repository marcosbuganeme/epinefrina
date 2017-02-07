package encaixeme.persistencia.dto;

import java.math.BigDecimal;
import java.util.Date;

import encaixeme.modelo.Dia;
import encaixeme.modelo.Semana;
import encaixeme.modelo.arquitetura.EntidadeArquitetura;

/**
 * @author marcos
 *
 */

public class AgendaDTO extends EntidadeArquitetura<Long> {

	private static final long serialVersionUID = 1L;

	private String cnpj;
	private Long idEmpresa;
	private String descricao;
	private String razaoSocial;

	private Date horario;
	private Semana semana;
	private Dia periodo;

	private Long idAgenda;
	private String titulo;
	private BigDecimal valor;
	private Date resultado;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public Date getHorario() {
		return horario;
	}

	public void setHorario(Date horario) {
		this.horario = horario;
	}

	public Semana getSemana() {
		return semana;
	}

	public void setSemana(Semana semana) {
		this.semana = semana;
	}

	public Dia getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Dia periodo) {
		this.periodo = periodo;
	}

	public Long getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(Long idAgenda) {
		this.idAgenda = idAgenda;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getResultado() {
		return resultado;
	}

	public void setResultado(Date resultado) {
		this.resultado = resultado;
	}
}