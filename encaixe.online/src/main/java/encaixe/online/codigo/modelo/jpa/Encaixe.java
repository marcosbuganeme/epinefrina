package encaixe.online.codigo.modelo.jpa;

import java.io.Serializable;

public interface Encaixe extends Serializable {

	Long getIdentificador();

	boolean isNovoRegistro();
}