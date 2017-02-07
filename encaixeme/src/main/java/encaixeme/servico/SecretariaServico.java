package encaixeme.servico;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import encaixeme.modelo.Secretaria;
import encaixeme.persistencia.UsuarioDAO;
import encaixeme.persistencia.transacional.Transacao;
import encaixeme.servico.exception.NegocioException;
import encaixeme.utilitarios.geral.UtilMensagem;

public class SecretariaServico implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject private UsuarioDAO dao;

	@Transacao
	public String salva(Secretaria secretaria) throws NegocioException {

		regrasDeNegocio(secretaria);
		dao.salvar(secretaria);
		
		StringBuilder builder = new StringBuilder();
		builder
			.append("Secretária, ")
			.append(secretaria.getNome())
			.append(" foi criada !!");

		return "Um e-mail foi enviado á " + secretaria.getNome() + ". Aguarde a confirmação!!";
	}

	private void regrasDeNegocio(Secretaria secretaria) {

		String validacao = validaCamposObrigatorios(secretaria);

		if ( StringUtils.isNotBlank(validacao) ) {

			throw new NegocioException(validacao);
		}

		if ( dao.isEmailExiste(secretaria.getUsuario().getEmail()) ) {

			throw new NegocioException(UtilMensagem.EMAIL_ATIVO);
		}
	}

	private String validaCamposObrigatorios(Secretaria secretaria) {

		int index = 0;

		List<String> campos = new ArrayList<>();
		StringBuilder builder = new StringBuilder();

		itensFormulario(secretaria, campos);

		if ( !campos.isEmpty() ) {

			if ( campos.size() < 2 ) {

				builder
					.append("Preencha o campo: [");
			} else {

				builder
					.append("Preencha os campos: [");
			}

			int total = campos.size() - 1;

			for (String item : campos) {

				builder
					.append(item);

				if ( index < total ) {

					builder
						.append(", ");
				}

				index++;
			}

			campos.clear();

			builder
				.append("]");
		}

		return builder.toString();
	}

	private void itensFormulario(Secretaria secretaria, List<String> campos) {

		if ( StringUtils.isBlank(secretaria.getNome()) ) {

			campos.add("Nome");
		}

		if ( StringUtils.isBlank(secretaria.getUsuario().getEmail()) ) {

			campos.add("E-mail");
		}
	}
}