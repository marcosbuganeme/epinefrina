package encaixeme.servico;

import java.io.Serializable;

import javax.inject.Inject;

import encaixeme.modelo.Empresa;
import encaixeme.persistencia.UsuarioDAO;
import encaixeme.persistencia.transacional.Transacao;
import encaixeme.servico.exception.NegocioException;
import encaixeme.utilitarios.geral.UtilMensagem;
import encaixeme.utilitarios.geral.UtilValidaDocumentos;

public class EmpresaServico implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Inject	private Mailer mailer;
	@Inject	private UsuarioDAO dao;

	@Transacao
	public String salvar(Empresa empresa) throws NegocioException {

		validaRequisitos(empresa);
		dao.salvar(empresa);
//		enviaEmail(empresa);

		return empresa.getResponsavel() + " verifique a caixa de entrada do seu e-mail !";
	}

	@Transacao
	public String alterar(Empresa empresa) {

		Empresa novaEmpresa = dao.alterar(empresa);
//		enviaEmailAlterar(novaEmpresa);

		return novaEmpresa.getResponsavel() + " seus dados foram alterados e enviados por e-mail !";
	}
	
//	private void enviaEmailAlterar(Empresa empresa) {
//
//		Usuario usuario = empresa.getUsuario();
//		MailMessage mensagem = mailer.novaMensagem();
//		mensagem
//			.to(usuario.getEmail())
//			.subject("Alteração de dados")
//			.bodyHtml("<html><body><h1>Você alterou algumas informações</h1></body></html>")
//			.send();
//	}

	private void validaRequisitos(Empresa empresa) {

		if ( UtilValidaDocumentos.isNotCNPJ(empresa.getCnpj()) ) {
			throw new NegocioException(UtilMensagem.CNPJ_INVALIDO);
		}
		
		if ( dao.isLoginExiste(empresa.getCnpj()) ) {
			throw new NegocioException(UtilMensagem.CNPJ_ATIVO);
		}

		if ( dao.isEmailExiste(empresa.getUsuario().getEmail()) ) {
			throw new NegocioException(UtilMensagem.EMAIL_ATIVO);
		}
	}

//	private void enviaEmail(Empresa empresa) {
//
//		Usuario usuario = empresa.getUsuario();
//		MailMessage mensagem = mailer.novaMensagem();
//
//		mensagem
//			.to(usuario.getEmail())
//			.subject("ID do usuário: " + empresa.getId())
//			.bodyHtml("<html><body><h2>Login do usuário: " + usuario.getLogin() + "</h2><br><h2>Sua senha é: " + usuario.getSenhaOriginal() + "</h2></body></html>")
//			.send();
//	}

	public Empresa porEmailELogin(String email, String login) {

		return dao.empresaPorEmailELogin(email, login);
	}
}