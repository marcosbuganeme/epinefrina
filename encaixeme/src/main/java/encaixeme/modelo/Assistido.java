package encaixeme.modelo;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import db.encaixeme.UtilTelefone;
import encaixeme.modelo.arquitetura.EntidadeArquitetura;
import encaixeme.utilitarios.geral.UtilValidaDocumentos;

@Entity
@Table(name = "assistido")
public class Assistido extends EntidadeArquitetura<Long> {

	private static final long serialVersionUID = 1L;

	private boolean contrato;

	private String cpf;
	private String ddd;
	private String nome;
	private String telefone;
	private Usuario usuario;
	private Date nascimento;
	private PlanoSaude planoSaude;

	private List<Agendamento> agendamentos;

	@PrePersist
	private void gatilhoAssistido() {
		this.usuario.setPermissao(EnumPermissao.ASSISTIDO);
		this.ddd = UtilTelefone.removeCaracteresDDD(this.ddd);
		this.cpf = UtilValidaDocumentos.removeMascaraCPF(this.cpf);
		this.usuario.setLogin(this.cpf);
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
	@Column(length = 11, nullable = false, updatable = false, unique = true)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@NotEmpty
	@Column(length = 2, nullable = false)
	public String getDdd() {
		return ddd;
	}

	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	@NotEmpty
	@Column(length = 100, nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_assistido_possui_usuario"), name = "id_usuario", nullable = false)
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_assistido_possui_plano_saude"), name = "id_plano_saude")
	public PlanoSaude getPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(PlanoSaude planoSaude) {
		this.planoSaude = planoSaude;
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "assistido")
	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}
}