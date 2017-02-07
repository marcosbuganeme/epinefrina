package encaixeme.modelo.arquitetura;

import java.io.Serializable;

/**
 * Interface pai de todas as classes da camada de modelo
 * 
 * @author Marcos Olavo S. Buganeme
 */

public interface Entidade extends Serializable {

	Serializable getId();

	boolean isNovo();
}