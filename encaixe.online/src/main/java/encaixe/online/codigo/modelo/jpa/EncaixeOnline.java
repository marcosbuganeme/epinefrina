package encaixe.online.codigo.modelo.jpa;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EncaixeOnline implements Encaixe {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long identificador;

	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
	}

	@Override
	public Long getIdentificador() {
		return identificador;
	}

	@Override
	public boolean isNovoRegistro() {
		return identificador == null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EncaixeOnline)) {
			return false;
		}
		EncaixeOnline other = (EncaixeOnline) obj;
		if (identificador == null) {
			if (other.identificador != null) {
				return false;
			}
		} else if (!identificador.equals(other.identificador)) {
			return false;
		}
		return true;
	}
}