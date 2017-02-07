package encaixeme.persistencia.dto.arquitetura;

import java.io.Serializable;

/**
 * @author Marcos Olavo S. Buganeme
 * @param <PK>
 * @param <E>
 */

public interface DTO<PK extends Serializable> extends Serializable {

	PK getId();
	void setId(PK id);
}
