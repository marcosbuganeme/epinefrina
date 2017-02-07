package encaixeme.persistencia;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import encaixeme.modelo.Atividade;
import encaixeme.persistencia.arquitetura.HibernateDAO;

/**
 * @author Marcos Olavo S. Buganeme
 *
 */

public class AtividadeDAO extends HibernateDAO<Atividade> {

	private static final long serialVersionUID = 1L;

	@Inject	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}