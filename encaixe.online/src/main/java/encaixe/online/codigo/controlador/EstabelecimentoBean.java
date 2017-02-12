package encaixe.online.codigo.controlador;

import java.io.Serializable;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import encaixe.online.codigo.excecao.NegocioException;
import encaixe.online.codigo.modelo.jpa.Estabelecimento;
import encaixe.online.codigo.modelo.jpa.Usuario;
import encaixe.online.codigo.modelo.servico.EstabelecimentoServico;
import encaixe.online.codigo.utilidades.jsf.ExibeMensagem;

@Model
public class EstabelecimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EstabelecimentoServico servico;
	
	@Inject
	private ExibeMensagem mensagem;

	private Usuario usuario;
	private Estabelecimento estabelecimento;
	
	public EstabelecimentoBean() {
		usuario = new Usuario();
		estabelecimento = new Estabelecimento();
	}

	public String gravar() {
		try {

			servico.salvar(estabelecimento, usuario);

			mensagemSalvarSucesso();

			return "/publico/login.xhtml?faces-redirect=true";

		} catch (NegocioException exception) {

			mensagem.error(exception.getMessage(), null);
			return null;
		}
	}

	private void mensagemSalvarSucesso() {
		StringBuilder builder = new StringBuilder();
		builder
			.append("<p>Seja bem vindo, ")
			.append(estabelecimento.getRazaoSocial()).append(" !! <br />")
			.append("Em instantes um e-mail automático será enviado para o email [ ")
			.append(usuario.getEmail()).append(" ]");
		mensagem.addFlash(builder.toString(), null);
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}
}