package encaixeme.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import encaixeme.modelo.Empresa;
import encaixeme.persistencia.dto.UsuarioDTO;
import encaixeme.servico.EmpresaServico;
import encaixeme.utilitarios.jsf.MessageHelper;

@Model
public class AlteraEmpresaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Empresa empresa;
	
	@Inject private MessageHelper helper;
	@Inject private FacesContext context;
	@Inject private EmpresaServico servico;
	@Inject private UsuarioBean usuarioBean;

	@PostConstruct
	public void iniciar() {
		if ( !context.isPostback() ) {

			UsuarioDTO usuario = usuarioBean.getUsuario();
			empresa = servico.porEmailELogin(usuario.getEmail(), usuario.getLogin());
		}
	}

	public void alterar() {

		String mensagemAlterar = servico.alterar(empresa);
		helper.info(mensagemAlterar, null);
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}