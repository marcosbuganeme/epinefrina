package encaixeme.managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import encaixeme.modelo.Empresa;
import encaixeme.modelo.Usuario;
import encaixeme.servico.EmpresaServico;
import encaixeme.servico.exception.NegocioException;
import encaixeme.utilitarios.geral.UtilMensagem;
import encaixeme.utilitarios.jsf.MessageHelper;

@Named
@ViewScoped
public class EmpresaBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Empresa empresa;
	
	@Inject	private MessageHelper helper;
	@Inject	private EmpresaServico servico;

	@PostConstruct
	private void inicializar() {
		empresa = new Empresa();
		empresa.setUsuario(new Usuario());
	}
	
	public String salvar() {
		try {

			verificaSenhas();

			verificaContrato();

			String mensagem = servico.salvar(empresa);

			helper.addFlash(mensagem, null);

			inicializar();

			return "/pages/public/login?faces-redirect=true";

		} catch (NegocioException e) {

			helper.error(e.getMessage(), null);
		}
//		} catch (SendFailedException e) {
//
//			helper.error("Falha ao enviar e-mail para " + empresa.getUsuario().getEmail(), null);
//		}
		return null;
	}

	private void verificaContrato() {
		if ( !empresa.isContrato() ) {
			throw new NegocioException(UtilMensagem.TERMO_NAO_ACEITO);
		}
	}

	private void verificaSenhas() {
		if ( !StringUtils.equals(empresa.getUsuario().getSenha(), empresa.getUsuario().getContraSenha()) ) {
			throw new NegocioException(UtilMensagem.SENHAS_NAO_COINCIDEM);
		}
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
}