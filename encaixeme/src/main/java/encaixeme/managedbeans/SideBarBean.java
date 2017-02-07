
package encaixeme.managedbeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import encaixeme.managedbeans.formulario.SideBarForm;
import encaixeme.persistencia.dto.AgendaDTO;
import encaixeme.servico.SideBarServico;

@Named
@ViewScoped
public class SideBarBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject	private SideBarForm form;
	@Inject	private SideBarServico servico;
	@Inject	private UsuarioBean usuarioBean;

	public List<AgendaDTO> autoCompletarAgendas(String titulo) {

		Long idUsuario = usuarioBean.getUsuario().getId();

		return servico.autoCompletar(idUsuario, titulo);
	}

	public SideBarForm getForm() {
		return form;
	}
}