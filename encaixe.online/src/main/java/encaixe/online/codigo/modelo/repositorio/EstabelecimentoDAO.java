package encaixe.online.codigo.modelo.repositorio;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import encaixe.online.codigo.excecao.NegocioException;
import encaixe.online.codigo.modelo.jpa.Estabelecimento;

public class EstabelecimentoDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(EstabelecimentoDAO.class.getName());

	@Inject
	private EntityManager entityManager;

	public void salvar(Estabelecimento estabelecimento) throws NegocioException {
		entityManager.persist(estabelecimento);
		entityManager.flush();
	}

	public boolean isCnpjExiste(String documento) {
		String jpql = "select e.cnpj from Estabelecimento e where e.cnpj = '" + documento + "'";

		try {

			TypedQuery<String> query = entityManager.createQuery(jpql, String.class);
			return !query.getSingleResult().isEmpty();

		} catch (NoResultException exception) {

			StringBuilder builder = new StringBuilder();
			builder
				.append("CNPJ [ ").append(documento).append(" ] ")
				.append("foi consultado ás")
				.append(new SimpleDateFormat("dd/MM/yyyy hh:MM").format(new Date()));

			LOG.warning(builder.toString());

			return false;
		}
	}
}