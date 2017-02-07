package encaixeme.persistencia.arquitetura;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import encaixeme.modelo.arquitetura.Entidade;

@SuppressWarnings("unchecked") 
public abstract class HibernateDAO<E extends Entidade> implements Serializable {

	private static final long serialVersionUID = 1L;

	protected abstract EntityManager getEntityManager();

	protected Session getSession() {
		return (Session) getEntityManager().getDelegate();
	}

	private Class<E> getEntidadeConcreta() {
		final Type[] tipos = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments();
		return (Class<E>) tipos[0];
	}

	protected Criteria getCriteria() {
		Class<E> entidade = getEntidadeConcreta();
		return getSession().createCriteria(entidade);
	}

	public E porId(Serializable id) {
		return (E) getCriteria()
						.add(Restrictions.eq("id", id))
						.uniqueResult();
	}

	public E porId(E entidade) {
		String idEntidade = entidade.getId().toString();
		return (E) getCriteria()
						.add(Restrictions.eq("id", new Long(idEntidade)))
						.uniqueResult();
	}
}