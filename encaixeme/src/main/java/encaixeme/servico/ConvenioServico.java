package encaixeme.servico;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import encaixeme.persistencia.ConvenioDAO;
import encaixeme.persistencia.dto.ConvenioDTO;

public class ConvenioServico implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject	private ConvenioDAO dao;

	public String conveniosSuportados(Long idEmpresa) {

		StringBuilder builder = new StringBuilder();

		if ( idEmpresa != null ) {

			List<ConvenioDTO> convenios = dao.porAgenda(idEmpresa);
			int contador = 0;
			int quantidade = convenios.size() - 1;

			if ( !convenios.isEmpty() ) {

				for (ConvenioDTO convenio : convenios) {

					builder
						.append(convenio.getNome());

					if (contador < quantidade) {

						builder.append(" | ");
					}

					contador++;
				}
			}
		} else {
			builder
				.append("Nenhum plano de saúde foi adicionado !!");
		}

		return builder.toString();
	}
}