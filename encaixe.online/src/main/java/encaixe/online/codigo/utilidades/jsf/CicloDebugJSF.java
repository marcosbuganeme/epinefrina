package encaixe.online.codigo.utilidades.jsf;

import java.util.logging.Logger;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class CicloDebugJSF implements PhaseListener {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(CicloDebugJSF.class.getName());
	
	@Override
	public void afterPhase(PhaseEvent evento) {
		LOG.info("@after " + evento.getPhaseId());
		System.out.println("@after " + evento.getPhaseId());
	}

	@Override
	public void beforePhase(PhaseEvent evento) {
		LOG.info("@before " + evento.getPhaseId());
		System.out.println("@before " + evento.getPhaseId());
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}