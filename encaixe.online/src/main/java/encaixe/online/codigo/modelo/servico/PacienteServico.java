package encaixe.online.codigo.modelo.servico;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.inject.Inject;

import encaixe.online.codigo.excecao.NegocioException;
import encaixe.online.codigo.modelo.jpa.Paciente;
import encaixe.online.codigo.modelo.jpa.Usuario;
import encaixe.online.codigo.modelo.repositorio.PacienteDAO;
import encaixe.online.codigo.utilidades.cdi.Transacional;
import encaixe.online.codigo.utilidades.gerais.Documento;

public class PacienteServico implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(PacienteServico.class.getName());

	@Inject
	private PacienteDAO dao;

	@Transacional
	public void salvar(Paciente paciente, Usuario usuario) {
		invocaAntesDeSalvar(paciente, usuario);
		dao.salvar(paciente);
		escreveLogDaOperacao(paciente, usuario);
	}

	private void escreveLogDaOperacao(Paciente paciente, Usuario usuario) {
		StringBuilder builder = new StringBuilder();

		builder
			.append("Paciente [ ")
			.append(paciente.getCpf()).append(" ] \n")
			.append("Identificação de email [ ")
			.append(usuario.getEmail()).append(" ] \n")
			.append("criado ás ")
			.append(new SimpleDateFormat("dd/MM/yyyy hh:MM").format(new Date()));

		LOG.info(builder.toString());
	}

	private void invocaAntesDeSalvar(Paciente paciente, Usuario usuario) {
		preparaUsuario(paciente, usuario);
		preparaPaciente(paciente);
	}

	private void preparaPaciente(Paciente paciente) {
		verificaCPF(paciente);
		verificaTelefone(paciente);
	}

	private void verificaTelefone(Paciente paciente) {
		String formatado = paciente.getTelefone().trim().replace("(", " ").replace(")", " ").replace("-", " ").replaceAll(" ", "");
		paciente.setTelefone(formatado);
	}

	private void verificaCPF(Paciente paciente) {
		String documento = Documento.formataCPF(paciente.getCpf());
		paciente.setCpf(documento);

		if ( Documento.isNotCPF(documento) ) {
			throw new NegocioException("Este cpf é inválido !!");
		}

		if ( dao.isCpfExiste(documento) ) {
			throw new NegocioException("Este cpf está vinculado ao serviço do encaixe.online, tente outro !!");
		}
	}

	private void preparaUsuario(Paciente paciente, Usuario usuario) {
		UtilitarioSenha.montarSenha(usuario);
		paciente.setUsuario(usuario);
	}
}