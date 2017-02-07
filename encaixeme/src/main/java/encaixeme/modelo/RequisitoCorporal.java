package encaixeme.modelo;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import encaixeme.modelo.arquitetura.EntidadeArquitetura;

/**
 * Representa as partes do corpo humano possíveis para que a especialidade seja
 * executada
 * 
 * @author Marcos Olavo S. Buganeme
 */

@Entity
@Table(name = "requisito_corporal")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class RequisitoCorporal extends EntidadeArquitetura<Long> {
	private static final long serialVersionUID = 1L;
	private CorpoHumano corpoHumano;
	private Especialidade especialidade;

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_corpo_humano_envolvido"), name = "id_corpo_humano", nullable = false)
	public CorpoHumano getCorpoHumano() {
		return corpoHumano;
	}

	public void setCorpoHumano(CorpoHumano corpoHumano) {
		this.corpoHumano = corpoHumano;
	}

	@ManyToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_especialidade_aplicada"), name = "id_especialidade", nullable = false)
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
}