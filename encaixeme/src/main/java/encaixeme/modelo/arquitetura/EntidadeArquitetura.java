package encaixeme.modelo.arquitetura;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Entidade abstrata que implementa a interface pai de todas as entidades do
 * modelo de dados<br>
 * Seu objetivo é prover as funções comuns entre as demais classes concretas
 * 
 * @author Marcos Olavo S. Buganeme
 *
 * @param <ID>
 *            - identificador manipulado pela classe concreta
 */

@MappedSuperclass
public abstract class EntidadeArquitetura<ID extends Serializable> implements Entidade {

	private static final long serialVersionUID = 1L;

	private ID id;

	@Transient
	public boolean isNovo() {
		return this.id == null;
	}
	
	@Id
	@Override
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		int numeroPrimo = 31;
		int resultado = 1;
		resultado = numeroPrimo * resultado + ((id == null) ? 0 : id.hashCode());
		return resultado;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EntidadeArquitetura)) {
			return false;
		}

		@SuppressWarnings("rawtypes")
		EntidadeArquitetura other = (EntidadeArquitetura) obj;

		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
}