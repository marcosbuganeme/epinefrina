package encaixeme.modelo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import db.encaixeme.UtilTelefone;
import encaixeme.modelo.arquitetura.EntidadeArquitetura;
import encaixeme.utilitarios.geral.UtilValidaDocumentos;

@Entity
@Table(name = "empresa")
public class Empresa extends EntidadeArquitetura<Long> {

	private static final long serialVersionUID = 1L;

	private boolean contrato;

	private String ddd;
	private String site;
	private String cnpj;
	private String telefone;
	private Usuario usuario;
	private String responsavel;
	private String razaoSocial;

	private List<Agenda> agendas;
	private List<ConvenioEmpresa> convenios;
	private List<CategoriaRelacionada> categorias;

	@PrePersist
	private void gatilhoEmpresa() {
		this.usuario.setPermissao(EnumPermissao.EMPRESA);
		this.ddd = UtilTelefone.removeCaracteresDDD(this.ddd);
		this.cnpj = UtilValidaDocumentos.removeMascaraCNPJ(this.cnpj);
		this.usuario.setLogin(this.cnpj);
		this.telefone = UtilTelefone.removeCaracteresTelefone(this.telefone);
	}

	@Transient
	public boolean isContrato() {
		return contrato;
	}

	public void setContrato(boolean contrato) {
		this.contrato = contrato;
	}

	@NotEmpty
	@Column(length = 2, nullable = false)
	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	@Column(length = 100)
	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@NotEmpty
	@Column(length = 14, nullable = false, updatable = false, unique = true)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@NotEmpty
	@Column(length = 9, nullable = false)
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_empresa_possui_usuario"), name = "id_usuario")
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(length = 100, nullable = false)
	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	@NotEmpty
	@Column(length = 100, nullable = false, updatable = false)
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	@OneToMany(mappedBy = "empresa")
	public List<Agenda> getAgendas() {
		return agendas;
	}

	public void setAgendas(List<Agenda> agendas) {
		this.agendas = agendas;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
	public List<ConvenioEmpresa> getConvenios() {
		return convenios;
	}

	public void setConvenios(List<ConvenioEmpresa> convenios) {
		this.convenios = convenios;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "empresa")
	public List<CategoriaRelacionada> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaRelacionada> categorias) {
		this.categorias = categorias;
	}
}