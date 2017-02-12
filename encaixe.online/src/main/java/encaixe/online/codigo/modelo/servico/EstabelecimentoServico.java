package encaixe.online.codigo.modelo.servico;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.inject.Inject;

import encaixe.online.codigo.excecao.NegocioException;
import encaixe.online.codigo.modelo.jpa.Estabelecimento;
import encaixe.online.codigo.modelo.jpa.Usuario;
import encaixe.online.codigo.modelo.repositorio.EstabelecimentoDAO;
import encaixe.online.codigo.utilidades.cdi.Transacional;
import encaixe.online.codigo.utilidades.gerais.Documento;

public class EstabelecimentoServico implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(EstabelecimentoServico.class.getName());

	@Inject
	private EstabelecimentoDAO dao;

	@Transacional
	public void salvar(Estabelecimento estabelecimento, Usuario usuario) {
		invocaAntesDeSalvar(estabelecimento, usuario);
		dao.salvar(estabelecimento);
		escreveLogDaOperacao(estabelecimento, usuario);
	}

	private void escreveLogDaOperacao(Estabelecimento estabelecimento, Usuario usuario) {
		StringBuilder builder = new StringBuilder();
		builder
			.append("Estabelecimento [ ")
			.append(estabelecimento.getCnpj()).append(" ] \n")
			.append("Identificação de email [ ")
			.append(usuario.getEmail()).append(" ] \n")
			.append("criado ás ")
			.append(new SimpleDateFormat("dd/MM/yyyy hh:MM").format(new Date()));
		LOG.info(builder.toString());
	}

	private void invocaAntesDeSalvar(Estabelecimento estabelecimento, Usuario usuario) {
		preparaUsuario(estabelecimento, usuario);
		preparaEmpresa(estabelecimento);
	}

	private void preparaEmpresa(Estabelecimento estabelecimento) {
		verificaCNPJ(estabelecimento);
		verificaTelefone(estabelecimento);
	}

	private void verificaTelefone(Estabelecimento estabelecimento) {
		String formatado = estabelecimento.getTelefone().trim().replace("(", " ").replace(")", " ").replace("-", " ").replaceAll(" ", "");
		estabelecimento.setTelefone(formatado);
	}

	private void verificaCNPJ(Estabelecimento estabelecimento) {
		String documento = Documento.formataCNPJ(estabelecimento.getCnpj());
		estabelecimento.setCnpj(documento);

		if ( Documento.isNotCNPJ(documento) ) {
			throw new NegocioException("Este cnpj é inválido !!");
		}

		if ( dao.isCnpjExiste(documento) ) {
			throw new NegocioException("Este cnpj está vinculado ao serviço do encaixe.online, tente outro !!");
		}
	}

	private void preparaUsuario(Estabelecimento estabelecimento, Usuario usuario) {
		UtilitarioSenha.montarSenha(usuario);
		estabelecimento.setUsuario(usuario);
	}
}