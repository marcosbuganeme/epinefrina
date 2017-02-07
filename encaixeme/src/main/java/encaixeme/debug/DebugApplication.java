package encaixeme.debug;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;

/**
 * Classe que serve como depurador para verificar o ciclo de vida do JSF
 * 
 * @author Marcos Olavo S. Buganeme
 */

public class DebugApplication implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(final PhaseEvent event) {
		System.out.println("@after " + event.getPhaseId());
	}

	@Override
	public void beforePhase(final PhaseEvent event) {
		if ( event.getPhaseId().equals(PhaseId.RESTORE_VIEW) ) {
			HttpServletRequest requisicao = (HttpServletRequest) event.getFacesContext().getExternalContext().getRequest();

			if ( requisicao.getUserPrincipal() != null ) {
				System.out.println("Usuário: " + requisicao.getUserPrincipal());
			}
		}

		System.out.println("@before " + event.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}