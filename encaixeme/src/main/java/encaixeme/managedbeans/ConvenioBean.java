package encaixeme.managedbeans;

import java.io.Serializable;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import encaixeme.managedbeans.formulario.ConvenioForm;
import encaixeme.servico.ConvenioServico;

@Model
public class ConvenioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject	private ConvenioForm form;
	@Inject	private ConvenioServico servico;

	public void formataPlanoConvenioPorEmpresa(Long idEmpresa) {

		String conveniosFormatados = servico.conveniosSuportados(idEmpresa);
		form.setConvenios(conveniosFormatados);
	}

	public ConvenioForm getForm() {
		return form;
	}
}