package encaixeme.persistencia;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.criterion.Order;

import encaixeme.modelo.PlanoSaude;
import encaixeme.persistencia.arquitetura.HibernateDAO;

@SuppressWarnings("unchecked")
public class PlanoSaudeDAO extends HibernateDAO<PlanoSaude> {

	private static final long serialVersionUID = 1L;

	@Inject	private EntityManager entityManager;

	public List<PlanoSaude> todos() {
		return getCriteria()
					.addOrder(Order.asc("nome"))
					.setCacheable(true)
					.list();
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
}