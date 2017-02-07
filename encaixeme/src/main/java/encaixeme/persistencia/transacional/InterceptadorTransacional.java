package encaixeme.persistencia.transacional;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 * @author Marcos Olavo S. Buganeme
 *
 */

@Transacao
@Interceptor
@Priority(Interceptor.Priority.APPLICATION + 1)
public class InterceptadorTransacional implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject	
	private EntityManager manager;

	@AroundInvoke public Object invoke(final InvocationContext context) throws Exception {
		final EntityTransaction entidadeTransacional = manager.getTransaction();
		boolean criador = false;

		try {
			if ( !entidadeTransacional.isActive() ) {
				entidadeTransacional.begin();
				entidadeTransacional.rollback();
				entidadeTransacional.begin();
				criador = true;
			}
			return context.proceed();

		} catch ( final Exception excecao ) {
			if ( entidadeTransacional != null && criador ) {
				entidadeTransacional.rollback();
			}
			throw excecao;
		} finally {
			if ( entidadeTransacional != null && entidadeTransacional.isActive() && criador ) {
				entidadeTransacional.commit();
			}
		}
	}
}