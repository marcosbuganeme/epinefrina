package encaixeme.utilitarios.cdi;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

@ApplicationScoped
public class HttpProducer {

	@Inject
	private ExternalContext externalContext;

	@Produces
	@RequestScoped
	public HttpServletResponse getResponse() {
		return (HttpServletResponse) this.externalContext.getResponse();
	}
}