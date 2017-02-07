package encaixeme.utilitarios.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Classe produtora dos objetos básicos do JSF
 * 
 * @author Marcos Olavo S. Buganeme
 */

@ApplicationScoped
public class FacesContextProducer {

	@Produces
	@RequestScoped
	public FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

	@Produces
	@RequestScoped
	public ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}
}