package encaixeme.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import encaixeme.managedbeans.formulario.SecretariaForm;
import encaixeme.modelo.Secretaria;
import encaixeme.modelo.Usuario;
import encaixeme.servico.SecretariaServico;
import encaixeme.servico.exception.NegocioException;
import encaixeme.utilitarios.jsf.MessageHelper;

@Named
@ViewScoped
public class SecretariaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject private SecretariaForm form;
	@Inject private MessageHelper helper;
	@Inject private SecretariaServico servico; 

	@PostConstruct
	private void iniciar() {
		reiniciar();
	}

	public String salvar() {
		try {
			String mensagemSucesso = servico.salva(form.getEntidade());

			helper.addFlash(mensagemSucesso, null);

			reiniciar();

			return "/pages/auth/index";

		} catch (NegocioException exception) {

			helper.error(exception.getMessage(), null);
		}

		return null;
	}

	private void reiniciar() {
		form.setEntidade(new Secretaria());
		form.getEntidade().setUsuario(new Usuario());
	}

	public SecretariaForm getForm() {
		return form;
	}
}