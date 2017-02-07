package encaixeme.managedbeans.formulario;

import encaixeme.managedbeans.formulario.arquitetura.Formulario;
import encaixeme.modelo.ConvenioEmpresa;

/**
 * @author Marcos Olavo S. Buganeme
 */

public class ConvenioForm extends Formulario<ConvenioEmpresa> {

	private static final long serialVersionUID = 1L;

	private String convenios;

	public String getConvenios() {
		return convenios;
	}

	public void setConvenios(String convenios) {
		this.convenios = convenios;
	}
}