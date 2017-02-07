package encaixeme.servico;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import encaixeme.modelo.Usuario;
import encaixeme.persistencia.UsuarioDAO;
import encaixeme.servico.exception.NegocioException;
import encaixeme.utilitarios.geral.UtilMensagem;
import encaixeme.utilitarios.geral.UtilValidaDocumentos;

public class EsqueciSenhaServico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject private UsuarioDAO dao;

	public Usuario regrasDaValidacao(Usuario usuario) {

		String loginFormatado = validarDocumento(usuario.getLogin());
		usuario.setLogin(loginFormatado);

		Usuario consulta = dao.porEmailELogin(usuario.getEmail(), loginFormatado);

		if ( consulta == null ) {

			throw new NegocioException(UtilMensagem.REGISTRO_NAO_CADASTRADO);
		}

		return consulta;
	}

	private String validarDocumento(String documento) {

		if ( StringUtils.isBlank(documento) ) {

			throw new NegocioException(UtilMensagem.CNPJ_CPF_INVALIDO);
		}

		if ( documento.length() == 14 ) {

			if ( UtilValidaDocumentos.isNotCPF(documento) ) {

				throw new NegocioException(UtilMensagem.CPF_INVALIDO);
			}

			documento = UtilValidaDocumentos.removeMascaraCPF(documento);

		} else {

			if ( UtilValidaDocumentos.isNotCNPJ(documento) ) {

				throw new NegocioException(UtilMensagem.CNPJ_INVALIDO);
			}

			documento = UtilValidaDocumentos.removeMascaraCNPJ(documento);
		}

		return documento;
	}
}