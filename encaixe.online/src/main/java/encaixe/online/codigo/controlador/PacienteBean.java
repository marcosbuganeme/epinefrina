package encaixe.online.codigo.controlador;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import encaixe.online.codigo.excecao.NegocioException;
import encaixe.online.codigo.modelo.jpa.Paciente;
import encaixe.online.codigo.modelo.jpa.Usuario;
import encaixe.online.codigo.modelo.servico.PacienteServico;
import encaixe.online.codigo.utilidades.jsf.ExibeMensagem;

@Model
public class PacienteBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(PacienteBean.class.getName());

	@Inject
	private PacienteServico servico;

	@Inject
	private ExibeMensagem mensagem;

	private Usuario usuario;
	private Paciente paciente;

	public PacienteBean() {
		usuario = new Usuario();
		paciente = new Paciente();
	}

	public String gravar() {
		try {

			servico.salvar(paciente, usuario);

			mensagemSalvarSucesso();

			return "/publico/login.xhtml?faces-redirect=true";

		} catch (NegocioException exception) {

			mensagem.error(exception.getMessage(), null);
			LOG.severe(exception.getMessage());

			return null;
		}
	}

	private void mensagemSalvarSucesso() {
		StringBuilder builder = new StringBuilder();

		builder
			.append("<p>Seja bem vindo, ")
			.append(paciente.getNome()).append(" !! <br />")
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

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
}