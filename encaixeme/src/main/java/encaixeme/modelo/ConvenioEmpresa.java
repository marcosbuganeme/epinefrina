package encaixeme.modelo;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import encaixeme.modelo.arquitetura.EntidadeArquitetura;

@Entity
@Table(name = "convenio_empresa")
public class ConvenioEmpresa extends EntidadeArquitetura<Long> {
	private static final long serialVersionUID = 1L;
	private Empresa empresa;
	private PlanoSaude planoSaude;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_convenio_suportado_empresa"), name = "id_empresa", nullable = false)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_plano_Saude_atendido_empresa"), name = "id_plano_saude", nullable = false)
	public PlanoSaude getPlanoSaude() {
		return planoSaude;
	}

	public void setPlanoSaude(PlanoSaude planoSaude) {
		this.planoSaude = planoSaude;
	}
}