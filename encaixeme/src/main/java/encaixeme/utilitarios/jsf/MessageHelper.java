package encaixeme.utilitarios.jsf;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ApplicationScoped
public class MessageHelper {

	@Inject	private FacesContext faces;

	public void addFlash(final String resumo, final String detalhe) {
		faces.getExternalContext().getFlash().setKeepMessages(true);
		info(resumo, detalhe);
	}

	private void exibir(final Severity severidade, final String resumo, final String detalhe) {
		faces.addMessage(null, new FacesMessage(severidade, resumo, detalhe));
	}

	public void info(final String resumo, final String detalhe) {
		exibir(FacesMessage.SEVERITY_INFO, resumo, detalhe);
	}

	public void warn(final String resumo, final String detalhe) {
		exibir(FacesMessage.SEVERITY_WARN, resumo, detalhe);
	}

	public void error(final String resumo, final String detalhe) {
		exibir(FacesMessage.SEVERITY_ERROR, resumo, detalhe);
	}
}