package encaixe.online.codigo.utilidades.jsf;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ApplicationScoped
public class ExibeMensagem {

	@Inject
	private FacesContext faces;

	public void addFlash(String resumo, String detalhe) {
		faces.getExternalContext().getFlash().setKeepMessages(true);
		info(resumo, detalhe);
	}

	private void exibe(Severity severidade, String resumo, String detalhe) {
		faces.addMessage(null, new FacesMessage(severidade, resumo, detalhe));
	}

	public void info(String resumo, String detalhe) {
		exibe(FacesMessage.SEVERITY_INFO, resumo, detalhe);
	}

	public void warn(String resumo, String detalhe) {
		exibe(FacesMessage.SEVERITY_WARN, resumo, detalhe);
	}

	public void error(String resumo, String detalhe) {
		exibe(FacesMessage.SEVERITY_ERROR, resumo, detalhe);
	}
}