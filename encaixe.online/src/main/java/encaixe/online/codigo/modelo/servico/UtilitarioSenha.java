package encaixe.online.codigo.modelo.servico;

import encaixe.online.codigo.excecao.NegocioException;
import encaixe.online.codigo.modelo.jpa.Usuario;
import encaixe.online.codigo.utilidades.gerais.SHA256;

public final class UtilitarioSenha {

	public static void montarSenha(Usuario usuario) throws NegocioException {
		verificaConteudoDasSenhas(usuario);
		criptografaSenha(usuario);
	}

	private static void verificaConteudoDasSenhas(Usuario usuario) {
		if (!usuario.getSenha().equals(usuario.getContraSenha())) {
			throw new NegocioException("Senhas incompatíveis");
		}
	}

	private static void criptografaSenha(Usuario usuario) {
		String criptografada = SHA256.criptografar(usuario.getSenha());
		usuario.setSenha(criptografada);
	}
}