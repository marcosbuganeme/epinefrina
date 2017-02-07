package encaixeme.servico;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import encaixeme.modelo.Assistido;
import encaixeme.modelo.PlanoSaude;
import encaixeme.persistencia.PlanoSaudeDAO;
import encaixeme.persistencia.UsuarioDAO;
import encaixeme.persistencia.transacional.Transacao;
import encaixeme.servico.exception.NegocioException;
import encaixeme.utilitarios.geral.UtilMensagem;
import encaixeme.utilitarios.geral.UtilValidaDocumentos;

public class AssistidoServico implements Serializable {

	private static final long serialVersionUID = 1L;

//	@Inject	private Mailer mailer;
	@Inject	private UsuarioDAO dao;
	@Inject	private PlanoSaudeDAO planoSaudeDAO;

	public List<PlanoSaude> todosPlanosDeSaude() {

		return planoSaudeDAO.todos();
	}

	@Transacao
	public String salvar(Assistido assistido) throws NegocioException {

		validaCPF(assistido.getCpf());
		validaEmail(assistido.getUsuario().getEmail());

		dao.salvar(assistido);
//		enviarEmail(assistido);

		return "Bem vindo, " + assistido.getNome() + " seu cadastro acaba de ser criado !!";
	}

//	private void enviarEmail(Assistido assistido) {
//
//		MailMessage mensagem = mailer.novaMensagem();
//
//		String email = assistido.getUsuario().getEmail();
//		String login = assistido.getUsuario().getLogin();
//		String senha = assistido.getUsuario().getSenhaOriginal();
//
//		mensagem
//			.to(email).subject("Seu número de identificação é: " + assistido.getId())
//			.bodyHtml("<html><body><h2>Login do usuário: " + login + "</h2><br><h2>Senha: " + senha + "</h2></body></html>")
//			.send();
//	}

	private void validaEmail(String email) throws NegocioException {

		if (StringUtils.isNotBlank(email)) {
			if (dao.isEmailExiste(email)) {
				throw new NegocioException(UtilMensagem.EMAIL_ATIVO);
			}
		}
	}

	private void validaCPF(String cpf) throws NegocioException {

		if (UtilValidaDocumentos.isNotCPF(cpf)) {
			throw new NegocioException(UtilMensagem.CPF_INVALIDO);
		}

		if (dao.isLoginExiste(cpf)) {
			throw new NegocioException(UtilMensagem.CPF_ATIVO);
		}
	}
}